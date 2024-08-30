package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantPackage;
import com.renxin.psychology.service.IPsyConsultantPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 咨询师成长套餐Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/system/package")
public class PsyConsultantPackageController extends BaseController
{
    @Autowired
    private IPsyConsultantPackageService psyConsultantPackageService;

    /**
     * 查询咨询师成长套餐列表
     */
    //@PreAuthorize("@ss.hasPermi('system:package:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantPackage psyConsultantPackage)
    {
        startPage();
        List<PsyConsultantPackage> list = psyConsultantPackageService.selectPsyConsultantPackageList(psyConsultantPackage);
        return getDataTable(list);
    }

    /**
     * 导出咨询师成长套餐列表
     */
    //@PreAuthorize("@ss.hasPermi('system:package:export')")
    @Log(title = "咨询师成长套餐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantPackage psyConsultantPackage)
    {
        List<PsyConsultantPackage> list = psyConsultantPackageService.selectPsyConsultantPackageList(psyConsultantPackage);
        ExcelUtil<PsyConsultantPackage> util = new ExcelUtil<PsyConsultantPackage>(PsyConsultantPackage.class);
        util.exportExcel(response, list, "咨询师成长套餐数据");
    }

    /**
     * 获取咨询师成长套餐详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:package:query')")
    @GetMapping(value = "/queryById/{packageId}")
    public AjaxResult getInfo(@PathVariable("packageId") Long packageId)
    {
        return AjaxResult.success(psyConsultantPackageService.selectPsyConsultantPackageByPackageId(packageId));
    }

    /**
     * 新增咨询师成长套餐
     */
    //@PreAuthorize("@ss.hasPermi('system:package:add')")
    @Log(title = "咨询师成长套餐", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PsyConsultantPackage psyConsultantPackage)
    {
        int i = psyConsultantPackageService.insertPsyConsultantPackage(psyConsultantPackage);

        psyConsultantPackageService.selectPsyConsultantPackageByPackageId(psyConsultantPackage.getPackageId());
        return toAjax(i);
    }

    /**
     * 修改咨询师成长套餐
     */
    //@PreAuthorize("@ss.hasPermi('system:package:edit')")
    @Log(title = "咨询师成长套餐", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody PsyConsultantPackage psyConsultantPackage)
    {
        int i = psyConsultantPackageService.updatePsyConsultantPackage(psyConsultantPackage);

        psyConsultantPackageService.selectPsyConsultantPackageByPackageId(psyConsultantPackage.getPackageId());
        return toAjax(i);
    }

    /**
     * 删除咨询师成长套餐
     */
    //@PreAuthorize("@ss.hasPermi('system:package:remove')")
    @Log(title = "咨询师成长套餐", businessType = BusinessType.DELETE)
	@DeleteMapping("/deleteByIds/{packageIds}")
    public AjaxResult remove(@PathVariable Long[] packageIds)
    {
        return toAjax(psyConsultantPackageService.deletePsyConsultantPackageByPackageIds(packageIds));
    }

    /**
     * 刷新课套餐存
     */
    //@PreAuthorize("@ss.hasPermi('course:course:list')")
    @GetMapping("/refreshCacheAll")
    public AjaxResult refreshCacheAll()
    {
        psyConsultantPackageService.refreshCacheAll();
        return AjaxResult.success();
    }
    
}
