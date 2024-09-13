package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.OrderTutorTime;

/**
 * 订单计时Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
public interface IOrderTutorTimeService 
{
    /**
     * 查询订单计时
     * 
     * @param orderTutorTimeId 订单计时主键
     * @return 订单计时
     */
    public OrderTutorTime selectOrderTutorTimeByOrderTutorTimeId(Long orderTutorTimeId);

    /**
     * 查询订单计时列表
     * 
     * @param orderTutorTime 订单计时
     * @return 订单计时集合
     */
    public List<OrderTutorTime> selectOrderTutorTimeList(OrderTutorTime orderTutorTime);

    /**
     * 新增订单计时
     * 
     * @param orderTutorTime 订单计时
     * @return 结果
     */
    public int insertOrderTutorTime(OrderTutorTime orderTutorTime);

    /**
     * 修改订单计时
     * 
     * @param orderTutorTime 订单计时
     * @return 结果
     */
    public int updateOrderTutorTime(OrderTutorTime orderTutorTime);

    /**
     * 批量删除订单计时
     * 
     * @param orderTutorTimeIds 需要删除的订单计时主键集合
     * @return 结果
     */
    public int deleteOrderTutorTimeByOrderTutorTimeIds(Long[] orderTutorTimeIds);

    /**
     * 删除订单计时信息
     * 
     * @param orderTutorTimeId 订单计时主键
     * @return 结果
     */
    public int deleteOrderTutorTimeByOrderTutorTimeId(Long orderTutorTimeId);

    /**
     * 根据订单id查询订单教练计时列表
     * @param orderId
     * @return
     */
    List<OrderTutorTime> selectOrderTutorTimeListByOrderId(Long orderId);
}
