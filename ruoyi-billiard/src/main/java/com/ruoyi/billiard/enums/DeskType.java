package com.ruoyi.billiard.enums;

import com.ruoyi.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum DeskType implements IEnum {
    CHINESE(0, "中式"),
    AMERICA(1, "美式"),
    SNOOKER(2, "斯诺克"),
    POKER(3, "棋牌")
    ;
    private final Integer value;
    private final String desc;
}