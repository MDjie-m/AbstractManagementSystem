package com.ruoyi.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginSystem {
    BACKEND_SYSTEM(1,"后台系统"),
    CASHIER_SYSTEM(2,"收银系统"),
    MINI_APP_SYSTEM(3,"小程序"),
    ;
    private final int value;
    private final String desc;
}
