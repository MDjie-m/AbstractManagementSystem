package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.OrderRefund;

/**
 * 订单退款Service接口
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
public interface IOrderRefundService  extends IService<OrderRefund>
{
    /**
     * 查询订单退款
     * 
     * @param orderRefundId 订单退款主键
     * @return 订单退款
     */
    public OrderRefund selectOrderRefundByOrderRefundId(Long orderRefundId);

    /**
     * 查询订单退款列表
     * 
     * @param orderRefund 订单退款
     * @return 订单退款集合
     */
    public List<OrderRefund> selectOrderRefundList(OrderRefund orderRefund);

    /**
     * 新增订单退款
     * 
     * @param orderRefund 订单退款
     * @return 结果
     */
    public int insertOrderRefund(OrderRefund orderRefund);

    /**
     * 修改订单退款
     * 
     * @param orderRefund 订单退款
     * @return 结果
     */
    public int updateOrderRefund(OrderRefund orderRefund);

    /**
     * 批量删除订单退款
     * 
     * @param orderRefundIds 需要删除的订单退款主键集合
     * @return 结果
     */
    public int deleteOrderRefundByOrderRefundIds(Long[] orderRefundIds);

    /**
     * 删除订单退款信息
     * 
     * @param orderRefundId 订单退款主键
     * @return 结果
     */
    public int deleteOrderRefundByOrderRefundId(Long orderRefundId);
}
