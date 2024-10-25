package com.ruoyi.billiard.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.billiard.domain.OrderTutorTime;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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


    Map<String, Object> selectOrderGoodsStatistics(@Param("status") Integer status, @Param("storeId") Long storeId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    Long selectWorkedCount(@Param("storeId") Long storeId, @Param("startTime") Date startTime);
}
