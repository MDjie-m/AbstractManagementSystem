package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 咨询师权益核销对象 psy_consultant_equity_verification
 * 
 * @author renxin
 * @date 2024-06-26
 */
@NoArgsConstructor
@TableName("psy_consultant_equity_verification")
public class PsyConsultantEquityVerification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 权益主键 */
    private Long id;

    /** 咨询师ID */
    @Excel(name = "咨询师ID")
    private Long consultantId;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long orderId;

    /** 1团督 2 个督 3体验  4课程 */
    @Excel(name = "1团督 2 个督 3体验  4课程")
    private String cycleType;

    /** 核销次数 */
    @Excel(name = "核销次数")
    private Long cycleNumber;

    /** 0:有效 1:失效 */
    @Excel(name = "0:有效 1:失效")
    private String status;

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
    public void setConsultantId(Long consultantId) 
    {
        this.consultantId = consultantId;
    }

    public Long getConsultantId() 
    {
        return consultantId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setCycleType(String cycleType) 
    {
        this.cycleType = cycleType;
    }

    public String getCycleType() 
    {
        return cycleType;
    }
    public void setCycleNumber(Long cycleNumber) 
    {
        this.cycleNumber = cycleNumber;
    }

    public Long getCycleNumber() 
    {
        return cycleNumber;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("consultantId", getConsultantId())
            .append("orderId", getOrderId())
            .append("cycleType", getCycleType())
            .append("cycleNumber", getCycleNumber())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
