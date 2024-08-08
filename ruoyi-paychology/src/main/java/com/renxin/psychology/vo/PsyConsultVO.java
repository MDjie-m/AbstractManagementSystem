package com.renxin.psychology.vo;

import com.renxin.common.core.domain.BaseValObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 咨询服务对象 psy_consult
 *
 * @author renxin
 * @date 2023-06-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PsyConsultVO extends BaseValObj implements Serializable
{
    private static final long serialVersionUID = 5172540262606370812L;

    /** 登录名 */
    private Long userId;

    /** 登录名 */
    private String userName;
    private String nickName;

    /** 头像 */
    private String avatar;
    private String img;

    /** 用户邮箱 */
    private String email;



    
    /** 手机号码 */
    private String phonenumber;

    /** 用户性别（0男 1女 2未知） */
    private String sex;

    /** 标签 */
    private String tabs;

    /** 咨询方向 */
    private String way;
    private String wayStr;

    /** 介绍文案 */
    private String info;

    /** 图文详情 */
    private String detail;

    /** 服务数量 */
    private Integer serve;

    /** 咨询人数 */
    private Integer workNum;

    /** 服务时长 */
    private Integer workTime;

    /** 从业时间 */
    private Integer workHours;

    /** 企业微信名片 */
    private String wxCard;

    /** 咨询寄语 */
    private String zxWord;

    /** 咨询风格 */
    private String zxStyle;

    /** 咨询须知 */
    private String notice;

    /** 受训经历 */
    private String experience;

    /** 执业资格 */
    private String qualification;

    /** 咨询方式 */
    private String mode;

    /** 城市 */
    private String city;

    /** 省份 */
    private String province;

    private String openId;

    private String share;

    /** 咨询流派 */
    private String genre;

    /** 首页展示的执业资格 */
    private String indexQualification;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 移动端（0代表显示 1代表隐藏） */
    private String isShow;

    /** 状态（0正常 1停用） */
    private String status;

    private String lang;
    
    /** 级别  1.学员咨询师   2.初级咨询师   3.中级咨询师   4.高级咨询师   5.督导师*/
    private Integer level;

    //服务对象   1来访者  2咨询师(督导)   3咨询师(体验)
    private String serviceObject;

    /** 团队督导券张数 */
    private Integer teamSupNum;

    /** 个人督导券张数 */
    private Integer personSupNum;

    /** 个人体验券张数 */
    private Integer personExpNum;

    /** 课程券张数 */
    private Integer courseNum;

    //团督时长
    private int teamSupTime;
    //个督时长
    private int personSupTime;
    //个人体验时长
    private int personExpTime;
    //咨询时长
    private int consultTime;
    //倾听时长
    private int listenTime;
    
}
