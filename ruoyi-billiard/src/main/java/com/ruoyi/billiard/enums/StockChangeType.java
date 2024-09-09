package com.ruoyi.billiard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;


@Getter
@AllArgsConstructor
public enum StockChangeType implements IEnum{
    IN(0,"入库"),
    OUT(1,"出库"),
    CHECK(2,"盘点")
    ;
    private final int value;
    private final String desc;

    public static StockChangeType valueOf(Integer val){
        for (StockChangeType stockChangeType : StockChangeType.values()) {
            if(Objects.equals(val,stockChangeType.getValue())){
                return  stockChangeType;
            }
        }
        return  null;
    }
}
