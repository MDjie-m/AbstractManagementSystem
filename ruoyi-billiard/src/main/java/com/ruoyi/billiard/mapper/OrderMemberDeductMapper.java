package com.ruoyi.billiard.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.ruoyi.billiard.domain.OrderMemberDeduct;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员付款记录Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-21
 */
@Mapper
public interface OrderMemberDeductMapper extends MyBaseMapper<OrderMemberDeduct>
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
     * 删除会员付款记录
     * 
     * @param orderMemberDeductId 会员付款记录主键
     * @return 结果
     */
    public int deleteOrderMemberDeductByOrderMemberDeductId(Long orderMemberDeductId);

    /**
     * 批量删除会员付款记录
     * 
     * @param orderMemberDeductIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderMemberDeductByOrderMemberDeductIds(Long[] orderMemberDeductIds);

    BigDecimal queryTotal(@Param("storeId") Long storeId, @Param("startTime") Date startTime);
}
