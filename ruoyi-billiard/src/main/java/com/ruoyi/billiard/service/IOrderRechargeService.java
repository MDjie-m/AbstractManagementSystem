package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.OrderRecharge;

/**
 * 会员充值Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
public interface IOrderRechargeService 
{
    /**
     * 查询会员充值
     * 
     * @param orderRechargeId 会员充值主键
     * @return 会员充值
     */
    public OrderRecharge selectOrderRechargeByOrderRechargeId(Long orderRechargeId);

    /**
     * 查询会员充值列表
     * 
     * @param orderRecharge 会员充值
     * @return 会员充值集合
     */
    public List<OrderRecharge> selectOrderRechargeList(OrderRecharge orderRecharge);

    /**
     * 新增会员充值
     * 
     * @param orderRecharge 会员充值
     * @return 结果
     */
    public int insertOrderRecharge(OrderRecharge orderRecharge);

    /**
     * 修改会员充值
     * 
     * @param orderRecharge 会员充值
     * @return 结果
     */
    public int updateOrderRecharge(OrderRecharge orderRecharge);

    /**
     * 批量删除会员充值
     * 
     * @param orderRechargeIds 需要删除的会员充值主键集合
     * @return 结果
     */
    public int deleteOrderRechargeByOrderRechargeIds(Long[] orderRechargeIds);

    /**
     * 删除会员充值信息
     * 
     * @param orderRechargeId 会员充值主键
     * @return 结果
     */
    public int deleteOrderRechargeByOrderRechargeId(Long orderRechargeId);

    /**
     * 根据订单id查询订单会员充值列表
     * @param orderId
     * @return
     */
    List<OrderRecharge> selectOrderRechargeListByOrderId(Long orderId);
}
