package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceType implements IEnum{
    CAMERA(0,"摄像头"),
    LIGHT(1,"灯光"),
    ;
    private final int value;
    private final String desc;
}
