package com.ruoyi.billiard.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.domain.StockLog;
import com.ruoyi.billiard.domain.vo.StockCheckRes;
import com.ruoyi.billiard.service.IGoodsService;
import com.ruoyi.billiard.service.IStockLogService;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.ruoyi.billiard.domain.Stock;
import com.ruoyi.billiard.service.IStockService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存Controller
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@RestController
@RequestMapping("/billiard/stock")
public class StockController extends BaseController {
    @Autowired
    private IStockService stockService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IStockLogService stockLogService;

    /**
     * 查询库存列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:stock:list')")
    @GetMapping("/list")
    public PageResVo<Stock> list(Stock stock) {
        startPage();
        List<Stock> list = stockService.selectStockList(stock);
        return PageResVo.success(list);
    }

    /**
     * 查询门店商品
     */
    @PreAuthorize("@ss.hasPermi('billiard:stock:list')")
    @GetMapping("/{storeId}/goods/list")
    public ResultVo<List<Goods>> list(@PathVariable Long storeId) {
        List<Goods> list = goodsService.selectGoodsList(Goods.builder().storeId(storeId).build());
        return ResultVo.success(list);
    }

    /**
     * 导出库存列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:stock:export')")
    @Log(title = "库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Stock stock) {
        List<Stock> list = stockService.selectStockList(stock);
        ExcelUtil<Stock> util = new ExcelUtil<Stock>(Stock.class);
        util.exportExcel(response, list, "库存数据");
    }

    /**
     * 获取库存详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:stock:query')")
    @GetMapping(value = "/{stockId}")
    public ResultVo<Stock> getInfo(@PathVariable("stockId") Long stockId) {
        return ResultVo.success(stockService.selectStockByStockId(stockId));
    }

    @PreAuthorize("@ss.hasPermi('billiard:stock:query')")
    @GetMapping(value = "/log/list")
    public PageResVo<StockLog> listLog(StockLog log) {
        startPage();
        return PageResVo.success(stockLogService.selectStockLogList(log));
    }

    /**
     * 新增库存
     */
    @PreAuthorize("@ss.hasPermi('billiard:stock:add')")
    @Log(title = "库存", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody Stock stock) {
        return ResultVo.success(stockService.insertStock(stock));
    }

    /**
     * 修改库存
     */
    @PreAuthorize("@ss.hasPermi('billiard:stock:edit')")
    @Log(title = "库存维护", businessType = BusinessType.UPDATE)
    @PostMapping("/change")
    public ResultVo<Integer> editStock(@RequestBody @Validated StockLog req) {
        return ResultVo.success(stockService.editStock(req));
    }

    @PreAuthorize("@ss.hasPermi('billiard:stock:edit')")
    @Log(title = "库存盘点", businessType = BusinessType.UPDATE)
    @PostMapping("/check")
    public ResultVo<StockCheckRes> editStock(@RequestBody @Validated @Valid List<StockLog> req) {
        return ResultVo.success(stockService.checkStockWithDetail(req));
    }

    /**
     * 删除库存
     */
    @PreAuthorize("@ss.hasPermi('billiard:stock:remove')")
    @Log(title = "库存", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stockIds}")
    public ResultVo<Integer> remove(@PathVariable Long[] stockIds) {
        return ResultVo.success(stockService.deleteStockByStockIds(stockIds));
    }
}
