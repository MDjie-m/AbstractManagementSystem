package com.ruoyi.system.domain.vo;

import java.util.List;

public class PriceVo {
    private List<String> supplierNames;
    private String productName;
    private String startDate;
    private String endDate;

    public List<String> getSupplierNames() {
        return supplierNames;
    }

    public void setSupplierNames(List<String> supplierNames) {
        this.supplierNames = supplierNames;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
