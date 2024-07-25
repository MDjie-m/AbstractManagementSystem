package com.renxin.psychology.request;

import com.renxin.common.core.domain.BaseValObj;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 咨询服务配置对象 psy_consult_server_config
 * 
 * @author renxin
 * @date 2023-07-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PsyConsultServeConfigReq extends BaseValObj implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联咨询师")
    private Long nId;

    @ApiModelProperty(value = "关联咨询师")
    private Long cId;

    @ApiModelProperty(value = "添加时间")
    private String dateLimit;

    @ApiModelProperty(value = "服务名称")
    private String name;

    @ApiModelProperty(value = "服务类型")
    private Integer type;

    private String startTime; //开始时间

    private String endTime; //结束时间

    private String delFlag;

    @ApiModelProperty(value = "状态")
    private String status;
}
