package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BasePlusEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 咨询服务对象 psy_consult
 *
 * @author renxin
 * @date 2023-06-25
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_consult")
public class PsyConsult extends BasePlusEntity implements Serializable
{
    private static final long serialVersionUID = 5172540262606370812L;



    /** 登录名 */
    @Excel(name = "登录名")
    @ApiModelProperty("系统账号")
    private String userName;

    /** 姓名 */
    @Excel(name = "姓名")
    @ApiModelProperty("姓名")
    private String nickName;

    /** 头像 */
//    @Excel(name = "头像")
    @ApiModelProperty("头像")
    private String avatar;
    
    @ApiModelProperty("列表图片")
    private String img;

    /** 用户邮箱 */
    @Excel(name = "邮箱")
    @ApiModelProperty("邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    @ApiModelProperty("手机号码")
    private String phonenumber;

    /** 用户性别 */
    @Excel(name = "性别")
    @ApiModelProperty("性别")
    private String sex;

    /** 标签 */
    @Excel(name = "标签")
    private String tabs;

    /** 擅长领域 */
    @ApiModelProperty("擅长领域")
    private String way;

    @Excel(name = "擅长领域")
    @ApiModelProperty("擅长领域str")
    private String wayStr;

    /** 学历简介 */
    @Excel(name = "学历/简介")
    @ApiModelProperty("学历/简介")
    private String info;

    /** 图文详情 */
    @ApiModelProperty("图文详情")
    private String detail;

    /** 服务数量 */
    private Integer serve;

    /** 咨询人数 */
    @Excel(name = "咨询人数")
    @ApiModelProperty("咨询人数")
    private Integer workNum;
    
    /** 服务时长 */
    @Excel(name = "服务时长")
    @ApiModelProperty("服务时长(小时)")
    private Integer workTime;

    /** 从业时间 */
    @Excel(name = "从业年限(年)")
    @ApiModelProperty("从业年限(年)")
    private Integer workHours;

    /** 企业微信名片 */
//    @Excel(name = "企业微信名片")
    @ApiModelProperty("企业微信名片")
    private String wxCard;

    /** 咨询寄语 */
    @Excel(name = "咨询寄语")
    @ApiModelProperty("咨询寄语")
    private String zxWord;

    /** 咨询风格 */
    @Excel(name = "咨询风格")
    @ApiModelProperty("咨询风格")
    private String zxStyle;

    /** 咨询须知 */
    @Excel(name = "咨询须知")
    @ApiModelProperty("咨询须知")
    private String notice;

    /** 执业资格 */
    @Excel(name = "执业资格")
    @ApiModelProperty("执业资格")
    private String qualification;

    /** 首页展示的执业资格 */
    @Excel(name = "首页展示的执业资格")
    @ApiModelProperty("首页展示的执业资格")
    private String indexQualification;

    /** 可咨询形式 */
    @Excel(name = "可咨询形式")
    @ApiModelProperty("可咨询形式")
    private String mode;

    /** 城市 */
    @Excel(name = "城市")
    @ApiModelProperty("城市")
    private String city;

    /** 省份 */
    @Excel(name = "省份")
    @ApiModelProperty("省份")
    private String province;

    /** 咨询流派 */
    @Excel(name = "咨询流派")
    @ApiModelProperty("咨询流派")
    private String genre;

    private String openId;


    @ApiModelProperty("分享推荐")
    private String share;

    @Excel(name = "语种")
    @ApiModelProperty("语种")
    private String lang;

    /** 受训经历 */
//    @Excel(name = "受训经历")
    @ApiModelProperty("受训经历")
    private String experience;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    @ApiModelProperty("删除标识")
    private String delFlag;

    /** 移动端（0代表显示 1代表隐藏） */
    @ApiModelProperty("是否展示 0显示 1隐藏")
    private String isShow;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    @ApiModelProperty("状态 0正常 1停用")
    private String status;
    
    //是否新用户(未领取新人礼包) 0是 1否
    private Integer isNewPeople; 

    //设备id
    private String deviceId;

    //推送id
    private String pushClientId;

    //设备品牌
    private String deviceBrand;
    
    //设备型号
    private String deviceModel;
    
    //最后登录ip
    private String lastLoginIp;

    private String sourceChannelId;//来源渠道id
    private Long introduceUserId;//介绍人id
    //入驻状态   0未入驻   1已入驻
    private Integer settleStatus;

    // 今日可约
    @TableField(exist = false)
    private Integer buy;
    

    @TableField(exist = false)
    private BigDecimal price;

    @TableField(exist = false)
    private BigDecimal newPrice;

    /** 团队督导券张数 */
    @TableField(exist = false)
    private Integer teamSupNum;

    /** 个案督导券张数 */
    @TableField(exist = false)
    private Integer personSupNum;

    /** 个人体验券张数 */
    @TableField(exist = false)
    private Integer personExpNum;

    /** 课程券张数 */
    @TableField(exist = false)
    private Integer courseNum;

    /**级别  1.学员咨询师   2.初级咨询师   3.中级咨询师   4.高级咨询师   5.初级督导师  6.中级督导师  7.高级督导师*/
    @ApiModelProperty("级别  1.学员咨询师   2.初级咨询师   3.中级咨询师   4.高级咨询师   5.初级督导师  6.中级督导师  7.高级督导师 ")
    private Integer level;

    //服务对象   1来访者  2个案督导  3个人体验
    @ApiModelProperty("服务对象   1来访者  2个案督导  3个人体验")
    private String serviceObject;
    //服务名
    @TableField(exist = false)
    private String serverName;
    //最后下单日期
    @TableField(exist = false)
    private String maxOrderDate;

    //已付款订单次数
    @TableField(exist = false)
    private Integer userOrderCount;
    @TableField(exist = false)
    private List<Long> idList;

    //姓名+手机尾号
    @TableField(exist = false)
    private String nameAndPhone;
}
