package com.renxin.course.service;

import java.util.List;
import com.renxin.course.domain.CourCourseClass;

/**
 * 类型Service接口
 * 
 * @author renxin
 * @date 2023-04-07
 */
public interface ICourCourseClassService 
{
    /**
     * 查询类型
     * 
     * @param id 类型主键
     * @return 类型
     */
    public CourCourseClass selectCourCourseClassById(Long id);

    /**
     * 查询类型列表
     * 
     * @param courCourseClass 类型
     * @return 类型集合
     */
    public List<CourCourseClass> selectCourCourseClassList(CourCourseClass courCourseClass);

    /**
     * 新增类型
     * 
     * @param courCourseClass 类型
     * @return 结果
     */
    public int insertCourCourseClass(CourCourseClass courCourseClass);

    /**
     * 修改类型
     * 
     * @param courCourseClass 类型
     * @return 结果
     */
    public int updateCourCourseClass(CourCourseClass courCourseClass);

    /**
     * 批量删除类型
     * 
     * @param ids 需要删除的类型主键集合
     * @return 结果
     */
    public int deleteCourCourseClassByIds(Long[] ids);

    /**
     * 删除类型信息
     * 
     * @param id 类型主键
     * @return 结果
     */
    public int deleteCourCourseClassById(Long id);
}
