package com.renxin.course.service;

import java.util.List;

import com.renxin.course.domain.CourUserCourseSection;

/**
 * 用户-课程-章节关系Service接口
 * 
 * @author renxin
 * @date 2023-03-15
 */
public interface ICourUserCourseSectionService 
{
    /**
     * 查询用户-课程-章节关系
     * 
     * @param id 用户-课程-章节关系主键
     * @return 用户-课程-章节关系
     */
    public CourUserCourseSection selectCourUserCourseSectionById(Long id);

    /**
     * 查询用户-课程-章节关系列表
     * 
     * @param courUserCourseSection 用户-课程-章节关系
     * @return 用户-课程-章节关系集合
     */
    public List<CourUserCourseSection> selectCourUserCourseSectionList(CourUserCourseSection courUserCourseSection);

    /**
     * 新增用户-课程-章节关系
     * 
     * @param courUserCourseSection 用户-课程-章节关系
     * @return 结果
     */
    public int insertCourUserCourseSection(CourUserCourseSection courUserCourseSection);

    /**
     * 修改用户-课程-章节关系
     * 
     * @param courUserCourseSection 用户-课程-章节关系
     * @return 结果
     */
    public int updateCourUserCourseSection(CourUserCourseSection courUserCourseSection);

    /**
     * 批量删除用户-课程-章节关系
     * 
     * @param ids 需要删除的用户-课程-章节关系主键集合
     * @return 结果
     */
    public int deleteCourUserCourseSectionByIds(Long[] ids);

    /**
     * 删除用户-课程-章节关系信息
     * 
     * @param id 用户-课程-章节关系主键
     * @return 结果
     */
    public int deleteCourUserCourseSectionById(Long id);

    void initCourUserCourseSection(Long userId, Long courseId, Integer userType);


    /**
     * 记录用户学习课程章节的结束时间点
     *
     * @param userCourseSection 用户-课程-章节关系
     * @return 结果
     */
    int recordEndTime(CourUserCourseSection userCourseSection);

    /**
     * 查询用户学习课程章节的结束时间点
     *
     * @param userCourseSection 用户-课程-章节关系
     * @return 结束时间点
     */
    Integer findEndTime(CourUserCourseSection userCourseSection);
    
    //修改章节笔记
    Integer updateSectionNote(CourUserCourseSection req);
}
