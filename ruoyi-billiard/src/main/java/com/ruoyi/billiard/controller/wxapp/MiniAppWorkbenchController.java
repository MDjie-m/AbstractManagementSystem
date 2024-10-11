package com.ruoyi.billiard.controller.wxapp;

import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.dto.HomeReportDto;
import com.ruoyi.billiard.domain.dto.OrderTypeDetailDto;
import com.ruoyi.billiard.domain.vo.HomeReportVo;
import com.ruoyi.billiard.domain.vo.StockCheckRes;
import com.ruoyi.billiard.domain.vo.miniappdomain.HomeReportVoConsume;
import com.ruoyi.billiard.domain.vo.miniappdomain.HomeReportVoConsumeDetail;
import com.ruoyi.billiard.enums.OrderType;
import com.ruoyi.billiard.service.IGoodsService;
import com.ruoyi.billiard.service.IOrderService;
import com.ruoyi.billiard.service.IStockLogService;
import com.ruoyi.billiard.service.IStockService;
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
import java.util.Objects;

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
    private IGoodsService goodsService;
    @Autowired
    private IStockLogService stockLogService;


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

        OrderType orderType = dto.getOrderType();
        if (Objects.equals(orderType, OrderType.AGGREGATE_CONSUMPTION)) {
            List<Order> consumeDetail = (List<Order>) consume.getTotal().getConsumeDetail();
            consumeDetail.forEach(item -> {
                if (Objects.nonNull(item.getOrderType())) {
                    item.setOrderTypeText(item.getOrderType().getDesc());
                }
                if (Objects.nonNull(item.getPayType())) {
                    item.setPayTypeText(item.getPayType().getDesc());
                }
            });
            ExcelUtil<Order> util = new ExcelUtil<>(Order.class);
            util.exportExcel(response, consumeDetail, "订单总消费明细");
        }
        if (Objects.equals(orderType, OrderType.TABLE_CHARGE)) {
            HomeReportVoConsumeDetail reportVoConsumeDetail = consume.getTypeList()
                    .stream()
                    .filter(item -> Objects.equals(item.getConsumeName(), OrderType.TABLE_CHARGE.getDesc()))
                    .findFirst().orElse(null);
            List<OrderDeskTime> consumeDetail = (List<OrderDeskTime>) reportVoConsumeDetail.getConsumeDetail();
            AssertUtil.notNullOrEmpty(consumeDetail, "未找到订单球桌费用数据");
            ExcelUtil<OrderDeskTime> util = new ExcelUtil<>(OrderDeskTime.class);
            util.exportExcel(response, consumeDetail, "订单球桌费用明细");
        }
        if (Objects.equals(orderType, OrderType.COMMODITY_PURCHASE)) {
            HomeReportVoConsumeDetail reportVoConsumeDetail = consume.getTypeList()
                    .stream()
                    .filter(item -> Objects.equals(item.getConsumeName(), OrderType.COMMODITY_PURCHASE.getDesc()))
                    .findFirst().orElse(null);
            List<OrderGoods> consumeDetail = (List<OrderGoods>) reportVoConsumeDetail.getConsumeDetail();
            AssertUtil.notNullOrEmpty(consumeDetail, "未找到订单商品消费费用数据");
            ExcelUtil<OrderGoods> util = new ExcelUtil<>(OrderGoods.class);
            util.exportExcel(response, consumeDetail, "订单商品消费明细");
        }
        if (Objects.equals(orderType, OrderType.MEMBER_RECHARGE)) {
            HomeReportVoConsumeDetail reportVoConsumeDetail = consume.getTypeList()
                    .stream()
                    .filter(item -> Objects.equals(item.getConsumeName(), OrderType.MEMBER_RECHARGE.getDesc()))
                    .findFirst().orElse(null);
            List<OrderRecharge> consumeDetail = (List<OrderRecharge>) reportVoConsumeDetail.getConsumeDetail();
            AssertUtil.notNullOrEmpty(consumeDetail, "未找到订单会员充值费用数据");
            ExcelUtil<OrderRecharge> util = new ExcelUtil<>(OrderRecharge.class);
            util.exportExcel(response, consumeDetail, "订单会员充值费用明细");
        }
        if (Objects.equals(orderType, OrderType.TEACHING_ASSISTANT_FEE)) {
            HomeReportVoConsumeDetail reportVoConsumeDetail = consume.getTypeList()
                    .stream()
                    .filter(item -> Objects.equals(item.getConsumeName(), OrderType.TEACHING_ASSISTANT_FEE.getDesc()))
                    .findFirst().orElse(null);
            List<OrderTutorTime> consumeDetail = (List<OrderTutorTime>) reportVoConsumeDetail.getConsumeDetail();
            AssertUtil.notNullOrEmpty(consumeDetail, "未找到订单助教费用数据");
            ExcelUtil<OrderTutorTime> util = new ExcelUtil<>(OrderTutorTime.class);
            util.exportExcel(response, consumeDetail, "订单助教费用明细");
        }

    }

    /**
     * 查询库存列表
     */
    @PreAuthorize("@ss.hasPermi('miniapp:stock:list')")
    @GetMapping("/stockList")
    public PageResVo<Stock> stockList(@RequestBody Stock stock) {
        List<Stock> list = stockService.selectStockList(stock);
        return PageResVo.success(list);
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
        AssertUtil.notNullOrEmpty(req.getStockId(),"门店不能为空");
        return ResultVo.success(stockService.editStock(req));
    }
//    监控 miniapp:monitor:list
}
