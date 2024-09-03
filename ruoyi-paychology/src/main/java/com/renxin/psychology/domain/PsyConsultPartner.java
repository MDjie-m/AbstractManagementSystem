package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.renxin.common.core.domain.BasePlusEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.renxin.common.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 咨询师入驻申请对象 psy_consult_partner
 * 
 * @author renxin
 * @date 2023-11-07
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_consult_partner")
public class PsyConsultPartner extends BasePlusEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 登录名 */
    @Excel(name = "登录名")
    @TableField(exist = false)
    private Long userId;

    /** 咨询师号 */
    private Long consultId;
    @Excel(name = "咨询师")
    private String consultName;

    /** 步骤1-4 */
    private Integer step;

    /** 申请类型 */
//    private Integer type;

/*    @Excel(name = "申请类型")
    @TableField(exist = false)
    private String typeName;*/

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private Integer sex;

    /** wechat */
    @Excel(name = "微信号")
    private String wechat;

    /** qq */
    @Excel(name = "qq号")
    private String qq;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 语种 */
    @Excel(name = "语种")
    private String lang;

    /** 从业时间 */
    @Excel(name = "从业时间")
    private Integer workHours;

    /** 咨询流派 */
    @Excel(name = "咨询流派")
    private String genre;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String card;

    /** 证件照 */
    @Excel(name = "证件照")
    private String cardImg;

    /** 0-草稿,1-审核中,2-审核通过-已开通账号,3-审核通过-未开通账号,4-审核驳回,5-实名认证中 */
    private String status;

    @Excel(name = "0-草稿,1-审核中,2-审核通过-已开通账号,3-审核通过-未开通账号,4-审核驳回,5-实名认证中")
    @TableField(exist = false)
    private String statusName;

    /** 费用 */
    @Excel(name = "费用")
    private BigDecimal money;

    /** 结算比率 */
    @Excel(name = "结算比率")
    private BigDecimal ratio;

    /** 说明 */
    @Excel(name = "说明")
    private String remark;

    /** 通过时间 */
    @Excel(name = "通过时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date passTime;
    
    @TableField(exist = false)
    private List<PsyConsultPartnerItem> itemList;

}
