package com.renxin.wechat.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WechatPayVO implements Serializable {
    private Long userId;
    private Long courseId;

    private Long gaugeId;

    private BigDecimal amount;

    // 咨询服务
    private Long consultId;
    private Long serveId;
    private Long workId;
    private Long orderId;
    private Integer time;

    private String module;

    private String outTradeNo;

    private String supServerType;//督导服务类型
    private String supServerId;//督导服务id


    private BigDecimal originalPrice;//原价
    private String orderServerType;//下单服务类型
    private String orderServerId;//下单服务id
    private String couponNo;//使用优惠券编号
    private String isUseGaugeAnalyse;//是否使用测评解析  Y是  N否

}
