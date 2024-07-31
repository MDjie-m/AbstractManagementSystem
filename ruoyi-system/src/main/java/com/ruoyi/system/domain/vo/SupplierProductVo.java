package com.ruoyi.system.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class SupplierProductVo {
    // 供应商名称
    private String supplierName;
    // 人名币报价
    private BigDecimal rmb;
    // 美元报价
    private BigDecimal usd;
    // 考察评级
    private String inspectionRate;
    // 审核评级
    private String auditRate;
    // 报价时间
    private Date priceDate;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getRmb() {
        return rmb;
    }

    public void setRmb(BigDecimal rmb) {
        this.rmb = rmb;
    }

    public BigDecimal getUsd() {
        return usd;
    }

    public void setUsd(BigDecimal usd) {
        this.usd = usd;
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
}
