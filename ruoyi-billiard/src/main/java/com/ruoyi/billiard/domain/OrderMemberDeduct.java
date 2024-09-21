package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 会员付款记录对象 t_order_member_deduct
 * 
 * @author zhoukeu
 * @date 2024-09-21
 */
@TableName("t_order_member_deduct")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMemberDeduct extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */

    @TableId("order_member_deduct_id")
    private Long orderMemberDeductId;

    /** 订单id */
    @Excel(name = "订单id")

    @TableField("order_id")
    private Long orderId;

    /** 会员id */
    @Excel(name = "会员id")

    @TableField("member_id")
    private Long memberId;

    /** 实际付款总金额 */
    @Excel(name = "实际付款总金额")

    @TableField("total_amount")
    private BigDecimal totalAmount;







}
