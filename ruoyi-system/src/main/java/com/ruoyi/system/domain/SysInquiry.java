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
    private Integer inquiryId;

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
    private String unit;

    /** 0-未删除，1-已删除 */
    private Integer delFlag;

    public Integer getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Integer inquiryId) {
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
                ", unit='" + unit + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}
