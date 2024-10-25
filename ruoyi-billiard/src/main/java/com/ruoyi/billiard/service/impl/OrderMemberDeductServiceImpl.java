package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderMemberDeductMapper;
import com.ruoyi.billiard.domain.OrderMemberDeduct;
import com.ruoyi.billiard.service.IOrderMemberDeductService;

/**
 * 会员付款记录Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-21
 */
@Service
public class OrderMemberDeductServiceImpl implements IOrderMemberDeductService 
{
    @Autowired
    private OrderMemberDeductMapper orderMemberDeductMapper;

    /**
     * 查询会员付款记录
     * 
     * @param orderMemberDeductId 会员付款记录主键
     * @return 会员付款记录
     */
    @Override
    public OrderMemberDeduct selectOrderMemberDeductByOrderMemberDeductId(Long orderMemberDeductId)
    {
        return orderMemberDeductMapper.selectById(orderMemberDeductId);
    }

    /**
     * 查询会员付款记录列表
     * 
     * @param orderMemberDeduct 会员付款记录
     * @return 会员付款记录
     */
    @Override
    public List<OrderMemberDeduct> selectOrderMemberDeductList(OrderMemberDeduct orderMemberDeduct)
    {
        return orderMemberDeductMapper.selectOrderMemberDeductList(orderMemberDeduct);
    }

    /**
     * 新增会员付款记录
     * 
     * @param orderMemberDeduct 会员付款记录
     * @return 结果
     */
    @Override
    public int insertOrderMemberDeduct(OrderMemberDeduct orderMemberDeduct)
    {
        SecurityUtils.fillCreateUser(orderMemberDeduct);
        orderMemberDeduct.setOrderMemberDeductId(IdUtils.singleNextId());
        return orderMemberDeductMapper.insert(orderMemberDeduct);
    }

    /**
     * 修改会员付款记录
     * 
     * @param orderMemberDeduct 会员付款记录
     * @return 结果
     */
    @Override
    public int updateOrderMemberDeduct(OrderMemberDeduct orderMemberDeduct)
    {
        SecurityUtils.fillUpdateUser(orderMemberDeduct);

        return orderMemberDeductMapper.updateById(orderMemberDeduct);
    }

    /**
     * 批量删除会员付款记录
     * 
     * @param orderMemberDeductIds 需要删除的会员付款记录主键
     * @return 结果
     */
    @Override
    public int deleteOrderMemberDeductByOrderMemberDeductIds(Long[] orderMemberDeductIds)
    {
        return orderMemberDeductMapper.deleteOrderMemberDeductByOrderMemberDeductIds(orderMemberDeductIds);
    }

    /**
     * 删除会员付款记录信息
     * 
     * @param orderMemberDeductId 会员付款记录主键
     * @return 结果
     */
    @Override
    public int deleteOrderMemberDeductByOrderMemberDeductId(Long orderMemberDeductId)
    {
        return orderMemberDeductMapper.deleteOrderMemberDeductByOrderMemberDeductId(orderMemberDeductId);
    }
}
