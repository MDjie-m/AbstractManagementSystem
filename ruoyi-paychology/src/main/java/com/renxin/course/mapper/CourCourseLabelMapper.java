package com.renxin.course.mapper;

import java.util.List;
import com.renxin.course.domain.CourCourseLabel;

/**
 * labelMapper接口
 * 
 * @author renxin
 * @date 2023-03-16
 */
public interface CourCourseLabelMapper 
{
    /**
     * 查询label
     * 
     * @param id label主键
     * @return label
     */
    public CourCourseLabel selectCourCourseLabelById(Long id);

    /**
     * 查询label列表
     * 
     * @param courCourseLabel label
     * @return label集合
     */
    public List<CourCourseLabel> selectCourCourseLabelList(CourCourseLabel courCourseLabel);

    /**
     * 新增label
     * 
     * @param courCourseLabel label
     * @return 结果
     */
    public int insertCourCourseLabel(CourCourseLabel courCourseLabel);

    /**
     * 修改label
     * 
     * @param courCourseLabel label
     * @return 结果
     */
    public int updateCourCourseLabel(CourCourseLabel courCourseLabel);

    /**
     * 删除label
     * 
     * @param id label主键
     * @return 结果
     */
    public int deleteCourCourseLabelById(Long id);

    /**
     * 批量删除label
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourCourseLabelByIds(Long[] ids);
}
