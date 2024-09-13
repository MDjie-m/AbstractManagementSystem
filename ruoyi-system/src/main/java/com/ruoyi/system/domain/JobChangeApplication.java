package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 调岗申请对象 job_change_application
 * 
 * @author KK
 * @date 2024-09-13
 */
public class JobChangeApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 调岗表标题 */
    @Excel(name = "调岗表标题")
    private String applicationTitle;

    /** 申请人 */
    @Excel(name = "申请人")
    private String applicationName;

    /** 当前部门 */
    @Excel(name = "当前部门")
    private String currentDepartment;

    /** 当前职务 */
    @Excel(name = "当前职务")
    private String currentPosition;

    /** 当前职级 */
    @Excel(name = "当前职级")
    private String currentRank;

    /** 调岗后部门id */
    private Long targetDepartmentId;

    /** 调岗后职务 */
    @Excel(name = "调岗后职务")
    private String targetPosition;

    /** 调岗后职级 */
    @Excel(name = "调岗后职级")
    private String targetRank;

    /** 转岗生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转岗生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectiveDate;

    /** 调岗理由 */
    @Excel(name = "调岗理由")
    private String transferReason;

    /** 文件名字 */
    private String fileName;

    /** 附件证明 */
    private String attachmentFile;

    /** 用户id */
    private Long userId;

    /** 部门id */
    private Long deptId;

    /** 职位id */
    private Long postId;

    /** $column.columnComment */
    private Long newRoseId;

    /** $column.columnComment */
    private String targetDepartment;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setApplicationTitle(String applicationTitle) 
    {
        this.applicationTitle = applicationTitle;
    }

    public String getApplicationTitle() 
    {
        return applicationTitle;
    }
    public void setApplicationName(String applicationName) 
    {
        this.applicationName = applicationName;
    }

    public String getApplicationName() 
    {
        return applicationName;
    }
    public void setCurrentDepartment(String currentDepartment) 
    {
        this.currentDepartment = currentDepartment;
    }

    public String getCurrentDepartment() 
    {
        return currentDepartment;
    }
    public void setCurrentPosition(String currentPosition) 
    {
        this.currentPosition = currentPosition;
    }

    public String getCurrentPosition() 
    {
        return currentPosition;
    }
    public void setCurrentRank(String currentRank) 
    {
        this.currentRank = currentRank;
    }

    public String getCurrentRank() 
    {
        return currentRank;
    }
    public void setTargetDepartmentId(Long targetDepartmentId) 
    {
        this.targetDepartmentId = targetDepartmentId;
    }

    public Long getTargetDepartmentId() 
    {
        return targetDepartmentId;
    }
    public void setTargetPosition(String targetPosition) 
    {
        this.targetPosition = targetPosition;
    }

    public String getTargetPosition() 
    {
        return targetPosition;
    }
    public void setTargetRank(String targetRank) 
    {
        this.targetRank = targetRank;
    }

    public String getTargetRank() 
    {
        return targetRank;
    }
    public void setEffectiveDate(Date effectiveDate) 
    {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() 
    {
        return effectiveDate;
    }
    public void setTransferReason(String transferReason) 
    {
        this.transferReason = transferReason;
    }

    public String getTransferReason() 
    {
        return transferReason;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setAttachmentFile(String attachmentFile) 
    {
        this.attachmentFile = attachmentFile;
    }

    public String getAttachmentFile() 
    {
        return attachmentFile;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setPostId(Long postId) 
    {
        this.postId = postId;
    }

    public Long getPostId() 
    {
        return postId;
    }
    public void setNewRoseId(Long newRoseId) 
    {
        this.newRoseId = newRoseId;
    }

    public Long getNewRoseId() 
    {
        return newRoseId;
    }
    public void setTargetDepartment(String targetDepartment) 
    {
        this.targetDepartment = targetDepartment;
    }

    public String getTargetDepartment() 
    {
        return targetDepartment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("applicationTitle", getApplicationTitle())
            .append("applicationName", getApplicationName())
            .append("currentDepartment", getCurrentDepartment())
            .append("currentPosition", getCurrentPosition())
            .append("currentRank", getCurrentRank())
            .append("targetDepartmentId", getTargetDepartmentId())
            .append("targetPosition", getTargetPosition())
            .append("targetRank", getTargetRank())
            .append("effectiveDate", getEffectiveDate())
            .append("transferReason", getTransferReason())
            .append("fileName", getFileName())
            .append("attachmentFile", getAttachmentFile())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("postId", getPostId())
            .append("newRoseId", getNewRoseId())
            .append("targetDepartment", getTargetDepartment())
            .toString();
    }
}
