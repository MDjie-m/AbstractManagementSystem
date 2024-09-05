package com.ruoyi.system.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Pattern;

import com.ruoyi.system.domain.vo.*;
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
    @PostMapping("/add")
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
     * 比价
     */
    @PostMapping("/statistics")
    public List<AsticVo> productPriceStatistics (@RequestBody PriceVo vo)  {
        return sysSupplierPriceService.productPriceStatistics(vo.getSupplierIds(),vo.getProductId(),vo.getStartDate(), vo.getEndDate());
    }

    /**
     * 查询所有已报价产品列表(比价列表)
     */
    @GetMapping("/quoteableProducts")
    public List<QuoteVo>  quoteableProducts (@RequestParam(required = false) String supplierId,
                                             @RequestParam(required = false) String productId)  {
        return sysSupplierPriceService.quoteableProducts(supplierId , productId);
    }

    /**
     * 根据产品id返回对此产品报价的供应商列表
     */
    @GetMapping("/quoteSupplier")
    public TableDataInfo  quoteSupplier (@RequestParam String productId,
                                         @RequestParam(required = false)Integer classification,
                                         @RequestParam Date startDate,
                                         @RequestParam Date endDate)  {
        startPage();
        List<SupplierProductVo> supplierProductVos = sysSupplierPriceService.quoteSupplier(productId,classification,startDate,endDate);
        return getDataTable(supplierProductVos);
    }
//    /**
//     * 获取当日汇率
//     */
//    @GetMapping("/getCurrencyRate")
//    public  Double getCurrencyRate() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String response = HttpUtils.sendGet(
//                "https://www.mxnzp.com/api/exchange_rate/aim",
//                "app_id=hksekkhmsgmwtutu" +
//                        "&app_secret=u2Sss9eU4FowLXhMD9T3U1uDWb4TQrow" +
//                        "&from=CNY" +
//                        "&to=USD");
//        Double currency = null;
//        try {
//            Map<String, Object> rootMap = objectMapper.readValue(response, Map.class);
//            @SuppressWarnings("unchecked")
//            Map<String, Object> dataMap = (Map<String, Object>) rootMap.get("data");
//            if (dataMap != null && dataMap.containsKey("price")) {
//                currency = (Double) dataMap.get("price");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return currency;
//    }

}
