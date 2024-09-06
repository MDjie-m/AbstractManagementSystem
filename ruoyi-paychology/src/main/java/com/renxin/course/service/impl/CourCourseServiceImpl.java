package com.renxin.course.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.constant.CacheConstants;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.domain.RelateInfo;
import com.renxin.common.utils.DateUtils;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourSection;
import com.renxin.course.domain.CourUserCourseSection;
import com.renxin.course.domain.dto.CourseQueryDTO;
import com.renxin.course.mapper.CourCourseMapper;
import com.renxin.course.mapper.CourSectionMapper;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.service.ICourSectionService;
import com.renxin.course.service.ICourUserCourseSectionService;
import com.renxin.course.vo.CourseListVO;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.mapper.PsyConsultantTeamSupervisionMapper;
import com.renxin.psychology.service.IPsyConsultantOrderService;
import com.renxin.psychology.service.IPsyConsultantTeamSupervisionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 课程Service业务层处理
 * 
 * @author renxin
 * @date 2023-03-14
 */
@Service
@Slf4j
public class CourCourseServiceImpl extends ServiceImpl<CourCourseMapper, CourCourse> 
        implements ICourCourseService
{
    @Autowired
    private ICourCourseService self; // 注入自身

    @Resource
    private RedisCache redisCache;
    
    @Autowired
    private CourCourseMapper courCourseMapper;

    @Autowired
    private CourSectionMapper sectionMapper;

    @Autowired
    private ICourSectionService courSectionService;

    @Autowired
    private ICourUserCourseSectionService courUserCourseSectionService;

    @Autowired
    private IPsyConsultantOrderService consultantOrderService;
    
    
    /**
     * 查询课程
     * 
     * @param id 课程主键
     * @return 课程
     */
    @Override
    @Cacheable(value = CacheConstants.COURSE_BY_ID_KEY, key = "#id", unless = "#result == null")
    public CourCourse selectCourCourseById(Long id)
    {
        log.info( java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) 
                + "--------------------------------连接MySQL查询课程:" + id);
        CourCourse courCourse = courCourseMapper.selectCourCourseById(id);
        if (ObjectUtils.isEmpty(courCourse)){
            return null;
        }
        
        CourSection courSection = CourSection.builder()
                .courseId(id)
                .isBuy(0)//不展示课程链接
                .build();
        List<CourSection> sectionList = courSectionService.selectCourSectionDetailList(courSection);
        courCourse.setSectionList(sectionList);
        
        return courCourse;
    }

    /**
     * 课程分类
     *
     * @param typeId 课程主键
     * @return 课程
     */
    @Override
    public CourCourse selectCourCourseByType(Integer typeId)
    {
        return courCourseMapper.selectCourCourseByType(typeId);
    }
    /**
     * 查询课程列表
     * 
     * @param courCourse 课程
     * @return 课程
     */
    @Override
    public List<CourCourse> selectCourCourseList(CourCourse courCourse)
    {
        return courCourseMapper.selectCourCourseList(courCourse);
    }

    /**
     * 新增课程
     * 
     * @param courCourse 课程
     * @return 结果
     */
    @Override
    public int insertCourCourse(CourCourse courCourse)
    {
        courCourse.setCreateTime(DateUtils.getNowDate());
        int i = courCourseMapper.insertCourCourse(courCourse);
        refreshIdList();
        return i;
    }

    /**
     * 修改课程
     * 
     * @param courCourse 课程
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = CacheConstants.COURSE_BY_ID_KEY, key = "#req.id")
    public int updateCourCourse(CourCourse req)
    {
        req.setUpdateTime(DateUtils.getNowDate());
        int i = courCourseMapper.updateCourCourse(req);
        
        //根据课程是否免费, 同步章节是否免费
        if (req.getPayType() == 0){//付费课程
            //将其下的"免费"章节, 修改为"试听"
            sectionMapper.update(null,new LambdaUpdateWrapper<CourSection>()
                    .set(CourSection::getType,1)
                    .eq(CourSection::getCourseId,req.getId())
                    .eq(CourSection::getType,2));
        }
        else if (req.getPayType() == 1) {//免费课程
            //将其下的所有章节, 修改为"免费"
            sectionMapper.update(null,new LambdaUpdateWrapper<CourSection>()
                    .set(CourSection::getType,2)
                    .eq(CourSection::getCourseId,req.getId()));
        }
            
        refreshIdList();
        return i;
    }

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteCourCourseByIds(Long[] ids)
    {
        int i = courCourseMapper.deleteCourCourseByIds(ids);
        //批量删除缓存
        redisCache.deleteMultiCache(CacheConstants.COURSE_BY_ID_KEY,Arrays.asList(ids));
        refreshIdList();
        return i;
    }

    /**
     * 删除课程信息
     * 
     * @param id 课程主键
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = CacheConstants.COURSE_BY_ID_KEY, key = "#id")
    public int deleteCourCourseById(Long id)
    {
        int i = courCourseMapper.deleteCourCourseById(id);
        refreshIdList();
        return i;
    }

    /**
     * 根据课程的章节判断课程是否未完成
     *
     * @param userId 用户ID
     * @param courseId 课程编号
     * @return 课程信息
     */
    public boolean calCourCourseList(Long userId, Long courseId) {
        CourUserCourseSection courUserCourseSection = new CourUserCourseSection();
        courUserCourseSection.setCourseId(courseId);
        List<CourUserCourseSection> courUserCourseSectionList =
                courUserCourseSectionService.selectCourUserCourseSectionList(courUserCourseSection);

        if (courUserCourseSectionList == null || courUserCourseSectionList.size() == 0) {
            return true; // 未完成
        }
        return courUserCourseSectionList.stream().anyMatch(item -> item.getFinishStatus() == 0);
    }

    /**
     * 根据课程的章节计算课程学习时长
     *
     * @param userId 用户ID
     * @param courseId 课程ID
     * @return 课程信息
     */
    public Integer calCourCourseStudyDuration(Long userId, Long courseId) {
        Integer studyDuration = 0;
        CourUserCourseSection courUserCourseSection = new CourUserCourseSection();
        courUserCourseSection.setUserId(userId);
        courUserCourseSection.setCourseId(courseId);
        List<CourUserCourseSection> courUserCourseSectionList =
                courUserCourseSectionService.selectCourUserCourseSectionList(courUserCourseSection);

        for (CourUserCourseSection userCourseSection: courUserCourseSectionList) {
            if (userCourseSection.getFinishStatus() == CourConstant.SECTION_UNFINISHED) { // 用户未学习完成过当前章节
                studyDuration += userCourseSection.getEndTime() == null ? 0 : userCourseSection.getEndTime();
            } else {
                studyDuration += courSectionService.selectCourSectionById(userCourseSection.getSectionId()).getDuration();
            }
        }

        return studyDuration;
    }

    /**
     * 查询课程是否支付
     *
     * @param courseId 课程ID
     * @return 课程支付数量
     */
    public int getPaidCourseCount(Long userId, Long courseId) {
        return courCourseMapper.getPaidCourseCount(userId.longValue(), courseId);
    }
    
    /**
     * 根据用户ID查询课程列表
     *
     * @param userId 用户ID
     * @return 课程集合
     */
    public List<CourCourse> getCourseListByUserId(Long userId)
    {
        return courCourseMapper.getCourseListByUserId(userId);
    }

    /**
     * 根据条件询课程列表
     */
    public List<CourCourse> queryCourCourseList(CourseQueryDTO courseQueryDTO) {
        return courCourseMapper.queryCourCourseList(courseQueryDTO);
    }

    @Override
    /*@Cacheable(value = "getCourseListByClassIdCache", key = "#courCourse.idList",
            unless = "#result == null||#result.isEmpty()")*/
    public List<CourseListVO> getCourseListByClassId(CourCourse courCourse){
        return courCourseMapper.getCourseListByClassId(courCourse);
    }

    //我的已购课程列表
    @Override
    public List<CourCourse> myCourseList(CourCourse req){
        List<CourCourse> courseList = new ArrayList<>();
        if (req.getUserType() == 2){//咨询师
            courseList = courCourseMapper.myCourseList(req);
        }
        return courseList;
    }
    
    //查询与课程的关联信息
    @Override
    public RelateInfo getCourseRelateInfo(CourCourse req){
        RelateInfo relateInfo = new RelateInfo();
        //是否已购
        PsyConsultantOrder orderReq = new PsyConsultantOrder();
        orderReq.setPayConsultantId(req.getUserId());
        orderReq.setServerType("4");//课程
        orderReq.setServerId(req.getId()+"");
        List<PsyConsultantOrder> orderList = consultantOrderService.selectPsyConsultantOrderList(orderReq);
        relateInfo.setIsBuy(orderList.size() > 0 ? CourConstant.COURSE_BUY : CourConstant.COURSE_NOT_BUY);

        //章节信息
        CourSection courSectionReq = CourSection.builder()
                .courseId(req.getId())
                .isBuy(relateInfo.getIsBuy())
                .userId(req.getUserId())
                .userType(req.getUserType())
                .build();
        List<CourSection> sectionList = courSectionService.selectCourSectionDetailList(courSectionReq);
        relateInfo.setSectionList(sectionList);
        
        return relateInfo;
    }

    //刷新缓存
    @Override
    public void refreshCacheByIdList(List<Long> idList){
        redisCache.deleteMultiCache(CacheConstants.COURSE_BY_ID_KEY,idList);
        for (Long id : idList) {
            self.selectCourCourseById(id);
        }
        refreshIdList();
    }

    @Override
    public void refreshCacheById(Long id){
        refreshCacheByIdList(Arrays.asList(id));
    }

    @Override
    public void refreshCacheAll(){
        //获取完整id清单
        List<Long> courseIdList = courCourseMapper.selectList(new LambdaQueryWrapper<CourCourse>()
                .select(CourCourse::getId)
                .orderByDesc(CourCourse::getCreateTime)).stream().map(p -> p.getId()).collect(Collectors.toList());

        //刷新缓存
        refreshCacheByIdList(courseIdList);
        refreshIdList();
    }

    //刷新该对象 各种类型下的id清单
    @Override
    public void refreshIdList(){
        //完整对象清单
        List<CourCourse> allCourseList = courCourseMapper.selectList(new LambdaQueryWrapper<CourCourse>()
                .select(CourCourse::getId,CourCourse::getType)
                .orderByDesc(CourCourse::getCreateTime));
        
        //删除原先的所有idList
        redisCache.deleteStartWith(CacheConstants.COURSE_ID_LIST);
        
        //id清单放入缓存
        ////完整id清单
        List<Long> allIdList = allCourseList.stream().map(p -> p.getId()).collect(Collectors.toList());
        redisCache.setCacheList(CacheConstants.COURSE_ID_LIST + "::" + "all",allIdList);
        
        ////各个类型的id清单
        Map<Integer, List<Long>> listMap =  allCourseList.stream()
                .collect(Collectors.groupingBy(
                        CourCourse::getType,
                        Collectors.mapping(CourCourse::getId, Collectors.toList())
                ));
        for (Map.Entry<Integer, List<Long>> entry : listMap.entrySet()) {
            redisCache.setCacheList(CacheConstants.COURSE_ID_LIST + "::" + "type" + entry.getKey(), entry.getValue());
        }
        
    }
    
}
