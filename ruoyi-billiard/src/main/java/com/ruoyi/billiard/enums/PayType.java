package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum PayType implements IEnum {
    SCANTHECODE(0, "扫码"),
    CASH(1, "现金"),
    MEMBER(2, "会员"),

    ;
    private final Integer value;
    private final String desc;


    public static List<Map<String, Object>> getValueMap() {
        List<Map<String, Object>> valueList = new ArrayList<>();
        for (PayType item : values()) {
            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("name", item.desc);
            valueMap.put("value", item.value + "");
            valueList.add(valueMap);
        }
        return valueList;
    }
}
