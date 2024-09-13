package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.OrderRecharge;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员充值Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@Mapper
public interface OrderRechargeMapper extends MyBaseMapper<OrderRecharge>
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
     * 删除会员充值
     * 
     * @param orderRechargeId 会员充值主键
     * @return 结果
     */
    public int deleteOrderRechargeByOrderRechargeId(Long orderRechargeId);

    /**
     * 批量删除会员充值
     * 
     * @param orderRechargeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderRechargeByOrderRechargeIds(Long[] orderRechargeIds);
}
