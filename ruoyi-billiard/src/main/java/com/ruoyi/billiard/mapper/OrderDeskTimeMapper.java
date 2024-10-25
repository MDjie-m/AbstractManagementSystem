package com.ruoyi.billiard.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.billiard.domain.OrderDeskTime;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.common.core.domain.model.KeyValueVo;
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
public interface OrderDeskTimeMapper extends MyBaseMapper<OrderDeskTime>
{

    /**
     * 查询订单计时列表
     * 
     * @param orderDeskTime 订单计时
     * @return 订单计时集合
     */
    public List<OrderDeskTime> selectOrderDeskTimeList(OrderDeskTime orderDeskTime);

    Map<String, Object> selectOrderDeskTimeStatistics(@Param("status") Integer status, @Param("storeId") Long storeId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<StoreDesk> selectDeskByOrderIds(@Param("orderIds") List<Long> orderIds);
    List<KeyValueVo<Long,Long>> getDeskCalcTimes(@Param("orderIds") List<Long > orderIds);

    Long selectTotalTime(@Param("storeId") Long storeId, @Param("startTime") Date startTime);
}
