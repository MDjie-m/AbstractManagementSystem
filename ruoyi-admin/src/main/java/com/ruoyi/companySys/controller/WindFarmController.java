package com.ruoyi.companySys.controller;

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
import com.ruoyi.companySys.domain.WindFarm;
import com.ruoyi.companySys.service.IWindFarmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 风场管理Controller
 * 
 * @author GG
 * @date 2024-07-13
 */
@RestController
@RequestMapping("/companySys/farm")
public class WindFarmController extends BaseController
{
    @Autowired
    private IWindFarmService windFarmService;

    /**
     * 查询风场管理列表
     */
    @PreAuthorize("@ss.hasPermi('companySys:farm:list')")
    @GetMapping("/list")
    public TableDataInfo list(WindFarm windFarm)
    {
        startPage();
        List<WindFarm> list = windFarmService.selectWindFarmList(windFarm);
        return getDataTable(list);
    }

    /**
     * 导出风场管理列表
     */
    @PreAuthorize("@ss.hasPermi('companySys:farm:export')")
    @Log(title = "风场管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WindFarm windFarm)
    {
        List<WindFarm> list = windFarmService.selectWindFarmList(windFarm);
        ExcelUtil<WindFarm> util = new ExcelUtil<WindFarm>(WindFarm.class);
        util.exportExcel(response, list, "风场管理数据");
    }

    /**
     * 获取风场管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('companySys:farm:query')")
    @GetMapping(value = "/{wId}")
    public AjaxResult getInfo(@PathVariable("wId") Long wId)
    {
        return success(windFarmService.selectWindFarmByWId(wId));
    }

    /**
     * 新增风场管理
     */
    @PreAuthorize("@ss.hasPermi('companySys:farm:add')")
    @Log(title = "风场管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WindFarm windFarm)
    {
        return toAjax(windFarmService.insertWindFarm(windFarm));
    }

    /**
     * 修改风场管理
     */
    @PreAuthorize("@ss.hasPermi('companySys:farm:edit')")
    @Log(title = "风场管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WindFarm windFarm)
    {
        return toAjax(windFarmService.updateWindFarm(windFarm));
    }

    /**
     * 删除风场管理
     */
    @PreAuthorize("@ss.hasPermi('companySys:farm:remove')")
    @Log(title = "风场管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wIds}")
    public AjaxResult remove(@PathVariable Long[] wIds)
    {
        return toAjax(windFarmService.deleteWindFarmByWIds(wIds));
    }
}
