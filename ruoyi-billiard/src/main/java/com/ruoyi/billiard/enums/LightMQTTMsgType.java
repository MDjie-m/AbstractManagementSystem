package com.ruoyi.billiard.enums;

import com.ruoyi.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;


@Getter
@AllArgsConstructor
public enum LightMQTTMsgType implements IEnum {
    INFO(1,"获取设备信息"),
    SETTING(2,"设置"),
    SWITCH(3,"盘点")
    ;
    private final Integer value;
    private final String desc;

    public static LightMQTTMsgType valueOf(Integer val){
        for (LightMQTTMsgType item : values()) {
            if(Objects.equals(val,item.getValue())){
                return  item;
            }
        }
        return  null;
    }
}
