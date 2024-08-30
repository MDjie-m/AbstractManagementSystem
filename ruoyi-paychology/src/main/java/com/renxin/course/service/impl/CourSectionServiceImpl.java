package com.renxin.course.service.impl;

import java.util.List;

import com.renxin.common.exception.ServiceException;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourSection;
import com.renxin.course.mapper.CourSectionMapper;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.service.ICourSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Autowired
    private ICourCourseService courCourseService;

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


    @Override
    public List<CourSection> selectCourSectionDetailList(CourSection courSection){
        return courSectionMapper.selectCourSectionDetailList(courSection);
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
        int i = courSectionMapper.insertCourSection(courSection);
        
        courCourseService.refreshCacheById(courSection.getCourseId());
        return i;
    }

    /**
     * 修改章节
     * 
     * @param courSection 章节
     * @return 结果
     */
    @Override
    public int updateCourSection(CourSection section)
    {
        CourCourse course = courCourseService.getBaseMapper().selectById(section.getCourseId());
        if (course.getPayType() == 0 && section.getType() == 2){
            throw new ServiceException("付费课程的章节不可修改为[免费], 可以设为[付费]或[试听]");
        }
        if (course.getPayType() == 1 && section.getType() != 2){
            throw new ServiceException("免费课程的章节必须为[免费]");
        }
        int i = courSectionMapper.updateCourSection(section);

        courCourseService.refreshCacheById(section.getCourseId());
        return i;
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
        //int i = courSectionMapper.deleteCourSectionByIds(ids);

        for (Long id : ids) {
            deleteCourSectionById(id);
        }
        return ids.length;
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
        CourSection courSection = courSectionMapper.selectCourSectionById(id);
        int i = courSectionMapper.deleteCourSectionById(id);

        courCourseService.refreshCacheById(courSection.getCourseId());
        return i;
    }
}
