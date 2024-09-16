package com.ruoyi.billiard.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderDeskTimeMapper;
import com.ruoyi.billiard.domain.OrderDeskTime;
import com.ruoyi.billiard.service.IOrderDeskTimeService;

/**
 * 订单计时Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Service
public class OrderDeskTimeServiceImpl implements IOrderDeskTimeService 
{
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
    public OrderDeskTime selectOrderDeskTimeByOrderDeskTimeId(Long orderDeskTimeId)
    {
        return orderDeskTimeMapper.selectById(orderDeskTimeId);
    }

    /**
     * 查询订单计时列表
     * 
     * @param orderDeskTime 订单计时
     * @return 订单计时
     */
    @Override
    public List<OrderDeskTime> selectOrderDeskTimeList(OrderDeskTime orderDeskTime)
    {
        return orderDeskTimeMapper.selectOrderDeskTimeList(orderDeskTime);
    }

    /**
     * 新增订单计时
     * 
     * @param orderDeskTime 订单计时
     * @return 结果
     */
    @Override
    public int insertOrderDeskTime(OrderDeskTime orderDeskTime)
    {
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
    public int updateOrderDeskTime(OrderDeskTime orderDeskTime)
    {
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
    public int deleteOrderDeskTimeByOrderDeskTimeId(Long orderDeskTimeId)
    {
        return orderDeskTimeMapper.deleteById(orderDeskTimeId);
    }

    @Override
    public List<OrderDeskTime> selectOrderDeskTimeListByOrderId(Long orderId) {
        LambdaQueryWrapper<OrderDeskTime> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(OrderDeskTime::getOrderId, orderId);
        List<OrderDeskTime> orderDeskTimes = Optional.ofNullable(orderDeskTimeMapper.selectList(wrapper)).orElse(Collections.emptyList());
        return orderDeskTimes.stream().map(orderDeskTime -> {
            Long deskId = orderDeskTime.getDeskId();
            StoreDesk storeDesk = Optional.ofNullable(storeDeskService.selectStoreDeskByDeskId(deskId)).orElse(new StoreDesk());
            orderDeskTime.setStoreDesk(storeDesk);
            return orderDeskTime;
        }).collect(Collectors.toList());
    }
}
