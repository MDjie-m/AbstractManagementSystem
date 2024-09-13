package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.OrderDeskTime;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单计时Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Mapper
public interface OrderDeskTimeMapper extends MyBaseMapper<OrderDeskTime>
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
     * 删除订单计时
     * 
     * @param orderDeskTimeId 订单计时主键
     * @return 结果
     */
    public int deleteOrderDeskTimeByOrderDeskTimeId(Long orderDeskTimeId);

    /**
     * 批量删除订单计时
     * 
     * @param orderDeskTimeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderDeskTimeByOrderDeskTimeIds(Long[] orderDeskTimeIds);
}
