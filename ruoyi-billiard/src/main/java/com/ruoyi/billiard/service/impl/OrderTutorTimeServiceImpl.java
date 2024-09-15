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
import com.ruoyi.billiard.mapper.OrderTutorTimeMapper;
import com.ruoyi.billiard.domain.OrderTutorTime;
import com.ruoyi.billiard.service.IOrderTutorTimeService;

/**
 * 订单计时Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Service
public class OrderTutorTimeServiceImpl implements IOrderTutorTimeService 
{
    @Autowired
    private OrderTutorTimeMapper orderTutorTimeMapper;

    @Autowired
    private IStoreDeskService storeDeskService;

    /**
     * 查询订单计时
     * 
     * @param orderTutorTimeId 订单计时主键
     * @return 订单计时
     */
    @Override
    public OrderTutorTime selectOrderTutorTimeByOrderTutorTimeId(Long orderTutorTimeId)
    {
        return orderTutorTimeMapper.selectById(orderTutorTimeId);
    }

    /**
     * 查询订单计时列表
     * 
     * @param orderTutorTime 订单计时
     * @return 订单计时
     */
    @Override
    public List<OrderTutorTime> selectOrderTutorTimeList(OrderTutorTime orderTutorTime)
    {
        return orderTutorTimeMapper.selectOrderTutorTimeList(orderTutorTime);
    }

    /**
     * 新增订单计时
     * 
     * @param orderTutorTime 订单计时
     * @return 结果
     */
    @Override
    public int insertOrderTutorTime(OrderTutorTime orderTutorTime)
    {
        SecurityUtils.fillCreateUser(orderTutorTime);
        orderTutorTime.setOrderTutorTimeId(IdUtils.singleNextId());
        return orderTutorTimeMapper.insert(orderTutorTime);
    }

    /**
     * 修改订单计时
     * 
     * @param orderTutorTime 订单计时
     * @return 结果
     */
    @Override
    public int updateOrderTutorTime(OrderTutorTime orderTutorTime)
    {
        SecurityUtils.fillUpdateUser(orderTutorTime);

        return orderTutorTimeMapper.updateOrderTutorTime(orderTutorTime);
    }

    /**
     * 批量删除订单计时
     * 
     * @param orderTutorTimeIds 需要删除的订单计时主键
     * @return 结果
     */
    @Override
    public int deleteOrderTutorTimeByOrderTutorTimeIds(Long[] orderTutorTimeIds)
    {
        return orderTutorTimeMapper.deleteOrderTutorTimeByOrderTutorTimeIds(orderTutorTimeIds);
    }

    /**
     * 删除订单计时信息
     * 
     * @param orderTutorTimeId 订单计时主键
     * @return 结果
     */
    @Override
    public int deleteOrderTutorTimeByOrderTutorTimeId(Long orderTutorTimeId)
    {
        return orderTutorTimeMapper.deleteOrderTutorTimeByOrderTutorTimeId(orderTutorTimeId);
    }

    @Override
    public List<OrderTutorTime> selectOrderTutorTimeListByOrderId(Long orderId) {
        LambdaQueryWrapper<OrderTutorTime> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(OrderTutorTime::getOrderId, orderId);
        List<OrderTutorTime> orderTutorTimes = Optional.ofNullable(orderTutorTimeMapper.selectList(wrapper)).orElse(Collections.emptyList());
        return orderTutorTimes.stream().map(p -> {
            Long deskId = p.getDeskId();
            StoreDesk storeDesk = Optional.ofNullable(storeDeskService.selectStoreDeskByDeskId(deskId)).orElse(new StoreDesk());
            p.setStoreDesk(storeDesk);
            return p;
        }).collect(Collectors.toList());
    }
}
