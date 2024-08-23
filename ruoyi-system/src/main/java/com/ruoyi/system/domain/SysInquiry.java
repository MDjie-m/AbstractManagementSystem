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
 * @date 2024-08-16
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
    private Long feedbackStatus;

    /** 报价价格 */
    private BigDecimal priceRmb;

    /** 美金报价 */
    private BigDecimal priceUsd;

    /** 0-未删除，1-已删除 */
    private Long delFlg;

    public void setInquiryId(Long inquiryId)
    {
        this.inquiryId = inquiryId;
    }

    public Long getInquiryId()
    {
        return inquiryId;
    }
    public void setBuyerId(Long buyerId)
    {
        this.buyerId = buyerId;
    }

    public Long getBuyerId()
    {
        return buyerId;
    }
    public void setProductId(String productId) 
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setInquiryDate(Date inquiryDate) 
    {
        this.inquiryDate = inquiryDate;
    }

    public Date getInquiryDate() 
    {
        return inquiryDate;
    }
    public void setResponseDate(Date responseDate) 
    {
        this.responseDate = responseDate;
    }

    public Date getResponseDate() 
    {
        return responseDate;
    }
    public void setFeedbackStatus(Long feedbackStatus) 
    {
        this.feedbackStatus = feedbackStatus;
    }

    public Long getFeedbackStatus() 
    {
        return feedbackStatus;
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
    public void setDelFlg(Long delFlg) 
    {
        this.delFlg = delFlg;
    }

    public Long getDelFlg() 
    {
        return delFlg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inquiryId", getInquiryId())
            .append("buyerId", getBuyerId())
            .append("productId", getProductId())
            .append("inquiryDate", getInquiryDate())
            .append("responseDate", getResponseDate())
            .append("feedbackStatus", getFeedbackStatus())
            .append("priceRmb", getPriceRmb())
            .append("priceUsd", getPriceUsd())
            .append("delFlg", getDelFlg())
            .append("remark", getRemark())
            .toString();
    }
}
