package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.domain.StockLog;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StockCheckRes {
    private List<StockLog> successList=new ArrayList<>();
    private List<StockLog> failList=new ArrayList<>();
}
