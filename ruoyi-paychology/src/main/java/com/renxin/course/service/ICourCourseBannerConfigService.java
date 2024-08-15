package com.renxin.course.service;

import com.renxin.course.domain.CourCourseBannerConfig;

import java.util.List;

/**
 * 课程banner配置Service接口
 * 
 * @author renxin
 * @date 2023-03-14
 */
public interface ICourCourseBannerConfigService 
{
    /**
     * 查询课程banner配置
     * 
     * @param id 课程banner配置主键
     * @return 课程banner配置
     */
    public CourCourseBannerConfig selectCourCourseBannerConfigById(Long id);

    /**
     * 查询课程banner配置列表
     * 
     * @param courCourseBannerConfig 课程banner配置
     * @return 课程banner配置集合
     */
    public List<CourCourseBannerConfig> selectCourCourseBannerConfigList(CourCourseBannerConfig courCourseBannerConfig);

    /**
     * 新增课程banner配置
     * 
     * @param courCourseBannerConfig 课程banner配置
     * @return 结果
     */
    public int insertCourCourseBannerConfig(CourCourseBannerConfig courCourseBannerConfig);

    /**
     * 修改课程banner配置
     * 
     * @param courCourseBannerConfig 课程banner配置
     * @return 结果
     */
    public int updateCourCourseBannerConfig(CourCourseBannerConfig courCourseBannerConfig);

    /**
     * 批量删除课程banner配置
     * 
     * @param ids 需要删除的课程banner配置主键集合
     * @return 结果
     */
    public int deleteCourCourseBannerConfigByIds(Long[] ids);

    /**
     * 删除课程banner配置信息
     * 
     * @param id 课程banner配置主键
     * @return 结果
     */
    public int deleteCourCourseBannerConfigById(Long id);
}
