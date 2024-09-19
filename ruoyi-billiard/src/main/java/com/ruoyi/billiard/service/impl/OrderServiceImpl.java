package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.constant.OrderErrorMsg;
import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.vo.OrderCommandResVo;
import com.ruoyi.billiard.domain.vo.OrderPrePayReqVo;
import com.ruoyi.billiard.enums.CalcTimeStatus;
import com.ruoyi.billiard.enums.DeskStatus;
import com.ruoyi.billiard.enums.OrderStatus;
import com.ruoyi.billiard.enums.OrderType;
import com.ruoyi.billiard.mapper.*;
import com.ruoyi.billiard.service.*;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.MyBaseEntity;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private OrderGoodsMapper orderGoodsMapper;

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

    @Autowired
    private IStoreService storeService;

    @Autowired
    private LightTimerMapper lightTimerMapper;

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
    public Order selectRelationOrderWithDetail(Long deskId) {

        Order order = orderMapper.selectCurrentRelationOrder(deskId);
        if (Objects.isNull(order)) {
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
        List<Order> orders = Optional.ofNullable(orderMapper.selectOrderList(order)).orElse(Collections.emptyList());
        orders.forEach(p -> p.setStoreName(storeService.selectStoreByStoreId(p.getStoreId()).getStoreName()));
        return orders;
    }

    /**
     * 新增订单
     *
     * @param order 订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrder(Order order) {
        SecurityUtils.fillCreateUser(order);
        order.setOrderId(IdUtils.singleNextId());
        return orderMapper.insert(order);
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOrder(Order order) {
        SecurityUtils.fillUpdateUser(order);

        return orderMapper.updateById(order);
    }

    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的订单主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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

        lightTimerMapper.delete(lightTimerMapper.query().eq(LightTimer::getDeskId, deskId));
        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order swapToNewDesk(Long oldDeskId, Long orderId, Long newDeskId) {

        AssertUtil.isTrue(!Objects.equals(oldDeskId, newDeskId), "当前台桌和目标台桌不能一样.");
        Order order = orderMapper.selectOrderByOrderId(orderId);
        AssertUtil.isTrue(Objects.nonNull(order) && Objects.equals(orderId, order.getOrderId()), "找不到相关订单");
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), order.getStatus()), "当前订单不是计费中,无法换台.");

        lightTimerMapper.delete(lightTimerMapper.query().eq(LightTimer::getDeskId, oldDeskId));

        //更新上一桌计费时间
        List<OrderDeskTime> deskTimes = orderDeskTimeMapper.selectList(orderDeskTimeMapper.query().eq(OrderDeskTime::getDeskId, oldDeskId)
                .eq(OrderDeskTime::getOrderId, orderId));
        Date endTime = DateUtils.removeSeconds(DateUtils.getNowDate());
        deskTimes.forEach(p -> {
            p.setEndTime(endTime);
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
        oldDesk.setCurrentOrderId(null);

        newDesk.setStatus(DeskStatus.BUSY.getValue());
        newDesk.setCurrentOrderId(orderId);

        SecurityUtils.fillUpdateUser(oldDesk);
        SecurityUtils.fillUpdateUser(newDesk);
        storeDeskMapper.updateAllWithId(oldDesk);
        storeDeskMapper.updateAllWithId(newDesk);


        return selectOrderByOrderId(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order mergeToNewDesk(Long oldDeskId, Long oldOrderId, Long newDeskId) {
        AssertUtil.isTrue(!Objects.equals(oldDeskId, newDeskId), "当前台桌和目标台桌不能一样.");
        Order order = orderMapper.selectOrderByOrderId(oldOrderId);
        AssertUtil.isTrue(Objects.nonNull(order) && Objects.equals(oldOrderId, order.getOrderId()), "找不到相关订单");
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), order.getStatus()), "当前订单不是计费中,无法并台.");

        Order newOrder = orderMapper.selectCurrentRelationOrder(newDeskId);
        AssertUtil.isTrue(Objects.nonNull(newOrder), "找不新台桌到相关订单");
        AssertUtil.isTrue(!Objects.equals(oldOrderId, newOrder.getOrderId()), "当前台桌与目标台桌已经并台，不用重复操作");

        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), newOrder.getStatus()), "当前订单不是计费中,无法并台.");


        lightTimerMapper.delete(lightTimerMapper.query().eq(LightTimer::getDeskId, oldDeskId));

        copyFeesToNewOrder(oldOrderId, newOrder.getOrderId());

        stopOrder(oldOrderId, order.getStoreId(), false);

        //更新状态
        StoreDesk oldDesk = storeDeskMapper.selectById(oldDeskId);
        oldDesk.setStatus(DeskStatus.WAIT.getValue());
        oldDesk.setCurrentOrderId(newOrder.getOrderId());
        storeDeskMapper.updateById(oldDesk);

        return selectOrderByOrderId(newOrder.getOrderId());
    }

    private void copyFeesToNewOrder(Long oldOrderId, Long newOrderId) {
        Order oldOrder = selectOrderByOrderId(oldOrderId);
        MyBaseEntity baseEntity = new MyBaseEntity();
        SecurityUtils.fillUpdateUser(baseEntity);

        List<OrderDeskTime> deskTimes = oldOrder.getOrderDeskTimes().stream().map(p -> {
            OrderDeskTime time = new OrderDeskTime();
            BeanUtils.copyProperties(p, time);
            time.setStatus(CalcTimeStatus.BUSY.getValue());
            time.setOrderId(newOrderId);
            time.setOrderDeskTimeId(IdUtils.singleNextId());
            SecurityUtils.fillUpdateUser(time, baseEntity);
            return time;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(deskTimes)) {
            orderDeskTimeMapper.insertBatch(deskTimes);
        }

        List<OrderTutorTime> tutorTimes = oldOrder.getOrderTutorTimes().stream().map(p -> {
            OrderTutorTime time = new OrderTutorTime();
            BeanUtils.copyProperties(p, time);
            time.setStatus(CalcTimeStatus.BUSY.getValue());
            time.setOrderId(newOrderId);
            time.setOrderTutorTimeId(IdUtils.singleNextId());
            SecurityUtils.fillUpdateUser(time, baseEntity);
            return time;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(tutorTimes)) {
            orderTutorTimeMapper.insertBatch(tutorTimes);
        }

        List<OrderGoods> goods = oldOrder.getOrderGoods().stream().map(p -> {
            OrderGoods time = new OrderGoods();
            BeanUtils.copyProperties(p, time);

            time.setOrderId(newOrderId);
            time.setOrderDetailId(IdUtils.singleNextId());
            SecurityUtils.fillUpdateUser(time, baseEntity);
            return time;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(goods)) {
            orderGoodsMapper.insertBatch(goods);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order pauseCalcFee(Long deskId) {

        Order order = orderMapper.selectCurrentRelationOrder(deskId);
        AssertUtil.notNullOrEmpty(order, "订单不存在");
        OrderDeskTime time = orderDeskTimeMapper.selectOne(orderDeskTimeMapper.query().eq(OrderDeskTime::getDeskId, deskId)
                .isNull(OrderDeskTime::getEndTime).orderByDesc(OrderDeskTime::getStartTime)
                .eq(OrderDeskTime::getOrderId, order.getOrderId())
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

        return selectRelationOrderWithDetail(time.getOrderId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order resumeCalcFee(Long deskId) {
        StoreDesk desk = storeDeskMapper.selectById(deskId);
        Order order = orderMapper.selectCurrentRelationOrder(deskId);
        AssertUtil.notNullOrEmpty(order, "未找到相关订单");
        AssertUtil.isTrue(Objects.equals(order.getStatus(), OrderStatus.CHARGING.getValue()), "订单状态不正确");
        final Date now = DateUtils.removeSeconds(DateUtils.getNowDate());
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
        return selectRelationOrderWithDetail(deskId);
    }

    private void tutorSwapToNewDesk(Long deskId, OrderTutorTime item, Date now, Order order) {
        item.setStatus(CalcTimeStatus.STOP.getValue());
        SecurityUtils.fillUpdateUser(item);
        if (Objects.isNull(item.getEndTime())) {
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

    private Order queryValidOrder(Long storeId, Long orderId) {
        Order order = orderMapper.selectById(orderId);
        AssertUtil.notNullOrEmpty(order, OrderErrorMsg.ORDER_NOT_FOUND);
        AssertUtil.isTrue(Objects.equals(storeId, order.getStoreId()), OrderErrorMsg.INVALID_ORDER);
        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderCommandResVo suspendOrder(Long orderId, Long storeId) {
        Order order = queryValidOrder(storeId, orderId);
        AssertUtil.isTrue(Arrays.asList(OrderStatus.CHARGING.getValue(), OrderStatus.WAIT_SETTLED.getValue()).contains(
                order.getStatus()), OrderErrorMsg.ORDER_NOT_CHARGING_OR_STOP);


        order = stopAllCalcTimes(orderId, false);

        //更新order
        order.setStatus(OrderStatus.SUSPEND.getValue());
        SecurityUtils.fillUpdateUser(order);
        orderMapper.updateById(order);

        OrderCommandResVo resVo = new OrderCommandResVo();
        resVo.setOrder(selectOrderByOrderId(orderId));
        if (CollectionUtils.isNotEmpty(resVo.getOrder().getOrderDeskTimes())) {
            List<OrderDeskTime> times = resVo.getOrder().getOrderDeskTimes();
            times.sort(Comparator.comparing(OrderDeskTime::getCreateTime).reversed());
            resVo.setDesk(storeDeskMapper.selectStoreDeskByDeskId(times.get(0).getDeskId()));
        }
        resVo.calcFees();
        clearDeskOrder(orderId);
        return resVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BigDecimal prePayAmount(OrderPrePayReqVo reqVo) {
        Order order = queryValidOrder(reqVo.getStoreId(), reqVo.getOrderId());
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), order.getStatus()),
                OrderErrorMsg.ORDER_NOT_CHARGING);

        Order newOrder = new Order();
        newOrder.setOrderId(order.getOrderId());
        newOrder.setPrePayAmount(Optional.ofNullable(order.getPrePayAmount()).orElse(BigDecimal.ZERO).add(reqVo.getAmount()).setScale(2, RoundingMode.DOWN));
        SecurityUtils.fillUpdateUser(newOrder);
        orderMapper.updateById(newOrder);
        return newOrder.getPrePayAmount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderCommandResVo stopOrder(Long orderId, Long storeId, boolean stopByTimer) {
        Order order = queryValidOrder(storeId, orderId);
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), order.getStatus()),
                OrderErrorMsg.ORDER_NOT_CHARGING);


        List<StoreDesk> desks = storeDeskMapper.selectList(storeDeskMapper.query().eq(StoreDesk::getCurrentOrderId, order.getOrderId()));
        AssertUtil.notNullOrEmpty(desks, OrderErrorMsg.DESK_NOT_FOUND);

        List<Long> deskIds=  desks.stream().map(StoreDesk::getDeskId).collect(Collectors.toList());
        lightTimerMapper.delete(lightTimerMapper.query().in(LightTimer::getDeskId, deskIds));

        order = stopAllCalcTimes(orderId, stopByTimer);
        order.setStatus(OrderStatus.WAIT_SETTLED.getValue());
        SecurityUtils.fillUpdateUser(order);
        orderMapper.updateById(order);

        clearDeskOrder(orderId);


        OrderCommandResVo resVo = new OrderCommandResVo();
        resVo.setOrder(selectOrderByOrderId(orderId));
        resVo.setDesks(storeDeskMapper.selectList(storeDeskMapper.query().in(StoreDesk::getDeskId,deskIds)));
        resVo.setDesk(resVo.getDesks().get(0));
        resVo.calcFees();

        return resVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean voidOrder(Long orderId, Long storeId, String remark) {
        Order order = queryValidOrder(storeId, orderId);
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), order.getStatus()),
                OrderErrorMsg.ORDER_NOT_CHARGING);

        order = stopAllCalcTimes(orderId, false);

        order.setStatus(OrderStatus.VOID.getValue());
        order.setRemark(remark);
        SecurityUtils.fillUpdateUser(order);
        orderMapper.updateById(order);

        clearDeskOrder(orderId);

        return Boolean.TRUE;
    }
    private  void clearDeskOrder(Long orderId){
        storeDeskMapper.update(null,storeDeskMapper.edit().lambda().set(StoreDesk::getStatus,DeskStatus.WAIT.getValue())
                        .set(StoreDesk::getCurrentOrderId,null)
                .eq(StoreDesk::getCurrentOrderId,orderId));
    }

    /**
     * 停止所有计费 并 更新球桌关联orderId
     *
     * @param orderId
     */
    private Order stopAllCalcTimes(Long orderId, Boolean byTimer)

    {
        final Date endTime = byTimer ? DateUtils.removeSeconds(new Date()) : DateUtils.getNowDate();
        Order order = orderMapper.selectById(orderId);
        SecurityUtils.fillUpdateUser(order);
        //更新台桌结束时间
        List<BigDecimal> amounts = Lists.newArrayList();
        List<OrderDeskTime> deskTimes =
                orderDeskTimeMapper.selectList(orderDeskTimeMapper.query()
                        .eq(OrderDeskTime::getOrderId, orderId));
        deskTimes.forEach(time -> {
            if (Objects.isNull(time.getEndTime())) {
                time.setEndTime(endTime);

            }
            if (Objects.isNull(time.getTotalAmountDue())) {
                time.setTotalAmountDue(time.calcFee());
            }
            amounts.add(time.getTotalAmountDue());

            time.setStatus(CalcTimeStatus.STOP.getValue());
            SecurityUtils.fillUpdateUser(time, order);
            orderDeskTimeMapper.updateById(time);
        });
        //更新教练时间


        List<OrderTutorTime> tutorTimes =
                orderTutorTimeMapper.selectList(orderTutorTimeMapper.query()
                        .eq(OrderTutorTime::getOrderId, orderId));
        tutorTimes.forEach(time -> {
            if (Objects.isNull(time.getEndTime())) {
                time.setEndTime(endTime);
            }
            if (Objects.isNull(time.getTotalAmountDue())) {
                time.setTotalAmountDue(time.calcFee());
            }
            amounts.add(time.getTotalAmountDue());
            time.setStatus(CalcTimeStatus.STOP.getValue());
            SecurityUtils.fillUpdateUser(time, order);
            orderTutorTimeMapper.updateById(time);
        });
        List<OrderGoods> goods =
                orderGoodsService.selectOrderGoodsListByOrderId(orderId);
        goods.forEach(item -> {
            if (Objects.isNull(item.getTotalAmountDue())) {
                item.setTotalAmountDue(BaseFee.calcGoodsFee(item));
                SecurityUtils.fillUpdateUser(item, order);
                orderGoodsMapper.updateById(item);
            }
            amounts.add(item.getTotalAmountDue());
        });
        //更新球桌状态
        if (Objects.equals(order.getStatus(), OrderStatus.CHARGING.getValue())) {
            StoreDesk desk = storeDeskMapper.selectOne(storeDeskMapper.query().eq(StoreDesk::getCurrentOrderId, order.getOrderId()));
            AssertUtil.notNullOrEmpty(desk, "当前计费订单没有关联的台桌");
            desk.setStatus(DeskStatus.WAIT.getValue());
            desk.setCurrentOrderId(null);
            storeDeskMapper.updateAllWithId(desk);
        }
        order.setTotalAmountDue(BaseFee.sumTotalFees(amounts));
        orderMapper.update(null, orderMapper.edit().lambda()
                .set(Order::getTotalAmountDue, order.getTotalAmountDue()).eq(Order::getOrderId, orderId));
        return order;
    }

    @Override
    public void checkOrderTimer() {
        //   orderDeskTimeMapper.
    }
}
