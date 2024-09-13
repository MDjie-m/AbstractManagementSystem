package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum EmployeeStatus implements IEnum{
    WORK(0,"在职"),
    OUT(1,"离职"),
    ;
    private final Integer value;
    private final String desc;
}