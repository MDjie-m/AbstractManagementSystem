package com.ruoyi.billiard.controller.cashier;

import com.ruoyi.billiard.domain.vo.DeskQueryResVo;
import com.ruoyi.billiard.domain.vo.OrderCommandResVo;
import com.ruoyi.billiard.service.IOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("cashier/order")
public class CashierOrderController extends BaseController {

    @Resource
    private IOrderService orderService;

    /**
     * 订单挂起
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
    @PostMapping("/{orderId}/stop")
    public ResultVo<OrderCommandResVo> stopOrder(@PathVariable Long orderId) {
        OrderCommandResVo res = orderService.stopOrder(orderId, getStoreIdWithThrow());
        return ResultVo.success(res);
    }
    @PreAuthorize("@ss.hasPermi('cashier:desk:list')")
    @PostMapping("/{orderId}/void")
    public ResultVo<Boolean> voidOrder(@PathVariable Long orderId,String remark) {
        Boolean res = orderService.voidOrder(orderId, getStoreIdWithThrow(),remark);
        return ResultVo.success(res);
    }
}
