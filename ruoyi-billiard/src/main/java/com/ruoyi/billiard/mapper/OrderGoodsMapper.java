package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.OrderGoods;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购买商品Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Mapper
public interface OrderGoodsMapper extends MyBaseMapper<OrderGoods>
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
     * 删除购买商品
     * 
     * @param orderDetailId 购买商品主键
     * @return 结果
     */
    public int deleteOrderGoodsByOrderDetailId(Long orderDetailId);

    /**
     * 批量删除购买商品
     * 
     * @param orderDetailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderGoodsByOrderDetailIds(Long[] orderDetailIds);
}
