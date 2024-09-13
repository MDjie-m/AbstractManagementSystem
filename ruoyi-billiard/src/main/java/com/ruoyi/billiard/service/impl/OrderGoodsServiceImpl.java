package com.ruoyi.billiard.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderGoodsMapper;
import com.ruoyi.billiard.domain.OrderGoods;
import com.ruoyi.billiard.service.IOrderGoodsService;

/**
 * 购买商品Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Service
public class OrderGoodsServiceImpl implements IOrderGoodsService 
{
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    /**
     * 查询购买商品
     * 
     * @param orderDetailId 购买商品主键
     * @return 购买商品
     */
    @Override
    public OrderGoods selectOrderGoodsByOrderDetailId(Long orderDetailId)
    {
        return orderGoodsMapper.selectById(orderDetailId);
    }

    /**
     * 查询购买商品列表
     * 
     * @param orderGoods 购买商品
     * @return 购买商品
     */
    @Override
    public List<OrderGoods> selectOrderGoodsList(OrderGoods orderGoods)
    {
        return orderGoodsMapper.selectOrderGoodsList(orderGoods);
    }

    /**
     * 新增购买商品
     * 
     * @param orderGoods 购买商品
     * @return 结果
     */
    @Override
    public int insertOrderGoods(OrderGoods orderGoods)
    {
        SecurityUtils.fillCreateUser(orderGoods);
        orderGoods.setOrderDetailId(IdUtils.singleNextId());
        return orderGoodsMapper.insertOrderGoods(orderGoods);
    }

    /**
     * 修改购买商品
     * 
     * @param orderGoods 购买商品
     * @return 结果
     */
    @Override
    public int updateOrderGoods(OrderGoods orderGoods)
    {
        SecurityUtils.fillUpdateUser(orderGoods);

        return orderGoodsMapper.updateOrderGoods(orderGoods);
    }

    /**
     * 批量删除购买商品
     * 
     * @param orderDetailIds 需要删除的购买商品主键
     * @return 结果
     */
    @Override
    public int deleteOrderGoodsByOrderDetailIds(Long[] orderDetailIds)
    {
        return orderGoodsMapper.deleteOrderGoodsByOrderDetailIds(orderDetailIds);
    }

    /**
     * 删除购买商品信息
     * 
     * @param orderDetailId 购买商品主键
     * @return 结果
     */
    @Override
    public int deleteOrderGoodsByOrderDetailId(Long orderDetailId)
    {
        return orderGoodsMapper.deleteOrderGoodsByOrderDetailId(orderDetailId);
    }

    @Override
    public List<OrderGoods> selectOrderGoodsListByOrderId(Long orderId) {
        LambdaQueryWrapper<OrderGoods> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(OrderGoods::getOrderId, orderId);
        return Optional.ofNullable(orderGoodsMapper.selectList(wrapper)).orElse(Collections.emptyList());
    }
}
