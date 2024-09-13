package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.OrderTutorTime;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单计时Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Mapper
public interface OrderTutorTimeMapper extends MyBaseMapper<OrderTutorTime>
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
     * 删除订单计时
     * 
     * @param orderTutorTimeId 订单计时主键
     * @return 结果
     */
    public int deleteOrderTutorTimeByOrderTutorTimeId(Long orderTutorTimeId);

    /**
     * 批量删除订单计时
     * 
     * @param orderTutorTimeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderTutorTimeByOrderTutorTimeIds(Long[] orderTutorTimeIds);
}
