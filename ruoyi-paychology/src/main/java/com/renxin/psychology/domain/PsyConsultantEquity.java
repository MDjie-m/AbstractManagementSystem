package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 咨询师权益对象 psy_consultant_equity
 * 
 * @author renxin
 * @date 2024-06-26
 */
@NoArgsConstructor
@TableName("psy_consultant_equity")
public class PsyConsultantEquity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 权益主键 */
    private Long id;

    /** 咨询师ID */
    @Excel(name = "咨询师ID")
    private Long consultantId;

    /** 套餐ID */
    @Excel(name = "套餐ID")
    private Long packageId;

    /** 团督周期 第几期 */
    @Excel(name = "团督周期 第几期")
    private String teamSupervision;

    /** 套餐次数 */
    @Excel(name = "套餐次数")
    private Long supervisionNumber;

    /** 个督套餐 */
    @Excel(name = "个督套餐")
    private String personSupervision;

    /** 个督套餐次数 */
    @Excel(name = "个督套餐次数")
    private Long supervisionNumber2;

    /** 体验套餐 */
    @Excel(name = "体验套餐")
    private String personExperience;

    /** 体验套餐次数 */
    @Excel(name = "体验套餐次数")
    private Long supervisionNumber3;

    /** 课程套餐 */
    @Excel(name = "课程套餐")
    private String courseSupervision;

    /** 课程套餐次数 */
    @Excel(name = "课程套餐次数")
    private Long supervisionNumber4;

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
    public void setPackageId(Long packageId) 
    {
        this.packageId = packageId;
    }

    public Long getPackageId() 
    {
        return packageId;
    }
    public void setTeamSupervision(String teamSupervision) 
    {
        this.teamSupervision = teamSupervision;
    }

    public String getTeamSupervision() 
    {
        return teamSupervision;
    }
    public void setSupervisionNumber(Long supervisionNumber) 
    {
        this.supervisionNumber = supervisionNumber;
    }

    public Long getSupervisionNumber() 
    {
        return supervisionNumber;
    }
    public void setPersonSupervision(String personSupervision) 
    {
        this.personSupervision = personSupervision;
    }

    public String getPersonSupervision() 
    {
        return personSupervision;
    }
    public void setSupervisionNumber2(Long supervisionNumber2) 
    {
        this.supervisionNumber2 = supervisionNumber2;
    }

    public Long getSupervisionNumber2() 
    {
        return supervisionNumber2;
    }
    public void setPersonExperience(String personExperience) 
    {
        this.personExperience = personExperience;
    }

    public String getPersonExperience() 
    {
        return personExperience;
    }
    public void setSupervisionNumber3(Long supervisionNumber3) 
    {
        this.supervisionNumber3 = supervisionNumber3;
    }

    public Long getSupervisionNumber3() 
    {
        return supervisionNumber3;
    }
    public void setCourseSupervision(String courseSupervision) 
    {
        this.courseSupervision = courseSupervision;
    }

    public String getCourseSupervision() 
    {
        return courseSupervision;
    }
    public void setSupervisionNumber4(Long supervisionNumber4) 
    {
        this.supervisionNumber4 = supervisionNumber4;
    }

    public Long getSupervisionNumber4() 
    {
        return supervisionNumber4;
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
            .append("packageId", getPackageId())
            .append("teamSupervision", getTeamSupervision())
            .append("supervisionNumber", getSupervisionNumber())
            .append("personSupervision", getPersonSupervision())
            .append("supervisionNumber2", getSupervisionNumber2())
            .append("personExperience", getPersonExperience())
            .append("supervisionNumber3", getSupervisionNumber3())
            .append("courseSupervision", getCourseSupervision())
            .append("supervisionNumber4", getSupervisionNumber4())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
