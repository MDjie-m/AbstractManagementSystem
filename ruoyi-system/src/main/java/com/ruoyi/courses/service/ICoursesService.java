package com.ruoyi.courses.service;

import java.util.List;
import com.ruoyi.courses.domain.Courses;

/**
 * 课程管理Service接口
 * 
 * @author ruoyi
 * @date 2024-11-05
 */
public interface ICoursesService 
{
    /**
     * 查询课程管理
     * 
     * @param courseId 课程管理主键
     * @return 课程管理
     */
    public Courses selectCoursesByCourseId(Long courseId);

    /**
     * 查询课程管理列表
     * 
     * @param courses 课程管理
     * @return 课程管理集合
     */
    public List<Courses> selectCoursesList(Courses courses);

    /**
     * 新增课程管理
     * 
     * @param courses 课程管理
     * @return 结果
     */
    public int insertCourses(Courses courses);

    /**
     * 修改课程管理
     * 
     * @param courses 课程管理
     * @return 结果
     */
    public int updateCourses(Courses courses);

    /**
     * 批量删除课程管理
     * 
     * @param courseIds 需要删除的课程管理主键集合
     * @return 结果
     */
    public int deleteCoursesByCourseIds(Long[] courseIds);

    /**
     * 删除课程管理信息
     * 
     * @param courseId 课程管理主键
     * @return 结果
     */
    public int deleteCoursesByCourseId(Long courseId);
}
