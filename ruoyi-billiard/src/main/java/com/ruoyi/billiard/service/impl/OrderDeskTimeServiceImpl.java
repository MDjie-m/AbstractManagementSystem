package com.ruoyi.billiard.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderDeskTimeMapper;
import com.ruoyi.billiard.domain.OrderDeskTime;
import com.ruoyi.billiard.service.IOrderDeskTimeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单计时Service业务层处理
 *
 * @author zhoukeu
 * @date 2024-09-13
 */
@Service
public class OrderDeskTimeServiceImpl implements IOrderDeskTimeService {
    @Autowired
    private OrderDeskTimeMapper orderDeskTimeMapper;

    @Autowired
    private IStoreDeskService storeDeskService;

    /**
     * 查询订单计时
     *
     * @param orderDeskTimeId 订单计时主键
     * @return 订单计时
     */
    @Override
    public OrderDeskTime selectOrderDeskTimeByOrderDeskTimeId(Long orderDeskTimeId) {
        return orderDeskTimeMapper.selectById(orderDeskTimeId);
    }

    /**
     * 查询订单计时列表
     *
     * @param orderDeskTime 订单计时
     * @return 订单计时
     */
    @Override
    public List<OrderDeskTime> selectOrderDeskTimeList(OrderDeskTime orderDeskTime) {
        return orderDeskTimeMapper.selectOrderDeskTimeList(orderDeskTime);
    }

    /**
     * 新增订单计时
     *
     * @param orderDeskTime 订单计时
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrderDeskTime(OrderDeskTime orderDeskTime) {
        SecurityUtils.fillCreateUser(orderDeskTime);
        orderDeskTime.setOrderDeskTimeId(IdUtils.singleNextId());
        return orderDeskTimeMapper.insert(orderDeskTime);
    }

    /**
     * 修改订单计时
     *
     * @param orderDeskTime 订单计时
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOrderDeskTime(OrderDeskTime orderDeskTime) {
        SecurityUtils.fillUpdateUser(orderDeskTime);

        return orderDeskTimeMapper.updateById(orderDeskTime);
    }


    /**
     * 删除订单计时信息
     *
     * @param orderDeskTimeId 订单计时主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOrderDeskTimeByOrderDeskTimeId(Long orderDeskTimeId) {
        return orderDeskTimeMapper.deleteById(orderDeskTimeId);
    }

    @Override
    public List<OrderDeskTime> selectOrderDeskTimeListByOrderId(Long orderId) {
        List<OrderDeskTime> orderDeskTimes = Optional.ofNullable(orderDeskTimeMapper.selectList(orderDeskTimeMapper.query()
                        .eq(OrderDeskTime::getOrderId, orderId).orderByDesc(OrderDeskTime::getCreateTime)))
                .orElse(Collections.emptyList());
        return getOrderDeskTimes(orderDeskTimes);
    }

    @Override
    public List<OrderDeskTime> selectOrderDeskTimeListByOrderIds(List<Long> orderIds) {
        List<OrderDeskTime> orderDeskTimes = Lists.newArrayList();

        if (CollectionUtils.isNotEmpty(orderIds)) {
            orderDeskTimes = Optional.ofNullable(orderDeskTimeMapper.selectList(orderDeskTimeMapper.query()
                            .in(OrderDeskTime::getOrderId, orderIds).orderByDesc(OrderDeskTime::getCreateTime)))
                    .orElse(Collections.emptyList());
        }
        return getOrderDeskTimes(orderDeskTimes);
    }

    private List<OrderDeskTime> getOrderDeskTimes(List<OrderDeskTime> orderDeskTimes) {
        return orderDeskTimes.stream().map(orderDeskTime -> {
            Long deskId = orderDeskTime.getDeskId();
            StoreDesk storeDesk = Optional.ofNullable(storeDeskService.selectStoreDeskByDeskId(deskId)).orElse(new StoreDesk());
            orderDeskTime.setStoreDesk(storeDesk);
            orderDeskTime.setDeskName(storeDesk.getDeskName()+ " " +storeDesk.getDeskNum());
            orderDeskTime.setIsTransfer(Objects.nonNull(orderDeskTime.getFromDeskId()));
            return orderDeskTime;
        }).collect(Collectors.toList());
    }
}
