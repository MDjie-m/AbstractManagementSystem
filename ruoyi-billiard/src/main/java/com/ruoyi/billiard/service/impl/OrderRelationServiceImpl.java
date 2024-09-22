package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderRelationMapper;
import com.ruoyi.billiard.domain.OrderRelation;
import com.ruoyi.billiard.service.IOrderRelationService;

/**
 * 订单关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-23
 */
@Service
public class OrderRelationServiceImpl implements IOrderRelationService 
{
    @Autowired
    private OrderRelationMapper orderRelationMapper;

    /**
     * 查询订单关系
     * 
     * @param orderRelationId 订单关系主键
     * @return 订单关系
     */
    @Override
    public OrderRelation selectOrderRelationByOrderRelationId(Long orderRelationId)
    {
        return orderRelationMapper.selectById(orderRelationId);
    }

    /**
     * 查询订单关系列表
     * 
     * @param orderRelation 订单关系
     * @return 订单关系
     */
    @Override
    public List<OrderRelation> selectOrderRelationList(OrderRelation orderRelation)
    {
        return orderRelationMapper.selectOrderRelationList(orderRelation);
    }

    /**
     * 新增订单关系
     * 
     * @param orderRelation 订单关系
     * @return 结果
     */
    @Override
    public int insertOrderRelation(OrderRelation orderRelation)
    {
        SecurityUtils.fillCreateUser(orderRelation);
        orderRelation.setOrderRelationId(IdUtils.singleNextId());
        return orderRelationMapper.insert(orderRelation);
    }

    /**
     * 修改订单关系
     * 
     * @param orderRelation 订单关系
     * @return 结果
     */
    @Override
    public int updateOrderRelation(OrderRelation orderRelation)
    {
        SecurityUtils.fillUpdateUser(orderRelation);

        return orderRelationMapper.updateById(orderRelation);
    }

    /**
     * 批量删除订单关系
     * 
     * @param orderRelationIds 需要删除的订单关系主键
     * @return 结果
     */
    @Override
    public int deleteOrderRelationByOrderRelationIds(Long[] orderRelationIds)
    {
        return orderRelationMapper.deleteOrderRelationByOrderRelationIds(orderRelationIds);
    }

    /**
     * 删除订单关系信息
     * 
     * @param orderRelationId 订单关系主键
     * @return 结果
     */
    @Override
    public int deleteOrderRelationByOrderRelationId(Long orderRelationId)
    {
        return orderRelationMapper.deleteOrderRelationByOrderRelationId(orderRelationId);
    }
}
