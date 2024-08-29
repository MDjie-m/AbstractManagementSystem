package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.system.domain.dto.SysProductStandardDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
 * @author tyc
 * @date 2024-08-23
 */
@RestController
@RequestMapping("/system/standard")
public class SysProductStandardController extends BaseController
{
    @Autowired
    private ISysProductStandardService sysProductStandardService;

    /**
     * 查询平台产品列表，游客也可以访问
     */
    @Anonymous
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysProductStandardDTO sysProductStandardDTO)
    {
        PageHelper.startPage(sysProductStandardDTO.getPageNum(),sysProductStandardDTO.getPageSize());
        List<SysProductStandard> list = sysProductStandardService.selectSysProductStandardList(sysProductStandardDTO);
        return getDataTable(list);
    }

    /**
     * 导出产品列表
     */
    @PreAuthorize("@ss.hasPermi('system:standard:export')")
    @Log(title = "平台产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysProductStandard sysProductStandard)
    {
//        List<SysProductStandard> list = sysProductStandardService.selectSysProductStandardList(sysProductStandard);
//        ExcelUtil<SysProductStandard> util = new ExcelUtil<SysProductStandard>(SysProductStandard.class);
//        util.exportExcel(response, list, "产品数据");
    }

    /**
     * 获取平台产品详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:standard:query')")
    @Anonymous
    @GetMapping("/query")
    public AjaxResult getInfo(@RequestParam String productId)
    {
        return success(sysProductStandardService.selectSysProductStandardByProductId(productId));
    }

    /**
     * 新增产品
     */
    @PreAuthorize("@ss.hasPermi('system:standard:add')")
    @Log(title = "平台产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysProductStandard sysProductStandard)
    {
        return toAjax(sysProductStandardService.insertSysProductStandard(sysProductStandard));
    }

    /**
     * 修改产品
     */
    @PreAuthorize("@ss.hasPermi('system:standard:edit')")
    @Log(title = "平台产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysProductStandard sysProductStandard)
    {
        return toAjax(sysProductStandardService.updateSysProductStandard(sysProductStandard));
    }

    /**
     * 批量删除平台产品
     */
    @PreAuthorize("@ss.hasPermi('system:standard:remove')")
    @Log(title = "平台产品", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody String[] productIds)
    {
        return toAjax(sysProductStandardService.deleteSysProductStandardByProductIds(productIds));
    }
}
