package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 台桌和教练计费时间状态
 */
@Getter
@AllArgsConstructor
public enum CalcTimeStatus implements IEnum{

    BUSY(1,"计费中"),
    PAUSE(2,"暂停"),
    STOP(3,"已停止"),

    ;
    private final Integer value;
    private final String desc;
}