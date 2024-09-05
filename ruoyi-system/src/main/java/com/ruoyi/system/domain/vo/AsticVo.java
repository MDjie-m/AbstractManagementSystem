package com.ruoyi.system.domain.vo;

import java.util.Date;
import java.util.List;

public class AsticVo {
    private String name;
    private List<PriceDetailVo> quotes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PriceDetailVo> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<PriceDetailVo> quotes) {
        this.quotes = quotes;
    }
}