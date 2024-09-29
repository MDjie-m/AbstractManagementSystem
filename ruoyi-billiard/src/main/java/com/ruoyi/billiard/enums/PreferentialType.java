package com.ruoyi.billiard.enums;

import com.ruoyi.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PreferentialType implements IEnum {

    DISCOUNT(0, "折扣"),
    WIPE_ZERO(1, "抹零"),
    ;

    private final Integer value;
    private final String desc;
}
