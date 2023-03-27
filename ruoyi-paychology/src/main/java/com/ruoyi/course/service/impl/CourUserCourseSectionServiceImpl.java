package com.ruoyi.course.service.impl;

import java.util.List;

import com.ruoyi.course.constant.CourConstant;
import com.ruoyi.course.domain.CourCourse;
import com.ruoyi.course.domain.CourSection;
import com.ruoyi.course.service.ICourCourseService;
import com.ruoyi.course.service.ICourSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.course.mapper.CourUserCourseSectionMapper;
import com.ruoyi.course.domain.CourUserCourseSection;
import com.ruoyi.course.service.ICourUserCourseSectionService;

/**
 * 用户-课程-章节关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-15
 */
@Service
public class CourUserCourseSectionServiceImpl implements ICourUserCourseSectionService 
{
    @Autowired
    private CourUserCourseSectionMapper courUserCourseSectionMapper;

    @Autowired
    private ICourSectionService courSectionService;

    /**
     * 查询用户-课程-章节关系
     * 
     * @param id 用户-课程-章节关系主键
     * @return 用户-课程-章节关系
     */
    @Override
    public CourUserCourseSection selectCourUserCourseSectionById(Integer id)
    {
        return courUserCourseSectionMapper.selectCourUserCourseSectionById(id);
    }

    /**
     * 查询用户-课程-章节关系列表
     * 
     * @param courUserCourseSection 用户-课程-章节关系
     * @return 用户-课程-章节关系
     */
    @Override
    public List<CourUserCourseSection> selectCourUserCourseSectionList(CourUserCourseSection courUserCourseSection)
    {
        return courUserCourseSectionMapper.selectCourUserCourseSectionList(courUserCourseSection);
    }


    /**
     * 新增用户-课程-章节关系
     * 
     * @param courUserCourseSection 用户-课程-章节关系
     * @return 结果
     */
    @Override
    public int insertCourUserCourseSection(CourUserCourseSection courUserCourseSection)
    {
        return courUserCourseSectionMapper.insertCourUserCourseSection(courUserCourseSection);
    }

    /**
     * 修改用户-课程-章节关系
     * 
     * @param courUserCourseSection 用户-课程-章节关系
     * @return 结果
     */
    @Override
    public int updateCourUserCourseSection(CourUserCourseSection courUserCourseSection)
    {
        return courUserCourseSectionMapper.updateCourUserCourseSection(courUserCourseSection);
    }

    /**
     * 批量删除用户-课程-章节关系
     * 
     * @param ids 需要删除的用户-课程-章节关系主键
     * @return 结果
     */
    @Override
    public int deleteCourUserCourseSectionByIds(Integer[] ids)
    {
        return courUserCourseSectionMapper.deleteCourUserCourseSectionByIds(ids);
    }

    /**
     * 删除用户-课程-章节关系信息
     * 
     * @param id 用户-课程-章节关系主键
     * @return 结果
     */
    @Override
    public int deleteCourUserCourseSectionById(Integer id)
    {
        return courUserCourseSectionMapper.deleteCourUserCourseSectionById(id);
    }

    /**
     * 初始化用户与课程、章节的关系，记录初始的学习状态
     *
     * @param userId 用户ID
     * @param courseId 课程ID
     */
    @Override
    public void initCourUserCourseSection(Integer userId, Integer courseId) {

        // 根据课程ID查询课程章节列表
        CourSection courSection = new CourSection();
        courSection.setCourseId(courseId);
        List<CourSection> courSectionList = courSectionService.selectCourSectionList(courSection);

        CourUserCourseSection courUserCourseSection = new CourUserCourseSection();
        courUserCourseSection.setUserId(userId);
        courUserCourseSection.setCourseId(courseId);
        courUserCourseSection.setEndTime(0);
        courUserCourseSection.setFinishStatus(CourConstant.SECTION_UNFINISHED);
        for(CourSection section: courSectionList) {
            // 记录每一章节用户初始的学习状态
            courUserCourseSection.setSectionId(section.getId());
            try {
                insertCourUserCourseSection(courUserCourseSection);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
