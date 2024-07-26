package com.ruoyi.windSys.controller;

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
import com.ruoyi.windSys.domain.BladePart;
import com.ruoyi.windSys.service.IBladePartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 叶片管理Controller
 * 
 * @author GG
 * @date 2024-07-13
 */
@RestController
@RequestMapping("/windSys/part")
public class BladePartController extends BaseController
{
    @Autowired
    private IBladePartService bladePartService;

    /**
     * 查询叶片管理列表
     */
    @PreAuthorize("@ss.hasPermi('windSys:part:list')")
    @GetMapping("/list")
    public TableDataInfo list(BladePart bladePart)
    {
        startPage();
        List<BladePart> list = bladePartService.selectBladePartList(bladePart);
        return getDataTable(list);
    }

    /**
     * 导出叶片管理列表
     */
    @PreAuthorize("@ss.hasPermi('windSys:part:export')")
    @Log(title = "叶片管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BladePart bladePart)
    {
        List<BladePart> list = bladePartService.selectBladePartList(bladePart);
        ExcelUtil<BladePart> util = new ExcelUtil<BladePart>(BladePart.class);
        util.exportExcel(response, list, "叶片管理数据");
    }

    /**
     * 获取叶片管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('windSys:part:query')")
    @GetMapping(value = "/{bpId}")
    public AjaxResult getInfo(@PathVariable("bpId") Long bpId)
    {
        return success(bladePartService.selectBladePartByBpId(bpId));
    }

    /**
     * 新增叶片管理
     */
    @PreAuthorize("@ss.hasPermi('windSys:part:add')")
    @Log(title = "叶片管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BladePart bladePart)
    {
        return toAjax(bladePartService.insertBladePart(bladePart));
    }

    /**
     * 修改叶片管理
     */
    @PreAuthorize("@ss.hasPermi('windSys:part:edit')")
    @Log(title = "叶片管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BladePart bladePart)
    {
        return toAjax(bladePartService.updateBladePart(bladePart));
    }

    /**
     * 删除叶片管理
     */
    @PreAuthorize("@ss.hasPermi('windSys:part:remove')")
    @Log(title = "叶片管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bpIds}")
    public AjaxResult remove(@PathVariable Long[] bpIds)
    {
        return toAjax(bladePartService.deleteBladePartByBpIds(bpIds));
    }
}
