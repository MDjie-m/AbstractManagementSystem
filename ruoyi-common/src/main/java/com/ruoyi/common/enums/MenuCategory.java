package com.ruoyi.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;


@Getter
@AllArgsConstructor
public enum MenuCategory implements IEnum<Integer> {
    BACKEND(0, "后台"),
    CASHIER(1, "收银"),
    MINI_APP(2, "小程序");
    private final Integer value;
    private final String desc;

    public static MenuCategory valueOf(Integer val) {
        for (MenuCategory stockChangeType : MenuCategory.values()) {
            if (Objects.equals(val, stockChangeType.getValue())) {
                return stockChangeType;
            }
        }
        return null;
    }
}