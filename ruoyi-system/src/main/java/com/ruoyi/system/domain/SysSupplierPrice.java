package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long productId;

    /** 企业名称中文 */
    @Excel(name = "企业名称中文")
    private String supplierNameCn;

    /** 企业名称英文 */
    @Excel(name = "企业名称英文")
    private String supplierNameEn;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 人民币报价 */
    @Excel(name = "人民币报价")
    private BigDecimal priceRmb;

    /** 美金报价 */
    @Excel(name = "美金报价")
    private BigDecimal priceUsd;

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
    private Long delFlag;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String futureField1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String futureField2;

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
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
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
    public void setPriceRmb(BigDecimal priceRmb) 
    {
        this.priceRmb = priceRmb;
    }

    public BigDecimal getPriceRmb() 
    {
        return priceRmb;
    }
    public void setPriceUsd(BigDecimal priceUsd) 
    {
        this.priceUsd = priceUsd;
    }

    public BigDecimal getPriceUsd() 
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
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
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
}
