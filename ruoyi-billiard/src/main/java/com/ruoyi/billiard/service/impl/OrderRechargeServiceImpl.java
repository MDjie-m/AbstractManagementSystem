package com.ruoyi.billiard.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderRechargeMapper;
import com.ruoyi.billiard.domain.OrderRecharge;
import com.ruoyi.billiard.service.IOrderRechargeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员充值Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Service
public class OrderRechargeServiceImpl implements IOrderRechargeService 
{
    @Autowired
    private OrderRechargeMapper orderRechargeMapper;

    /**
     * 查询会员充值
     * 
     * @param orderRechargeId 会员充值主键
     * @return 会员充值
     */
    @Override
    public OrderRecharge selectOrderRechargeByOrderRechargeId(Long orderRechargeId)
    {
        return orderRechargeMapper.selectById(orderRechargeId);
    }

    /**
     * 查询会员充值列表
     * 
     * @param orderRecharge 会员充值
     * @return 会员充值
     */
    @Override
    public List<OrderRecharge> selectOrderRechargeList(OrderRecharge orderRecharge)
    {
        return orderRechargeMapper.selectOrderRechargeList(orderRecharge);
    }

    /**
     * 新增会员充值
     * 
     * @param orderRecharge 会员充值
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrderRecharge(OrderRecharge orderRecharge)
    {
        SecurityUtils.fillCreateUser(orderRecharge);
        orderRecharge.setOrderRechargeId(IdUtils.singleNextId());
        return orderRechargeMapper.insertOrderRecharge(orderRecharge);
    }

    /**
     * 修改会员充值
     * 
     * @param orderRecharge 会员充值
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOrderRecharge(OrderRecharge orderRecharge)
    {
        SecurityUtils.fillUpdateUser(orderRecharge);

        return orderRechargeMapper.updateOrderRecharge(orderRecharge);
    }

    /**
     * 批量删除会员充值
     * 
     * @param orderRechargeIds 需要删除的会员充值主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOrderRechargeByOrderRechargeIds(Long[] orderRechargeIds)
    {
        return orderRechargeMapper.deleteOrderRechargeByOrderRechargeIds(orderRechargeIds);
    }

    /**
     * 删除会员充值信息
     * 
     * @param orderRechargeId 会员充值主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOrderRechargeByOrderRechargeId(Long orderRechargeId)
    {
        return orderRechargeMapper.deleteOrderRechargeByOrderRechargeId(orderRechargeId);
    }

    @Override
    public List<OrderRecharge> selectOrderRechargeListByOrderId(Long orderId) {
        return Optional.ofNullable(orderRechargeMapper.selectList(orderRechargeMapper.query()
                .eq(OrderRecharge::getOrderId, orderId).orderByDesc(OrderRecharge::getCreateTime)))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<OrderRecharge> selectOrderRechargeByMemberIds(Long[] memberIds) {
        return Optional.ofNullable(orderRechargeMapper.selectList(orderRechargeMapper.query()
                .in(OrderRecharge::getMemberId, Arrays.asList(memberIds))))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<OrderRecharge> selectOrderRechargeListByOrderIds(List<Long> orderIds) {
        return Optional.ofNullable(orderRechargeMapper.selectList(orderRechargeMapper.query()
                        .in(OrderRecharge::getOrderId, orderIds).orderByDesc(OrderRecharge::getCreateTime)))
                .orElse(Collections.emptyList());
    }
}
