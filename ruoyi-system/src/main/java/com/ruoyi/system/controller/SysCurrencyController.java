package com.ruoyi.system.controller;

import java.math.BigDecimal;
import java.util.Date;
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
import com.ruoyi.system.domain.SysCurrency;
import com.ruoyi.system.service.ISysCurrencyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 汇率Controller
 * 
 * @author ruoyi
 * @date 2024-08-28
 */
@RestController
@RequestMapping("/system/currency")
public class SysCurrencyController extends BaseController
{
    @Autowired
    private ISysCurrencyService sysCurrencyService;


    /**
     * 查询最新汇率
     */
//    @PreAuthorize("@ss.hasPermi('system:currency:list')")
    @GetMapping("/getCurrency")
    public AjaxResult getCurrency()
    {
        Double currency = sysCurrencyService.getCurrency();
        return success(currency);
    }


    /**
     * 查询汇率列表
     */
    @PreAuthorize("@ss.hasPermi('system:currency:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCurrency sysCurrency)
    {
        startPage();
        List<SysCurrency> list = sysCurrencyService.selectSysCurrencyList(sysCurrency);
        return getDataTable(list);
    }

    /**
     * 导出汇率列表
     */
    @PreAuthorize("@ss.hasPermi('system:currency:export')")
    @Log(title = "汇率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCurrency sysCurrency)
    {
        List<SysCurrency> list = sysCurrencyService.selectSysCurrencyList(sysCurrency);
        ExcelUtil<SysCurrency> util = new ExcelUtil<SysCurrency>(SysCurrency.class);
        util.exportExcel(response, list, "汇率数据");
    }

    /**
     * 获取汇率详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:currency:query')")
    @GetMapping(value = "/{currencyId}")
    public AjaxResult getInfo(@PathVariable("currencyId") Long currencyId)
    {
        return success(sysCurrencyService.selectSysCurrencyByCurrencyId(currencyId));
    }

    /**
     * 新增汇率
     */
    @PreAuthorize("@ss.hasPermi('system:currency:add')")
    @Log(title = "汇率", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCurrency sysCurrency)
    {
        return toAjax(sysCurrencyService.insertSysCurrency(sysCurrency));
    }

    /**
     * 修改汇率
     */
    @PreAuthorize("@ss.hasPermi('system:currency:edit')")
    @Log(title = "汇率", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCurrency sysCurrency)
    {
        return toAjax(sysCurrencyService.updateSysCurrency(sysCurrency));
    }

    /**
     * 删除汇率
     */
    @PreAuthorize("@ss.hasPermi('system:currency:remove')")
    @Log(title = "汇率", businessType = BusinessType.DELETE)
	@DeleteMapping("/{currencyIds}")
    public AjaxResult remove(@PathVariable Long[] currencyIds)
    {
        return toAjax(sysCurrencyService.deleteSysCurrencyByCurrencyIds(currencyIds));
    }

}
