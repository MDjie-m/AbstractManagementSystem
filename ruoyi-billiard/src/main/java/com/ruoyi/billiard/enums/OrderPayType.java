package com.ruoyi.billiard.enums;

import com.ruoyi.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum OrderPayType implements IEnum {
    SCAN_QRCODE(0, "扫码支付"),
    CASH(1, "现金支付"),
    MEMBER(2, "会员支付"),

    ;
    private final Integer value;
    private final String desc;


    public static List<Map<String, Object>> getValueMap() {
        List<Map<String, Object>> valueList = new ArrayList<>();
        for (OrderPayType item : values()) {
            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("name", item.desc);
            valueMap.put("value", item.value + "");
            valueList.add(valueMap);
        }
        return valueList;
    }
}
