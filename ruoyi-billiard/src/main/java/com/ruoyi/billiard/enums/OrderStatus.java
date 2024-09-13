package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum OrderStatus implements IEnum{
    CHARGING(0,"计费中"),
    WAIT_SETTLED(1,"待结算"),
    SETTLED(2,"已结算"),
    VOID(3,"作废"),
    SUSPEND(4,"挂起订单")


    ;
    private final Integer value;
    private final String desc;


    public static List<Map<String, Object>> getValueMap() {
        List<Map<String, Object>> valueList = new ArrayList<>();
        for (OrderStatus item : values()) {
            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("name", item.desc);
            valueMap.put("value", item.value + "");
            valueList.add(valueMap);
        }
        return valueList;
    }
}
