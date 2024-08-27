package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.SysProduct;

import java.math.BigDecimal;

public class SysProductVO extends SysProduct {
    private String supplierNameCn;
    private String supplierNameEn;
    private String supplierNameOwn;
    private BigDecimal priceRmb;//报价的人民币价格
    private BigDecimal priceUsd;//报价的人民币价格

    private Integer inquiryTimes;//询价次数，询价菜单查看询价列表才显示

    public Integer getInquiryTimes() {
        return inquiryTimes;
    }

    public void setInquiryTimes(Integer inquiryTimes) {
        this.inquiryTimes = inquiryTimes;
    }

    public BigDecimal getPriceRmb() {
        return priceRmb;
    }

    public void setPriceRmb(BigDecimal priceRmb) {
        this.priceRmb = priceRmb;
    }

    public BigDecimal getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(BigDecimal priceUsd) {
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
