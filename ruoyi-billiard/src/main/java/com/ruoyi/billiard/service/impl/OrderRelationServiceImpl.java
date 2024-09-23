package com.ruoyi.billiard.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderRelationMapper;
import com.ruoyi.billiard.domain.OrderRelation;
import com.ruoyi.billiard.service.IOrderRelationService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单关系Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-23
 */
@Service
public class OrderRelationServiceImpl extends ServiceImpl<OrderRelationMapper, OrderRelation> implements IOrderRelationService {


    /**
     * 查询订单关系
     *
     * @param orderRelationId 订单关系主键
     * @return 订单关系
     */
    @Override
    public OrderRelation selectOrderRelationByOrderRelationId(Long orderRelationId) {
        return baseMapper.selectById(orderRelationId);
    }

    /**
     * 查询订单关系列表
     *
     * @param orderRelation 订单关系
     * @return 订单关系
     */
    @Override
    public List<OrderRelation> selectOrderRelationList(OrderRelation orderRelation) {
        return baseMapper.selectOrderRelationList(orderRelation);
    }

    /**
     * 新增订单关系
     *
     * @param orderRelation 订单关系
     * @return 结果
     */
    @Override
    public int insertOrderRelation(OrderRelation orderRelation) {
        SecurityUtils.fillCreateUser(orderRelation);
        orderRelation.setOrderRelationId(IdUtils.singleNextId());
        return baseMapper.insert(orderRelation);
    }

    /**
     * 修改订单关系
     *
     * @param orderRelation 订单关系
     * @return 结果
     */
    @Override
    public int updateOrderRelation(OrderRelation orderRelation) {
        SecurityUtils.fillUpdateUser(orderRelation);

        return baseMapper.updateById(orderRelation);
    }

    /**
     * 批量删除订单关系
     *
     * @param orderRelationIds 需要删除的订单关系主键
     * @return 结果
     */
    @Override
    public int deleteOrderRelationByOrderRelationIds(Long[] orderRelationIds) {
        return baseMapper.deleteOrderRelationByOrderRelationIds(orderRelationIds);
    }

    /**
     * 删除订单关系信息
     *
     * @param orderRelationId 订单关系主键
     * @return 结果
     */
    @Override
    public int deleteOrderRelationByOrderRelationId(Long orderRelationId) {
        return baseMapper.deleteOrderRelationByOrderRelationId(orderRelationId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRelation(Long newOrderId, Long oldOrderId) {
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query()
                .eq(OrderRelation::getSubOrderId, newOrderId)), "目标台桌已经并台台过，当前台桌无法并过去.");
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query()
                .eq(OrderRelation::getSubOrderId, oldOrderId)), "当前台桌已经并台过，无法再并台.");
        OrderRelation relation = OrderRelation.builder().orderRelationId(IdUtils.singleNextId())
                .mainOrderId(newOrderId)
                .subOrderId(oldOrderId)
                .build();
        SecurityUtils.fillCreateUser(relation);
        baseMapper.insert(relation);
    }
}
