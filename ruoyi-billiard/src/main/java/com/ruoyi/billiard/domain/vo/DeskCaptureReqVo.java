package com.ruoyi.billiard.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class DeskCaptureReqVo extends BaseDeviceReqVo {

    @NotNull(message = "台桌编号不能为空")
    private Integer deskNum;

}
