package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;


@Getter
@AllArgsConstructor
public enum LightTimerType implements IEnum{
    TEMP(0,"临时灯"),
    CALC_FEE(1,"计费灯"),
    ;
    private final Integer value;
    private final String desc;

    public static LightTimerType valueOf(Integer val){
        for (LightTimerType item : values()) {
            if(Objects.equals(val,item.getValue())){
                return  item;
            }
        }
        return  null;
    }
}
