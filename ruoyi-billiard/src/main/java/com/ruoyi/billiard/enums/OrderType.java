package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@Getter
@AllArgsConstructor
public enum OrderType implements IEnum {
    TABLECHARGE(1, "球桌费用"),
    MEMBERRECHARGE(2, "会员充值"),
    COMMODITYPURCHASE(3, "商品购买"),
    SPARRINGFEE(4, "陪练费用"),
    TEACHINGCOST(5, "教学费用"),

    ;
    private final int value;
    private final String desc;

    public static List<Map<String, Object>> getValueMap() {
        List<Map<String, Object>> valueList = new ArrayList<>();
        for (OrderType item : values()) {
            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("name", item.desc);
            valueMap.put("value", item.value + "");
            valueList.add(valueMap);
        }
        return valueList;
    }
}
