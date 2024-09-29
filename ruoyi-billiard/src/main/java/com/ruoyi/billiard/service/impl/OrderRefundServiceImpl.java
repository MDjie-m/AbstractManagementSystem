package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.OrderRefund;
import com.ruoyi.billiard.service.IOrderRefundService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.OrderRefundMapper;
/**
 * 订单退款Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@Service
public class OrderRefundServiceImpl extends  ServiceImpl<OrderRefundMapper,OrderRefund> implements IOrderRefundService
{


    /**
     * 查询订单退款
     * 
     * @param orderRefundId 订单退款主键
     * @return 订单退款
     */
    @Override
    public OrderRefund selectOrderRefundByOrderRefundId(Long orderRefundId)
    {
        return baseMapper.selectById(orderRefundId);
    }

    /**
     * 查询订单退款列表
     * 
     * @param orderRefund 订单退款
     * @return 订单退款
     */
    @Override
    public List<OrderRefund> selectOrderRefundList(OrderRefund orderRefund)
    {
        return baseMapper.selectOrderRefundList(orderRefund);
    }

    /**
     * 新增订单退款
     * 
     * @param orderRefund 订单退款
     * @return 结果
     */
    @Override
    public int insertOrderRefund(OrderRefund orderRefund)
    {
        SecurityUtils.fillCreateUser(orderRefund);
        orderRefund.setOrderRefundId(IdUtils.singleNextId());
        return baseMapper.insert(orderRefund);
    }

    /**
     * 修改订单退款
     * 
     * @param orderRefund 订单退款
     * @return 结果
     */
    @Override
    public int updateOrderRefund(OrderRefund orderRefund)
    {
        SecurityUtils.fillUpdateUser(orderRefund);

        return baseMapper.updateById(orderRefund);
    }

    /**
     * 批量删除订单退款
     * 
     * @param orderRefundIds 需要删除的订单退款主键
     * @return 结果
     */
    @Override
    public int deleteOrderRefundByOrderRefundIds(Long[] orderRefundIds)
    {
        return baseMapper.deleteOrderRefundByOrderRefundIds(orderRefundIds);
    }

    /**
     * 删除订单退款信息
     * 
     * @param orderRefundId 订单退款主键
     * @return 结果
     */
    @Override
    public int deleteOrderRefundByOrderRefundId(Long orderRefundId)
    {
        return baseMapper.deleteOrderRefundByOrderRefundId(orderRefundId);
    }
}
