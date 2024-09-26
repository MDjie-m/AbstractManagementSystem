package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@Getter
@AllArgsConstructor
public enum OrderType implements IEnum {
    AGGREGATE_CONSUMPTION(0, "总消费",null),
    TABLE_CHARGE(1, "球桌费用","D"),
    MEMBER_RECHARGE(2, "会员充值","R"),
    COMMODITY_PURCHASE(3, "商品购买","B"),
    SPARRING_FEE(4, "陪练费用","TA"),
    TEACHING_COST(5, "教学费用","TB"),
    TEACHING_ASSISTANT_FEE(6, "助教费用","TC"),

    ;
    private final Integer value;
    private final String desc;
    private final String prefix;
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
