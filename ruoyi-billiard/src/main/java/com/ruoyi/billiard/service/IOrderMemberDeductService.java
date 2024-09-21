package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.OrderMemberDeduct;

/**
 * 会员付款记录Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-21
 */
public interface IOrderMemberDeductService 
{
    /**
     * 查询会员付款记录
     * 
     * @param orderMemberDeductId 会员付款记录主键
     * @return 会员付款记录
     */
    public OrderMemberDeduct selectOrderMemberDeductByOrderMemberDeductId(Long orderMemberDeductId);

    /**
     * 查询会员付款记录列表
     * 
     * @param orderMemberDeduct 会员付款记录
     * @return 会员付款记录集合
     */
    public List<OrderMemberDeduct> selectOrderMemberDeductList(OrderMemberDeduct orderMemberDeduct);

    /**
     * 新增会员付款记录
     * 
     * @param orderMemberDeduct 会员付款记录
     * @return 结果
     */
    public int insertOrderMemberDeduct(OrderMemberDeduct orderMemberDeduct);

    /**
     * 修改会员付款记录
     * 
     * @param orderMemberDeduct 会员付款记录
     * @return 结果
     */
    public int updateOrderMemberDeduct(OrderMemberDeduct orderMemberDeduct);

    /**
     * 批量删除会员付款记录
     * 
     * @param orderMemberDeductIds 需要删除的会员付款记录主键集合
     * @return 结果
     */
    public int deleteOrderMemberDeductByOrderMemberDeductIds(Long[] orderMemberDeductIds);

    /**
     * 删除会员付款记录信息
     * 
     * @param orderMemberDeductId 会员付款记录主键
     * @return 结果
     */
    public int deleteOrderMemberDeductByOrderMemberDeductId(Long orderMemberDeductId);
}
