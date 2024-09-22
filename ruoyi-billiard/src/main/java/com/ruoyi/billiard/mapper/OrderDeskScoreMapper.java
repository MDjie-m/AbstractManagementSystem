package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.OrderDeskScore;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 台球桌比分Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-23
 */
@Mapper
public interface OrderDeskScoreMapper extends MyBaseMapper<OrderDeskScore>
{

    /**
     * 查询台球桌比分列表
     * 
     * @param orderDeskScore 台球桌比分
     * @return 台球桌比分集合
     */
    public List<OrderDeskScore> selectOrderDeskScoreList(OrderDeskScore orderDeskScore);




}
