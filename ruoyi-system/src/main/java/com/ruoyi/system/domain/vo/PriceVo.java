package com.ruoyi.system.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PriceVo {
    private List<String> supplierIds;
    private String productId;
    private String startDate;
    private String endDate;

    public List<String> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(List<String> supplierIds) {
        this.supplierIds = supplierIds;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
