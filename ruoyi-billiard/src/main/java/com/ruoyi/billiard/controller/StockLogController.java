package com.ruoyi.billiard.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
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
import com.ruoyi.billiard.domain.StockLog;
import com.ruoyi.billiard.service.IStockLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存日志Controller
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
@RestController
@RequestMapping("/billiard/log")
public class StockLogController extends BaseController
{
    @Autowired
    private IStockLogService stockLogService;

    /**
     * 查询库存日志列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:log:list')")
    @GetMapping("/list")
    public PageResVo<StockLog> list(StockLog stockLog)
    {
        startPage();
        List<StockLog> list = stockLogService.selectStockLogList(stockLog);
        return PageResVo.success(list);
    }

    /**
     * 导出库存日志列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:log:export')")
    @Log(title = "库存日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockLog stockLog)
    {
        List<StockLog> list = stockLogService.selectStockLogList(stockLog);
        ExcelUtil<StockLog> util = new ExcelUtil<StockLog>(StockLog.class);
        util.exportExcel(response, list, "库存日志数据");
    }

    /**
     * 获取库存日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:log:query')")
    @GetMapping(value = "/{stockLogId}")
    public ResultVo<StockLog> getInfo(@PathVariable("stockLogId") Long stockLogId)
    {
        return ResultVo.success(stockLogService.selectStockLogByStockLogId(stockLogId));
    }

    /**
     * 新增库存日志
     */
    @PreAuthorize("@ss.hasPermi('billiard:log:add')")
    @Log(title = "库存日志", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody StockLog stockLog)
    {
        return ResultVo.success(stockLogService.insertStockLog(stockLog));
    }



}
