package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceStatus implements IEnum{
    UNKNOWN(0,"未知"),
    ONLINE(1,"在线"),
    OFFLINE(2,"离线"),
    ;
    private final Integer value;
    private final String desc;
}
