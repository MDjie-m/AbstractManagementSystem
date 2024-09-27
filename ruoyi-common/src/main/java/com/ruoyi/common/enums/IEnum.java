package com.ruoyi.common.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public interface IEnum extends com.baomidou.mybatisplus.annotation.IEnum<Integer> {
    @JsonValue
    Integer getValue();
    String getDesc();



}
