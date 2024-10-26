package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.OrderTotal;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单结算Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-26
 */
@Mapper
public interface OrderTotalMapper extends MyBaseMapper<OrderTotal>
{
    /**
     * 查询订单结算
     * 
     * @param orderTotalId 订单结算主键
     * @return 订单结算
     */
    public OrderTotal selectOrderTotalByOrderTotalId(Long orderTotalId);

    /**
     * 查询订单结算列表
     * 
     * @param orderTotal 订单结算
     * @return 订单结算集合
     */
    public List<OrderTotal> selectOrderTotalList(OrderTotal orderTotal);


    /**
     * 删除订单结算
     * 
     * @param orderTotalId 订单结算主键
     * @return 结果
     */
    public int deleteOrderTotalByOrderTotalId(Long orderTotalId);

    /**
     * 批量删除订单结算
     * 
     * @param orderTotalIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderTotalByOrderTotalIds(Long[] orderTotalIds);
}
