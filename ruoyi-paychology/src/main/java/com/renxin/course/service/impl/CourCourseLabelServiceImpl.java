package com.renxin.course.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.course.mapper.CourCourseLabelMapper;
import com.renxin.course.domain.CourCourseLabel;
import com.renxin.course.service.ICourCourseLabelService;

/**
 * labelService业务层处理
 * 
 * @author renxin
 * @date 2023-03-16
 */
@Service
public class CourCourseLabelServiceImpl implements ICourCourseLabelService 
{
    @Autowired
    private CourCourseLabelMapper courCourseLabelMapper;

    /**
     * 查询label
     * 
     * @param id label主键
     * @return label
     */
    @Override
    public CourCourseLabel selectCourCourseLabelById(Long id)
    {
        return courCourseLabelMapper.selectCourCourseLabelById(id);
    }

    /**
     * 查询label列表
     * 
     * @param courCourseLabel label
     * @return label
     */
    @Override
    public List<CourCourseLabel> selectCourCourseLabelList(CourCourseLabel courCourseLabel)
    {
        return courCourseLabelMapper.selectCourCourseLabelList(courCourseLabel);
    }

    /**
     * 新增label
     * 
     * @param courCourseLabel label
     * @return 结果
     */
    @Override
    public int insertCourCourseLabel(CourCourseLabel courCourseLabel)
    {
        return courCourseLabelMapper.insertCourCourseLabel(courCourseLabel);
    }

    /**
     * 修改label
     * 
     * @param courCourseLabel label
     * @return 结果
     */
    @Override
    public int updateCourCourseLabel(CourCourseLabel courCourseLabel)
    {
        return courCourseLabelMapper.updateCourCourseLabel(courCourseLabel);
    }

    /**
     * 批量删除label
     * 
     * @param ids 需要删除的label主键
     * @return 结果
     */
    @Override
    public int deleteCourCourseLabelByIds(Long[] ids)
    {
        return courCourseLabelMapper.deleteCourCourseLabelByIds(ids);
    }

    /**
     * 删除label信息
     * 
     * @param id label主键
     * @return 结果
     */
    @Override
    public int deleteCourCourseLabelById(Long id)
    {
        return courCourseLabelMapper.deleteCourCourseLabelById(id);
    }
}
