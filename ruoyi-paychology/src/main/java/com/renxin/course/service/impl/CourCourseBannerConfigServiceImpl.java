package com.renxin.course.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.course.domain.CourCourseBannerConfig;
import com.renxin.course.mapper.CourCourseBannerConfigMapper;
import com.renxin.course.service.ICourCourseBannerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 课程banner配置Service业务层处理
 * 
 * @author renxin
 * @date 2023-03-14
 */
@Service
public class CourCourseBannerConfigServiceImpl implements ICourCourseBannerConfigService
{
    @Autowired
    private CourCourseBannerConfigMapper courCourseBannerConfigMapper;

    /**
     * 查询课程banner配置
     * 
     * @param id 课程banner配置主键
     * @return 课程banner配置
     */
    @Override
    public CourCourseBannerConfig selectCourCourseBannerConfigById(Long id)
    {
        return courCourseBannerConfigMapper.selectCourCourseBannerConfigById(id);
    }

    /**
     * 查询课程banner配置列表
     * 
     * @param courCourseBannerConfig 课程banner配置
     * @return 课程banner配置
     */
    @Override
    public List<CourCourseBannerConfig> selectCourCourseBannerConfigList(CourCourseBannerConfig courCourseBannerConfig)
    {
        return courCourseBannerConfigMapper.selectCourCourseBannerConfigList(courCourseBannerConfig);
    }

    /**
     * 新增课程banner配置
     * 
     * @param courCourseBannerConfig 课程banner配置
     * @return 结果
     */
    @Override
    public int insertCourCourseBannerConfig(CourCourseBannerConfig courCourseBannerConfig)
    {
        courCourseBannerConfig.setCreateTime(DateUtils.getNowDate());
        return courCourseBannerConfigMapper.insertCourCourseBannerConfig(courCourseBannerConfig);
    }

    /**
     * 修改课程banner配置
     * 
     * @param courCourseBannerConfig 课程banner配置
     * @return 结果
     */
    @Override
    public int updateCourCourseBannerConfig(CourCourseBannerConfig courCourseBannerConfig)
    {
        return courCourseBannerConfigMapper.updateCourCourseBannerConfig(courCourseBannerConfig);
    }

    /**
     * 批量删除课程banner配置
     * 
     * @param ids 需要删除的课程banner配置主键
     * @return 结果
     */
    @Override
    public int deleteCourCourseBannerConfigByIds(Long[] ids)
    {
        return courCourseBannerConfigMapper.deleteCourCourseBannerConfigByIds(ids);
    }

    /**
     * 删除课程banner配置信息
     * 
     * @param id 课程banner配置主键
     * @return 结果
     */
    @Override
    public int deleteCourCourseBannerConfigById(Long id)
    {
        return courCourseBannerConfigMapper.deleteCourCourseBannerConfigById(id);
    }
}
