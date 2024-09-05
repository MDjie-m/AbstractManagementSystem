package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商报价对象 sys_supplier_price
 * 
 * @author wzh
 * @date 2024-07-21
 */
public class SysSupplierPrice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String supplierPriceId;

    /** 供应商id：外键 */
    @Excel(name = "供应商id：外键")
    private String supplierId;

    /** 产品id：外键 */
    @Excel(name = "产品id：外键")
    private String productId;

    /** 企业名称中文 */
    @Excel(name = "企业名称中文")
    private String supplierNameCn;

    /** 企业名称英文 */
    @Excel(name = "企业名称英文")
    private String supplierNameEn;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 单价 */
    @Excel(name = "单价")
    private Double unitprice;

    /** 单价单位 */
    @Excel(name = "单价单位")
    private String unitpriceUnit;

    /** 人民币报价 */
    @Excel(name = "人民币报价")
    private Double priceRmb;

    /** 人民币报价单位 */
    @JsonProperty("RMBQuoteUnit")
    @Excel(name = "人民币报价单位")
    private String RMBQuoteUnit;

    /** 美金报价 */
    @Excel(name = "美金报价")
    private Double priceUsd;

    /** 美金报价单位 */
    @JsonProperty("USDQuoteUnit")
    @Excel(name = "美金报价单位")
    private String USDQuoteUnit;

    /** 产品名称 */
    @Excel(name = "汇率")
    private Double currency;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 操作员id */
    @Excel(name = "操作员id")
    private String userId;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 是否删除 0:未删除 1:已删除 */
    private Integer delFlag;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String futureField1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String futureField2;

    public SysSupplierPrice() {
    }

    public SysSupplierPrice( String supplierPriceId, String supplierId, String productId, String supplierNameCn, String supplierNameEn, String productName, Double unitprice, String unitpriceUnit, Double priceRmb, String RMBQuoteUnit, Double priceUsd, String USDQuoteUnit, Double currency, Date time, String userId, String remarks, Integer delFlag, String futureField1, String futureField2) {
        this.supplierPriceId = supplierPriceId;
        this.supplierId = supplierId;
        this.productId = productId;
        this.supplierNameCn = supplierNameCn;
        this.supplierNameEn = supplierNameEn;
        this.productName = productName;
        this.unitprice = unitprice;
        this.unitpriceUnit = unitpriceUnit;
        this.priceRmb = priceRmb;
        this.RMBQuoteUnit = RMBQuoteUnit;
        this.priceUsd = priceUsd;
        this.USDQuoteUnit = USDQuoteUnit;
        this.currency = currency;
        this.time = time;
        this.userId = userId;
        this.remarks = remarks;
        this.delFlag = delFlag;
        this.futureField1 = futureField1;
        this.futureField2 = futureField2;
    }

    public void setSupplierPriceId(String supplierPriceId) 
    {
        this.supplierPriceId = supplierPriceId;
    }

    public String getSupplierPriceId() 
    {
        return supplierPriceId;
    }
    public void setSupplierId(String supplierId) 
    {
        this.supplierId = supplierId;
    }

    public String getSupplierId() 
    {
        return supplierId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setSupplierNameCn(String supplierNameCn)
    {
        this.supplierNameCn = supplierNameCn;
    }

    public String getSupplierNameCn() 
    {
        return supplierNameCn;
    }
    public void setSupplierNameEn(String supplierNameEn) 
    {
        this.supplierNameEn = supplierNameEn;
    }

    public String getSupplierNameEn() 
    {
        return supplierNameEn;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setPriceRmb(Double priceRmb)
    {
        this.priceRmb = priceRmb;
    }

    public Double getPriceRmb()
    {
        return priceRmb;
    }
    public void setPriceUsd(Double priceUsd)
    {
        this.priceUsd = priceUsd;
    }

    public Double getPriceUsd()
    {
        return priceUsd;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag()
    {
        return delFlag;
    }
    public void setFutureField1(String futureField1) 
    {
        this.futureField1 = futureField1;
    }

    public String getFutureField1() 
    {
        return futureField1;
    }
    public void setFutureField2(String futureField2) 
    {
        this.futureField2 = futureField2;
    }

    public String getFutureField2() 
    {
        return futureField2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplierPriceId", getSupplierPriceId())
            .append("supplierId", getSupplierId())
            .append("productId", getProductId())
            .append("supplierNameCn", getSupplierNameCn())
            .append("supplierNameEn", getSupplierNameEn())
            .append("productName", getProductName())
            .append("priceRmb", getPriceRmb())
            .append("priceUsd", getPriceUsd())
            .append("time", getTime())
            .append("userId", getUserId())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .append("futureField1", getFutureField1())
            .append("futureField2", getFutureField2())
            .toString();
    }

    /**
     * 获取
     * @return unitprice
     */
    public Double getUnitprice() {
        return unitprice;
    }

    /**
     * 设置
     * @param unitprice
     */
    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * 获取
     * @return unitpriceUnit
     */
    public String getUnitpriceUnit() {
        return unitpriceUnit;
    }

    /**
     * 设置
     * @param unitpriceUnit
     */
    public void setUnitpriceUnit(String unitpriceUnit) {
        this.unitpriceUnit = unitpriceUnit;
    }

    /**
     * 获取
     * @return RMBQuoteUnit
     */
    public String getRMBQuoteUnit() {
        return RMBQuoteUnit;
    }

    /**
     * 设置
     * @param RMBQuoteUnit
     */
    public void setRMBQuoteUnit(String RMBQuoteUnit) {
        this.RMBQuoteUnit = RMBQuoteUnit;
    }

    /**
     * 获取
     * @return USDQuoteUnit
     */
    public String getUSDQuoteUnit() {
        return USDQuoteUnit;
    }

    /**
     * 设置
     * @param USDQuoteUnit
     */
    public void setUSDQuoteUnit(String USDQuoteUnit) {
        this.USDQuoteUnit = USDQuoteUnit;
    }

    /**
     * 获取
     * @return currency
     */
    public Double getCurrency() {
        return currency;
    }

    /**
     * 设置
     * @param currency
     */
    public void setCurrency(Double currency) {
        this.currency = currency;
    }
}
