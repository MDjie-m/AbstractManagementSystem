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
     * 查询订单计时列表
     * 
     * @param orderTutorTime 订单计时
     * @return 订单计时集合
     */
    public List<OrderTutorTime> selectOrderTutorTimeList(OrderTutorTime orderTutorTime);


}
