package com.renxin.pocket.controller.wechat.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WechatPayDTO implements Serializable {
    private Long userId;
    private Long courseId;

    private Long gaugeId;
    private Long teamId;//团队id

    // 咨询服务
    private Long consultId;
    private Long serveId;
    private Long workId;
    private Long orderId;
    private Integer time;
    private Integer memberType;//成员类型  1:正式成员   2:观摩成员

    private BigDecimal amount;
    private BigDecimal originalPrice;//原价
    private BigDecimal consultantRatio;//咨询师分配比例

    private String module;
    private String outTradeNo;
    
    
    private String payConsultId;//付费咨询师id
    private String supServerType;//督导服务类型
    private String supServerId;//督导服务id
    
    private String orderServerType;//下单服务类型
    private String orderServerId;//下单服务id
    private String couponNo;//使用优惠券编号
    private String isUseGaugeAnalyse;//是否使用测评解析  Y是  N否
    private String paymentChannel;//支付渠道  aliPay   wechatPay
}
