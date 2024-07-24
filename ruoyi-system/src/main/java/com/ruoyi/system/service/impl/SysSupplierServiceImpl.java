package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.mapper.SysSupplierMapper;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.service.ISysSupplierService;

/**
 * 供应商Service业务层处理
 * 
 * @author xgg
 * @date 2024-07-21
 */
@Service
public class SysSupplierServiceImpl implements ISysSupplierService 
{
    @Autowired
    private SysSupplierMapper sysSupplierMapper;

    /**
     * 查询供应商
     * 
     * @param supplierId 供应商主键
     * @return 供应商
     */
    @Override
    public SysSupplier selectSysSupplierBySupplierId(String supplierId)
    {
        return sysSupplierMapper.selectSysSupplierBySupplierId(supplierId);
    }

    /**
     * 查询供应商列表
     * 
     * @param sysSupplier 供应商
     * @return 供应商
     */
    @Override
    public List<SysSupplier> selectSysSupplierList(SysSupplier sysSupplier)
    {
        return sysSupplierMapper.selectSysSupplierList(sysSupplier);
    }

    /**
     * 新增供应商
     * 
     * @param sysSupplier 供应商
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysSupplier(SysSupplier sysSupplier)
    {
        sysSupplier.setSupplierId(UUID.randomUUID().toString());
        int rows = sysSupplierMapper.insertSysSupplier(sysSupplier);
        insertSysProduct(sysSupplier);
        return rows;
    }

    /**
     * 修改供应商
     * 
     * @param sysSupplier 供应商
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysSupplier(SysSupplier sysSupplier)
    {
        sysSupplierMapper.deleteSysProductBySupplierId(sysSupplier.getSupplierId());
        insertSysProduct(sysSupplier);
        return sysSupplierMapper.updateSysSupplier(sysSupplier);
    }

    /**
     * 批量删除供应商
     * 
     * @param supplierIds 需要删除的供应商主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysSupplierBySupplierIds(String[] supplierIds)
    {
        sysSupplierMapper.deleteSysProductBySupplierIds(supplierIds);
        return sysSupplierMapper.deleteSysSupplierBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商信息
     * 
     * @param supplierId 供应商主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysSupplierBySupplierId(String supplierId)
    {
        sysSupplierMapper.deleteSysProductBySupplierId(supplierId);
        return sysSupplierMapper.deleteSysSupplierBySupplierId(supplierId);
    }

    /**
     * 批量保存
     * @param sysSupplier
     * @return
     */
    @Override
    public int saveSysSupplier(List<SysSupplier> sysSupplier) {
        return sysSupplierMapper.saveSysSupplier(sysSupplier);
    }

    /**
     * 新增产品信息
     * 
     * @param sysSupplier 供应商对象
     */
    public void insertSysProduct(SysSupplier sysSupplier)
    {
        List<SysProduct> sysProductList = sysSupplier.getSysProductList();
        String supplierId = sysSupplier.getSupplierId();
        if (StringUtils.isNotNull(sysProductList))
        {
            List<SysProduct> list = new ArrayList<SysProduct>();
            for (SysProduct sysProduct : sysProductList)
            {
                sysProduct.setSupplierId(supplierId);
                list.add(sysProduct);
            }
            if (list.size() > 0)
            {
                sysSupplierMapper.batchSysProduct(list);
            }
        }
    }
}
