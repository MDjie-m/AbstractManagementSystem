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
     * 查询订单计时列表
     * 
     * @param orderDeskTime 订单计时
     * @return 订单计时集合
     */
    public List<OrderDeskTime> selectOrderDeskTimeList(OrderDeskTime orderDeskTime);

}
