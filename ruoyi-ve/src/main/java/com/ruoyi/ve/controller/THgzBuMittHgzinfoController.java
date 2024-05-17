package com.ruoyi.ve.controller;

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
import com.ruoyi.ve.domain.THgzBuMittHgzinfo;
import com.ruoyi.ve.service.ITHgzBuMittHgzinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 国家合格证Controller
 * 
 * @author cherigo
 * @date 2024-04-22
 */
@RestController
@RequestMapping("/ve/hgzinfo")
public class THgzBuMittHgzinfoController extends BaseController
{
    @Autowired
    private ITHgzBuMittHgzinfoService tHgzBuMittHgzinfoService;

    /**
     * 查询国家合格证列表
     */
    @PreAuthorize("@ss.hasPermi('ve:hgzinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(THgzBuMittHgzinfo tHgzBuMittHgzinfo)
    {
        startPage();
        List<THgzBuMittHgzinfo> list = tHgzBuMittHgzinfoService.selectTHgzBuMittHgzinfoList(tHgzBuMittHgzinfo);
        return getDataTable(list);
    }

    /**
     * 导出国家合格证列表
     */
    @PreAuthorize("@ss.hasPermi('ve:hgzinfo:export')")
    @Log(title = "国家合格证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, THgzBuMittHgzinfo tHgzBuMittHgzinfo)
    {
        List<THgzBuMittHgzinfo> list = tHgzBuMittHgzinfoService.selectTHgzBuMittHgzinfoList(tHgzBuMittHgzinfo);
        ExcelUtil<THgzBuMittHgzinfo> util = new ExcelUtil<THgzBuMittHgzinfo>(THgzBuMittHgzinfo.class);
        util.exportExcel(response, list, "国家合格证数据");
    }

    /**
     * 获取国家合格证详细信息
     */
    @PreAuthorize("@ss.hasPermi('ve:hgzinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tHgzBuMittHgzinfoService.selectTHgzBuMittHgzinfoById(id));
    }

    /**
     * 新增国家合格证
     */
    @PreAuthorize("@ss.hasPermi('ve:hgzinfo:add')")
    @Log(title = "国家合格证", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody THgzBuMittHgzinfo tHgzBuMittHgzinfo)
    {
        return toAjax(tHgzBuMittHgzinfoService.insertTHgzBuMittHgzinfo(tHgzBuMittHgzinfo));
    }

    /**
     * 修改国家合格证
     */
    @PreAuthorize("@ss.hasPermi('ve:hgzinfo:edit')")
    @Log(title = "国家合格证", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody THgzBuMittHgzinfo tHgzBuMittHgzinfo)
    {
        return toAjax(tHgzBuMittHgzinfoService.updateTHgzBuMittHgzinfo(tHgzBuMittHgzinfo));
    }

    /**
     * 删除国家合格证
     */
    @PreAuthorize("@ss.hasPermi('ve:hgzinfo:remove')")
    @Log(title = "国家合格证", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tHgzBuMittHgzinfoService.deleteTHgzBuMittHgzinfoByIds(ids));
    }
}
