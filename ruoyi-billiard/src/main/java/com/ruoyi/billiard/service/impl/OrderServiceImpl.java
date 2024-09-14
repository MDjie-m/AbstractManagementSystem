package com.ruoyi.billiard.service.impl;

import java.util.List;
import java.util.Optional;

import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.service.*;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderMapper;

/**
 * 订单Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IOrderDeskTimeService orderDeskTimeService;

    @Autowired
    private IOrderGoodsService orderGoodsService;

    @Autowired
    private IOrderRechargeService orderRechargeService;

    @Autowired
    private IOrderTutorTimeService orderTutorTimeService;

    @Autowired
    private IMemberService memberService;

    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    @Override
    public Order selectOrderByOrderId(Long orderId)
    {
        Order order = orderMapper.selectById(orderId);
        // 获取会员信息
        Long memberId = order.getMemberId();
        Member member = Optional.ofNullable(memberService.selectMemberByMemberId(memberId)).orElse(new Member());
        order.setMember(member);

        // 查询球桌计时列表
        List<OrderDeskTime> orderDeskTimeList = orderDeskTimeService.selectOrderDeskTimeListByOrderId(orderId);
        order.setOrderDeskTimes(orderDeskTimeList);
        // 查询订单商品列表
        List<OrderGoods> orderGoodsList = orderGoodsService.selectOrderGoodsListByOrderId(orderId);
        order.setOrderGoods(orderGoodsList);
        // 查询订单会员充值列表
        List<OrderRecharge> orderRechargeList = orderRechargeService.selectOrderRechargeListByOrderId(orderId);
        order.setOrderRecharges(orderRechargeList);
        // 查询订单教练计时列表
        List<OrderTutorTime> orderTutorTimeList = orderTutorTimeService.selectOrderTutorTimeListByOrderId(orderId);
        order.setOrderTutorTimes(orderTutorTimeList);
        return order;
    }

    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增订单
     * 
     * @param order 订单
     * @return 结果
     */
    @Override
    public int insertOrder(Order order)
    {
        SecurityUtils.fillCreateUser(order);
        order.setOrderId(IdUtils.singleNextId());
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改订单
     * 
     * @param order 订单
     * @return 结果
     */
    @Override
    public int updateOrder(Order order)
    {
        SecurityUtils.fillUpdateUser(order);

        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderByOrderIds(Long[] orderIds)
    {
        return orderMapper.deleteOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单信息
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderByOrderId(Long orderId)
    {
        return orderMapper.deleteOrderByOrderId(orderId);
    }
}
