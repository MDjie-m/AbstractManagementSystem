package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.OrderRefund;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单退款Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@Mapper
public interface OrderRefundMapper extends MyBaseMapper<OrderRefund>
{
    /**
     * 查询订单退款
     * 
     * @param orderRefundId 订单退款主键
     * @return 订单退款
     */
    public OrderRefund selectOrderRefundByOrderRefundId(Long orderRefundId);

    /**
     * 查询订单退款列表
     * 
     * @param orderRefund 订单退款
     * @return 订单退款集合
     */
    public List<OrderRefund> selectOrderRefundList(OrderRefund orderRefund);


    /**
     * 删除订单退款
     * 
     * @param orderRefundId 订单退款主键
     * @return 结果
     */
    public int deleteOrderRefundByOrderRefundId(Long orderRefundId);

    /**
     * 批量删除订单退款
     * 
     * @param orderRefundIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderRefundByOrderRefundIds(Long[] orderRefundIds);
}
