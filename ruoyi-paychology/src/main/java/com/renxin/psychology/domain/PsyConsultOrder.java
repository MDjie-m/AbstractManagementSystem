package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BasePlusEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 咨询订单对象 psy_consult_order
 * 
 * @author renxin
 * @date 2023-06-26
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_consult_order")
public class PsyConsultOrder extends BasePlusEntity implements Serializable
{

    private static final long serialVersionUID = 6679722822685766673L;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /** 咨询师 */
    @Excel(name = "咨询师")
    private Long consultId;

    /** 咨询类型  */
    private String consultType;

    @TableField(exist = false)
    private String consultTypeName;

    /** 咨询师 */
    @Excel(name = "咨询师")
    private String consultName;

    /** 咨询师 */
    @Excel(name = "转介咨询师")
    private Long refConsultId;

    /** 咨询师 */
    @Excel(name = "转介咨询师")
    private String refConsultName;

    /** 服务 */
    @Excel(name = "服务")
    private Long serveId;

    /** 服务 */
    @Excel(name = "服务")
    private String serveName;

    /** 客户id */
    @Excel(name = "客户id")
    private Long userId;

    /** 客户 */
    @Excel(name = "客户")
    private String nickName;

    /** 应付费用 */
    @Excel(name = "应付费用")
    private BigDecimal amount;

    /** 实际支付 */
    @Excel(name = "实际支付")
    private BigDecimal pay;

    /** 付款时间 */
    @Excel(name = "付款时间")
    private Date payTime;

    @Excel(name = "下次预约时间")
    private String orderTime;

    /** 可预约数量 */
    @Excel(name = "可预约数量")
    private Integer num;

    /** 已预约数量 */
    @Excel(name = "已预约数量")
    private Integer buyNum;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 订单状态0-待付款 1-进行中 2-已完成 3-已取消 4-已关闭*/
    @Excel(name = "订单状态0-待付款 1-进行中 2-已完成 3-已取消 4-已关闭")
    private String status;

    /** 支付状态1,未支付 2,支付成功 3,退款中 4,部分退 5,全单退 6,退款失败 */
    @Excel(name = "支付状态1,未支付 2,支付成功 3,退款中 4,部分退 5,全单退 6,退款失败")
    private String payStatus;

    /** 下单方式0,H5 1,MP 2,DY 5,PC */
    @Excel(name = "下单方式0,H5 1,MP 2,DY 5,PC")
    private String source;

    @Excel(name = "订单渠道")
    private String channel;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "改价原因")
    private String memo1;

    @Excel(name = "转介原因")
    private String reason;
    private String payParam;//支付参数

    private BigDecimal originalPrice;//原价
    private String couponNo;//使用优惠券编号

    @TableField(exist = false)
    private String phone;

    @TableField(exist = false)
    private String statusName;

    @TableField(exist = false)
    private String payStatusName;
}
