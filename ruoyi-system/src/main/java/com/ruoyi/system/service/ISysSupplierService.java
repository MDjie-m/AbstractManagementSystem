package com.ruoyi.system.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.domain.vo.AuditVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    public AjaxResult insertSysSupplier(SysSupplier sysSupplier);

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

    /**
     * 导入供应商列表
     * @param file 上传的excel文件
     * @throws IOException
     */
    public void saveSysSupplier(MultipartFile file) throws IOException;

    /**
     * 导出供应商数据
     * @param response
     * @param supplier 查询条件
     * @throws IOException
     */
    public void exportSysSupplier(HttpServletResponse response, SysSupplier supplier) throws IOException;

    /**
     * 编辑供应商审核状态
     * @param vo
     * @return
     */
    public int auditSysSupplier(AuditVo vo);

    /**
     * 将审核已通过的供应商从候选状态变更为待考察状态
     * @param supplierIds 供应商id列表
     * @return 操作行数
     */
    int inspectSysSupplier(List<String> supplierIds);

    /**
     * 审核考察
     * @param vo
     * @return
     */
    int inspectionAuditSysSupplier(AuditVo vo);
}
