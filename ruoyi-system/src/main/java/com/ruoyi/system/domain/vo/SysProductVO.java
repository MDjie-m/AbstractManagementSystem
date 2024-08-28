package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.SysProduct;

import java.math.BigDecimal;

public class SysProductVO extends SysProduct {
    private String supplierNameCn;
    private String supplierNameEn;
    private String supplierNameOwn;
    private Double priceRmb;//报价的人民币价格
    private String RMBQuoteUnit;//人民币报价单位
    private Double priceUsd;//报价的美元价格

    private Integer inquiryTimes;//询价次数，询价菜单查看询价列表才显示

    public String getRMBQuoteUnit() {
        return RMBQuoteUnit;
    }

    public void setRMBQuoteUnit(String RMBQuoteUnit) {
        this.RMBQuoteUnit = RMBQuoteUnit;
    }

    public Integer getInquiryTimes() {
        return inquiryTimes;
    }

    public void setInquiryTimes(Integer inquiryTimes) {
        this.inquiryTimes = inquiryTimes;
    }

    public Double getPriceRmb() {
        return priceRmb;
    }

    public void setPriceRmb(Double priceRmb) {
        this.priceRmb = priceRmb;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getSupplierNameCn() {
        return supplierNameCn;
    }

    public void setSupplierNameCn(String supplierNameCn) {
        this.supplierNameCn = supplierNameCn;
    }

    public String getSupplierNameEn() {
        return supplierNameEn;
    }

    public void setSupplierNameEn(String supplierNameEn) {
        this.supplierNameEn = supplierNameEn;
    }

    public String getSupplierNameOwn() {
        return supplierNameOwn;
    }

    public void setSupplierNameOwn(String supplierNameOwn) {
        this.supplierNameOwn = supplierNameOwn;
    }
}
