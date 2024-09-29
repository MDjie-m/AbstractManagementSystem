package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.OrderPay;
import com.ruoyi.billiard.service.IOrderPayService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.OrderPayMapper;
/**
 * 订单预付费Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@Service
public class OrderPayServiceImpl extends  ServiceImpl<OrderPayMapper,OrderPay> implements IOrderPayService
{


    /**
     * 查询订单预付费
     * 
     * @param orderPrePayId 订单预付费主键
     * @return 订单预付费
     */
    @Override
    public OrderPay selectOrderPayByOrderPrePayId(Long orderPrePayId)
    {
        return baseMapper.selectById(orderPrePayId);
    }

    /**
     * 查询订单预付费列表
     * 
     * @param orderPay 订单预付费
     * @return 订单预付费
     */
    @Override
    public List<OrderPay> selectOrderPayList(OrderPay orderPay)
    {
        return baseMapper.selectOrderPayList(orderPay);
    }

    /**
     * 新增订单预付费
     * 
     * @param orderPay 订单预付费
     * @return 结果
     */
    @Override
    public int insertOrderPay(OrderPay orderPay)
    {
        SecurityUtils.fillCreateUser(orderPay);
        orderPay.setOrderPrePayId(IdUtils.singleNextId());
        return baseMapper.insert(orderPay);
    }

    /**
     * 修改订单预付费
     * 
     * @param orderPay 订单预付费
     * @return 结果
     */
    @Override
    public int updateOrderPay(OrderPay orderPay)
    {
        SecurityUtils.fillUpdateUser(orderPay);

        return baseMapper.updateById(orderPay);
    }

    /**
     * 批量删除订单预付费
     * 
     * @param orderPrePayIds 需要删除的订单预付费主键
     * @return 结果
     */
    @Override
    public int deleteOrderPayByOrderPrePayIds(Long[] orderPrePayIds)
    {
        return baseMapper.deleteOrderPayByOrderPrePayIds(orderPrePayIds);
    }

    /**
     * 删除订单预付费信息
     * 
     * @param orderPrePayId 订单预付费主键
     * @return 结果
     */
    @Override
    public int deleteOrderPayByOrderPrePayId(Long orderPrePayId)
    {
        return baseMapper.deleteOrderPayByOrderPrePayId(orderPrePayId);
    }
}
