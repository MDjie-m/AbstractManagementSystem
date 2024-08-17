package com.renxin.web.controller.advert;


import com.renxin.advert.domain.PsyAdvertItem;
import com.renxin.advert.service.IPsyAdvertItemService;
import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.service.IPsyConsultantTeamSupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 广告条目Controller
 * 
 * @author renxin
 * @date 2024-08-16
 */
@RestController
@RequestMapping("/system/advertItem")
public class PsyAdvertItemController extends BaseController
{
    @Autowired
    private IPsyAdvertItemService psyAdvertItemService;
    

    /**
     * 查询广告条目列表
     */
    //@PreAuthorize("@ss.hasPermi('system:advertItem:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyAdvertItem psyAdvertItem)
    {
        startPage();
        List<PsyAdvertItem> list = psyAdvertItemService.selectPsyAdvertItemList(psyAdvertItem);
        return getDataTable(list);
    }

    /**
     * 导出广告条目列表
     */
    //@PreAuthorize("@ss.hasPermi('system:advertItem:export')")
    @Log(title = "广告条目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyAdvertItem psyAdvertItem)
    {
        List<PsyAdvertItem> list = psyAdvertItemService.selectPsyAdvertItemList(psyAdvertItem);
        ExcelUtil<PsyAdvertItem> util = new ExcelUtil<PsyAdvertItem>(PsyAdvertItem.class);
        util.exportExcel(response, list, "广告条目数据");
    }

    /**
     * 获取广告条目详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:advertItem:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyAdvertItemService.selectPsyAdvertItemById(id));
    }

    /**
     * 新增广告条目
     */
    //@PreAuthorize("@ss.hasPermi('system:advertItem:add')")
    @Log(title = "广告条目", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PsyAdvertItem psyAdvertItem)
    {
        return toAjax(psyAdvertItemService.insertPsyAdvertItem(psyAdvertItem));
    }

    /**
     * 修改广告条目
     */
    //@PreAuthorize("@ss.hasPermi('system:advertItem:edit')")
    @Log(title = "广告条目", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PsyAdvertItem psyAdvertItem)
    {
        return toAjax(psyAdvertItemService.updatePsyAdvertItem(psyAdvertItem));
    }

    /**
     * 删除广告条目
     */
    //@PreAuthorize("@ss.hasPermi('system:advertItem:remove')")
    @Log(title = "广告条目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyAdvertItemService.deleteItemByIds(ids));
    }
}
