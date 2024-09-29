package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.OrderPay;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单预付费Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@Mapper
public interface OrderPayMapper extends MyBaseMapper<OrderPay>
{
    /**
     * 查询订单预付费
     * 
     * @param orderPrePayId 订单预付费主键
     * @return 订单预付费
     */
    public OrderPay selectOrderPayByOrderPrePayId(Long orderPrePayId);

    /**
     * 查询订单预付费列表
     * 
     * @param orderPay 订单预付费
     * @return 订单预付费集合
     */
    public List<OrderPay> selectOrderPayList(OrderPay orderPay);


    /**
     * 删除订单预付费
     * 
     * @param orderPrePayId 订单预付费主键
     * @return 结果
     */
    public int deleteOrderPayByOrderPrePayId(Long orderPrePayId);

    /**
     * 批量删除订单预付费
     * 
     * @param orderPrePayIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderPayByOrderPrePayIds(Long[] orderPrePayIds);
}
