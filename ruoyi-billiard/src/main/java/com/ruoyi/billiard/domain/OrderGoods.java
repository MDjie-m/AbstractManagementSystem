package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import java.math.BigDecimal;

/**
 * 购买商品对象 t_order_goods
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@TableName("t_order_goods")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoods extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */

    @TableId("order_detail_id")
    private Long orderDetailId;

    /** 订单id */
    @Excel(name = "订单id")

    @TableField("order_id")
    private Long orderId;

    /** 球桌id */
    @Excel(name = "球桌id")

    @TableField("desk_id")
    private Long deskId;

    /** 商品id */
    @Excel(name = "商品id")

    @TableField("goods_id")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")

    @TableField("goods_name")
    private String goodsName;

    /** 单价 */
    @Excel(name = "单价")


    @TableField("price")
    private BigDecimal price;

    /** 数量 */
    @Excel(name = "数量")

    @TableField("num")
    private Integer num;

    /** 应付总金额  */
    @Excel(name = "应付总金额 ")

    @TableField("total_amount_due")
    private BigDecimal totalAmountDue;

    /** 折扣金额 */
    @Excel(name = "折扣金额")

    @TableField("total_discount_amount")
    private BigDecimal totalDiscountAmount;

    /** 实际支付金额 */
    @Excel(name = "实际支付金额")

    @TableField("total_amount")
    private BigDecimal totalAmount;

    /** 当前折扣 */
    @Excel(name = "当前折扣")

    @TableField("discount_value")
    private BigDecimal discountValue;

    /** 抹零金额 */
    @Excel(name = "抹零金额")

    @TableField("total_wipe_zero")
    private BigDecimal totalWipeZero;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;

















}
