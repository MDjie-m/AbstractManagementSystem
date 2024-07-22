package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysSupplierPriceMapper;
import com.ruoyi.system.domain.SysSupplierPrice;
import com.ruoyi.system.service.ISysSupplierPriceService;

/**
 * 供应商报价Service业务层处理
 * 
 * @author wzh
 * @date 2024-07-21
 */
@Service
public class SysSupplierPriceServiceImpl implements ISysSupplierPriceService 
{
    @Autowired
    private SysSupplierPriceMapper sysSupplierPriceMapper;

    /**
     * 查询供应商报价
     * 
     * @param supplierPriceId 供应商报价主键
     * @return 供应商报价
     */
    @Override
    public SysSupplierPrice selectSysSupplierPriceBySupplierPriceId(String supplierPriceId)
    {
        return sysSupplierPriceMapper.selectSysSupplierPriceBySupplierPriceId(supplierPriceId);
    }

    /**
     * 查询供应商报价列表
     * 
     * @param sysSupplierPrice 供应商报价
     * @return 供应商报价
     */
    @Override
    public List<SysSupplierPrice> selectSysSupplierPriceList(SysSupplierPrice sysSupplierPrice)
    {
        return sysSupplierPriceMapper.selectSysSupplierPriceList(sysSupplierPrice);
    }

    /**
     * 新增供应商报价
     * 
     * @param sysSupplierPrice 供应商报价
     * @return 结果
     */
    @Override
    public int insertSysSupplierPrice(SysSupplierPrice sysSupplierPrice)
    {
        sysSupplierPrice.setSupplierPriceId(UUID.randomUUID().toString());
        return sysSupplierPriceMapper.insertSysSupplierPrice(sysSupplierPrice);
    }

    /**
     * 修改供应商报价
     * 
     * @param sysSupplierPrice 供应商报价
     * @return 结果
     */
    @Override
    public int updateSysSupplierPrice(SysSupplierPrice sysSupplierPrice)
    {
        return sysSupplierPriceMapper.updateSysSupplierPrice(sysSupplierPrice);
    }

    /**
     * 批量删除供应商报价
     * 
     * @param supplierPriceIds 需要删除的供应商报价主键
     * @return 结果
     */
    @Override
    public int deleteSysSupplierPriceBySupplierPriceIds(String[] supplierPriceIds)
    {
        return sysSupplierPriceMapper.deleteSysSupplierPriceBySupplierPriceIds(supplierPriceIds);
    }

    /**
     * 删除供应商报价信息
     * 
     * @param supplierPriceId 供应商报价主键
     * @return 结果
     */
    @Override
    public int deleteSysSupplierPriceBySupplierPriceId(String supplierPriceId)
    {
        return sysSupplierPriceMapper.deleteSysSupplierPriceBySupplierPriceId(supplierPriceId);
    }
}
