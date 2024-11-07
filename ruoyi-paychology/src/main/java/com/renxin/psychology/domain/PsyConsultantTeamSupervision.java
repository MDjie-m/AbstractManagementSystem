package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import org.apache.poi.hpsf.Decimal;

import java.io.Serializable;
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

    /** 团队主键 */
    private Long id;

    /** 团队标题 */
    @Excel(name = "团队标题")
    private String title;

    //头像
    private String avatarPicUrl;
    
    /** head图片 */
    @Excel(name = "head图片")
    private String headPicUrl;
    
    /** 小组特色(图片) */
    private String specialPicUrl;

    /** 报名须知(图片) */
    private String registerNoticePicUrl;
    
    private String cycle;

    /** 周期次数 */
    @Excel(name = "活动次数")
    private Integer cycleNumber;

    /** 督导师 */
    @Excel(name = "督导师")
    private Long consultantId;

    /** 0:招募中 1:已开始 2:已结束 3:暂停 */
    @Excel(name = "0:招募中 1:已开始 2:已结束 3:暂停")
    private Integer status;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    private Integer delFlag;

    
    /** 服务对象类型 1.来访者  2.咨询师*/
    private Integer serveUserType;
    
    /** 当前期数 / 第几期 */
    private Integer periodNo;

    /** 星期几活动 */
    private Integer weekDay;

    /** 活动日开始时间 */
    private String lectureStartTime;

    /** 活动日结束时间 */
    private String lectureEndTime;

    //课堂时长(小时)
    @TableField(exist = false)
    private Double lectureHour;

    /** 初次活动日期 */
    private String firstLectureDate;

    /** 最大团队人数 */
    private Integer maxNumPeople;

    /** 入会价格 */
    private BigDecimal price;
    //督导师每堂课的收入
    private BigDecimal lectureAmount;
    //标签
    private String label;
    
    /** 团体类型  1.团体督导  2.1V2督导  3.读书会  4.活动小组 */
    private Integer teamType;
    //是否可观摩
    private String isAbleOb;
    //观摩价格
    private BigDecimal obPrice;


    /** 督导师 - 登录名 */
    @TableField(exist = false)
    private String consultUserName;
    /** 督导师 - 姓名 */
    @TableField(exist = false)
    private String consultNickName;
    /** 督导师 - 头像 */
    @TableField(exist = false)
    private String consultAvatar;
    /** 督导师 - 列表图片 */
    @TableField(exist = false)
    private String consultImg;
    /** 督导师 - 标签 */
    @TableField(exist = false)
    private String consultTabs;
    /** 督导师 - 介绍 */
    @TableField(exist = false)
    private String consultInfo;
    /** 督导师 - 详情 */
    @TableField(exist = false)
    private String consultDetail;
    /** 督导师 - 经历 */
    @TableField(exist = false)
    private String consultExperience;
    /** 督导师 - 流派方向 */
    @TableField(exist = false)
    private String consultGenre;
    /** 督导师 - 咨询方式 */
    @TableField(exist = false)
    private String consultMode;
    /** 督导师 - 执业资格 */
    @TableField(exist = false)
    private String consultQualification;
    /** 督导师 - 咨询方向 */
    @TableField(exist = false)
    private String consultWayStr;
    /** 督导师 - 级别  1.学员咨询师   2.初级咨询师   3.中级咨询师   4.高级咨询师   5.督导师 */
    @TableField(exist = false)
    private String consultLevel;


    /** 最早讲课时间*/
    @TableField(exist = false)
    private String lectureStartTimeBegin;
    @TableField(exist = false)
    /** 最晚讲课时间*/
    private String lectureEndTimeFinish;
    @TableField(exist = false)
    /** 最高价格*/
    private Double maxPrice;
    /** 最低价格*/
    @TableField(exist = false)
    private Double minPrice;
    /** 近期几天内可约*/
    @TableField(exist = false)
    private Integer waitDays;
    /** 剩余名额数*/
    @TableField(exist = false)
    private Integer surplusJoinNum;

    /** 成员清单*/
    @TableField(exist = false)
    private List<PsyConsultantSupervisionMember> memberList;
    
    @TableField(exist = false)
    private Integer totalNum;//总服务次数
    @TableField(exist = false)
    private Integer usedNum;//已使用服务次数
    @TableField(exist = false)
    private Integer surplusNum;//剩余服务次数
    @TableField(exist = false)
    private String nextBeginTime;//下次服务开始时间

    /** 督导师详情*/
    @TableField(exist = false)
    private PsyConsult consultantDetail;
    @TableField(exist = false)
    private List<Long> idList;
    //清单类型
    @TableField(exist = false)
    private String listType;
    
}
