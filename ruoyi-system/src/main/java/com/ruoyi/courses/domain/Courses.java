package com.ruoyi.courses.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程管理对象 courses
 * 
 * @author ruoyi
 * @date 2024-11-05
 */
public class Courses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private String courseCode;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 科目/分类 */
    @Excel(name = "科目/分类")
    private String subject;

    /** 课程描述 */
    @Excel(name = "课程描述")
    private String courseDescription;

    /** 课程价格 */
    @Excel(name = "课程价格")
    private BigDecimal price;

    /** 目标人群 */
    @Excel(name = "目标人群")
    private String targetAudience;

    /** 课程状态 */
    @Excel(name = "课程状态")
    private String status;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setCourseCode(String courseCode) 
    {
        this.courseCode = courseCode;
    }

    public String getCourseCode() 
    {
        return courseCode;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setSubject(String subject) 
    {
        this.subject = subject;
    }

    public String getSubject() 
    {
        return subject;
    }
    public void setCourseDescription(String courseDescription) 
    {
        this.courseDescription = courseDescription;
    }

    public String getCourseDescription() 
    {
        return courseDescription;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setTargetAudience(String targetAudience) 
    {
        this.targetAudience = targetAudience;
    }

    public String getTargetAudience() 
    {
        return targetAudience;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseCode", getCourseCode())
            .append("courseName", getCourseName())
            .append("subject", getSubject())
            .append("courseDescription", getCourseDescription())
            .append("price", getPrice())
            .append("targetAudience", getTargetAudience())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
