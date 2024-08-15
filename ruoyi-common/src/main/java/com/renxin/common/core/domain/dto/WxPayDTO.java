package com.renxin.common.core.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @User hogan
 * @Time 2022/10/24 16:48
 * @e-mail hkcugwh@163.com
 **/
@Data
@ApiModel("微信支付实体类")
public class WxPayDTO {

    @ApiModelProperty("测评id")
    private Long gaugeId;

    @ApiModelProperty("支付金额")
    private BigDecimal amount;

    @ApiModelProperty("模块")
    private String name;

    @ApiModelProperty("支付状态")
    private int status;

    @ApiModelProperty("订单id")
    private Integer orderId;




}
