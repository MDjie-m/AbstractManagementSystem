package com.renxin.gauge.service;

import java.util.List;
import com.renxin.gauge.domain.PsyGaugeBannerConfig;

/**
 * 测评banner配置Service接口
 * 
 * @author renxin
 * @date 2022-10-18
 */
public interface IPsyGaugeBannerConfigService 
{
    /**
     * 查询测评banner配置
     * 
     * @param id 测评banner配置主键
     * @return 测评banner配置
     */
    public PsyGaugeBannerConfig selectPsyGaugeBannerConfigById(Long id);

    /**
     * 查询测评banner配置列表
     * 
     * @param psyGaugeBannerConfig 测评banner配置
     * @return 测评banner配置集合
     */
    public List<PsyGaugeBannerConfig> selectPsyGaugeBannerConfigList(PsyGaugeBannerConfig psyGaugeBannerConfig);

    /**
     * 新增测评banner配置
     * 
     * @param psyGaugeBannerConfig 测评banner配置
     * @return 结果
     */
    public int insertPsyGaugeBannerConfig(PsyGaugeBannerConfig psyGaugeBannerConfig);

    /**
     * 修改测评banner配置
     * 
     * @param psyGaugeBannerConfig 测评banner配置
     * @return 结果
     */
    public int updatePsyGaugeBannerConfig(PsyGaugeBannerConfig psyGaugeBannerConfig);

    /**
     * 批量删除测评banner配置
     * 
     * @param ids 需要删除的测评banner配置主键集合
     * @return 结果
     */
    public int deletePsyGaugeBannerConfigByIds(Long[] ids);

    /**
     * 删除测评banner配置信息
     * 
     * @param id 测评banner配置主键
     * @return 结果
     */
    public int deletePsyGaugeBannerConfigById(Long id);
}
