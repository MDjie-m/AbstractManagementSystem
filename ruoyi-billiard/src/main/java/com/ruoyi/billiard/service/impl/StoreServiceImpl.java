package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.vo.PayDetailVo;
import com.ruoyi.billiard.domain.vo.PreferentialVo;
import com.ruoyi.billiard.domain.vo.StoreDashboardResVo;
import com.ruoyi.billiard.enums.OrderPayType;
import com.ruoyi.billiard.enums.OrderStatus;
import com.ruoyi.billiard.enums.OrderType;
import com.ruoyi.billiard.enums.PreferentialType;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.billiard.mapper.StoreTutorMapper;
import com.ruoyi.billiard.mapper.StoreUserMapper;
import com.ruoyi.billiard.service.IStoreScheduleService;
import com.ruoyi.common.core.domain.model.Tuple;
import com.ruoyi.common.core.domain.model.Tuple3;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreMapper;
import com.ruoyi.billiard.service.IStoreService;

import javax.annotation.Resource;

/**
 * 门店Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-05
 */
@Service
public class StoreServiceImpl implements IStoreService {
    @Resource
    private StoreMapper storeMapper;

    @Resource
    private StoreUserMapper storeUserMapper;

    @Resource
    private StoreTutorMapper storeTutorMapper;

    @Resource
    private StoreDeskMapper storeDeskMapper;

    @Resource
    private IStoreScheduleService storeScheduleService;

    @Value("${cashier.aes-key}")
    private String aesKey;


    /**
     * 查询门店
     *
     * @param storeId 门店主键
     * @return 门店
     */
    @Override
    public Store selectStoreByStoreId(Long storeId) {
        return storeMapper.selectById(storeId);
    }

    /**
     * 查询门店列表
     *
     * @param store 门店
     * @return 门店
     */
    @Override
    public List<Store> selectStoreList(Store store) {
        return storeMapper.selectStoreList(store);
    }

    /**
     * 新增门店
     *
     * @param store 门店
     * @return 结果
     */
    @Override
    public int insertStore(Store store) {
        store.setStoreId(IdUtils.singleNextId());
        SecurityUtils.fillCreateUser(store);
        return storeMapper.insert(store);
    }

    /**
     * 修改门店
     *
     * @param store 门店
     * @return 结果
     */
    @Override
    public int updateStore(Store store) {
        SecurityUtils.fillUpdateUser(store);
        return storeMapper.updateStore(store);
    }

    /**
     * 批量删除门店
     *
     * @param storeIds 需要删除的门店主键
     * @return 结果
     */
    @Override
    public int deleteStoreByStoreIds(Long[] storeIds) {
        AssertUtil.isTrue(!storeUserMapper.existsIn(StoreUser::getStoreId, Arrays.asList(storeIds)), "门店还有员工，不能删除.");
        AssertUtil.isTrue(!storeTutorMapper.existsIn(StoreTutor::getStoreId, Arrays.asList(storeIds)), "门店还有教练，不能删除.");
        AssertUtil.isTrue(!storeDeskMapper.existsIn(StoreDesk::getStoreId, Arrays.asList(storeIds)), "门店还有球桌，不能删除.");
        return storeMapper.deleteStoreByStoreIds(storeIds);
    }

    /**
     * 删除门店信息
     *
     * @param storeId 门店主键
     * @return 结果
     */
    @Override
    public int deleteStoreByStoreId(Long storeId) {
        return storeMapper.deleteStoreByStoreId(storeId);
    }

