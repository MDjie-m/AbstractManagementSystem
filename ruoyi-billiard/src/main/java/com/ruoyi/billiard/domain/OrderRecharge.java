package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import java.math.BigDecimal;

/**
 * 会员充值对象 t_order_recharge
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@TableName("t_order_recharge")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecharge extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */

    @TableId("order_recharge_id")
    private Long orderRechargeId;

    /** 订单id */
    @Excel(name = "订单id")

    @TableField("order_id")
    private Long orderId;

    /** 会员id */
    @Excel(name = "会员id")

    @TableField("member_id")
    private Long memberId;

    /** 充值金额 */
    @Excel(name = "充值金额")

    @TableField("recharge_amount")
    private BigDecimal rechargeAmount;

    /** 充值赠送金额 */
    @Excel(name = "充值赠送金额")

    @TableField("give_amount")
    private BigDecimal giveAmount;

    /** 支付金额 */
    @Excel(name = "支付金额")

    @TableField("total_amount")
    private BigDecimal totalAmount;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;











}
