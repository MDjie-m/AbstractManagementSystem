package com.ruoyi.system.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class PriceDetailVo {
    private Date time;
    private BigDecimal price;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
