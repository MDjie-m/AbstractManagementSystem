package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.OrderDeskTime;

/**
 * 订单计时Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
public interface IOrderDeskTimeService 
{
    /**
     * 查询订单计时
     * 
     * @param orderDeskTimeId 订单计时主键
     * @return 订单计时
     */
    public OrderDeskTime selectOrderDeskTimeByOrderDeskTimeId(Long orderDeskTimeId);

    /**
     * 查询订单计时列表
     * 
     * @param orderDeskTime 订单计时
     * @return 订单计时集合
     */
    public List<OrderDeskTime> selectOrderDeskTimeList(OrderDeskTime orderDeskTime);

    /**
     * 新增订单计时
     * 
     * @param orderDeskTime 订单计时
     * @return 结果
     */
    public int insertOrderDeskTime(OrderDeskTime orderDeskTime);

    /**
     * 修改订单计时
     * 
     * @param orderDeskTime 订单计时
     * @return 结果
     */
    public int updateOrderDeskTime(OrderDeskTime orderDeskTime);

    /**
     * 删除订单计时信息
     * 
     * @param orderDeskTimeId 订单计时主键
     * @return 结果
     */
    public int deleteOrderDeskTimeByOrderDeskTimeId(Long orderDeskTimeId);

    /**
     * 根据订单id查询球桌计时数据
     * @param orderId
     * @return
     */
    List<OrderDeskTime> selectOrderDeskTimeListByOrderId(Long orderId);

    /**
     * 根据订单ids查询球桌计时数据
     * @param orderIds
     * @return
     */
    List<OrderDeskTime> selectOrderDeskTimeListByOrderIds(List<Long> orderIds);
}