    @Override
    public List<Store> selectAll() {
        return storeMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public StoreDashboardResVo queryStoreDashboard(Long storeId, Date startTime, Date endTime) {
        if (StringUtils.equals(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, startTime), (DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, endTime)))) {
            Tuple3<Date, Date, Date> time = storeScheduleService.getDaySchedule(storeId, startTime);
            startTime = time.getValue();
            endTime = time.getValue1();
        } else {
            Tuple<Date, Date> time = storeScheduleService.getDaySchedule(storeId, startTime, endTime);
            startTime = time.getValue();
            endTime = time.getValue1();
        }
        StoreDashboardResVo resVo = new StoreDashboardResVo();
        resVo.setStarTime(startTime);
        resVo.setEndTime(endTime);
        //总额
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Order::getStoreId, storeId).between(Order::getCreateTime, startTime, endTime)
                .eq(Order::getStatus, OrderStatus.SETTLED.getValue());

        Tuple<BigDecimal, Long> orderInfo = storeMapper.queryOrderTotal("total_amount", queryWrapper);
        resVo.setTotalAmount(orderInfo.getValue());
        resVo.setTotalOrderCount(orderInfo.getValue1());

        // 退款
        queryWrapper.clear();
        queryWrapper.lambda().eq(Order::getStoreId, storeId).between(Order::getCreateTime, startTime, endTime)
                .eq(Order::getStatus, OrderStatus.SETTLED.getValue()).gt(Order::getRefundAmount, 0);
        orderInfo = storeMapper.queryOrderTotal("refund_amount", queryWrapper);
        resVo.setRefundAmount(orderInfo.getValue());
        resVo.setRefundOrderCount(orderInfo.getValue1());


        // 挂单
        queryWrapper.clear();
        queryWrapper.lambda().eq(Order::getStoreId, storeId).between(Order::getCreateTime, startTime, endTime)
                .eq(Order::getStatus, OrderStatus.SUSPEND.getValue());
        orderInfo = storeMapper.queryOrderTotal("total_amount", queryWrapper);
        resVo.setSuspendAmount(orderInfo.getValue());
        resVo.setSuspendOrderCount(orderInfo.getValue1());


        //充值
        queryWrapper.clear();
        queryWrapper.lambda().eq(Order::getStoreId, storeId).between(Order::getCreateTime, startTime, endTime)
                .eq(Order::getOrderType, OrderType.MEMBER_RECHARGE)
                .eq(Order::getStatus, OrderStatus.SETTLED.getValue());
        orderInfo = storeMapper.queryOrderTotal("total_amount", queryWrapper);
        resVo.setRechargeAmount(orderInfo.getValue());
        resVo.setRechargeOrderCount(orderInfo.getValue1());

        //优惠
        queryWrapper.clear();
        queryWrapper.lambda().eq(Order::getStoreId, storeId).between(Order::getCreateTime, startTime, endTime)
                .eq(Order::getStatus, OrderStatus.SETTLED.getValue()).gt(Order::getTotalWipeZero, 0);
        Tuple<BigDecimal, Long> wipeZeroInfo = storeMapper.queryOrderTotal("total_wipe_zero", queryWrapper);

        queryWrapper.clear();
        queryWrapper.lambda().eq(Order::getStoreId, storeId).between(Order::getCreateTime, startTime, endTime)
                .eq(Order::getStatus, OrderStatus.SETTLED.getValue()).gt(Order::getDiscountValue, 0);
        Tuple<BigDecimal, Long> disCountInfo = storeMapper.queryOrderTotal("discount_value", queryWrapper);

        resVo.getPreferentialList().add(PreferentialVo.builder()
                .type(PreferentialType.DISCOUNT).amount(disCountInfo.getValue()).build());
        resVo.getPreferentialList().add(PreferentialVo.builder()
                .type(PreferentialType.WIPE_ZERO).amount(wipeZeroInfo.getValue()).build());
        resVo.setPreferentialTotal(resVo.getPreferentialList().stream().map(PreferentialVo::getAmount).reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO.setScale(2, RoundingMode.DOWN)));

        //支付方式统计
        QueryWrapper<Order> payQueryWrapper = new QueryWrapper<>();
        payQueryWrapper.eq("b.store_id", storeId).between("b.create_time", startTime, endTime)
                .groupBy("a.pay_type");
        Map<Integer, Tuple3<BigDecimal, Long, Integer>> payList = ArrayUtil.toMap(
                storeMapper.queryPayTotal("amount", "pay_type", payQueryWrapper),
                Tuple3::getValue2, (Tuple3<BigDecimal, Long, Integer> p) -> p);

        resVo.setPayList(Arrays.stream(OrderPayType.values())
                .map(p -> {
                    Tuple3<BigDecimal, Long, Integer> item = payList.get(p.getValue());
                    if (Objects.isNull(item)) {
                        return PayDetailVo.builder().type(p).amount(BigDecimal.ZERO.setScale(2, RoundingMode.DOWN))
                                .count(0L).build();
                    }
                    return PayDetailVo.builder().type(p).amount(item.getValue())
                            .count(item.getValue1()).build();
                }).collect(Collectors.toList()));
        resVo.setPayTotalAmount(resVo.getPayList().stream().map(PayDetailVo::getAmount).reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO.setScale(2, RoundingMode.DOWN)));
        //退款统计
        QueryWrapper<Order> refundQueryWrapper = new QueryWrapper<>();
        refundQueryWrapper.between("b.create_time", startTime, endTime)
                .eq("b.store_id", storeId).groupBy("a.return_pay_type");
        Map<Integer, Tuple3<BigDecimal, Long, Integer>> refundList = ArrayUtil.toMap(
                storeMapper.queryRefundTotal("amount", "return_pay_type", refundQueryWrapper),
                Tuple3::getValue2, (Tuple3<BigDecimal, Long, Integer> p) -> p);

        resVo.setRefundList(Arrays.stream(new OrderPayType[]{OrderPayType.CASH, OrderPayType.SCAN_QRCODE})
                .map(p -> {
                    Tuple3<BigDecimal, Long, Integer> item = refundList.get(p.getValue());
                    if (Objects.isNull(item)) {
                        return PayDetailVo.builder().type(p).amount(BigDecimal.ZERO.setScale(2, RoundingMode.DOWN))
                                .count(0L).build();
                    }
                    return PayDetailVo.builder().type(p).amount(item.getValue())
                            .count(item.getValue1()).build();
                }).collect(Collectors.toList()));

        //分支箱
        QueryWrapper<Order> subQuery = new QueryWrapper<>();
        subQuery.between("b.create_time", startTime, endTime)
                .eq("b.status", OrderStatus.SETTLED.getValue())
                .lambda().eq(Order::getStoreId, storeId);
        resVo.getOrderSubItems().add(storeMapper.queryOrderSubItems("t_order_desk_time", subQuery));
        resVo.getOrderSubItems().get(resVo.getOrderSubItems().size() - 1).setType(OrderType.TABLE_CHARGE);

        resVo.getOrderSubItems().add(storeMapper.queryOrderSubItems("t_order_tutor_time", subQuery));
        resVo.getOrderSubItems().get(resVo.getOrderSubItems().size() - 1).setType(OrderType.TEACHING_ASSISTANT_FEE);

        resVo.getOrderSubItems().add(storeMapper.queryOrderSubItems("t_order_goods", subQuery));
        resVo.getOrderSubItems().get(resVo.getOrderSubItems().size() - 1).setType(OrderType.COMMODITY_PURCHASE);

        resVo.getOrderSubItems().add(storeMapper.queryOrderSubItems("t_order_recharge", subQuery));
        resVo.getOrderSubItems().get(resVo.getOrderSubItems().size() - 1).setType(OrderType.MEMBER_RECHARGE);
        return resVo;
    }

    @Override
    public StoreSwapRecord swapPreview(Long storeId, Date startTime, Date endTime) {
        StoreDashboardResVo dashboardResVo = queryStoreDashboard(storeId, startTime, endTime);

        StoreSwapRecord res = new StoreSwapRecord();
        res.setTotal(dashboardResVo.getTotalAmount());
        res.setStartTime(DateUtils.toLocalDateTime(dashboardResVo.getStarTime()));
        res.setEndTime(DateUtils.toLocalDateTime(dashboardResVo.getEndTime()));
        dashboardResVo.getPayList().stream().filter(payDetailVo -> Objects.equals(payDetailVo.getType(), OrderPayType.CASH))
                .findFirst().ifPresent(p -> {
                    res.setCashTotal(p.getAmount());
                });
        dashboardResVo.getOrderSubItems().stream().filter(p -> Objects.equals(p.getType(), OrderType.TABLE_CHARGE))
                .findFirst().ifPresent(p -> {
                    res.setDeskTotal(p.getAmount());
                });
        dashboardResVo.getOrderSubItems().stream().filter(p -> Objects.equals(p.getType(), OrderType.TEACHING_ASSISTANT_FEE))
                .findFirst().ifPresent(p -> {
                    res.setTutorTotal(p.getAmount());
                });
        dashboardResVo.getOrderSubItems().stream().filter(p -> Objects.equals(p.getType(), OrderType.COMMODITY_PURCHASE))
                .findFirst().ifPresent(p -> {
                    res.setGoodsTotal(p.getAmount());
                });
        dashboardResVo.getOrderSubItems().stream().filter(p -> Objects.equals(p.getType(), OrderType.COMMODITY_PURCHASE))
                .findFirst().ifPresent(p -> {
                    res.setGoodsTotal(p.getAmount());
                });
        res.setSuspendOrderAmount(dashboardResVo.getSuspendAmount());
        res.setSuspendOrderCount(dashboardResVo.getSuspendOrderCount());


        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Order::getStoreId, storeId).between(Order::getCreateTime, dashboardResVo.getStarTime(), dashboardResVo.getEndTime())
                .in(Order::getStatus, OrderStatus.WAIT_SETTLED, OrderStatus.CHARGING);
        Tuple<BigDecimal, Long> orderInfo = storeMapper.queryOrderTotal("total_amount", queryWrapper);
        res.setNotSettledOrderAmount(orderInfo.getValue());
        res.setNotSettledOrderCount(orderInfo.getValue1());
        return res;
    }

    @Override
    public String getApiKey(Long storeId) {
        AssertUtil.isTrue(storeMapper.exists(Store::getStoreId, storeId), "非法参数");
        return aesKey;
    }
}
