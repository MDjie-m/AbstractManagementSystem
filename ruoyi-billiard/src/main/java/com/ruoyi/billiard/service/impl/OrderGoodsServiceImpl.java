package com.ruoyi.billiard.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.service.IGoodsService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderGoodsMapper;
import com.ruoyi.billiard.domain.OrderGoods;
import com.ruoyi.billiard.service.IOrderGoodsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购买商品Service业务层处理
 *
 * @author zhoukeu
 * @date 2024-09-13
 */
@Service
public class OrderGoodsServiceImpl implements IOrderGoodsService {
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private IGoodsService goodsService;

    /**
     * 查询购买商品
     *
     * @param orderDetailId 购买商品主键
     * @return 购买商品
     */
    @Override
    public OrderGoods selectOrderGoodsByOrderDetailId(Long orderDetailId) {
        return orderGoodsMapper.selectById(orderDetailId);
    }

    /**
     * 查询购买商品列表
     *
     * @param orderGoods 购买商品
     * @return 购买商品
     */
    @Override
    public List<OrderGoods> selectOrderGoodsList(OrderGoods orderGoods) {
        return orderGoodsMapper.selectOrderGoodsList(orderGoods);
    }

    /**
     * 新增购买商品
     *
     * @param orderGoods 购买商品
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrderGoods(OrderGoods orderGoods) {
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
    @Transactional(rollbackFor = Exception.class)
    public int updateOrderGoods(OrderGoods orderGoods) {
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
    @Transactional(rollbackFor = Exception.class)
    public int deleteOrderGoodsByOrderDetailIds(Long[] orderDetailIds) {
        return orderGoodsMapper.deleteOrderGoodsByOrderDetailIds(orderDetailIds);
    }

    /**
     * 删除购买商品信息
     *
     * @param orderDetailId 购买商品主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOrderGoodsByOrderDetailId(Long orderDetailId) {
        return orderGoodsMapper.deleteOrderGoodsByOrderDetailId(orderDetailId);
    }

    @Override
    public List<OrderGoods> selectOrderGoodsListByOrderId(Long orderId) {
        List<OrderGoods> orderGoodsList = Optional.ofNullable(orderGoodsMapper.selectList(orderGoodsMapper.query().eq(OrderGoods::getOrderId, orderId))).orElse(Collections.emptyList());
        return orderGoodsList.stream().map(p -> {
            Long goodsId = p.getGoodsId();
            Goods goods = Optional.ofNullable(goodsService.selectGoodsByGoodsId(goodsId)).orElse(new Goods());
            p.setGoods(goods);
            return p;
        }).collect(Collectors.toList());
    }
}
