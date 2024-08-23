package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysProductStandard;
import com.ruoyi.system.service.ISysProductStandardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品Controller
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
@RestController
@RequestMapping("/system/standard")
public class SysProductStandardController extends BaseController
{
    @Autowired
    private ISysProductStandardService sysProductStandardService;

    /**
     * 查询产品列表
     */
    @PreAuthorize("@ss.hasPermi('system:standard:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysProductStandard sysProductStandard)
    {
        startPage();
        List<SysProductStandard> list = sysProductStandardService.selectSysProductStandardList(sysProductStandard);
        return getDataTable(list);
    }

    /**
     * 导出产品列表
     */
    @PreAuthorize("@ss.hasPermi('system:standard:export')")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysProductStandard sysProductStandard)
    {
        List<SysProductStandard> list = sysProductStandardService.selectSysProductStandardList(sysProductStandard);
        ExcelUtil<SysProductStandard> util = new ExcelUtil<SysProductStandard>(SysProductStandard.class);
        util.exportExcel(response, list, "产品数据");
    }

    /**
     * 获取产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:standard:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") String productId)
    {
        return success(sysProductStandardService.selectSysProductStandardByProductId(productId));
    }

    /**
     * 新增产品
     */
    @PreAuthorize("@ss.hasPermi('system:standard:add')")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysProductStandard sysProductStandard)
    {
        return toAjax(sysProductStandardService.insertSysProductStandard(sysProductStandard));
    }

    /**
     * 修改产品
     */
    @PreAuthorize("@ss.hasPermi('system:standard:edit')")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysProductStandard sysProductStandard)
    {
        return toAjax(sysProductStandardService.updateSysProductStandard(sysProductStandard));
    }

    /**
     * 删除产品
     */
    @PreAuthorize("@ss.hasPermi('system:standard:remove')")
    @Log(title = "产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable String[] productIds)
    {
        return toAjax(sysProductStandardService.deleteSysProductStandardByProductIds(productIds));
    }
}
