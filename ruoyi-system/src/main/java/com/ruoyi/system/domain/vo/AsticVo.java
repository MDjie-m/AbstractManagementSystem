package com.ruoyi.system.domain.vo;

import java.util.Date;
import java.util.List;

public class AsticVo {
    private String name;
    private List<PriceVo> quotes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PriceVo> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<PriceVo> quotes) {
        this.quotes = quotes;
    }
}