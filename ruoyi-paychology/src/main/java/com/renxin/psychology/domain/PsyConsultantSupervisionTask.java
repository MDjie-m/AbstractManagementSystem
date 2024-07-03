package com.renxin.psychology.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 团队督导(组织)任务对象 psy_consultant_supervision_task
 * 
 * @author renxin
 * @date 2024-06-26
 */
@NoArgsConstructor
@TableName("psy_consultant_supervision_task")
public class PsyConsultantSupervisionTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 团督ID 个督ID */
    @Excel(name = "团督ID 个督ID")
    private Long supervisionId;

    /** 1:团督  2.个体督导  3个人体验 */
    @Excel(name = "1:团督  2.个体督导  3个人体验")
    private String supervisionType;

    /** 咨询师ID */
    @Excel(name = "咨询师ID")
    private String consultantId;

    /** 任务执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务执行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskDatetime;

    /** 周期 */
    @Excel(name = "周期")
    private String cycle;

    /** 周期次数 */
    @Excel(name = "周期次数")
    private Long cycleNumber;

    /** 主题 */
    @Excel(name = "主题")
    private String theme;

    /** 督导记录 */
    @Excel(name = "督导记录")
    private String supervisionRecord;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setSupervisionId(Long supervisionId) 
    {
        this.supervisionId = supervisionId;
    }

    public Long getSupervisionId() 
    {
        return supervisionId;
    }
    public void setSupervisionType(String supervisionType) 
    {
        this.supervisionType = supervisionType;
    }

    public String getSupervisionType() 
    {
        return supervisionType;
    }
    public void setConsultantId(String consultantId) 
    {
        this.consultantId = consultantId;
    }

    public String getConsultantId() 
    {
        return consultantId;
    }
    public void setTaskDatetime(Date taskDatetime) 
    {
        this.taskDatetime = taskDatetime;
    }

    public Date getTaskDatetime() 
    {
        return taskDatetime;
    }
    public void setCycle(String cycle) 
    {
        this.cycle = cycle;
    }

    public String getCycle() 
    {
        return cycle;
    }
    public void setCycleNumber(Long cycleNumber) 
    {
        this.cycleNumber = cycleNumber;
    }

    public Long getCycleNumber() 
    {
        return cycleNumber;
    }
    public void setTheme(String theme) 
    {
        this.theme = theme;
    }

    public String getTheme() 
    {
        return theme;
    }
    public void setSupervisionRecord(String supervisionRecord) 
    {
        this.supervisionRecord = supervisionRecord;
    }

    public String getSupervisionRecord() 
    {
        return supervisionRecord;
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
            .append("taskId", getTaskId())
            .append("supervisionId", getSupervisionId())
            .append("supervisionType", getSupervisionType())
            .append("consultantId", getConsultantId())
            .append("taskDatetime", getTaskDatetime())
            .append("cycle", getCycle())
            .append("cycleNumber", getCycleNumber())
            .append("theme", getTheme())
            .append("supervisionRecord", getSupervisionRecord())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
