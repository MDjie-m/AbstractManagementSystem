package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@Getter
@AllArgsConstructor
public enum OrderType implements IEnum {
    TABLE_CHARGE(1, "球桌费用"),
    MEMBER_RECHARGE(2, "会员充值"),
    COMMODITY_PURCHASE(3, "商品购买"),
    SPARRING_FEE(4, "陪练费用"),
    TEACHING_COST(5, "教学费用"),

    ;
    private final Integer value;
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
    public static OrderType valueOf(Integer val){
        for (OrderType item : values()) {
            if(Objects.equals(val,item.getValue())){
                return  item;
            }
        }
        return  null;
    }
}
