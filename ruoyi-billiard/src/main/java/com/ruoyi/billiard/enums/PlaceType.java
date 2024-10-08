package com.ruoyi.billiard.enums;

import com.ruoyi.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PlaceType implements IEnum {
    PUBLIC_PLACE(0, "大厅"),
    PRIVATE_ROOM(1, "包厢"),
    ;
    private final Integer value;
    private final String desc;
}