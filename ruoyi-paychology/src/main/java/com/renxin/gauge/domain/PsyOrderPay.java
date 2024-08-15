package com.renxin.gauge.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 心理咨询订单支付信息对象 psy_order_pay
 * 
 * @author renxin
 * @date 2022-10-12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PsyOrderPay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private Long orderId;

    private Long consultantOrderId;

    /** 支付状态(1-待支付,2-已支付，3-取消支付) */
    @Excel(name = "支付状态(1-待支付,2-已支付，3-取消支付)")
    private Integer payStatus;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal amount;

    /** 支付ID */
    @Excel(name = "支付ID")
    private String payId;

    /** 支付时间 */
    @Excel(name = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /** 支付方式，支付方式一定是微信,这里标记为0-测评 1-课程 */
    @Excel(name = "支付方式")
    private int payType;
}
