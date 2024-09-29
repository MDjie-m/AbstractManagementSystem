package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.constant.OrderErrorMsg;
import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.dto.HomeReportDto;
import com.ruoyi.billiard.domain.vo.*;
import com.ruoyi.billiard.domain.vo.miniappdomain.HomeReportVoConsume;
import com.ruoyi.billiard.domain.vo.miniappdomain.HomeReportVoConsumeDetail;
import com.ruoyi.billiard.enums.*;
import com.ruoyi.billiard.mapper.*;
import com.ruoyi.billiard.service.*;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.MyBaseEntity;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
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
    private IOrderPayService orderPayService;


    @Autowired
    private IOrderRefundService orderRefundService;
    @Autowired
    private OrderRechargeMapper orderRechargeMapper;

    @Autowired
    private IOrderRelationService orderRelationService;

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
    private GoodsMapper goodsMapper;

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
    private StoreTutorMapper storeTutorMapper;

    @Autowired
    private IStoreService storeService;

    @Autowired
    private LightTimerMapper lightTimerMapper;

    @Autowired
    private IStockService stockService;

    @Autowired
    private IOrderDeskScoreService orderDeskScoreService;


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
        order.setOrderNo(createOrderNum(OrderType.TABLE_CHARGE, order.getOrderId()));
        order.setOrderType(OrderType.TABLE_CHARGE);
        order.setStatus(OrderStatus.CHARGING.getValue());

        order.setStoreId(desk.getStoreId());
        order.setTotalAmountDue(BigDecimal.ZERO);
        SecurityUtils.fillCreateUser(order);
        orderMapper.insert(order);

        BigDecimal price = priceService.queryPriceByType(desk.getStoreId(), desk.getDeskType());
        AssertUtil.notNullOrEmpty(price, "未设置价格无法开台.请联系管理员设置价格.");

        Date startTime = DateUtils.removeSeconds(new Date());
        OrderDeskTime deskTime = OrderDeskTime.builder()
                .deskId(deskId)
                .status(CalcTimeStatus.BUSY.getValue())
                .orderId(order.getOrderId())
                .price(price).startTime(startTime).totalAmountDue(BigDecimal.ZERO).build();
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

        //停止旧的计分
        orderDeskScoreService.stopRecordScore(oldDeskId, orderId);
        //插入新的计费

        BigDecimal price = priceService.queryPriceByType(newDesk.getStoreId(), newDesk.getDeskType());
        AssertUtil.notNullOrEmpty(price, "目标台桌类型未设置价格无法换台.请联系管理员设置价格.");
        OrderDeskTime deskTime = OrderDeskTime.builder()
                .deskId(newDeskId)
                .orderId(order.getOrderId())
                .price(price)
                .fromDeskId(oldDeskId)
                .status(CalcTimeStatus.BUSY.getValue())
                .startTime(endTime)
                .totalAmountDue(BigDecimal.ZERO).build();
        orderDeskScoreService.initScore(newDeskId, orderId);

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
        Order oldOrder = orderMapper.selectOrderByOrderId(oldOrderId);
        AssertUtil.isTrue(Objects.nonNull(oldOrder) && Objects.equals(oldOrderId, oldOrder.getOrderId()), "找不到相关订单");
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), oldOrder.getStatus()), "当前订单不是计费中,无法并台.");

        Order newOrder = orderMapper.selectCurrentRelationOrder(newDeskId);
        AssertUtil.isTrue(Objects.nonNull(newOrder), "找不新台桌到相关订单");
        AssertUtil.isTrue(!Objects.equals(oldOrderId, newOrder.getOrderId()), "当前台桌与目标台桌已经并台，不用重复操作");

        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), newOrder.getStatus()), "当前订单不是计费中,无法并台.");
        // 添加关系并检查
        orderRelationService.addRelation(newOrder.getOrderId(), oldOrderId);

        lightTimerMapper.delete(lightTimerMapper.query().eq(LightTimer::getDeskId, oldDeskId));

        copyFeesToNewOrder(oldOrderId, newOrder.getOrderId());

        stopAllCalcTimes(oldOrderId, false, false);

        oldOrder = orderMapper.selectOrderByOrderId(oldOrderId);
        oldOrder.setStatus(OrderStatus.VOID.getValue());
        oldOrder.setRemark(StringUtils.format("并台自动废弃旧订单，已合并到订单:{}", newOrder.getOrderNo()));
        SecurityUtils.fillUpdateUser(oldOrder);
        orderMapper.updateById(oldOrder);
        return selectOrderByOrderId(newOrder.getOrderId());
    }

    private void copyFeesToNewOrder(Long oldOrderId, Long newOrderId) {
        Order oldOrder = selectOrderByOrderId(oldOrderId);
        MyBaseEntity baseEntity = new MyBaseEntity();
        SecurityUtils.fillUpdateUser(baseEntity);

        List<OrderDeskTime> deskTimes = oldOrder.getOrderDeskTimes().stream().map(p -> {
            OrderDeskTime time = new OrderDeskTime();
            BeanUtils.copyProperties(p, time);
            time.setOrderId(newOrderId);
            time.setOrderDeskTimeId(IdUtils.singleNextId());
            SecurityUtils.fillUpdateUser(time, baseEntity);
            storeDeskMapper.update(null, storeDeskMapper.updateWrapper().set(StoreDesk::getCurrentOrderId, newOrderId)
                    .eq(StoreDesk::getDeskId, time.getDeskId()).eq(StoreDesk::getCurrentOrderId, oldOrderId));
            return time;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(deskTimes)) {
            orderDeskTimeMapper.insertBatch(deskTimes);
        }

        List<OrderTutorTime> tutorTimes = oldOrder.getOrderTutorTimes().stream().map(p -> {
            OrderTutorTime time = new OrderTutorTime();
            BeanUtils.copyProperties(p, time);
            time.setOrderId(newOrderId);
            time.setOrderTutorTimeId(IdUtils.singleNextId());
            SecurityUtils.fillUpdateUser(time, baseEntity);

            storeTutorMapper.update(null, storeTutorMapper.updateWrapper().set(StoreTutor::getCurrentOrderId, newOrderId)
                    .eq(StoreTutor::getStoreTutorId, time.getTutorId()).eq(StoreTutor::getCurrentOrderId, oldOrderId));
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

    private String createOrderNum(OrderType type, Long id) {
        return String.format("%s%s", type.getPrefix(), id);
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

        List<StoreDesk> desks = storeDeskMapper.queryBusyDeskByOrderId(orderId);
        desks.forEach(p -> p.setStatus(CalcTimeStatus.STOP.getValue()));

        order = stopAllCalcTimes(orderId, true, false);


        //更新order
        order.setStatus(OrderStatus.SUSPEND.getValue());
        SecurityUtils.fillUpdateUser(order);
        orderMapper.updateById(order);

        return OrderCommandResVo.builder().busyDesks(desks)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BigDecimal prePayAmount(OrderPrePayReqVo reqVo) {
        Order order = queryValidOrder(reqVo.getStoreId(), reqVo.getOrderId());
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), order.getStatus()),
                OrderErrorMsg.ORDER_NOT_CHARGING);

        if (Objects.nonNull(order.getPrePayAmount())) {
            AssertUtil.isTrue(order.getPrePayAmount().compareTo(new BigDecimal("9999")) < 0, "预付费不能超过9999");
        }
        AssertUtil.isTrue(!Objects.equals(OrderPayType.MEMBER, reqVo.getPayType()), "不能为会员支付");
        Order newOrder = new Order();
        newOrder.setOrderId(order.getOrderId());
        newOrder.setPrePayAmount(Optional.ofNullable(order.getPrePayAmount())
                .orElse(BigDecimal.ZERO).add(reqVo.getAmount())
                .setScale(2, RoundingMode.DOWN));
        orderPayService.insertOrderPay(OrderPay.builder()
                .orderId(reqVo.getOrderId())
                .paid(Boolean.TRUE)
                .prePay(Boolean.TRUE)
                .payType(reqVo.getPayType())
                .payTime(new Date())
                .amount(reqVo.getAmount())
                .build());
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


        List<StoreDesk> desks = storeDeskMapper.queryBusyDeskByOrderId(orderId);
        desks.forEach(p -> {
            orderDeskScoreService.stopRecordScore(p.getDeskId(), orderId);
        });

        //关闭所有计费
        order = stopAllCalcTimes(orderId, true, stopByTimer);


        //更新订单状态
        order.setStatus(OrderStatus.WAIT_SETTLED.getValue());
        SecurityUtils.fillUpdateUser(order);
        orderMapper.updateById(order);


        return OrderCommandResVo.builder().busyDesks(desks)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean voidOrder(Long orderId, Long storeId, String remark, boolean needRevertGoods) {
        Order order = queryValidOrder(storeId, orderId);
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), order.getStatus()),
                OrderErrorMsg.ORDER_NOT_CHARGING);

        order = stopAllCalcTimes(orderId, true, false);
        if (needRevertGoods) {
            revertGoods(orderId, order.getStoreId());
        }

        order.setStatus(OrderStatus.VOID.getValue());
        order.setRemark(remark);
        SecurityUtils.fillUpdateUser(order);
        orderMapper.updateById(order);

        return Boolean.TRUE;
    }

    private void revertGoods(Long orderId, Long storeId) {
        List<OrderGoods> goods = orderGoodsMapper.selectOrderGoodsList(OrderGoods.builder().orderId(orderId).build());
        if (CollectionUtils.isEmpty(goods)) {
            return;
        }
        goods.forEach(p -> {
            stockService.updateStock(storeId, p.getGoodsId(), Long.valueOf(p.getNum()), StockChangeType.IN, "订单作废撤回", orderId);
        });
    }

    private void clearDeskOrder(Long orderId) {
        storeDeskMapper.update(null, storeDeskMapper.edit().lambda().set(StoreDesk::getStatus, DeskStatus.WAIT.getValue())
                .set(StoreDesk::getCurrentOrderId, null)
                .eq(StoreDesk::getCurrentOrderId, orderId));
    }


    @Override
    public void calOrderFees(Order order) {
        List<ITotalDueFee> feeItems = Lists.newArrayList();
        Map<Integer, BigDecimal> discountValueMap = memberService.getOrderMemberDisCountValue(order.getMemberId());

        Date endTime = DateUtils.getNowDate();


        stopDeskTimes(order.getOrderDeskTimes(), endTime, order.getMemberId(), false, false);
        stopTutorTimes(order.getOrderTutorTimes(), endTime, order.getMemberId(), false, false);
        stopGoods(order.getOrderGoods(), order.getMemberId(), false);
        feeItems.addAll(order.getOrderDeskTimes());
        feeItems.addAll(order.getOrderTutorTimes());
        feeItems.addAll(order.getOrderGoods());

        order.setTotalAmountDue(BaseFee.sumTotalFees(feeItems, ITotalDueFee::getTotalAmountDue));
        order.setTotalDiscountAmount(BaseFee.sumTotalFees(feeItems, ITotalDueFee::getTotalDiscountAmount));

        order.setTotalAmount(BaseFee.sumTotalFees(feeItems, ITotalDueFee::getTotalAmount));

        order.setTotalWipeZero(order.getTotalAmount().remainder(BigDecimal.ONE));
        order.setTotalAmount(BigDecimal.valueOf(order.getTotalAmount().intValue()));
        if (Objects.nonNull(order.getPrePayAmount()) && order.getPrePayAmount().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal refund = order.getPrePayAmount().subtract(order.getTotalAmount());
            if (refund.compareTo(BigDecimal.ZERO) >= 0) {
                order.setRefundAmount(refund);
            } else {
                order.setRepayAmount(refund.abs());
            }
        }

    }

    private void stopDeskTimes(List<OrderDeskTime> times, Date endTime,
                               Long memberId,
                               boolean needUpdate, boolean needUpdateDesk) {
        BigDecimal disCountValue = memberService.getMemberDisCountValue(OrderType.TABLE_CHARGE, memberId);

        times.forEach(time -> {
            Integer oldStatus = time.getStatus();
            if (Objects.isNull(time.getEndTime())) {
                time.setEndTime(endTime);
            }

            time.setTotalTime(time.getNum());
            time.calcAndSetFee(disCountValue);
            if (!needUpdate) {

                return;
            }
            time.setStatus(CalcTimeStatus.STOP.getValue());
            SecurityUtils.fillUpdateUser(time);
            orderDeskTimeMapper.updateById(time);
            if (needUpdateDesk && Objects.equals(oldStatus, CalcTimeStatus.BUSY.getValue())) {
                storeDeskMapper.update(null, storeDeskMapper.edit().lambda()
                        .set(StoreDesk::getStatus, DeskStatus.WAIT.getValue())
                        .set(StoreDesk::getCurrentOrderId, null)
                        .eq(StoreDesk::getDeskId, time.getDeskId())
                        .eq(StoreDesk::getCurrentOrderId, time.getOrderId()));
            }
        });
    }

    private void stopTutorTimes(List<OrderTutorTime> times, Date endTime,
                                Long memberId,
                                boolean needUpdate, boolean needUpdateTutor) {

        times.forEach(time -> {
            Integer oldStatus = time.getStatus();
            BigDecimal disCountValue = memberService.getMemberDisCountValue(OrderType.valueOf(time.getType()), memberId);
            if (Objects.isNull(time.getEndTime())) {
                time.setEndTime(endTime);
            }
            time.setTotalTime(time.getNum());
            time.calcAndSetFee(disCountValue);
            SecurityUtils.fillUpdateUser(time);
            if (!needUpdate) {
                return;
            }
            time.setStatus(CalcTimeStatus.STOP.getValue());
            SecurityUtils.fillUpdateUser(time);
            orderTutorTimeMapper.updateById(time);
            if (needUpdateTutor && Objects.equals(oldStatus, CalcTimeStatus.BUSY.getValue())) {
                storeTutorMapper.update(null, storeTutorMapper.edit().lambda()
                        .set(StoreTutor::getWorkStatus, TutorWorkStatus.WAIT.getValue())
                        .set(StoreTutor::getCurrentOrderId, null)
                        .eq(StoreTutor::getStoreTutorId, time.getTutorId())
                        .eq(StoreTutor::getCurrentOrderId, time.getOrderId()));
            }
        });
    }

    private void stopGoods(List<OrderGoods> times,
                           Long memberId,
                           boolean needUpdate) {
        times.forEach(item -> {
            BigDecimal disCountValue = memberService.getMemberDisCountValue(OrderType.COMMODITY_PURCHASE, memberId);

            item.calcAndSetFee(disCountValue);
            SecurityUtils.fillUpdateUser(item);
            if (needUpdate) {
                orderGoodsMapper.updateById(item);
            }
        });
    }

    /**
     * 停止所有计费 并 更新球桌关联orderId
     *
     * @param orderId
     */
    private Order stopAllCalcTimes(Long orderId, boolean updateDeskOrTutor, Boolean byTimer) {
        final Date endTime = byTimer ? DateUtils.removeSeconds(new Date()) : DateUtils.fileSecondsAddOneMinutes(DateUtils.getNowDate());
        Order order = orderMapper.selectById(orderId);
        SecurityUtils.fillUpdateUser(order);


        //更新台桌结束时间
        List<ITotalDueFee> feeItems = Lists.newArrayList();

        List<OrderDeskTime> deskTimes = orderDeskTimeMapper.selectList(orderDeskTimeMapper.query()
                .eq(OrderDeskTime::getOrderId, orderId));

        //更新台费
        stopDeskTimes(deskTimes, endTime, order.getMemberId(), true, updateDeskOrTutor);
        //更新教练时间

        List<OrderTutorTime> tutorTimes =
                orderTutorTimeMapper.selectList(orderTutorTimeMapper.query()
                        .eq(OrderTutorTime::getOrderId, orderId));
        stopTutorTimes(tutorTimes, endTime, order.getMemberId(), true, updateDeskOrTutor);


        List<OrderGoods> goods =
                orderGoodsService.selectOrderGoodsListByOrderId(orderId);
        stopGoods(goods, order.getMemberId(), true);
        feeItems.addAll(deskTimes);
        feeItems.addAll(tutorTimes);
        feeItems.addAll(goods);

        order.setTotalAmountDue(BaseFee.sumTotalFees(feeItems, ITotalDueFee::getTotalAmountDue));
        order.setTotalDiscountAmount(BaseFee.sumTotalFees(feeItems, ITotalDueFee::getTotalDiscountAmount));
        order.setTotalAmount(BaseFee.sumTotalFees(feeItems, ITotalDueFee::getTotalAmount));

        order.setTotalWipeZero(order.getTotalAmount().remainder(BigDecimal.ONE));
        order.setTotalAmount(BigDecimal.valueOf(order.getTotalAmount().intValue()));
        if (Objects.nonNull(order.getPrePayAmount()) && order.getPrePayAmount().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal refund = order.getPrePayAmount().subtract(order.getTotalAmount());
            if (refund.compareTo(BigDecimal.ZERO) >= 0) {
                order.setRefundAmount(refund);
            } else {
                order.setRepayAmount(refund.abs());
            }
        }


        orderMapper.update(null, orderMapper.edit().lambda()
                .set(Order::getTotalAmountDue, order.getTotalAmountDue())
                .set(Order::getTotalDiscountAmount, order.getTotalDiscountAmount())
                .set(Order::getTotalAmount, order.getTotalAmount())
                .set(Order::getRefundAmount, order.getRefundAmount())
                .set(Order::getRepayAmount, order.getRepayAmount())
                .set(Order::getTotalWipeZero, order.getTotalWipeZero())
                .eq(Order::getOrderId, orderId));
        return order;
    }

    @Override
    public void checkOrderTimer() {
        //   orderDeskTimeMapper.
    }

    @Override
    public List<Order> selectOrderByMemberIds(Long[] memberIds) {
        return Optional.ofNullable(orderMapper.selectList(orderMapper.query().in(Order::getMemberId, Arrays.asList(memberIds)))).orElse(Collections.emptyList());
    }

    @Override
    public Boolean fillMember(Long orderId, Long memberId) {
        Order order = orderMapper.selectById(orderId);
        AssertUtil.notNullOrEmpty(order, OrderErrorMsg.ORDER_NOT_FOUND);
        AssertUtil.isTrue(order.getStatus() <= OrderStatus.SETTLED.getValue()
                || Objects.equals(order.getStatus(), OrderStatus.SUSPEND.getValue()), "只有计费中和待结算的订单才能更改会员");
        SecurityUtils.fillUpdateUser(order);
        order.setMemberId(memberId);
        orderMapper.updateAllWithId(order);
        if (Objects.equals(OrderStatus.WAIT_SETTLED.getValue(), order.getStatus())
                || Objects.equals(OrderStatus.SUSPEND.getValue(), order.getStatus())) {
            stopAllCalcTimes(orderId, false, false);
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean finishOrder(FinishOrderReqVo reqVo) {
        Order order = orderMapper.selectById(reqVo.getOrderId());
        order.setPayType(reqVo.getPayType());
        AssertUtil.notNullOrEmpty(order, OrderErrorMsg.ORDER_NOT_FOUND);
        AssertUtil.isTrue(Arrays.asList(OrderStatus.WAIT_SETTLED.getValue(), OrderStatus.SUSPEND.getValue())
                .contains(order.getStatus()), "只有待结算和挂起的订单才能结算");
        if (Objects.equals(reqVo.getPayType(), OrderPayType.MEMBER)) {
            AssertUtil.notNullOrEmpty(order.getMemberId(), "订单未绑定会员");
            AssertUtil.isTrue(memberService.checkPwd(order.getMemberId(), reqVo.getPassword()), "密码错误");
            memberService.deductAmount(order.getMemberId(), order.getOrderId(), order.getTotalAmount());
        }
        order.setStatus(OrderStatus.SETTLED.getValue());
        order.setPayTime(new Date());

        if (order.getRefundAmount().compareTo(BigDecimal.ZERO) > 0) {
            orderRefundService.insertOrderRefund(OrderRefund.builder()
                    .orderId(order.getOrderId())
                    .paid(Boolean.TRUE)
                    .returnPayType(reqVo.getPayType())
                    .returnPayTime(new Date())
                    .amount(order.getRepayAmount().compareTo(BigDecimal.ZERO) > 0 ? order.getRepayAmount() : order.getTotalAmount())
                    .build());
        } else {
            orderPayService.insertOrderPay(OrderPay.builder()
                    .orderId(reqVo.getOrderId())
                    .paid(Boolean.TRUE)
                    .prePay(Boolean.FALSE)
                    .payType(reqVo.getPayType())
                    .payTime(new Date())
                    .amount(order.getRepayAmount().compareTo(BigDecimal.ZERO) > 0 ? order.getRepayAmount() : order.getTotalAmount())
                    .build());
        }

        orderMapper.updateById(order);
        return true;
    }

    private void checkOrderIsCharging(Order order) {
        AssertUtil.notNullOrEmpty(order, OrderErrorMsg.INVALID_ORDER);
        AssertUtil.equal(OrderStatus.CHARGING.getValue(), order.getStatus(), OrderErrorMsg.ORDER_NOT_CHARGING);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long orderShopping(Order reqVo) {
        if (CollectionUtils.isNotEmpty(reqVo.getOrderTutorTimes())) {
            Order order = orderMapper.selectCurrentRelationOrder(reqVo.getOrderTutorTimes().get(0).getDeskId());
            checkOrderIsCharging(order);
            SecurityUtils.fillUpdateUser(order);
            Date startTime = DateUtils.removeSeconds(new Date());
            reqVo.getOrderTutorTimes().forEach(p -> {
                StoreDesk desk = storeDeskMapper.selectById(p.getDeskId());

                AssertUtil.notNullOrEmpty(desk, "台桌不存在");
                AssertUtil.equal(desk.getStoreId(), order.getStoreId(), "台桌id不合法");
                AssertUtil.equal(desk.getStatus(), DeskStatus.BUSY.getValue(), "台桌不是计费状态,无法添加");
                AssertUtil.equal(desk.getCurrentOrderId(), order.getOrderId(), StringUtils.format("{}({})不在同一个订单", desk.getDeskName(), desk.getDeskNum()));

                StoreTutor tutor = storeTutorMapper.selectById(p.getTutorId());
                AssertUtil.notNullOrEmpty(tutor, "教练不存在");
                AssertUtil.equal(tutor.getStatus(), EmployeeStatus.WORK.getValue(), "教练已离职，无法添加");
                AssertUtil.equal(tutor.getStatus(), TutorWorkStatus.WAIT.getValue(), "教练不是空闲状态,无法添加");
                AssertUtil.isNullOrEmpty(tutor.getCurrentOrderId(), "教练正在计费中，无法添加");

                AssertUtil.isNullOrEmpty(orderTutorTimeMapper.selectList(orderTutorTimeMapper.query()
                        .eq(OrderTutorTime::getDeskId, desk.getDeskId()).in(OrderTutorTime::getStatus, CalcTimeStatus.PAUSE.getValue(),
                                CalcTimeStatus.BUSY.getValue())), StringUtils.format("台桌:{}({})已经有教练在计费,无法重复添加", desk.getDeskName(), desk.getDeskNum()));

                BigDecimal price = storeTutorMapper.queryPrice(p.getTutorId());
                AssertUtil.notNullOrEmpty(price, "未配置教练价格，请联系管理员添加");
                p.setOrderTutorTimeId(IdUtils.singleNextId());
                p.setOrderId(order.getOrderId());
                p.setStartTime(startTime);
                p.setStatus(CalcTimeStatus.BUSY.getValue());
                SecurityUtils.fillCreateUser(p, order);
                orderTutorTimeMapper.insert(p);

                tutor.setWorkStatus(TutorWorkStatus.BUSY.getValue());
                tutor.setCurrentOrderId(p.getOrderId());
                SecurityUtils.fillCreateUser(tutor, order);
                storeTutorMapper.updateById(tutor);
            });

            return order.getOrderId();

        }
        AssertUtil.notNullOrEmpty(reqVo.getOrderGoods(), "商品不能为空");
        Order order = null;
        if (Objects.nonNull(reqVo.getOrderId())) {
            order = orderMapper.selectById(reqVo.getOrderId());
            checkOrderIsCharging(order);
            SecurityUtils.fillUpdateUser(order);
        } else {
            order = new Order();
            order.setOrderId(IdUtils.singleNextId());
            order.setStoreId(reqVo.getStoreId());
            order.setOrderNo(createOrderNum(OrderType.COMMODITY_PURCHASE, order.getOrderId()));
            order.setTotalAmount(BigDecimal.ZERO);
            order.setOrderType(OrderType.COMMODITY_PURCHASE);
            order.setStatus(OrderStatus.WAIT_SETTLED.getValue());

            SecurityUtils.fillCreateUser(order);
            orderMapper.insert(order);
        }
        BigDecimal disCountValue = memberService.getMemberDisCountValue(OrderType.COMMODITY_PURCHASE, order.getMemberId());
        for (OrderGoods p : reqVo.getOrderGoods()) {
            Goods goods = goodsMapper.selectById(p.getGoodsId());
            AssertUtil.notNullOrEmpty(goods, "商品不存在");
            AssertUtil.equal(goods.getStoreId(), order.getStoreId(), "商品id不合法");
            AssertUtil.isTrue(goods.getSell(), "商品未上架");
            p.setPrice(p.getPrice());
            p.setGoodsName(goods.getGoodsName());
            p.setOrderId(order.getOrderId());
            p.setOrderDetailId(IdUtils.singleNextId());
            p.setGoods(goods);

            p.calcAndSetFee(disCountValue);
            SecurityUtils.fillUpdateUser(p, order);
            stockService.updateStock(reqVo.getStoreId(), p.getGoodsId(), 1L, StockChangeType.OUT, "商品售卖", p.getOrderId());
            orderGoodsMapper.insert(p);
        }
        if (Objects.isNull(reqVo.getOrderId())) {
            stopAllCalcTimes(order.getOrderId(), true, false);
        }

        return order.getOrderId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StopDeskResVo stopDesk(Long orderId, Long storeId, Long deskId) {
        Order order = queryValidOrder(storeId, orderId);
        AssertUtil.isTrue(Objects.equals(OrderStatus.CHARGING.getValue(), order.getStatus()),
                OrderErrorMsg.ORDER_NOT_CHARGING);
        List<StoreDesk> desks = storeDeskMapper.queryBusyDeskByOrderId(orderId);
        AssertUtil.notNullOrEmpty(desks, "台桌未在计费状态");
        AssertUtil.isTrue(desks.stream().anyMatch(p -> Objects.equals(p.getDeskId(), deskId)), "台桌不在当前订单");
        if (Objects.equals(desks.size(), 1)) {
            stopOrder(orderId, storeId, false);
            return StopDeskResVo.builder().orderId(orderId)
                    .hasOtherDesk(false).build();
        }
        Date endTime = DateUtils.fileSecondsAddOneMinutes(DateUtils.getNowDate());
        List<OrderDeskTime> times = orderDeskTimeMapper.selectList(orderDeskTimeMapper.query()
                .eq(OrderDeskTime::getDeskId, deskId)
                .eq(OrderDeskTime::getOrderId, orderId)
                .eq(OrderDeskTime::getStatus,
                        CalcTimeStatus.BUSY.getValue()));

        stopDeskTimes(times, endTime, order.getMemberId(), true, true);


        List<OrderTutorTime> tutorTimes = orderTutorTimeMapper.selectList(orderTutorTimeMapper.query().eq(OrderTutorTime::getDeskId, deskId)
                .eq(OrderTutorTime::getOrderId, orderId).eq(OrderTutorTime::getStatus, CalcTimeStatus.BUSY.getValue()));
        stopTutorTimes(tutorTimes, endTime, order.getMemberId(), true, true);

        return StopDeskResVo.builder().orderId(orderId)
                .hasOtherDesk(true).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void memberRecharge(OrderRecharge recharge) {
        Order order = new Order();
        BeanUtils.copyProperties(recharge, order);
        order.setStatus(OrderStatus.SETTLED.getValue());
        order.setOrderId(IdUtils.singleNextId());
        order.setOrderNo(createOrderNum(OrderType.MEMBER_RECHARGE, order.getOrderId()));
        order.setOrderType(OrderType.MEMBER_RECHARGE);
        order.setStoreId(recharge.getStoreId());
        order.setTotalAmountDue(recharge.getRechargeAmount());
        SecurityUtils.fillCreateUser(order);

        recharge.setOrderId(order.getOrderId());
        SecurityUtils.fillCreateUser(recharge, order);

        orderMapper.insert(order);
        orderRechargeMapper.insert(recharge);

        memberService.recharge(recharge);

    }

    @Override
    public List<OrderRecharge> selectRechargeOrderList(Order order) {

        return orderMapper.selectMemberRechargeOrderList(orderMapper.normalQuery()
                .eq("a.member_id", order.getMemberId())
                .eq("b.store_id", order.getStoreId())
                .orderByDesc("a.order_recharge_id"));
    }

    @Override
    public List<Order> selectDeductOrderList(Order order) {
        return orderMapper.selectList(orderMapper.normalQuery()
                .lambda().eq(Order::getMemberId, order.getMemberId())
                .eq(Order::getStoreId, order.getStoreId())
                .in(Order::getOrderType, OrderType.COMMODITY_PURCHASE, OrderType.TABLE_CHARGE)
                .in(Order::getStatus, OrderStatus.SETTLED.getValue())
                .orderByDesc(Order::getOrderId));
    }

    @Override
    public OrderRecharge getPreRecharge(Long storeId, Long memberId, BigDecimal recharge) {
        OrderRecharge res = new OrderRecharge();
        BigDecimal discountValue = memberService.getMemberDisCountValue(OrderType.MEMBER_RECHARGE, memberId);
        res.setStoreId(storeId);
        res.setRechargeAmount(recharge);
        res.calcAndSetFee(discountValue);
        return res;
    }

    @Override
    public HomeReportVo selectOrderData2Report(HomeReportDto dto) {
        ReportTimeType timeType = dto.getTimeType();
        HomeReportVo homeReportVo = new HomeReportVo();
        HomeReportVoConsume homeReportVoConsume = new HomeReportVoConsume();

        String nowDay = DateUtils.getDate();
        String endTime = nowDay + " 23:59";
        String startTime = "";
        if (Objects.equals(timeType, ReportTimeType.DAY)) {
            startTime = nowDay + " 00:00";
        }
        if (Objects.equals(timeType, ReportTimeType.WEEK)) {
            // 根据当前时间往前推一周
            String beforWeekDay = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addWeeks(new Date(), -1));
            startTime = beforWeekDay + " 00:00";

        }
        if (Objects.equals(timeType, ReportTimeType.MONTH)) {
            // 根据当前时间往前推一个月
            String beforMonthDay = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addMonths(new Date(), -1));
            startTime = beforMonthDay + " 00:00";

        }
        if (Objects.equals(timeType, ReportTimeType.YEAR)) {
            // 根据当前时间往前推一年
            String beforYearDay = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addYears(new Date(), -1));
            startTime = beforYearDay + " 00:00";

        }
        if (Objects.equals(timeType, ReportTimeType.CUSTOM)) {
            startTime = dto.getStartTime() + " 00:00";
            endTime = dto.getEndTime() + " 23:59";
        }
        // 查询总订单
        List<Order> orders = selectOrderByPayStatus(OrderStatus.SETTLED.getValue(), dto.getStoreId(), startTime, endTime);
        BigDecimal totalAmount = orders.stream().map(Order::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        HomeReportVoConsumeDetail totalConsum = HomeReportVoConsumeDetail.builder().consumeDetail(orders)
                .consumeName(OrderType.AGGREGATE_CONSUMPTION.getDesc())
                .consumeCount(orders.size())
                .consumeAmount(totalAmount)
                .consumePercent(new BigDecimal(100)).build();
        homeReportVoConsume.setTotal(totalConsum);

        // 查询挂起订单
        List<Order> suspendedOrder = selectOrderByPayStatus(OrderStatus.SUSPEND.getValue(), dto.getStoreId(), startTime, endTime);
        Map<String, Object> subOrderConsumeDetail = getSubOrderConsumeDetail(orders, totalAmount, suspendedOrder);
        // 消费类型统计
        List<HomeReportVoConsumeDetail> typeList = (List<HomeReportVoConsumeDetail>) subOrderConsumeDetail.get("typeList");
        // 挂起订单统计
        HomeReportVoConsumeDetail suspend = (HomeReportVoConsumeDetail) subOrderConsumeDetail.get("suspend");


        homeReportVoConsume.setTypeList(typeList);
        homeReportVo.setConsume(homeReportVoConsume);
        homeReportVo.setSuspend(suspend);

        return homeReportVo;
    }

    @Override
    public List<Order> selectOrderByPayStatus(Integer payStatus, Long storeId, String startTime, String endTime) {
        return Optional.ofNullable(orderMapper.selectList(orderMapper.query()
                        .eq(Objects.nonNull(storeId), Order::getStoreId, storeId)
                        .between(Order::getPayTime, startTime, endTime)
                        .eq(Order::getStatus, payStatus)
                        .orderByDesc(Order::getPayTime)))
                .orElse(Collections.emptyList());
    }

    /**
     * 子订单数据统计
     */
    public Map<String, Object> getSubOrderConsumeDetail(List<Order> orders, BigDecimal totalAmount, List<Order> suspendedOrder) {

        List<HomeReportVoConsumeDetail> typeList = new ArrayList<>();
        // 查询子订单
        List<Long> orderIds = orders.stream().map(Order::getOrderId).distinct().collect(Collectors.toList());

        Order sonOrder = getsTheSuborderBasedOnOrderIds(orderIds);
        // 订单关联陪练列表
        List<OrderTutorTime> orderTutorTimes = sonOrder.getOrderTutorTimes();
        // 订单关联商品列表
        List<OrderGoods> orderCommodityTimes = sonOrder.getOrderGoods();
        // 订单关联会员充值列表
        List<OrderRecharge> orderRechargeTimes = sonOrder.getOrderRecharges();
        // 订单关联球桌列表
        List<OrderDeskTime> orderDeskTimes = sonOrder.getOrderDeskTimes();

        // 订单去除空值
        orderTutorTimes = orderTutorTimes.stream().filter(Objects::nonNull).collect(Collectors.toList());
        orderCommodityTimes = orderCommodityTimes.stream().filter(Objects::nonNull).collect(Collectors.toList());
        orderRechargeTimes = orderRechargeTimes.stream().filter(Objects::nonNull).collect(Collectors.toList());
        orderDeskTimes = orderDeskTimes.stream().filter(Objects::nonNull).collect(Collectors.toList());

        // 球桌订单
        BigDecimal deskTotalAmount = orderDeskTimes.stream().map(OrderDeskTime::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal deskPercent = totalAmount.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : deskTotalAmount.divide(totalAmount, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        HomeReportVoConsumeDetail tableCharge = HomeReportVoConsumeDetail.builder()
                .consumeName(OrderType.TABLE_CHARGE.getDesc())
                .consumeCount(Integer.valueOf(String.valueOf(orderDeskTimes.stream().map(OrderDeskTime::getOrderId).distinct().count())))
                .consumeAmount(deskTotalAmount)
                .consumeDetail(orderDeskTimes)
                .consumePercent(deskPercent).build();
        typeList.add(tableCharge);

        // 商品订单
        BigDecimal commodityTotalAmount = orderCommodityTimes.stream().map(OrderGoods::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal goodsPercent = totalAmount.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : commodityTotalAmount.divide(totalAmount, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        HomeReportVoConsumeDetail commodityPurchase = HomeReportVoConsumeDetail.builder()
                .consumeName(OrderType.COMMODITY_PURCHASE.getDesc())
                .consumeCount(Integer.valueOf(String.valueOf(orderCommodityTimes.stream().map(OrderGoods::getOrderId).distinct().count())))
                .consumeAmount(commodityTotalAmount)
                .consumeDetail(orderCommodityTimes)
                .consumePercent(goodsPercent).build();
        typeList.add(commodityPurchase);

        // 会员充值
        BigDecimal rechargeTotalAmount = orderRechargeTimes.stream().map(OrderRecharge::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal rechargePercent = totalAmount.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : rechargeTotalAmount.divide(totalAmount, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        HomeReportVoConsumeDetail memberRecharge = HomeReportVoConsumeDetail.builder()
                .consumeName(OrderType.MEMBER_RECHARGE.getDesc())
                .consumeCount(Integer.valueOf(String.valueOf(orderRechargeTimes.stream().map(OrderRecharge::getOrderId).distinct().count())))
                .consumeAmount(rechargeTotalAmount)
                .consumeDetail(orderRechargeTimes)
                .consumePercent(rechargePercent).build();
        typeList.add(memberRecharge);

        // 助教费用
        BigDecimal tutorTotalAmount = orderTutorTimes.stream().map(OrderTutorTime::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal tutorPercent = totalAmount.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : tutorTotalAmount.divide(totalAmount, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        HomeReportVoConsumeDetail teachFee = HomeReportVoConsumeDetail.builder()
                .consumeName(OrderType.TEACHING_ASSISTANT_FEE.getDesc())
                .consumeCount(Integer.valueOf(String.valueOf(orderTutorTimes.stream().map(OrderTutorTime::getOrderId).distinct().count())))
                .consumeAmount(tutorTotalAmount)
                .consumeDetail(orderTutorTimes)
                .consumePercent(tutorPercent).build();
        typeList.add(teachFee);


        // 挂起订单
//        BigDecimal suspendedOrderTotalAmount = suspendedOrder.stream().map(Order::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        HomeReportVoConsumeDetail suspend = HomeReportVoConsumeDetail.builder()
                .consumeName(OrderStatus.SUSPEND.getDesc())
                .consumeCount(Integer.valueOf(String.valueOf(suspendedOrder.stream().map(Order::getOrderId).distinct().count())))
//                .consumeAmount(suspendedOrderTotalAmount)
                .consumeDetail(suspendedOrder).build();

        return new HashMap<String, Object>() {
            {
                put("typeList", typeList);
                put("suspend", suspend);
            }
        };
    }

    private Order getsTheSuborderBasedOnOrderIds(List<Long> orderIds) {
        Order order = new Order();
        if (CollectionUtils.isNotEmpty(orderIds)) {
            // 查询球桌计时列表
            List<OrderDeskTime> orderDeskTimeList = orderDeskTimeService.selectOrderDeskTimeListByOrderIds(orderIds);
            order.setOrderDeskTimes(orderDeskTimeList);
            // 查询订单商品列表
            List<OrderGoods> orderGoodsList = orderGoodsService.selectOrderGoodsListByOrderIds(orderIds);
            order.setOrderGoods(orderGoodsList);
            // 查询订单会员充值列表
            List<OrderRecharge> orderRechargeList = orderRechargeService.selectOrderRechargeListByOrderIds(orderIds);
            order.setOrderRecharges(orderRechargeList);
            // 查询订单教练计时列表
            List<OrderTutorTime> orderTutorTimeList = orderTutorTimeService.selectOrderTutorTimeListByOrderIds(orderIds);
            order.setOrderTutorTimes(orderTutorTimeList);
        }
        return order;
    }
}
