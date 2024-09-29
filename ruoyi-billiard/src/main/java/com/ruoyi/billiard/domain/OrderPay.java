package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.enums.OrderPayType;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 订单预付费对象 t_order_pay
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@TableName("t_order_pay")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPay extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预支付id */

    @TableId("order_pre_pay_id")
    private Long orderPrePayId;

    /** 订单  */
    @Excel(name = "订单 ")

    @TableField("order_id")
    private Long orderId;

    /** 预付费 */
    @Excel(name = "预付费")

    @TableField("amount")
    private BigDecimal amount;

    /** 支付方式：0=扫码，1=现金 */
    @Excel(name = "支付方式：0=扫码，1=现金")

    @TableField("pay_type")
    private OrderPayType payType;

    /** 是否是预付 */
    @Excel(name = "是否是预付")

    @TableField("pre_pay")
    private Boolean prePay;

    /** 是否支付 */
    @Excel(name = "是否支付")

    @TableField("paid")
    private Boolean paid;

    /** 付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("pay_time")
    private Date payTime;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;












}
