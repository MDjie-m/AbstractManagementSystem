package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.OrderRelation;

/**
 * 订单关系Service接口
 * 
 * @author ruoyi
 * @date 2024-09-23
 */
public interface IOrderRelationService 
{
    /**
     * 查询订单关系
     * 
     * @param orderRelationId 订单关系主键
     * @return 订单关系
     */
    public OrderRelation selectOrderRelationByOrderRelationId(Long orderRelationId);

    /**
     * 查询订单关系列表
     * 
     * @param orderRelation 订单关系
     * @return 订单关系集合
     */
    public List<OrderRelation> selectOrderRelationList(OrderRelation orderRelation);

    /**
     * 新增订单关系
     * 
     * @param orderRelation 订单关系
     * @return 结果
     */
    public int insertOrderRelation(OrderRelation orderRelation);

    /**
     * 修改订单关系
     * 
     * @param orderRelation 订单关系
     * @return 结果
     */
    public int updateOrderRelation(OrderRelation orderRelation);

    /**
     * 批量删除订单关系
     * 
     * @param orderRelationIds 需要删除的订单关系主键集合
     * @return 结果
     */
    public int deleteOrderRelationByOrderRelationIds(Long[] orderRelationIds);

    /**
     * 删除订单关系信息
     * 
     * @param orderRelationId 订单关系主键
     * @return 结果
     */
    public int deleteOrderRelationByOrderRelationId(Long orderRelationId);
}
