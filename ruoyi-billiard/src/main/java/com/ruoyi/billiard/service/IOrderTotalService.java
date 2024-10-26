package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.OrderTotal;

/**
 * 订单结算Service接口
 * 
 * @author ruoyi
 * @date 2024-10-26
 */
public interface IOrderTotalService  extends IService<OrderTotal>
{
    /**
     * 查询订单结算
     * 
     * @param orderTotalId 订单结算主键
     * @return 订单结算
     */
    public OrderTotal selectOrderTotalByOrderTotalId(Long orderTotalId);

    /**
     * 查询订单结算列表
     * 
     * @param orderTotal 订单结算
     * @return 订单结算集合
     */
    public List<OrderTotal> selectOrderTotalList(OrderTotal orderTotal);

    /**
     * 新增订单结算
     * 
     * @param orderTotal 订单结算
     * @return 结果
     */
    public int insertOrderTotal(OrderTotal orderTotal);

    /**
     * 修改订单结算
     * 
     * @param orderTotal 订单结算
     * @return 结果
     */
    public int updateOrderTotal(OrderTotal orderTotal);

    /**
     * 批量删除订单结算
     * 
     * @param orderTotalIds 需要删除的订单结算主键集合
     * @return 结果
     */
    public int deleteOrderTotalByOrderTotalIds(Long[] orderTotalIds);

    /**
     * 删除订单结算信息
     * 
     * @param orderTotalId 订单结算主键
     * @return 结果
     */
    public int deleteOrderTotalByOrderTotalId(Long orderTotalId);

    void calcTotal(Long orderId);

}
