package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.OrderPay;

/**
 * 订单预付费Service接口
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
public interface IOrderPayService  extends IService<OrderPay>
{
    /**
     * 查询订单预付费
     * 
     * @param orderPrePayId 订单预付费主键
     * @return 订单预付费
     */
    public OrderPay selectOrderPayByOrderPrePayId(Long orderPrePayId);

    /**
     * 查询订单预付费列表
     * 
     * @param orderPay 订单预付费
     * @return 订单预付费集合
     */
    public List<OrderPay> selectOrderPayList(OrderPay orderPay);

    /**
     * 新增订单预付费
     * 
     * @param orderPay 订单预付费
     * @return 结果
     */
    public int insertOrderPay(OrderPay orderPay);

    /**
     * 修改订单预付费
     * 
     * @param orderPay 订单预付费
     * @return 结果
     */
    public int updateOrderPay(OrderPay orderPay);

    /**
     * 批量删除订单预付费
     * 
     * @param orderPrePayIds 需要删除的订单预付费主键集合
     * @return 结果
     */
    public int deleteOrderPayByOrderPrePayIds(Long[] orderPrePayIds);

    /**
     * 删除订单预付费信息
     * 
     * @param orderPrePayId 订单预付费主键
     * @return 结果
     */
    public int deleteOrderPayByOrderPrePayId(Long orderPrePayId);
}
