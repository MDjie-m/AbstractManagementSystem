package com.ruoyi.billiard.domain;

import com.ruoyi.billiard.enums.ScorerBtnType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AddDeskScoreReqVo implements Serializable {

    @NotNull(message = "台桌编号不能为空")
    private Integer deskNum;
    @NotNull(message = "按钮类型不能为空")
    private ScorerBtnType btnType;

}
