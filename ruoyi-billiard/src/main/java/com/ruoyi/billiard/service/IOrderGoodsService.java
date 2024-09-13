package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.OrderGoods;

/**
 * 购买商品Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
public interface IOrderGoodsService 
{
    /**
     * 查询购买商品
     * 
     * @param orderDetailId 购买商品主键
     * @return 购买商品
     */
    public OrderGoods selectOrderGoodsByOrderDetailId(Long orderDetailId);

    /**
     * 查询购买商品列表
     * 
     * @param orderGoods 购买商品
     * @return 购买商品集合
     */
    public List<OrderGoods> selectOrderGoodsList(OrderGoods orderGoods);

    /**
     * 新增购买商品
     * 
     * @param orderGoods 购买商品
     * @return 结果
     */
    public int insertOrderGoods(OrderGoods orderGoods);

    /**
     * 修改购买商品
     * 
     * @param orderGoods 购买商品
     * @return 结果
     */
    public int updateOrderGoods(OrderGoods orderGoods);

    /**
     * 批量删除购买商品
     * 
     * @param orderDetailIds 需要删除的购买商品主键集合
     * @return 结果
     */
    public int deleteOrderGoodsByOrderDetailIds(Long[] orderDetailIds);

    /**
     * 删除购买商品信息
     * 
     * @param orderDetailId 购买商品主键
     * @return 结果
     */
    public int deleteOrderGoodsByOrderDetailId(Long orderDetailId);

    /**
     * 根据订单id查询订单商品列表
     * @param orderId
     * @return
     */
    List<OrderGoods> selectOrderGoodsListByOrderId(Long orderId);
}
