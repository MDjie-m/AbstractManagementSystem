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
import com.ruoyi.windSys.domain.WindTurbineInfo;
import com.ruoyi.windSys.service.IWindTurbineInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 风机管理Controller
 * 
 * @author GG
 * @date 2024-07-13
 */
@RestController
@RequestMapping("/windSys/wind")
public class WindTurbineInfoController extends BaseController
{
    @Autowired
    private IWindTurbineInfoService windTurbineInfoService;

    /**
     * 查询风机管理列表
     */
    @PreAuthorize("@ss.hasPermi('windSys:wind:list')")
    @GetMapping("/list")
    public TableDataInfo list(WindTurbineInfo windTurbineInfo)
    {
        startPage();
        List<WindTurbineInfo> list = windTurbineInfoService.selectWindTurbineInfoList(windTurbineInfo);
        return getDataTable(list);
    }

    /**
     * 导出风机管理列表
     */
    @PreAuthorize("@ss.hasPermi('windSys:wind:export')")
    @Log(title = "风机管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WindTurbineInfo windTurbineInfo)
    {
        List<WindTurbineInfo> list = windTurbineInfoService.selectWindTurbineInfoList(windTurbineInfo);
        ExcelUtil<WindTurbineInfo> util = new ExcelUtil<WindTurbineInfo>(WindTurbineInfo.class);
        util.exportExcel(response, list, "风机管理数据");
    }

    /**
     * 获取风机管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('windSys:wind:query')")
    @GetMapping(value = "/{wId}")
    public AjaxResult getInfo(@PathVariable("wId") Long wId)
    {
        return success(windTurbineInfoService.selectWindTurbineInfoByWId(wId));
    }

    /**
     * 新增风机管理
     */
    @PreAuthorize("@ss.hasPermi('windSys:wind:add')")
    @Log(title = "风机管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WindTurbineInfo windTurbineInfo)
    {
        return toAjax(windTurbineInfoService.insertWindTurbineInfo(windTurbineInfo));
    }

    /**
     * 修改风机管理
     */
    @PreAuthorize("@ss.hasPermi('windSys:wind:edit')")
    @Log(title = "风机管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WindTurbineInfo windTurbineInfo)
    {
        return toAjax(windTurbineInfoService.updateWindTurbineInfo(windTurbineInfo));
    }

    /**
     * 删除风机管理
     */
    @PreAuthorize("@ss.hasPermi('windSys:wind:remove')")
    @Log(title = "风机管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wIds}")
    public AjaxResult remove(@PathVariable Long[] wIds)
    {
        return toAjax(windTurbineInfoService.deleteWindTurbineInfoByWIds(wIds));
    }
}
