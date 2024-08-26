package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.dto.SysProductStandardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysProductStandardMapper;
import com.ruoyi.system.domain.SysProductStandard;
import com.ruoyi.system.service.ISysProductStandardService;

/**
 * 产品Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
@Service
public class SysProductStandardServiceImpl implements ISysProductStandardService 
{
    @Autowired
    private SysProductStandardMapper sysProductStandardMapper;

    /**
     * 查询产品
     * 
     * @param productId 产品主键
     * @return 产品
     */
    @Override
    public SysProductStandard selectSysProductStandardByProductId(String productId)
    {
        return sysProductStandardMapper.selectSysProductStandardByProductId(productId);
    }

    /**
     * 查询产品列表
     * 
     * @param sysProductStandardDTO 产品
     * @return 产品
     */
    @Override
    public List<SysProductStandard> selectSysProductStandardList(SysProductStandardDTO sysProductStandardDTO)
    {
        return sysProductStandardMapper.selectSysProductStandardList(sysProductStandardDTO);
    }

    /**
     * 新增产品
     * 
     * @param sysProductStandard 产品
     * @return 结果
     */
    @Override
    public int insertSysProductStandard(SysProductStandard sysProductStandard)
    {
        sysProductStandard.setCreateTime(DateUtils.getNowDate());
        return sysProductStandardMapper.insertSysProductStandard(sysProductStandard);
    }

    /**
     * 修改产品
     * 
     * @param sysProductStandard 产品
     * @return 结果
     */
    @Override
    public int updateSysProductStandard(SysProductStandard sysProductStandard)
    {
        sysProductStandard.setUpdateTime(DateUtils.getNowDate());
        return sysProductStandardMapper.updateSysProductStandard(sysProductStandard);
    }

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品主键
     * @return 结果
     */
    @Override
    public int deleteSysProductStandardByProductIds(String[] productIds)
    {
        return sysProductStandardMapper.deleteSysProductStandardByProductIds(productIds);
    }
}
