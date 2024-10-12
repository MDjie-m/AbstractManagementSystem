package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.enums.ScorerBtnType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
public class LightSwitchReqVo extends BaseDeviceReqVo {

    @NotNull(message = "台桌编号不能为空")
    private Integer deskNum;
    @NotNull(message = "开关不能为空")
    private Boolean  open;





}
