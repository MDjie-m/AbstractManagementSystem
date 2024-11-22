package com.ruoyi.courses.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.courses.mapper.CoursesMapper;
import com.ruoyi.courses.domain.Courses;
import com.ruoyi.courses.service.ICoursesService;

/**
 * 课程管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-05
 */
@Service
public class CoursesServiceImpl implements ICoursesService 
{
    @Autowired
    private CoursesMapper coursesMapper;

    /**
     * 查询课程管理
     * 
     * @param courseId 课程管理主键
     * @return 课程管理
     */
    @Override
    public Courses selectCoursesByCourseId(Long courseId)
    {
        return coursesMapper.selectCoursesByCourseId(courseId);
    }

    /**
     * 查询课程管理列表
     * 
     * @param courses 课程管理
     * @return 课程管理
     */
    @Override
    public List<Courses> selectCoursesList(Courses courses)
    {
        return coursesMapper.selectCoursesList(courses);
    }

    /**
     * 新增课程管理
     * 
     * @param courses 课程管理
     * @return 结果
     */
    @Override
    public int insertCourses(Courses courses)
    {
        courses.setCreateTime(DateUtils.getNowDate());
        return coursesMapper.insertCourses(courses);
    }

    /**
     * 修改课程管理
     * 
     * @param courses 课程管理
     * @return 结果
     */
    @Override
    public int updateCourses(Courses courses)
    {
        courses.setUpdateTime(DateUtils.getNowDate());
        return coursesMapper.updateCourses(courses);
    }

    /**
     * 批量删除课程管理
     * 
     * @param courseIds 需要删除的课程管理主键
     * @return 结果
     */
    @Override
    public int deleteCoursesByCourseIds(Long[] courseIds)
    {
        return coursesMapper.deleteCoursesByCourseIds(courseIds);
    }

    /**
     * 删除课程管理信息
     * 
     * @param courseId 课程管理主键
     * @return 结果
     */
    @Override
    public int deleteCoursesByCourseId(Long courseId)
    {
        return coursesMapper.deleteCoursesByCourseId(courseId);
    }
}
