package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.GoodsCategory;
import com.ruoyi.billiard.domain.Stock;
import com.ruoyi.billiard.domain.StockLog;
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.service.IStockService;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.billiard.service.IStoreTutorService;
import com.ruoyi.billiard.service.IStoreUserService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("cashier/stock")
public class CashierStockController extends BaseController {

    @Autowired
    private IStockService stockService;


    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/list")
    public ResultVo<List<Stock>> getStoreInfo(Stock stock) {
        stock.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(stockService.selectStockList(stock));
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @GetMapping("/category/list")
    public ResultVo<List<GoodsCategory>> getCategoryStock(Stock stock) {
        stock.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(stockService.getCategoryStock(stock));
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:edit')")
    @Log(title = "收银库存盘点", businessType = BusinessType.UPDATE)
    @PostMapping("/check")
    public ResultVo<List<String>> editStock(@RequestBody @Validated @Valid List<StockLog> req) {
        Long storeId = getStoreIdWithThrow();
        req.forEach(p -> p.setStoreId(storeId));
        return ResultVo.success(stockService.checkStock(req));
    }
}