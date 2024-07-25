package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.domain.vo.AuditVo;
import org.apache.ibatis.annotations.Param;

/**
 * 供应商Mapper接口
 * 
 * @author xgg
 * @date 2024-07-21
 */
public interface SysSupplierMapper 
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
     * 删除供应商
     * 
     * @param supplierId 供应商主键
     * @return 结果
     */
    public int deleteSysSupplierBySupplierId(String supplierId);

    /**
     * 批量删除供应商
     * 
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSupplierBySupplierIds(String[] supplierIds);

    /**
     * 批量删除产品
     * 
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysProductBySupplierIds(String[] supplierIds);
    
    /**
     * 批量新增产品
     * 
     * @param sysProductList 产品列表
     * @return 结果
     */
    public int batchSysProduct(List<SysProduct> sysProductList);
    

    /**
     * 通过供应商主键删除产品信息
     * 
     * @param supplierId 供应商ID
     * @return 结果
     */
    public int deleteSysProductBySupplierId(String supplierId);

    /**
     * 批量保存供应商信息
     * @param sysSupplier 供应商信息
     * @return 结构
     */
    public int saveSysSupplier(@Param("list") List<SysSupplier> sysSupplier);

    /**
     * 审核供应商
     * @param list 供应商id列表
     * @param auditStatus 审核状态
     * @param inspectionStatus 考察状态
     * @param remark 备注
     * @return
     */
    public  int editSysSupplierAudit(@Param("list") List<AuditVo> list,
                                     @Param("auditStatus")Integer auditStatus,
                                     @Param("inspectionStatus")Integer inspectionStatus,
                                     @Param("remark")String remark);

    /**
     * 执行xml里面的sql语句将审核通过的供应商的考察状态变更为待考察
     * @param supplierIds 供应商id列表
     * @return
     */
    int inspectSysSupplier(List<String> supplierIds);
}
