package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysSupplier;

/**
 * 供应商Service接口
 * 
 * @author xgg
 * @date 2024-07-21
 */
public interface ISysSupplierService 
{
    /**
     * 查询供应商
     * 
     * @param supplierId 供应商主键
     * @return 供应商
     */
    public SysSupplier selectSysSupplierBySupplierId(String supplierId);

    /**
     * 查询供应商列表
     * 
     * @param sysSupplier 供应商
     * @return 供应商集合
     */
    public List<SysSupplier> selectSysSupplierList(SysSupplier sysSupplier);

    /**
     * 新增供应商
     * 
     * @param sysSupplier 供应商
     * @return 结果
     */
    public int insertSysSupplier(SysSupplier sysSupplier);

    /**
     * 修改供应商
     * 
     * @param sysSupplier 供应商
     * @return 结果
     */
    public int updateSysSupplier(SysSupplier sysSupplier);

    /**
     * 批量删除供应商
     * 
     * @param supplierIds 需要删除的供应商主键集合
     * @return 结果
     */
    public int deleteSysSupplierBySupplierIds(String[] supplierIds);

    /**
     * 删除供应商信息
     * 
     * @param supplierId 供应商主键
     * @return 结果
     */
    public int deleteSysSupplierBySupplierId(String supplierId);

    public int saveSysSupplier(List<SysSupplier> sysSupplier);
}
