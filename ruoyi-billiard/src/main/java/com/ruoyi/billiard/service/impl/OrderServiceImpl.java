package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.enums.CalcTimeStatus;
import com.ruoyi.billiard.enums.DeskStatus;
import com.ruoyi.billiard.enums.OrderStatus;
import com.ruoyi.billiard.enums.OrderType;
import com.ruoyi.billiard.mapper.OrderDeskTimeMapper;
import com.ruoyi.billiard.mapper.OrderTutorTimeMapper;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.billiard.service.*;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service业务层处理
 *
 * @author zhoukeu
 * @date 2024-09-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IOrderDeskTimeService deskTimeService;

    @Autowired
    private OrderDeskTimeMapper orderDeskTimeMapper;
    @Autowired
    private IDeskPriceService priceService;

    @Autowired
    private IOrderDeskTimeService orderDeskTimeService;

    @Autowired
    private IOrderGoodsService orderGoodsService;

    @Autowired
    private IOrderRechargeService orderRechargeService;

    @Autowired
    private IOrderTutorTimeService orderTutorTimeService;

    @Autowired
    private OrderTutorTimeMapper orderTutorTimeMapper;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private StoreDeskMapper storeDeskMapper;

    /**
     * 查询订单
     *
     * @param orderId 订单主键
     * @return 订单
     */
    @Override
    public Order selectOrderByOrderId(Long orderId) {
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

    @Override
    public Order selectLastActiveOrder(Long deskId) {

        Order order = orderMapper.selectLastOrderByDeskId(deskId);
        if (Objects.isNull(order)) {
            return null;
        }
        StoreDesk desk = storeDeskMapper.selectById(deskId);
        if (!Arrays.asList(DeskStatus.BUSY.getValue(), DeskStatus.PAUSE.getValue()).contains(desk.getStatus())) {
            return null;
        }
        if (!Arrays.asList(OrderStatus.CHARGING.getValue()).contains(order.getStatus())) {
            return null;
        }
        return selectOrderByOrderId(order.getOrderId());
    }

    /**
     * 查询订单列表
     *
     * @param order 订单
     * @return 订单
     */
    @Override
    public List<Order> selectOrderList(Order order) {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增订单
     *
     * @param order 订单
     * @return 结果
     */
    @Override
    public int insertOrder(Order order) {
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
    public int updateOrder(Order order) {
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
    public int deleteOrderByOrderIds(Long[] orderIds) {
        return orderMapper.deleteOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单信息
     *
     * @param orderId 订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderByOrderId(Long orderId) {
        return orderMapper.deleteOrderByOrderId(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(Long deskId) {
        StoreDesk desk = storeDeskMapper.selectById(deskId);
        Order order = new Order();
        order.setOrderId(IdUtils.singleNextId());
        order.setOrderNo(createOrderNum(order.getOrderId()));
        order.setOrderType(OrderType.TABLE_CHARGE.getValue());
        order.setStatus(OrderStatus.CHARGING.getValue());

        order.setStoreId(desk.getStoreId());
        order.setTotalAmountDue(BigDecimal.ZERO);
        SecurityUtils.fillCreateUser(order);
        orderMapper.insert(order);

        BigDecimal price = priceService.queryPriceByType(desk.getStoreId(), desk.getDeskType());
        AssertUtil.notNullOrEmpty(price, "未设置价格无法开台.请联系管理员设置价格.");

        OrderDeskTime deskTime = OrderDeskTime.builder()
                .deskId(deskId)
                .status(CalcTimeStatus.BUSY.getValue())
                .orderId(order.getOrderId())
                .price(price).startTime(new Date()).totalAmountDue(BigDecimal.ZERO).build();
        deskTimeService.insertOrderDeskTime(deskTime);

        order.setOrderDeskTimes(Arrays.asList(deskTime));
        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order swapToNewDesk(Long oldDeskId, Long orderId, Long newDeskId) {

        AssertUtil.isTrue(!Objects.equals(oldDeskId, newDeskId), "当前台桌和目标台桌不能一样.");
        Order order = selectLastActiveOrder(oldDeskId);
        AssertUtil.isTrue(Objects.nonNull(order) && Objects.equals(orderId, order.getOrderId()), "找不到相关订单");
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), order.getStatus()), "当前订单不是计费中,无法换台.");

        //更新上一桌计费时间
        List<OrderDeskTime> deskTimes = orderDeskTimeMapper.selectList(orderDeskTimeMapper.query().eq(OrderDeskTime::getDeskId, oldDeskId)
                .eq(OrderDeskTime::getOrderId, orderId));
        Date endTime = DateUtils.getNowDate();
        deskTimes.forEach(p -> {
            p.setEndTime(DateUtils.removeSeconds(endTime));
            SecurityUtils.fillUpdateUser(p);
        });
        orderDeskTimeMapper.updateBatch(deskTimes);

        StoreDesk oldDesk = storeDeskMapper.selectById(oldDeskId);
        StoreDesk newDesk = storeDeskMapper.selectById(newDeskId);

        //插入新的计费
        BigDecimal price = priceService.queryPriceByType(newDesk.getStoreId(), newDesk.getDeskType());
        AssertUtil.notNullOrEmpty(price, "目标台桌类型未设置价格无法换台.请联系管理员设置价格.");
        OrderDeskTime deskTime = OrderDeskTime.builder()
                .deskId(newDeskId)
                .orderId(order.getOrderId())
                .price(price)
                .status(CalcTimeStatus.BUSY.getValue())
                .startTime(endTime)
                .totalAmountDue(BigDecimal.ZERO).build();

        deskTimeService.insertOrderDeskTime(deskTime);

        orderTutorTimeMapper.selectList(orderTutorTimeMapper.query()
                        .eq(OrderTutorTime::getOrderId, order.getOrderId())
                        .eq(OrderTutorTime::getStatus, CalcTimeStatus.BUSY.getValue()))
                .forEach(item -> {
                    tutorSwapToNewDesk(newDeskId, item, endTime, order);
                });

        //更新状态
        oldDesk.setStatus(DeskStatus.WAIT.getValue());
        newDesk.setStatus(DeskStatus.BUSY.getValue());
        SecurityUtils.fillUpdateUser(oldDesk);
        SecurityUtils.fillUpdateUser(newDesk);
        storeDeskMapper.updateBatch(Arrays.asList(oldDesk, newDesk));


        return selectOrderByOrderId(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order pauseCalcFee(Long deskId) {

        OrderDeskTime time = orderDeskTimeMapper.selectOne(orderDeskTimeMapper.query().eq(OrderDeskTime::getDeskId, deskId)
                .isNull(OrderDeskTime::getEndTime).orderByDesc(OrderDeskTime::getStartTime)
                .eq(OrderDeskTime::getStatus, CalcTimeStatus.BUSY.getValue()).last(" limit 1"));
        AssertUtil.notNullOrEmpty(time, "台桌未在计费状态");
        Date now = DateUtils.getNowDate();
        time.setEndTime(now);
        time.setStatus(CalcTimeStatus.PAUSE.getValue());
        SecurityUtils.fillUpdateUser(time);
        orderDeskTimeMapper.updateById(time);

        orderTutorTimeMapper.update(null, orderTutorTimeMapper.updateWrapper()
                .eq(OrderTutorTime::getOrderId, time.getOrderId())
                .isNull(OrderTutorTime::getEndTime)
                .eq(OrderTutorTime::getStatus, CalcTimeStatus.BUSY.getValue())
                .set(OrderTutorTime::getEndTime, now)
                .set(OrderTutorTime::getStatus, CalcTimeStatus.PAUSE.getValue())
                .set(OrderTutorTime::getUpdateById, time.getUpdateById())
                .set(BaseEntity::getUpdateBy, time.getUpdateBy())
                .set(BaseEntity::getUpdateTime, now));

        return selectLastActiveOrder(time.getOrderId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order resumeCalcFee(Long deskId) {
        StoreDesk desk = storeDeskMapper.selectById(deskId);
        Order order = orderMapper.selectLastOrderByDeskId(deskId);
        AssertUtil.notNullOrEmpty(order, "未找到相关订单");
        AssertUtil.isTrue(Objects.equals(order.getStatus(), OrderStatus.CHARGING.getValue()), "订单状态不正确");
        final Date now = DateUtils.getNowDate();
        orderDeskTimeMapper.selectList(orderDeskTimeMapper.query()
                        .eq(OrderDeskTime::getOrderId, order.getOrderId())
                        .eq(OrderDeskTime::getStatus, CalcTimeStatus.PAUSE.getValue()))
                .forEach(item -> {
                    item.setStatus(CalcTimeStatus.STOP.getValue());
                    SecurityUtils.fillUpdateUser(item);
                    orderDeskTimeMapper.updateById(item);

                    OrderDeskTime deskTime = OrderDeskTime.builder()
                            .deskId(desk.getDeskId())
                            .orderId(order.getOrderId())
                            .price(item.getPrice())
                            .startTime(now)
                            .status(CalcTimeStatus.BUSY.getValue())
                            .totalAmountDue(BigDecimal.ZERO).build();
                    SecurityUtils.fillCreateUser(deskTime);
                    orderDeskTimeMapper.insert(deskTime);
                });


        orderTutorTimeMapper.selectList(orderTutorTimeMapper.query()
                        .eq(OrderTutorTime::getOrderId, order.getOrderId())
                        .eq(OrderTutorTime::getStatus, CalcTimeStatus.PAUSE.getValue()))
                .forEach(item -> {
                    tutorSwapToNewDesk(deskId, item, now, order);
                });
        return selectLastActiveOrder(deskId);
    }

    private void tutorSwapToNewDesk(Long deskId, OrderTutorTime item, Date now, Order order) {
        item.setStatus(CalcTimeStatus.STOP.getValue());
        SecurityUtils.fillUpdateUser(item);
        if(Objects.isNull(item.getEndTime())){
            item.setEndTime(now);
        }
        orderTutorTimeMapper.updateById(item);
        OrderTutorTime newItem = new OrderTutorTime();
        newItem.setOrderTutorTimeId(IdUtils.singleNextId());
        newItem.setEndTime(null);
        newItem.setType(item.getType());
        newItem.setTotalAmount(BigDecimal.ZERO);
        newItem.setStartTime(now);
        newItem.setTotalAmount(BigDecimal.ZERO);
        newItem.setTutorId(item.getTutorId());
        newItem.setOrderId(order.getOrderId());
        newItem.setDeskId(deskId);
        newItem.setPrice(item.getPrice());
        newItem.setOrderTutorTimeId(IdUtils.singleNextId());
        newItem.setStatus(CalcTimeStatus.BUSY.getValue());
        SecurityUtils.fillCreateUser(newItem);
        orderTutorTimeMapper.insert(newItem);
    }

    private String createOrderNum(Long id) {
        return String.format("DESK%s", id);
    }
}
