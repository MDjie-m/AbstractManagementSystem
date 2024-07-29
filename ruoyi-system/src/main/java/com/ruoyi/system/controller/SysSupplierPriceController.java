package com.ruoyi.system.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.vo.AsticVo;
import com.ruoyi.system.domain.vo.PriceVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysSupplierPrice;
import com.ruoyi.system.service.ISysSupplierPriceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商报价Controller
 * 
 * @author wzh
 * @date 2024-07-21
 */
@RestController
@RequestMapping("/system/price")
public class SysSupplierPriceController extends BaseController
{
    @Autowired
    private ISysSupplierPriceService sysSupplierPriceService;

    /**
     * 查询供应商报价列表
     */
    @PreAuthorize("@ss.hasPermi('system:price:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSupplierPrice sysSupplierPrice)
    {
        startPage();
        List<SysSupplierPrice> list = sysSupplierPriceService.selectSysSupplierPriceList(sysSupplierPrice);
        return getDataTable(list);
    }

    /**
     * 导出供应商报价列表
     */
    @PreAuthorize("@ss.hasPermi('system:price:export')")
    @Log(title = "供应商报价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSupplierPrice sysSupplierPrice)
    {
        List<SysSupplierPrice> list = sysSupplierPriceService.selectSysSupplierPriceList(sysSupplierPrice);
        ExcelUtil<SysSupplierPrice> util = new ExcelUtil<SysSupplierPrice>(SysSupplierPrice.class);
        util.exportExcel(response, list, "供应商报价数据");
    }

    /**
     * 获取供应商报价详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:price:query')")
    @GetMapping(value = "/{supplierPriceId}")
    public AjaxResult getInfo(@PathVariable("supplierPriceId") String supplierPriceId)
    {
        return success(sysSupplierPriceService.selectSysSupplierPriceBySupplierPriceId(supplierPriceId));
    }

    /**
     * 新增供应商报价
     */
    @PreAuthorize("@ss.hasPermi('system:price:add')")
    @Log(title = "供应商报价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSupplierPrice sysSupplierPrice)
    {
        return toAjax(sysSupplierPriceService.insertSysSupplierPrice(sysSupplierPrice));
    }

    /**
     * 修改供应商报价
     */
    @PreAuthorize("@ss.hasPermi('system:price:edit')")
    @Log(title = "供应商报价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSupplierPrice sysSupplierPrice)
    {
        return toAjax(sysSupplierPriceService.updateSysSupplierPrice(sysSupplierPrice));
    }

    /**
     * 删除供应商报价
     */
    @PreAuthorize("@ss.hasPermi('system:price:remove')")
    @Log(title = "供应商报价", businessType = BusinessType.DELETE)
    @DeleteMapping("/{supplierPriceIds}")
    public AjaxResult remove(@PathVariable String[] supplierPriceIds)
    {
        return toAjax(sysSupplierPriceService.deleteSysSupplierPriceBySupplierPriceIds(supplierPriceIds));
    }
    /**
     * 报价列表数据统计
     */
    @PostMapping("/statistics")
    public List<AsticVo> productPriceStatistics (@RequestBody PriceVo vo)  {
        return sysSupplierPriceService.productPriceStatistics(vo.getSupplierNames(),vo.getProductName(),vo.getStartDate(), vo.getEndDate());
    }

}
