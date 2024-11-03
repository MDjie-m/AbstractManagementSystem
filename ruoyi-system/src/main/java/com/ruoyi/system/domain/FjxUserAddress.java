package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户地址对象 FJX_user_address
 * 
 * @author ruoyi
 * @date 2024-11-03
 */
public class FjxUserAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */
    private Long id;

    /** 地址记录的唯一标识 */
    @Excel(name = "地址记录的唯一标识")
    private Long addressId;

    /** 用户的唯一标识 */
    @Excel(name = "用户的唯一标识")
    private Long userId;

    /** 收件人姓名 */
    @Excel(name = "收件人姓名")
    private String recipientName;

    /** 收件人联系电话 */
    @Excel(name = "收件人联系电话")
    private String phone;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressDetail;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAddressId(Long addressId) 
    {
        this.addressId = addressId;
    }

    public Long getAddressId() 
    {
        return addressId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRecipientName(String recipientName) 
    {
        this.recipientName = recipientName;
    }

    public String getRecipientName() 
    {
        return recipientName;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAddressDetail(String addressDetail) 
    {
        this.addressDetail = addressDetail;
    }

    public String getAddressDetail() 
    {
        return addressDetail;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("addressId", getAddressId())
            .append("userId", getUserId())
            .append("recipientName", getRecipientName())
            .append("phone", getPhone())
            .append("addressDetail", getAddressDetail())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
