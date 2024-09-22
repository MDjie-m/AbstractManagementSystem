package com.ruoyi.billiard.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.billiard.enums.OrderStatus;
import com.ruoyi.billiard.enums.OrderType;
import com.ruoyi.billiard.enums.OrderPayType;
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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.service.IOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;


/**
 * 订单Controller
 *
 * @author zhoukeu
 * @date 2024-09-13
 */
@RestController
@RequestMapping("/billiard/order")
public class OrderController extends BaseController
{
    @Autowired
    private IOrderService orderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:order:list')")
    @GetMapping("/list")
    public PageResVo<Order> list(Order order)
    {
        startPage();
        List<Order> list = orderService.selectOrderList(order);
        return PageResVo.success(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Order order)
    {
        List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:order:query')")
    @GetMapping(value = "/{orderId}")
    public ResultVo<Order> getInfo(@PathVariable("orderId") Long orderId)
    {
        return ResultVo.success(orderService.selectOrderByOrderId(orderId));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('billiard:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody Order order)
    {
        return ResultVo.success(orderService.insertOrder(order));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('billiard:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody Order order)
    {
        return ResultVo.success(orderService.updateOrder(order));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('billiard:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] orderIds)
    {
        return  ResultVo.success(orderService.deleteOrderByOrderIds(orderIds));
    }


    /**
     * 获取订单类型枚举
     */
    @GetMapping("/getsAnOrderTypeEnumeration")
    public  ResultVo<List<Map<String, Object>>> getsAnOrderTypeEnumeration()
    {
        List<Map<String, Object>> valueMap = OrderType.getValueMap();
        return ResultVo.success(valueMap);
    }

    /**
     * 获取支付方式枚举
     */
    @GetMapping("/getsAnEnumerationOfPaymentMethods")
    public  ResultVo<List<Map<String, Object>>> getsAnEnumerationOfPaymentMethods()
    {
        List<Map<String, Object>> valueMap = OrderPayType.getValueMap();
        return ResultVo.success(valueMap);
    }

    /**
     * 获取订单状态枚举
     */
    @GetMapping("/getsAnOrderStatusEnumeration")
    public  ResultVo<List<Map<String, Object>>> getsAnOrderStatusEnumeration()
    {
        List<Map<String, Object>> valueMap = OrderStatus.getValueMap();
        return ResultVo.success(valueMap);
    }

}
