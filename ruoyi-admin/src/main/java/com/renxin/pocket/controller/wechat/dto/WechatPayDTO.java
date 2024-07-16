package com.renxin.pocket.controller.wechat.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WechatPayDTO implements Serializable {
    private Integer userId;
    private Integer courseId;

    private Integer gaugeId;

    // 咨询服务
    private Long consultId;
    private Long serveId;
    private Long workId;
    private Long orderId;
    private Integer time;

    private BigDecimal amount;

    private String module;
    private String outTradeNo;
    
    
    private String payConsultId;//付费咨询师id
    private String supServerType;//督导服务类型
    private String supServerId;//督导服务id
}
