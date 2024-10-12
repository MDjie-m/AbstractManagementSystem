package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.enums.ScorerBtnType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddDeskScoreReqVo extends BaseDeviceReqVo {

    @NotNull(message = "台桌编号不能为空")
    private Integer deskNum;
    @NotNull(message = "按钮类型不能为空")
    private ScorerBtnType btnType;





}
