package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.enums.OrderPayType;
import com.ruoyi.billiard.enums.OrderType;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 订单结算对象 t_order_total
 * 
 * @author ruoyi
 * @date 2024-10-26
 */
@TableName("t_order_total")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderTotal extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("order_total_id")
    private Long orderTotalId;

    /** 订单id */
    @Excel(name = "订单id")

    @TableField("order_id")
    private Long orderId;

    /** 订单编号 */
    @Excel(name = "订单编号")

    @TableField("order_no")
    private String orderNo;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 订单类型(    0=总营业额,1= 台桌费用 ，2=会员充值,3=商品购买，4=陪练费用 ，5=教学费用，6=助教费用) */
    @Excel(name = "订单类型(    0=总营业额,1= 台桌费用 ，2=会员充值,3=商品购买，4=陪练费用 ，5=教学费用，6=助教费用)")

    @TableField("order_type")
    private OrderType orderType;

    /** 订单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("order_date")
    private LocalDateTime orderDate;

    /** 收款金额 */
    @Excel(name = "收款金额")

    @TableField("total_amount")
    private BigDecimal totalAmount;

    /** 应付款收款金额 */
    @Excel(name = "应付款收款金额")

    @TableField("total_amount_due")
    private BigDecimal totalAmountDue;

    /** 折扣金额 */
    @Excel(name = "折扣金额")

    @TableField("discount_amount")
    private BigDecimal discountAmount;

    /** 支付方式：0=扫码，1=现金，2=会员 */
    @Excel(name = "支付方式：0=扫码，1=现金，2=会员")

    @TableField("pay_type")
    private OrderPayType payType;


    @TableField("unit_count")
    private Long  unitCount;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;















}
