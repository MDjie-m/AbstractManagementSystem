package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 团队督导(组织)对象 psy_consultant_team_supervision
 * 
 * @author renxin
 * @date 2024-06-26
 */
@NoArgsConstructor
@TableName("psy_consultant_team_supervision")
@Data
public class PsyConsultantTeamSupervision extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 团督主键 */
    private Long id;

    /** 团督标题 */
    @Excel(name = "团督标题")
    private String title;

    /** 团督logo */
    @Excel(name = "团督logo")
    private String logoPicUrl;

    /** 团督明细地址 */
    @Excel(name = "团督明细地址")
    private String detailPicUrl;

    /** 周期 */
    @Excel(name = "周期")
    private String cycle;

    /** 周期次数 */
    @Excel(name = "周期次数")
    private Long cycleNumber;

    /** 带领者咨询师 */
    @Excel(name = "带领者咨询师")
    private String consultantId;

    /** 0:招募中 1:已开始 2:已结束 3:暂停 */
    @Excel(name = "0:招募中 1:已开始 2:已结束 3:暂停")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;
    
    private Integer pageNum;
    private Integer pageSize;
    

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("logoPicUrl", getLogoPicUrl())
            .append("detailPicUrl", getDetailPicUrl())
            .append("cycle", getCycle())
            .append("cycleNumber", getCycleNumber())
            .append("consultantId", getConsultantId())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
