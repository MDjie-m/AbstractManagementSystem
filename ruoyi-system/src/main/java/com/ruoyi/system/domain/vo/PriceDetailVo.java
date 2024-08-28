package com.ruoyi.system.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class PriceDetailVo {
    private Date time;
    private Double price;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
