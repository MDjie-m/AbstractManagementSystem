package com.ruoyi.billiard.enums;

import com.ruoyi.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;


@Getter
@AllArgsConstructor
public enum TutorLevel implements IEnum {
    // 1=助教，2=教练，3=总教
    ASSISTANT(1, "助教"),
    COACH(2, "教练"),
    HEAD_COACH(3, "总教");
    private final Integer value;
    private final String desc;

    public static StockChangeType valueOf(Integer val) {
        for (StockChangeType stockChangeType : StockChangeType.values()) {
            if (Objects.equals(val, stockChangeType.getValue())) {
                return stockChangeType;
            }
        }
        return null;
    }
}
