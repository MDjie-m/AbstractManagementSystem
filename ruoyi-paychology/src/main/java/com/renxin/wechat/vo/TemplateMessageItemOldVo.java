package com.renxin.wechat.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TemplateMessageItemOldVo {
    public TemplateMessageItemOldVo() {}
    public TemplateMessageItemOldVo(String value) {
        this.value = value;
    }

    @ApiModelProperty(value = "显示的文字内容", required = true)
    private String value;

    @ApiModelProperty(value = "颜色")
    private String color = "#173177";
}
