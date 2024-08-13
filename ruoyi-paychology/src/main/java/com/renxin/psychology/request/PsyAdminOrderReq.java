package com.renxin.psychology.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * *
 * @author renxin
 * @date 2023-06-26
 */
@Data
public class PsyAdminOrderReq implements Serializable
{

    private static final long serialVersionUID = -8791334840022479201L;

    @ApiModelProperty(value = "添加时间")
    private String dateLimit;

    @ApiModelProperty(value = "咨询师姓名")
    private String nickName;

    @ApiModelProperty(value = "服务名称")
    private String serveName;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "咨询师id")
    private String consultId;

    @ApiModelProperty(value = "开始时间")
    private String startTime; //开始时间

    @ApiModelProperty(value = "结束时间")
    private String endTime; //结束时间

    @ApiModelProperty(value = "删除")
    private String delFlag;

    @ApiModelProperty(value = "订单状态")
    private String status;
    private String source;

    @ApiModelProperty(value = "支付状态")
    private String pay;
    private String payStatus;
    
    private String userId;

}
