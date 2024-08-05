package com.renxin.psychology.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 优惠券模版对象 psy_coupon_template
 * 
 * @author renxin
 * @date 2024-08-02
 */
public class PsyCouponTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 面向用户类型  1.来访者   2.咨询师 */
    @Excel(name = "面向用户类型  1.来访者   2.咨询师")
    private Long userType;

    /** 优惠券模版名称 */
    @Excel(name = "优惠券模版名称")
    private String couponName;

    /** 发行上限数量 */
    @Excel(name = "发行上限数量")
    private Long totalNum;

    /** 已发行数量 */
    @Excel(name = "已发行数量")
    private Long usedNum;

    /** 券类型  1.抵扣券   2.折扣券 */
    @Excel(name = "券类型  1.抵扣券   2.折扣券")
    private Long couponType;

    /** 服务类型  11.团督  12.个督  13.体验  14.咨询师课程    21.倾诉  22.咨询  23.测评  24.来访者课程   */
    @Excel(name = "服务类型  11.团督  12.个督  13.体验  14.咨询师课程    21.倾诉  22.咨询  23.测评  24.来访者课程  ")
    private Long serverType;

    /** 最大抵扣金额 */
    @Excel(name = "最大抵扣金额")
    private BigDecimal maxDeductionPrice;

    /** 折扣比例 (0.8即8折) */
    @Excel(name = "折扣比例 (0.8即8折)")
    private BigDecimal discountRate;

    /** 有效天数 */
    @Excel(name = "有效天数")
    private Long validityDay;

    /** 模版状态 */
    @Excel(name = "模版状态")
    private Long templateStatus;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserType(Long userType) 
    {
        this.userType = userType;
    }

    public Long getUserType() 
    {
        return userType;
    }
    public void setCouponName(String couponName) 
    {
        this.couponName = couponName;
    }

    public String getCouponName() 
    {
        return couponName;
    }
    public void setTotalNum(Long totalNum) 
    {
        this.totalNum = totalNum;
    }

    public Long getTotalNum() 
    {
        return totalNum;
    }
    public void setUsedNum(Long usedNum) 
    {
        this.usedNum = usedNum;
    }

    public Long getUsedNum() 
    {
        return usedNum;
    }
    public void setCouponType(Long couponType) 
    {
        this.couponType = couponType;
    }

    public Long getCouponType() 
    {
        return couponType;
    }
    public void setServerType(Long serverType) 
    {
        this.serverType = serverType;
    }

    public Long getServerType() 
    {
        return serverType;
    }
    public void setMaxDeductionPrice(BigDecimal maxDeductionPrice) 
    {
        this.maxDeductionPrice = maxDeductionPrice;
    }

    public BigDecimal getMaxDeductionPrice() 
    {
        return maxDeductionPrice;
    }
    public void setDiscountRate(BigDecimal discountRate) 
    {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountRate() 
    {
        return discountRate;
    }
    public void setValidityDay(Long validityDay) 
    {
        this.validityDay = validityDay;
    }

    public Long getValidityDay() 
    {
        return validityDay;
    }
    public void setTemplateStatus(Long templateStatus) 
    {
        this.templateStatus = templateStatus;
    }

    public Long getTemplateStatus() 
    {
        return templateStatus;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userType", getUserType())
            .append("couponName", getCouponName())
            .append("totalNum", getTotalNum())
            .append("usedNum", getUsedNum())
            .append("couponType", getCouponType())
            .append("serverType", getServerType())
            .append("maxDeductionPrice", getMaxDeductionPrice())
            .append("discountRate", getDiscountRate())
            .append("validityDay", getValidityDay())
            .append("templateStatus", getTemplateStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
