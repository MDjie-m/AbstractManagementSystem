package com.ruoyi.billiard.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.billiard.domain.OrderGoods;
import com.ruoyi.common.core.domain.model.Tuple;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    Map<String, Object> selectOrderGoodsStatistics(@Param("status") Integer status, @Param("storeId") Long storeId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    Tuple<BigDecimal,BigDecimal> queryGoodsCount(@Param("storeId") Long storeId, @Param("startTime") Date value);


}
