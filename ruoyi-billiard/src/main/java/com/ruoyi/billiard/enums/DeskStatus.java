package com.ruoyi.billiard.enums;

import com.ruoyi.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum DeskStatus implements IEnum {
    WAIT(0,"空闲"),
    BUSY(1,"计费中"),
    PAUSE(2,"暂停"),
    STOP(3,"已停止"),

    ;
    private final Integer value;
    private final String desc;
}