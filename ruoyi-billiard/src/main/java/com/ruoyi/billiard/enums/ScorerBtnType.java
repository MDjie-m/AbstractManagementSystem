package com.ruoyi.billiard.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;


@Getter
@AllArgsConstructor
public enum ScorerBtnType implements IEnum{
    RED(0,"红队",1),
    CLEAR(1,"清零",0),
    GREEN(2,"绿队",1)
    ;
    private final Integer value;
    private final String desc;
    private final int  score;



}
