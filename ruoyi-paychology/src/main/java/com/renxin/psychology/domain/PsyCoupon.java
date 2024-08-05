package com.renxin.psychology.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 用户-优惠券发行对象 psy_coupon
 * 
 * @author renxin
 * @date 2024-08-02
 */
public class PsyCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 优惠券编号 */
    private Long couponNo;

    /** 优惠券模版id */
    @Excel(name = "优惠券模版id")
    private Long templateId;

    /** 来访者id */
    @Excel(name = "来访者id")
    private Long userId;

    /** 咨询师id */
    @Excel(name = "咨询师id")
    private Long consultantId;

    /** 是否可用  0可用   1已消耗 */
    @Excel(name = "是否可用  0可用   1已消耗")
    private Long isUsable;

    /** 到期日期 */
    @Excel(name = "到期日期")
    private String expireDate;

    public void setCouponNo(Long couponNo) 
    {
        this.couponNo = couponNo;
    }

    public Long getCouponNo() 
    {
        return couponNo;
    }
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setConsultantId(Long consultantId) 
    {
        this.consultantId = consultantId;
    }

    public Long getConsultantId() 
    {
        return consultantId;
    }
    public void setIsUsable(Long isUsable) 
    {
        this.isUsable = isUsable;
    }

    public Long getIsUsable() 
    {
        return isUsable;
    }
    public void setExpireDate(String expireDate) 
    {
        this.expireDate = expireDate;
    }

    public String getExpireDate() 
    {
        return expireDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("couponNo", getCouponNo())
            .append("templateId", getTemplateId())
            .append("userId", getUserId())
            .append("consultantId", getConsultantId())
            .append("isUsable", getIsUsable())
            .append("createTime", getCreateTime())
            .append("expireDate", getExpireDate())
            .toString();
    }
}
