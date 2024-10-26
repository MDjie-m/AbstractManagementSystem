package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.enums.OrderStatus;
import com.ruoyi.billiard.enums.OrderType;
import com.ruoyi.billiard.mapper.*;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.service.IOrderTotalService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 订单结算Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-26
 */
@Service
public class OrderTotalServiceImpl extends ServiceImpl<OrderTotalMapper, OrderTotal> implements IOrderTotalService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDeskTimeMapper orderDeskTimeMapper;

    @Resource
    private OrderTutorTimeMapper orderTutorTimeMapper;

    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Resource
    private OrderRechargeMapper orderRechargeMapper;


    /**
     * 查询订单结算
     *
     * @param orderTotalId 订单结算主键
     * @return 订单结算
     */
    @Override
    public OrderTotal selectOrderTotalByOrderTotalId(Long orderTotalId) {
        return baseMapper.selectById(orderTotalId);
    }

    /**
     * 查询订单结算列表
     *
     * @param orderTotal 订单结算
     * @return 订单结算
     */
    @Override
    public List<OrderTotal> selectOrderTotalList(OrderTotal orderTotal) {
        return baseMapper.selectOrderTotalList(orderTotal);
    }

    /**
     * 新增订单结算
     *
     * @param orderTotal 订单结算
     * @return 结果
     */
    @Override
    public int insertOrderTotal(OrderTotal orderTotal) {
        SecurityUtils.fillCreateUser(orderTotal);
        orderTotal.setOrderTotalId(IdUtils.singleNextId());
        return baseMapper.insert(orderTotal);
    }

    /**
     * 修改订单结算
     *
     * @param orderTotal 订单结算
     * @return 结果
     */
    @Override
    public int updateOrderTotal(OrderTotal orderTotal) {
        SecurityUtils.fillUpdateUser(orderTotal);

        return baseMapper.updateById(orderTotal);
    }

    /**
     * 批量删除订单结算
     *
     * @param orderTotalIds 需要删除的订单结算主键
     * @return 结果
     */
    @Override
    public int deleteOrderTotalByOrderTotalIds(Long[] orderTotalIds) {
        return baseMapper.deleteOrderTotalByOrderTotalIds(orderTotalIds);
    }

    /**
     * 删除订单结算信息
     *
     * @param orderTotalId 订单结算主键
     * @return 结果
     */
    @Override
    public int deleteOrderTotalByOrderTotalId(Long orderTotalId) {
        return baseMapper.deleteOrderTotalByOrderTotalId(orderTotalId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void calcTotal(Long orderId) {

        Order order = orderMapper.selectById(orderId);
        if (Objects.isNull(order) || !Objects.equals(OrderStatus.SETTLED.getValue(), order.getStatus())) {
            return;
        }
        baseMapper.delete(baseMapper.query().eq(OrderTotal::getOrderId, orderId));
        saveMaster( order);

        saveGoodsFees(order);

        saveDeskFees(order);

        saveTutorFees(order);

        saveRecharge(order);
    }

    private void saveMaster(  Order order) {
        OrderTotal orderTotal = new OrderTotal();
        orderTotal.setOrderId(order.getOrderId());
        orderTotal.setOrderNo(order.getOrderNo());
        orderTotal.setTotalAmount(order.getTotalAmount());
        orderTotal.setPayType(order.getPayType());
        orderTotal.setStoreId(order.getStoreId());
        orderTotal.setTotalAmountDue(order.getTotalAmountDue());
        orderTotal.setDiscountAmount(order.getTotalDiscountAmount());
        orderTotal.setOrderDate(order.getCreateTime());
        orderTotal.setOrderType(OrderType.AGGREGATE_CONSUMPTION);
        orderTotal.setUnitCount(0L);
        insertOrderTotal(orderTotal);
    }

    private void saveGoodsFees(Order order) {
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectOrderGoodsList(OrderGoods.builder().orderId(order.getOrderId()).build());
        if (CollectionUtils.isEmpty(orderGoodsList)) {
            return;

        }
        OrderTotal itemTotal = new OrderTotal();
        itemTotal.setOrderType(OrderType.COMMODITY_PURCHASE);
        itemTotal.setOrderId(order.getOrderId());
        itemTotal.setOrderNo(order.getOrderNo());

        itemTotal.setPayType(order.getPayType());
        itemTotal.setStoreId(order.getStoreId());
        itemTotal.setOrderDate(order.getCreateTime());
        itemTotal.setTotalAmount(orderGoodsList.stream().map(OrderGoods::getTotalAmount).reduce(new BigDecimal("0.00"), BigDecimal::add));
        itemTotal.setTotalAmountDue(orderGoodsList.stream().map(OrderGoods::getTotalAmountDue).reduce(new BigDecimal("0.00"),BigDecimal::add) );
        itemTotal.setDiscountAmount(orderGoodsList.stream().map(OrderGoods::getTotalDiscountAmount).reduce(new BigDecimal("0.00"),BigDecimal::add));
        itemTotal.setUnitCount(Long.valueOf(orderGoodsList.stream().map(OrderGoods::getNum).reduce(Integer::sum).orElse(0)));
        insertOrderTotal(itemTotal);
    }

    private void saveTutorFees(Order order) {
        List<OrderTutorTime> tutorTimes = orderTutorTimeMapper.selectOrderTutorTimeList(OrderTutorTime.builder().orderId(order.getOrderId()).build());
        if (CollectionUtils.isEmpty(tutorTimes)) {
            return;
        }
        OrderTotal itemTotal = new OrderTotal();
        itemTotal.setOrderType(OrderType.TEACHING_ASSISTANT_FEE);
        itemTotal.setOrderId(order.getOrderId());
        itemTotal.setOrderNo(order.getOrderNo());
        itemTotal.setPayType(order.getPayType());
        itemTotal.setStoreId(order.getStoreId());
        itemTotal.setOrderDate(order.getCreateTime());
        itemTotal.setTotalAmount(tutorTimes.stream().map(OrderTutorTime::getTotalAmount).reduce(new BigDecimal("0.00"),BigDecimal::add));
        itemTotal.setTotalAmountDue(tutorTimes.stream().map(OrderTutorTime::getTotalAmountDue).reduce(new BigDecimal("0.00"),BigDecimal::add));
        itemTotal.setDiscountAmount(tutorTimes.stream().map(OrderTutorTime::getTotalDiscountAmount).reduce(new BigDecimal("0.00"),BigDecimal::add));
        itemTotal.setUnitCount(Long.valueOf(tutorTimes.stream().map(OrderTutorTime::getNum).reduce(Integer::sum).orElse(0)));
        insertOrderTotal(itemTotal);
        List<OrderType> types = Arrays.asList(OrderType.TEACHING_COST, OrderType.SPARRING_FEE);
        for (OrderType type : types) {

            List<OrderTutorTime> subItems = tutorTimes.stream().filter(p -> Objects.equals(p.getType(), type.getValue())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(subItems)) {
                continue;
            }
            OrderTotal subItem = new OrderTotal();
            subItem.setOrderType(type);
            subItem.setOrderId(order.getOrderId());
            subItem.setOrderNo(order.getOrderNo());
            subItem.setPayType(order.getPayType());
            subItem.setStoreId(order.getStoreId());
            subItem.setOrderDate(order.getCreateTime());
            subItem.setTotalAmount(subItems.stream().map(OrderTutorTime::getTotalAmount).reduce(new BigDecimal("0.00"),BigDecimal::add));
            subItem.setTotalAmountDue(subItems.stream().map(OrderTutorTime::getTotalAmountDue).reduce(new BigDecimal("0.00"),BigDecimal::add));
            subItem.setDiscountAmount(subItems.stream().map(OrderTutorTime::getTotalDiscountAmount).reduce(new BigDecimal("0.00"),BigDecimal::add));
            subItem.setUnitCount(Long.valueOf(subItems.stream().map(OrderTutorTime::getNum).reduce(Integer::sum).orElse(0)));
            insertOrderTotal(subItem);


        }
    }

    private void saveDeskFees(Order order) {
        List<OrderDeskTime> deskTimes = orderDeskTimeMapper.selectOrderDeskTimeList(OrderDeskTime.builder().orderId(order.getOrderId()).build());
        if (CollectionUtils.isNotEmpty(deskTimes)) {
            OrderTotal itemTotal = new OrderTotal();
            itemTotal.setOrderType(OrderType.TABLE_CHARGE);
            itemTotal.setOrderId(order.getOrderId());
            itemTotal.setUnitCount((long) deskTimes.stream().map(OrderDeskTime::getDeskId).collect(Collectors.toSet()).size());
            itemTotal.setOrderNo(order.getOrderNo());
            itemTotal.setPayType(order.getPayType());
            itemTotal.setStoreId(order.getStoreId());
            itemTotal.setOrderDate(order.getCreateTime());
            itemTotal.setTotalAmount(deskTimes.stream().map(OrderDeskTime::getTotalAmount).reduce(new BigDecimal("0.00"),BigDecimal::add));
            itemTotal.setTotalAmountDue(deskTimes.stream().map(OrderDeskTime::getTotalAmountDue).reduce(new BigDecimal("0.00"),BigDecimal::add));
            itemTotal.setDiscountAmount(deskTimes.stream().map(OrderDeskTime::getTotalDiscountAmount).reduce(new BigDecimal("0.00"),BigDecimal::add));
            itemTotal.setUnitCount(Long.valueOf(deskTimes.stream().map(OrderDeskTime::getNum).reduce(Integer::sum).orElse(0)));
            insertOrderTotal(itemTotal);
        }
    }

    private void saveRecharge(Order order) {
        List<OrderRecharge> recharges = orderRechargeMapper.selectOrderRechargeList(OrderRecharge.builder().orderId(order.getOrderId()).build());
        if (CollectionUtils.isEmpty(recharges)) {
            return;
        }
        OrderTotal itemTotal = new OrderTotal();
        itemTotal.setOrderType(OrderType.MEMBER_RECHARGE);
        itemTotal.setOrderId(order.getOrderId());
        itemTotal.setOrderNo(order.getOrderNo());
        itemTotal.setPayType(order.getPayType());
        itemTotal.setStoreId(order.getStoreId());
        itemTotal.setOrderDate(order.getCreateTime());
        itemTotal.setTotalAmount(recharges.stream().map(OrderRecharge::getTotalAmount).reduce(new BigDecimal("0.00"),BigDecimal::add));
        itemTotal.setTotalAmountDue(recharges.stream().map(OrderRecharge::getTotalAmountDue).reduce(new BigDecimal("0.00"),BigDecimal::add));
        itemTotal.setDiscountAmount(recharges.stream().map(OrderRecharge::getTotalDiscountAmount).reduce(new BigDecimal("0.00"),BigDecimal::add));
        itemTotal.setUnitCount(Long.valueOf(recharges.stream().map(OrderRecharge::getNum).reduce(Integer::sum).orElse(0)));
        insertOrderTotal(itemTotal);
    }


}
