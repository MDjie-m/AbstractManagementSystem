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
import com.ruoyi.system.domain.SysInspection;
import com.ruoyi.system.service.ISysInspectionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考察情况Controller
 * 
 * @author tyc
 * @date 2024-07-21
 */
@RestController
@RequestMapping("/system/inspection")
public class SysInspectionController extends BaseController
{
    @Autowired
    private ISysInspectionService sysInspectionService;

    /**
     * 查询考察情况列表
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysInspection sysInspection)
    {
        startPage();
        List<SysInspection> list = sysInspectionService.selectSysInspectionList(sysInspection);
        return getDataTable(list);
    }

    /**
     * 导出考察情况列表
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:export')")
    @Log(title = "考察情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysInspection sysInspection)
    {
        List<SysInspection> list = sysInspectionService.selectSysInspectionList(sysInspection);
        ExcelUtil<SysInspection> util = new ExcelUtil<SysInspection>(SysInspection.class);
        util.exportExcel(response, list, "考察情况数据");
    }

    /**
     * 获取考察情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:query')")
    @GetMapping(value = "/{inspectionId}")
    public AjaxResult getInfo(@PathVariable("inspectionId") String inspectionId)
    {
        return success(sysInspectionService.selectSysInspectionByInspectionId(inspectionId));
    }

    /**
     * 新增考察情况
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:add')")
    @Log(title = "考察情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysInspection sysInspection)
    {
        return toAjax(sysInspectionService.insertSysInspection(sysInspection));
    }

    /**
     * 修改考察情况
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:edit')")
    @Log(title = "考察情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysInspection sysInspection)
    {
        return toAjax(sysInspectionService.updateSysInspection(sysInspection));
    }

    /**
     * 删除考察情况
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:remove')")
    @Log(title = "考察情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inspectionIds}")
    public AjaxResult remove(@PathVariable String[] inspectionIds)
    {
        return toAjax(sysInspectionService.deleteSysInspectionByInspectionIds(inspectionIds));
    }
}
