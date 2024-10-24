package com.ruoyi.billiard.controller.wxapp;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.dto.HomeReportDto;
import com.ruoyi.billiard.domain.dto.OrderTypeDetailDto;
import com.ruoyi.billiard.domain.vo.HomeReportVo;
import com.ruoyi.billiard.domain.vo.StockCheckRes;
import com.ruoyi.billiard.domain.vo.YingShiYunVo;
import com.ruoyi.billiard.domain.vo.miniappdomain.HomeReportVoConsume;
import com.ruoyi.billiard.domain.vo.miniappdomain.HomeReportVoConsumeDetail;
import com.ruoyi.billiard.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-20:42
 * @className: MiniAppWorkbenchController 小程序工作台接口
 */
@RestController
@RequestMapping("api/mini-app/workbench")
public class MiniAppWorkbenchController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IStockService stockService;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IStoreService storeService;


    /**
     * 获取门店列表
     */
    @PreAuthorize("@ss.hasPermi('miniapp:index:query')")
    @GetMapping("/getStoreList")
    public ResultVo<List<Store>> getStoreList() {
        return ResultVo.success(storeService.findAListOfStoresByRole());
    }

    /**
     * 获取报表页面数据
     *
     * @param dto
     * @return ResultVo
     */
    @PreAuthorize("@ss.hasPermi('miniapp:index:query')")
    @PostMapping("/homeReport")
    public ResultVo<HomeReportVo> getReportData(@RequestBody HomeReportDto dto) {
        return ResultVo.success(orderService.selectOrderData2Report(dto));
    }

    /**
     * 分页查询报表类型数据列表
     */
    @PreAuthorize("@ss.hasPermi('miniapp:index:query')")
    @PostMapping("/homeReport/selectOrderTypeList")
    public PageResVo<?> selectOrderTypeList(@RequestBody HomeReportDto dto) {
        startPage();
        List<?> list = orderService.selectOrderTypeList(dto);
        return PageResVo.success(list);
    }

    /**
     * 导出报表页面消费数据
     *
     * @param response, dto
     */
    @PreAuthorize("@ss.hasPermi('miniapp:index:query')")
    @Log(title = "导出报表页面消费数据", businessType = BusinessType.EXPORT)
    @PostMapping("/homeReport/exportConsume")
    public void exportConsume(HttpServletResponse response, @RequestBody HomeReportDto dto) {
        HomeReportVo homeReportVo = orderService.selectOrderData2Report(dto);
        HomeReportVoConsume consume = homeReportVo.getConsume();

        List<HomeReportVoConsumeDetail> list = Lists.newArrayList();
        list.add(consume.getTotal());
        list.addAll(consume.getTypeList());
        ExcelUtil<HomeReportVoConsumeDetail> homeReportVoConsumeDetailExcelList = new ExcelUtil<>(HomeReportVoConsumeDetail.class);
        homeReportVoConsumeDetailExcelList.exportExcel(response, list, "消费统计");

    }

    /**
     * 根据订单类型获取报表流水明细订单详情
     */
    @PreAuthorize("@ss.hasPermi('miniapp:index:query')")
    @PostMapping("/homeReport/selectOrderTypeDetail")
    public ResultVo<?> selectOrderTypeDetail(@RequestBody OrderTypeDetailDto dto) {

        return ResultVo.success(orderService.selectOrderTypeDetail(dto));
    }
    /**
     * 获取报表流水数据各类型详情
     *
     * @param response, dto
     */
    @PreAuthorize("@ss.hasPermi('miniapp:index:query')")
    @Log(title = "报表流水分类数据详情导出", businessType = BusinessType.EXPORT)
    @PostMapping("/homeReport/exportConsumeDetail")
    public void exportConsumeDetail(HttpServletResponse response, @RequestBody HomeReportDto dto) {
        HomeReportVo homeReportVo = orderService.selectOrderData2Report(dto);
        HomeReportVoConsume consume = homeReportVo.getConsume();
        orderService.exportConsumeDetail(response, consume, dto);
    }

    /**
     * 查询库存列表
     */
    @PreAuthorize("@ss.hasPermi('miniapp:stock:list')")
    @GetMapping("/stockList/{storeId}")
    public ResultVo<List<Stock>> stockList(@PathVariable("storeId") Long storeId) {
        Stock stock = new Stock();
        stock.setStoreId(storeId);
        List<Stock> list = stockService.selectStockList(stock);
        return ResultVo.success(list);
    }

    @PreAuthorize("@ss.hasPermi('miniapp:stock:list')")
    @Log(title = "库存盘点", businessType = BusinessType.UPDATE)
    @PostMapping("/stockCheck")
    public ResultVo<StockCheckRes> editStock(@RequestBody @Validated @Valid List<StockLog> req) {

        return ResultVo.success(stockService.checkStockWithDetail(req));
    }

    /**
     * 修改库存
     */
    @PreAuthorize("@ss.hasPermi('miniapp:outorin:list')")
    @Log(title = "库存维护", businessType = BusinessType.UPDATE)
    @PostMapping("/stockChange")
    public ResultVo<Integer> editStock(@RequestBody @Validated StockLog req) {
        AssertUtil.isNullOrEmpty(req.getStockId(),"门店不能为空");
        return ResultVo.success(stockService.editStock(req));
    }

    /**
     * 查询监控列表
     */
    @PreAuthorize("@ss.hasPermi('miniapp:monitor:list')")
    @GetMapping("/monitorList")
    public ResultVo<YingShiYunVo> monitorList() {
        YingShiYunVo yingShiYunVo = deviceService.selectDeviceStoreList();
        logger.info("yingShiYunVo:{}", JSON.toJSONString(yingShiYunVo));
        return ResultVo.success(yingShiYunVo);
    }
}
