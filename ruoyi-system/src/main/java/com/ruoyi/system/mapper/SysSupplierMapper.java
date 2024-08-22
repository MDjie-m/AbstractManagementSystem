package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.domain.vo.supplierVo.SelectSupplierVo;
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
     * 根据产品查询供应商列表
     *
     * @param supplier 供应商详细包括了产品
     * @return 供应商集合
     */
    public List<SysSupplier> selectSupplierByProduct(SelectSupplierVo supplier);

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
    public  int editSysSupplierAudit(@Param("list") List<String> list,
                                     @Param("auditStatus")Integer auditStatus,
                                     @Param("inspectionStatus")Integer inspectionStatus,
                                     @Param("remark")String remark);

    /**
     * 执行xml里面的sql语句将审核通过的供应商的考察状态变更为待考察
     * @param supplierIds 供应商id列表
     * @return
     */
    int inspectSysSupplier(List<String> supplierIds);

    /**
     * 执行里面的sql语句更新供应商表中该供应商的考察评级以及对应的考察id
     * @param supplierId 供应商id
     * @param inspectionId 考察Id
     * @param rate 评级
     * @return
     */
    int updateInspectRate(@Param("supplierId") String supplierId,
                          @Param("inspectionId") String inspectionId, @Param("rate") String rate);

    /**
     * 修改供应商审核考察状态
     * @param list
     * @param inspectionAuditStatus
     * @return
     */
    public int inspectionAuditSysSupplier(@Param("list")List<String> list, @Param("inspectionAuditStatus") int inspectionAuditStatus,@Param("rate") String rate);

    /**
     * 根据法人电话核验唯一性
     * @param legalPersonTelephone 法人电话
     * @return 供应商信息
     */
    public SysSupplier checkLegalPersonTelephoneUnique(@Param("legalPersonTelephone") String legalPersonTelephone);
}
