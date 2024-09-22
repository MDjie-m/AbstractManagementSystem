package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.Member;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.domain.vo.*;
import com.ruoyi.billiard.enums.OrderStatus;
import com.ruoyi.billiard.service.IMemberService;
import com.ruoyi.billiard.service.IOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("cashier/order")
@Slf4j
public class CashierOrderController extends BaseController {

    @Resource
    private IOrderService orderService;

    @PreAuthorize("@ss.hasPermi('cashier:order:list')")
    @GetMapping("/list")
    public PageResVo<Order> orderList(@Validated Order reqVo) {
        reqVo.setStoreId(getStoreIdWithThrow());
        startPage();
        List<Order> res = orderService.selectOrderList(reqVo);
        return PageResVo.success(res);
    }

    @PreAuthorize("@ss.hasPermi('cashier:order:list')")
    @GetMapping("/{orderId}")
    public ResultVo<Order> getOrderInfo(@PathVariable Long orderId) {
        Order res = orderService.selectOrderByOrderId(orderId);
        if (Objects.equals(OrderStatus.CHARGING.getValue(), res.getStatus())) {
            orderService.calOrderFees(res);
        }
        return ResultVo.success(res);
    }

    @PreAuthorize("@ss.hasPermi('cashier:order:list')")
    @PostMapping("/{orderId}/member")
    public ResultVo<Boolean> fillMember(@PathVariable Long orderId, @RequestParam(required = false) Long memberId) {

        return ResultVo.success(orderService.fillMember(orderId, memberId));
    }

    @PreAuthorize("@ss.hasPermi('cashier:order:list')")
    @PostMapping("/finish")
    public ResultVo<Boolean> finishOrder(@RequestBody @Validated FinishOrderReqVo reqVo) {
        reqVo.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(orderService.finishOrder(reqVo));
    }

    @PreAuthorize("@ss.hasRole('cashier')")
    @PostMapping("/buy")
    public ResultVo<Long> buy(@Validated(IBuy.class) @RequestBody Order reqVo) {
        reqVo.setStoreId(getStoreIdWithThrow());
        Long res = orderService.orderShopping(reqVo);
        return ResultVo.success(res);
    }


    /**
     * 订单挂起
     *
     * @param orderId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{orderId}/suspend")
    public ResultVo<OrderCommandResVo> suspendOrder(@PathVariable Long orderId) {
        OrderCommandResVo res = orderService.suspendOrder(orderId, getStoreIdWithThrow());
        return ResultVo.success(res);
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/pre-pay")
    public ResultVo<BigDecimal> suspendOrder(@RequestBody @Validated OrderPrePayReqVo reqVo) {
        reqVo.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(orderService.prePayAmount(reqVo));
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{orderId}/stop")
    public ResultVo<OrderCommandResVo> stopOrder(@PathVariable Long orderId) {
        OrderCommandResVo res = orderService.stopOrder(orderId, getStoreIdWithThrow(), false);
        return ResultVo.success(res);
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{orderId}/stop-by-timer")
    public ResultVo<Boolean> stopOrderByTimer(@PathVariable Long orderId) {
        try {
            orderService.stopOrder(orderId, getStoreIdWithThrow(), true);
        } catch (Exception e) {
            log.error("定时器停止订单失败,orderId:{}", orderId, e);
        }
        return ResultVo.success(true);
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{orderId}/stop-desk")
    public ResultVo<StopDeskResVo> stopDesk(@PathVariable Long orderId, @RequestParam Long deskId) {
        return ResultVo.success(orderService.stopDesk(orderId, getStoreIdWithThrow(), deskId));
    }

    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{orderId}/void")
    public ResultVo<Boolean> voidOrder(@PathVariable Long orderId, String remark) {
        Boolean res = orderService.voidOrder(orderId, getStoreIdWithThrow(), remark);
        return ResultVo.success(res);
    }
}
