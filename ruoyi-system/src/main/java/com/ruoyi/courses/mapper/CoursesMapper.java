package com.ruoyi.courses.mapper;

import java.util.List;
import com.ruoyi.courses.domain.Courses;

/**
 * 课程管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-05
 */
public interface CoursesMapper 
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
     * 删除课程管理
     * 
     * @param courseId 课程管理主键
     * @return 结果
     */
    public int deleteCoursesByCourseId(Long courseId);

    /**
     * 批量删除课程管理
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCoursesByCourseIds(Long[] courseIds);
}
