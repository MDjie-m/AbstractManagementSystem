package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum BookingStatus implements IEnum{

    ACTIVE(0,"有效"),
    USED(1,"生效"),
    EXPIRE(2,"过期"),

    ;
    private final Integer value;
    private final String desc;
}