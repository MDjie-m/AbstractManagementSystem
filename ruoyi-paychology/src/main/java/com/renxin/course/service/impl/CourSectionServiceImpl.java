package com.renxin.course.service.impl;

import java.util.List;

import com.renxin.course.domain.CourSection;
import com.renxin.course.mapper.CourSectionMapper;
import com.renxin.course.service.ICourSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 章节Service业务层处理
 * 
 * @author renxin
 * @date 2023-03-14
 */
@Service
public class CourSectionServiceImpl implements ICourSectionService
{
    @Autowired
    private CourSectionMapper courSectionMapper;

    /**
     * 查询章节
     * 
     * @param id 章节主键
     * @return 章节
     */
    @Override
    public CourSection selectCourSectionById(Long id)
    {
        return courSectionMapper.selectCourSectionById(id);
    }

    /**
     * 查询章节列表
     * 
     * @param courSection 章节
     * @return 章节
     */
    @Override
    public List<CourSection> selectCourSectionList(CourSection courSection)
    {
        return courSectionMapper.selectCourSectionList(courSection);
    }

    /**
     * 新增章节
     * 
     * @param courSection 章节
     * @return 结果
     */
    @Override
    public int insertCourSection(CourSection courSection)
    {
        return courSectionMapper.insertCourSection(courSection);
    }

    /**
     * 修改章节
     * 
     * @param courSection 章节
     * @return 结果
     */
    @Override
    public int updateCourSection(CourSection courSection)
    {
        return courSectionMapper.updateCourSection(courSection);
    }

    /**
     * 批量删除章节
     * 
     * @param ids 需要删除的章节主键
     * @return 结果
     */
    @Override
    public int deleteCourSectionByIds(Long[] ids)
    {
        return courSectionMapper.deleteCourSectionByIds(ids);
    }

    /**
     * 删除章节信息
     * 
     * @param id 章节主键
     * @return 结果
     */
    @Override
    public int deleteCourSectionById(Long id)
    {
        return courSectionMapper.deleteCourSectionById(id);
    }
}
