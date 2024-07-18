package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
import java.util.List;

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

    /** head图片 */
    @Excel(name = "head图片")
    private String headPicUrl;
    
    /** 小组特色(图片) */
    private String specialPicUrl;

    /** 报名须知(图片) */
    private String registerNoticePicUrl;

    /** 周期 */
    @Excel(name = "周期")
    private String cycle;

    /** 周期次数 */
    @Excel(name = "周期次数")
    private Long cycleNumber;

    /** 督导师 */
    @Excel(name = "督导师")
    private String consultantId;

    /** 0:招募中 1:已开始 2:已结束 3:暂停 */
    @Excel(name = "0:招募中 1:已开始 2:已结束 3:暂停")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 当前期数 */
    private Integer periodNo;

    /** 星期几开课 */
    private Integer weekDay;

    /** 开课日开始时间 */
    private String lectureStartTime;

    /** 开课日结束时间 */
    private String lectureEndTime;

    //课堂时长(小时)
    private Double lectureHour;

    /** 初次开课日期 */
    private String firstLectureDate;

    /** 最大团队人数 */
    private Integer maxNumPeople;

    /** 入会价格 */
    private BigDecimal price;

    /** 督导类型  1.团体督导  2.个体督导  3.个人体验 */
    private Integer teamType;


    /** 督导师 - 姓名 */
    private String consultUserName;
    /** 督导师 - 头像 */
    private String consultAvatar;
    /** 督导师 - 标签 */
    private String consultTabs;
    /** 督导师 - 介绍 */
    private String consultInfo;
    /** 督导师 - 详情 */
    private String consultDetail;
    /** 督导师 - 经历 */
    private String consultExperience;
    /** 督导师 - 流派方向 */
    private String consultGenre;
    /** 督导师 - 咨询方式 */
    private String consultMode;
    /** 督导师 - 执业资格 */
    private String consultQualification;
    /** 督导师 - 咨询方向 */
    private String consultWayStr;


    /** 最早讲课时间*/
    private String lectureStartTimeBegin;
    /** 最晚讲课时间*/
    private String lectureEndTimeFinish;
    /** 最高价格*/
    private Double maxPrice;
    /** 最低价格*/
    private Double minPrice;
    /** 近期几天内可约*/
    private Integer waitDays;
    /** 剩余名额数*/
    private Integer surplusNum;

    /** 成员清单*/
    private List<PsyConsultantSupervisionMember> memberList;
    
    /** 督导师详情*/
    private PsyConsult consultantDetail;
    

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
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
