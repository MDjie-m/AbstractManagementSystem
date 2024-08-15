package com.renxin.course.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourUserCourseSection;
import com.renxin.course.domain.dto.CourseQueryDTO;
import com.renxin.course.mapper.CourCourseMapper;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.service.ICourSectionService;
import com.renxin.course.service.ICourUserCourseSectionService;
import com.renxin.course.vo.CourseListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 课程Service业务层处理
 * 
 * @author renxin
 * @date 2023-03-14
 */
@Service
public class CourCourseServiceImpl implements ICourCourseService
{
    @Autowired
    private CourCourseMapper courCourseMapper;

    @Autowired
    private ICourSectionService courSectionService;

    @Autowired
    private ICourUserCourseSectionService courUserCourseSectionService;

    /**
     * 查询课程
     * 
     * @param id 课程主键
     * @return 课程
     */
    @Override
    public CourCourse selectCourCourseById(Long id)
    {
        return courCourseMapper.selectCourCourseById(id);
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
        return courCourseMapper.insertCourCourse(courCourse);
    }

    /**
     * 修改课程
     * 
     * @param courCourse 课程
     * @return 结果
     */
    @Override
    public int updateCourCourse(CourCourse courCourse)
    {
        courCourse.setUpdateTime(DateUtils.getNowDate());
        return courCourseMapper.updateCourCourse(courCourse);
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
        return courCourseMapper.deleteCourCourseByIds(ids);
    }

    /**
     * 删除课程信息
     * 
     * @param id 课程主键
     * @return 结果
     */
    @Override
    public int deleteCourCourseById(Long id)
    {
        return courCourseMapper.deleteCourCourseById(id);
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

    public List<CourseListVO> getCourseListByClassId(CourCourse courCourse){
        return courCourseMapper.getCourseListByClassId(courCourse);
    }

}
