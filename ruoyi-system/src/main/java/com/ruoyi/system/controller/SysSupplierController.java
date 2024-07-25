package com.ruoyi.system.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.vo.AuditVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.service.ISysSupplierService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 供应商Controller
 * 
 * @author lyj
 * @date 2024-07-22
 */
@RestController
@RequestMapping("/system/supplier")
public class SysSupplierController extends BaseController
{
    @Autowired
    private ISysSupplierService sysSupplierService;
    @Value("staticFile.rootPath")
    private String rootPath;

    /**
     * 查询供应商列表
     */
    @PreAuthorize("@ss.hasPermi('system:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSupplier sysSupplier)
    {
        startPage();
        List<SysSupplier> list = sysSupplierService.selectSysSupplierList(sysSupplier);
        return getDataTable(list);
    }

    /**
     * 批量导入供应商数据
     * @param file 上传的excel文件
     * @return
     * @throws Exception
     */
    @Log(title = "供应商管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:supplier:import')")
    @PostMapping("/importData")
    public AjaxResult importData(@RequestParam("file") MultipartFile file) throws Exception
    {
        sysSupplierService.saveSysSupplier(file);
        return success();
    }

    /**
     * 导出供应商列表
     * @param response
     * @param supplier 查询条件
     * @throws IOException
     */
    @PreAuthorize("@ss.hasPermi('system:supplier:export')")
    @Log(title = "供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSupplier supplier) throws IOException {
        sysSupplierService.exportSysSupplier(response, supplier);
    }

    /**
     * 获取供应商详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:supplier:query')")
    @GetMapping(value = "/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId") String supplierId)
    {
        return success(sysSupplierService.selectSysSupplierBySupplierId(supplierId));
    }

    /**
     * 新增供应商
     */
    @PreAuthorize("@ss.hasPermi('system:supplier:add')")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSupplier sysSupplier)
    {
        return toAjax(sysSupplierService.insertSysSupplier(sysSupplier));
    }

    /**
     * 下载模板地址返回
     * @return
     */
    @PostMapping("/getUrl")
    public  AjaxResult getUrl(){
        // todo 对应文件名称
        String url = rootPath+"/template/"+"supplierExport.xlsx";
        return success().put("data",url);
    }
    /**
     * 修改供应商
     */
    @PreAuthorize("@ss.hasPermi('system:supplier:edit')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSupplier sysSupplier)
    {
        return toAjax(sysSupplierService.updateSysSupplier(sysSupplier));
    }

    /**
     * 删除供应商
     */
    @PreAuthorize("@ss.hasPermi('system:supplier:remove')")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable String[] supplierIds)
    {
        return toAjax(sysSupplierService.deleteSysSupplierBySupplierIds(supplierIds));
    }

    /**
     * 新增供应商
     */
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult noPermissionsadd(@RequestBody SysSupplier sysSupplier)
    {
        return toAjax(sysSupplierService.insertSysSupplier(sysSupplier));
    }

    /**
     * 审核供应商
     */
    @PreAuthorize("@ss.hasPermi('system:supplier:audit')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody AuditVo vo){
        return toAjax(sysSupplierService.auditSysSupplier(vo));
    }

    /**
     * 执行xml里面的sql语句将审核通过的供应商的考察状态变更为待考察
     * @param supplierIds 供应商id列表
     * @return
     * 需要操作这个界面的角色有 system:supplier:inspect 权限
     */
    @PreAuthorize("@ss.hasPermi('system:supplier:inspect')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PostMapping("/inspect")
    public AjaxResult inspect(@RequestBody List<String> supplierIds){
        return toAjax(sysSupplierService.inspectSysSupplier(supplierIds));
    }
}
