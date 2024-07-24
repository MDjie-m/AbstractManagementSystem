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
public class PsyAdminPartnerReq implements Serializable
{

    private static final long serialVersionUID = -147133832575660721L;

    /** 登录名 */
    private Integer userId;

    private Long id;
    
    private Long consultId;

    @ApiModelProperty(value = "添加时间")
    private String dateLimit;

    @ApiModelProperty(value = "咨询师姓名")
    private String name;

    private String startTime; //开始时间

    private String endTime; //结束时间

    private String status;

}
