package com.renxin.gauge.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.renxin.gauge.mapper.PsyGaugeBannerConfigMapper;
import com.renxin.gauge.domain.PsyGaugeBannerConfig;
import com.renxin.gauge.service.IPsyGaugeBannerConfigService;

import javax.annotation.Resource;

/**
 * 测评banner配置Service业务层处理
 * 
 * @author renxin
 * @date 2022-10-18
 */
@Service
public class PsyGaugeBannerConfigServiceImpl implements IPsyGaugeBannerConfigService 
{
    @Resource
    private PsyGaugeBannerConfigMapper psyGaugeBannerConfigMapper;

    /**
     * 查询测评banner配置
     * 
     * @param id 测评banner配置主键
     * @return 测评banner配置
     */
    @Override
    public PsyGaugeBannerConfig selectPsyGaugeBannerConfigById(Long id)
    {
        return psyGaugeBannerConfigMapper.selectPsyGaugeBannerConfigById(id);
    }

    /**
     * 查询测评banner配置列表
     * 
     * @param psyGaugeBannerConfig 测评banner配置
     * @return 测评banner配置
     */
    @Override
    public List<PsyGaugeBannerConfig> selectPsyGaugeBannerConfigList(PsyGaugeBannerConfig psyGaugeBannerConfig)
    {
        return psyGaugeBannerConfigMapper.selectPsyGaugeBannerConfigList(psyGaugeBannerConfig);
    }

    /**
     * 新增测评banner配置
     * 
     * @param psyGaugeBannerConfig 测评banner配置
     * @return 结果
     */
    @Override
    public int insertPsyGaugeBannerConfig(PsyGaugeBannerConfig psyGaugeBannerConfig)
    {
        psyGaugeBannerConfig.setCreateTime(DateUtils.getNowDate());
        return psyGaugeBannerConfigMapper.insertPsyGaugeBannerConfig(psyGaugeBannerConfig);
    }

    /**
     * 修改测评banner配置
     * 
     * @param psyGaugeBannerConfig 测评banner配置
     * @return 结果
     */
    @Override
    public int updatePsyGaugeBannerConfig(PsyGaugeBannerConfig psyGaugeBannerConfig)
    {
        return psyGaugeBannerConfigMapper.updatePsyGaugeBannerConfig(psyGaugeBannerConfig);
    }

    /**
     * 批量删除测评banner配置
     * 
     * @param ids 需要删除的测评banner配置主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeBannerConfigByIds(Long[] ids)
    {
        return psyGaugeBannerConfigMapper.deletePsyGaugeBannerConfigByIds(ids);
    }

    /**
     * 删除测评banner配置信息
     * 
     * @param id 测评banner配置主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeBannerConfigById(Long id)
    {
        return psyGaugeBannerConfigMapper.deletePsyGaugeBannerConfigById(id);
    }
}
