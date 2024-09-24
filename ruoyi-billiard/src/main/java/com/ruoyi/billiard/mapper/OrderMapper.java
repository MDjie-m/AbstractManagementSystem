package com.ruoyi.billiard.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.OrderMemberDeduct;
import com.ruoyi.billiard.domain.OrderRecharge;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 订单Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Mapper
public interface OrderMapper extends MyBaseMapper<Order>
{
    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    public Order selectOrderByOrderId(Long orderId);

    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单集合
     */
    public List<Order> selectOrderList(Order order);


    /**
     * 删除订单
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    public int deleteOrderByOrderId(Long orderId);

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderByOrderIds(Long[] orderIds);

    Order selectCurrentRelationOrder(Long deskId);

    List<OrderRecharge> selectMemberRechargeOrderList(@Param(Constants.WRAPPER) QueryWrapper<Order> orderLambdaQueryWrapper);

    List<OrderMemberDeduct> selectDeductOrderList(@Param(Constants.WRAPPER) QueryWrapper<Order> orderQueryWrapper);

}
