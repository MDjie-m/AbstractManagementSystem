package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysProductStandard;

/**
 * 产品Service接口
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
public interface ISysProductStandardService 
{
    /**
     * 查询产品
     * 
     * @param productId 产品主键
     * @return 产品
     */
    public SysProductStandard selectSysProductStandardByProductId(String productId);

    /**
     * 查询产品列表
     * 
     * @param sysProductStandard 产品
     * @return 产品集合
     */
    public List<SysProductStandard> selectSysProductStandardList(SysProductStandard sysProductStandard);

    /**
     * 新增产品
     * 
     * @param sysProductStandard 产品
     * @return 结果
     */
    public int insertSysProductStandard(SysProductStandard sysProductStandard);

    /**
     * 修改产品
     * 
     * @param sysProductStandard 产品
     * @return 结果
     */
    public int updateSysProductStandard(SysProductStandard sysProductStandard);

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品主键集合
     * @return 结果
     */
    public int deleteSysProductStandardByProductIds(String[] productIds);
}
