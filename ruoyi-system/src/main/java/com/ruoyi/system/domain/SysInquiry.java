package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * sys_inquiry
 * 
 * @author tyc
 * @date 2024-08-26
 */
public class SysInquiry extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 询价id */
    private Long inquiryId;

    /** 采购员id */
    private Long buyerId;

    /** 产品id */
    private String productId;

    /** 询价日期年月日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inquiryDate;

    /** 回应日期年月日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate;

    /** 询价反馈状态0待报价，1已报价,2已拒绝 */
    private Integer feedbackStatus;

    /** 报价价格 */
    private Double priceRmb;

    /** 美金报价 */
    private Double priceUsd;

    /** 报价单位 */
    private String RMBQuoteUnit;

    /** 产品单价 */
    private Double unitprice;

    /** 单价单位 */
    private String unitpriceUnit;

    /** 0-未删除，1-已删除 */
    private Integer delFlag;

    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(Date inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Integer getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(Integer feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
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

    public String getRMBQuoteUnit() {
        return RMBQuoteUnit;
    }

    public void setRMBQuoteUnit(String RMBQuoteUnit) {
        this.RMBQuoteUnit = RMBQuoteUnit;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public String getUnitpriceUnit() {
        return unitpriceUnit;
    }

    public void setUnitpriceUnit(String unitpriceUnit) {
        this.unitpriceUnit = unitpriceUnit;
    }

    @Override
    public String toString() {
        return "SysInquiry{" +
                "inquiryId=" + inquiryId +
                ", buyerId=" + buyerId +
                ", productId='" + productId + '\'' +
                ", inquiryDate=" + inquiryDate +
                ", responseDate=" + responseDate +
                ", feedbackStatus=" + feedbackStatus +
                ", priceRmb=" + priceRmb +
                ", priceUsd=" + priceUsd +
                ", unit='" + RMBQuoteUnit + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}
