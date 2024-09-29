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
 * 订单退款对象 t_order_refund
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@TableName("t_order_refund")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRefund extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预支付id */

    @TableId("order_refund_id")
    private Long orderRefundId;

    /** 订单  */
    @Excel(name = "订单 ")

    @TableField("order_id")
    private Long orderId;

    @TableField("store_id")
    private Long storeId;

    /** 金额 */
    @Excel(name = "金额")

    @TableField("amount")
    private BigDecimal amount;

    /** 支付方式：0=扫码，1=现金 */
    @Excel(name = "支付方式：0=扫码，1=现金")

    @TableField("return_pay_type")
    private OrderPayType returnPayType;

    /** 是否支付 */
    @Excel(name = "是否支付")

    @TableField("paid")
    private Boolean paid;

    /** 退款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退款时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("return_pay_time")
    private Date returnPayTime;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;











}
