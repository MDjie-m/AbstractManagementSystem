package com.renxin.gauge.domain;

import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 心理测评订单信息对象 psy_order
 * 
 * @author renxin
 * @date 2022-10-12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PsyOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderId;

    /** 测评编号 */
    @Excel(name = "测评编号")
    private Long gaugeId;

    /** 订单状态(1-创建,2-完成,3-关闭) */
    @Excel(name = "订单状态(1-创建,2-完成,3-关闭)")
    private Integer orderStatus;

    /** 应付金额 */
    @Excel(name = "应付金额")
    private BigDecimal amount;

    @Excel(name = "性别(1-男，2-女)")
    private String sex;

    @Excel(name = "年龄")
    private Integer age;

    @Excel(name = "手机号")
    private String mobile;

    /** 测评完成情况(1-已完成，2-未完成) */
    @Excel(name = "测评完成情况(1-已完成，2-未完成)")
    private Integer gaugeStatus;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户id */
    @Excel(name = "用户名")
    private String userName;

    /**
     * 量表标题
     */
    @Excel(name = "量表标题")
    private String gaugeTitle;


    @Excel(name = "量表副标题")
    private String gaugeSubtitle;

    private BigDecimal originalPrice;//原价
    private String couponNo;//使用优惠券编号
    private String isUseGaugeAnalyse;//是否使用测评解析  Y是  N否
    private String payParam;//支付参数


    /**
     * 量表简介
     */
    private String gaugeDes;
    private Integer gaugeType;
    private Integer gaugeNum;
    private BigDecimal gaugeRatio;
    private Integer gaugeScore;
    private String radar;
    private String wrong;
    private Integer finishedNum;
    private String headPicture;

    /**
     * 总得分
     */
    private String score;

    /**
     * 总得分对应图片
     */
    private String resultUrl;
}
