package com.ruoyi.system.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class SupplierProductVo {
    // 供应商名称
    private String supplierName;
    //产品类别
    private Integer classification;
    //产品规格
    private String specifications;
    // 报价时间
    private Date priceDate;
    // 人名币报价
    private Double rmb;
    //人民币报价单位
    private String rmbQuoteUnit;
    //报价展示
    private String quote;
    // 考察评级
    private String inspectionRate;
    // 审核评级
    private String auditRate;

    public SupplierProductVo() {
    }

    public SupplierProductVo(String supplierName, Integer classification, String specifications, Date priceDate, Double rmb, String rmbQuoteUnit, String quote, String inspectionRate, String auditRate) {
        this.supplierName = supplierName;
        this.classification = classification;
        this.specifications = specifications;
        this.priceDate = priceDate;
        this.rmb = rmb;
        this.rmbQuoteUnit = rmbQuoteUnit;
        this.quote = quote;
        this.inspectionRate = inspectionRate;
        this.auditRate = auditRate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getRmb() {
        return rmb;
    }

    public void setRmb(Double rmb) {
        this.rmb = rmb;
    }



    public String getInspectionRate() {
        return inspectionRate;
    }

    public void setInspectionRate(String inspectionRate) {
        this.inspectionRate = inspectionRate;
    }

    public String getAuditRate() {
        return auditRate;
    }

    public void setAuditRate(String auditRate) {
        this.auditRate = auditRate;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    /**
     * 获取
     * @return classification
     */
    public Integer getClassification() {
        return classification;
    }

    /**
     * 设置
     * @param classification
     */
    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    /**
     * 获取
     * @return specifications
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * 设置
     * @param specifications
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * 获取
     * @return rmbQuoteUnit
     */
    public String getRmbQuoteUnit() {
        return rmbQuoteUnit;
    }

    /**
     * 设置
     * @param rmbQuoteUnit
     */
    public void setRmbQuoteUnit(String rmbQuoteUnit) {
        this.rmbQuoteUnit = rmbQuoteUnit;
    }

    /**
     * 获取
     * @return quote
     */
    public String getQuote() {
        return quote;
    }

    /**
     * 设置
     * @param quote
     */
    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String toString() {
        return "SupplierProductVo{supplierName = " + supplierName + ", classification = " + classification + ", specifications = " + specifications + ", priceDate = " + priceDate + ", rmb = " + rmb + ", rmbQuoteUnit = " + rmbQuoteUnit + ", quote = " + quote + ", inspectionRate = " + inspectionRate + ", auditRate = " + auditRate + "}";
    }
}
