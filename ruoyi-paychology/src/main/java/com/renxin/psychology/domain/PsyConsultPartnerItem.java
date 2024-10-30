package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BasePlusEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 咨询师入驻申请子对象 psy_consult_partner_item
 * 
 * @author renxin
 * @date 2023-11-07
 */
@Data
@NoArgsConstructor
@TableName("psy_consult_partner_item")
public class PsyConsultPartnerItem extends BasePlusEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 主申请单id */
    @Excel(name = "主申请单id")
    private Long pId;

    /** 申请类型1-7 */
    @Excel(name = "申请类型1-7")
    private Integer type;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;

    /** 小时数 */
    @Excel(name = "小时数")
    private Long num;

    /** 1学校名称,2资质类型,3证书名称,4培训名称,5咨询类型,6督导类型,7其他经历 */
    @Excel(name = "学校名称,资质类型,证书名称,培训名称,咨询类型,督导类型,其他经历")
    private String param1;

    /** 专业名称,证书编号,主办机构,负责人姓名,督导姓名 */
    @Excel(name = "专业名称,证书编号,主办机构,负责人姓名,督导姓名")
    private String param2;

    /** 获得学位,机构联系方式,督导取向 */
    @Excel(name = "获得学位,机构联系方式,督导取向")
    private String param3;

    /** 证明照片 */
    @Excel(name = "证明照片")
    private String img;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    
    @TableField(exist = false)
    private Long consultantId;//咨询师id

}
