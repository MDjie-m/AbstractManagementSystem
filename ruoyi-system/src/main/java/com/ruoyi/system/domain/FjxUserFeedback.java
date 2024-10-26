package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户反馈信息对象 FJX_user_feedback
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
public class FjxUserFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private String id;

    /** 反馈记录的唯一标识 */
    @Excel(name = "反馈记录的唯一标识")
    private Long feedbackId;

    /** 反馈内容 */
    @Excel(name = "反馈内容")
    private String content;

    /** 用户的唯一标识 */
    @Excel(name = "用户的唯一标识")
    private Long userId;

    /** 反馈时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "反馈时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedbackTime;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private String status;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFeedbackId(Long feedbackId) 
    {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackId() 
    {
        return feedbackId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setFeedbackTime(Date feedbackTime) 
    {
        this.feedbackTime = feedbackTime;
    }

    public Date getFeedbackTime() 
    {
        return feedbackTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("feedbackId", getFeedbackId())
            .append("content", getContent())
            .append("userId", getUserId())
            .append("feedbackTime", getFeedbackTime())
            .append("status", getStatus())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
