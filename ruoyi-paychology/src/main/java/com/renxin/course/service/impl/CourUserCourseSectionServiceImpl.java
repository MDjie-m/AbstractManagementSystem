package com.renxin.course.service.impl;

import java.util.Date;
import java.util.List;

import com.renxin.common.exception.ServiceException;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourSection;
import com.renxin.course.service.ICourSectionService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.course.mapper.CourUserCourseSectionMapper;
import com.renxin.course.domain.CourUserCourseSection;
import com.renxin.course.service.ICourUserCourseSectionService;

import javax.annotation.Resource;

/**
 * 用户-课程-章节关系Service业务层处理
 * 
 * @author renxin
 * @date 2023-03-15
 */
@Service
public class CourUserCourseSectionServiceImpl implements ICourUserCourseSectionService 
{
    @Resource
    private CourUserCourseSectionMapper courUserCourseSectionMapper;

    @Resource
    private ICourSectionService courSectionService;

    /**
     * 查询用户-课程-章节关系
     * 
     * @param id 用户-课程-章节关系主键
     * @return 用户-课程-章节关系
     */
    @Override
    public CourUserCourseSection selectCourUserCourseSectionById(Long id)
    {
        return courUserCourseSectionMapper.selectCourUserCourseSectionById(id);
    }

    /**
     * 查询用户-课程-章节关系列表
     * 
     * @param courUserCourseSection 用户-课程-章节关系
     * @return 用户-课程-章节关系
     */
    @Override
    public List<CourUserCourseSection> selectCourUserCourseSectionList(CourUserCourseSection courUserCourseSection)
    {
        return courUserCourseSectionMapper.selectCourUserCourseSectionList(courUserCourseSection);
    }


    /**
     * 新增用户-课程-章节关系
     * 
     * @param courUserCourseSection 用户-课程-章节关系
     * @return 结果
     */
    @Override
    public int insertCourUserCourseSection(CourUserCourseSection courUserCourseSection)
    {
        return courUserCourseSectionMapper.insertCourUserCourseSection(courUserCourseSection);
    }

    /**
     * 修改用户-课程-章节关系
     * 
     * @param courUserCourseSection 用户-课程-章节关系
     * @return 结果
     */
    @Override
    public int updateCourUserCourseSection(CourUserCourseSection courUserCourseSection)
    {
        return courUserCourseSectionMapper.updateCourUserCourseSection(courUserCourseSection);
    }

    /**
     * 批量删除用户-课程-章节关系
     * 
     * @param ids 需要删除的用户-课程-章节关系主键
     * @return 结果
     */
    @Override
    public int deleteCourUserCourseSectionByIds(Long[] ids)
    {
        return courUserCourseSectionMapper.deleteCourUserCourseSectionByIds(ids);
    }

    /**
     * 删除用户-课程-章节关系信息
     *
     * @param id 用户-课程-章节关系主键
     * @return 结果
     */
    @Override
    public int deleteCourUserCourseSectionById(Long id)
    {
        return courUserCourseSectionMapper.deleteCourUserCourseSectionById(id);
    }

    /**
     * 初始化用户与课程、章节的关系，记录初始的学习状态
     *
     * @param userId 用户ID
     * @param courseId 课程ID
     */
    @Override
    public void initCourUserCourseSection(Long userId, Long courseId, Integer userType) {

        // 根据课程ID查询课程章节列表
        CourSection courSection = new CourSection();
        courSection.setCourseId(courseId);
        List<CourSection> courSectionList = courSectionService.selectCourSectionList(courSection);

        CourUserCourseSection courUserCourseSection = new CourUserCourseSection();
        courUserCourseSection.setUserId(userId.longValue());
        courUserCourseSection.setUserType(userType);
        courUserCourseSection.setCourseId(courseId);
        courUserCourseSection.setEndTime(0);
        courUserCourseSection.setFinishStatus(CourConstant.SECTION_UNFINISHED);
        for(CourSection section: courSectionList) {
            // 记录每一章节用户初始的学习状态
            courUserCourseSection.setSectionId(section.getId());
            try {
                // 如果之前有下过单，直接覆盖原有记录
                List<CourUserCourseSection> courUserCourseSectionList = selectCourUserCourseSectionList(courUserCourseSection);
                if (courUserCourseSectionList.size() == 0) {
                    insertCourUserCourseSection(courUserCourseSection);
                } else  {
                    updateCourUserCourseSection(courUserCourseSection);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * 记录用户学习课程章节的结束时间点
     *
     * @param userCourseSection 用户-课程-章节关系
     * @return 结果
     */
    @Override
    public int recordEndTime(CourUserCourseSection userCourseSection) {
        return courUserCourseSectionMapper.recordEndTime(userCourseSection);
    }

    /**
     * 查询用户学习课程章节的结束时间点
     *
     * @param userCourseSection 用户-课程-章节关系
     * @return 结束时间点
     */
    @Override
    public Integer findEndTime(CourUserCourseSection userCourseSection) {
        Integer endTime = courUserCourseSectionMapper.findEndTime(userCourseSection);
        if (endTime == null){
            return 0;
        }
        return endTime;
    }

    //修改章节笔记
    @Override
    public Integer updateSectionNote(CourUserCourseSection req){
        if (ObjectUtils.isEmpty(req.getCourseId()) || ObjectUtils.isEmpty(req.getSectionId())){
            throw new ServiceException("课程id和章节id不能为空");
        }
        List<CourUserCourseSection> sectionList = courUserCourseSectionMapper.selectCourUserCourseSectionList(req);
        if (ObjectUtils.isEmpty(sectionList)){
            throw new ServiceException("用户未购买该章节, 无法维护笔记");
        }
        CourUserCourseSection courseSection = sectionList.get(0);
        courseSection.setNote(req.getNote());
        courseSection.setNoteTime(new Date());
        int i = courUserCourseSectionMapper.updateCourUserCourseSection(courseSection);
        return i;
    }
}
