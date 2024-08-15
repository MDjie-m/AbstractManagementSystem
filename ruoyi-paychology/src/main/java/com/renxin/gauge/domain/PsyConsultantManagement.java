package com.renxin.gauge.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 咨询师管理对象 psy_consultant_management
 * 
 * @author yangchuang
 * @date 2023-01-29
 */
@Data
public class PsyConsultantManagement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 咨询师名称 */
    @Excel(name = "咨询师名称")
    private String name;

    /** 所在城市 */
    @Excel(name = "所在城市")
    private String city;

    /** 性别 */
    @Excel(name = "性别")
    private Integer sex;

    /** 咨询方向 */
    @Excel(name = "咨询方向")
    private String consultingDirection;

    /** 简介 */
    @Excel(name = "简介")
    private String summary;

    /** 咨询人数 */
    @Excel(name = "咨询人数")
    private Integer consultingNum;

    /** 服务时长 */
    @Excel(name = "服务时长")
    private Integer serviceDuration;

    /** 从业年限（或者存从业时间） */
    @Excel(name = "从业年限", readConverterExp = "或=者存从业时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date employmentTime;

    /** 咨询服务（只存id） */
    @Excel(name = "咨询服务", readConverterExp = "只=存id")
    private String consultationMethods;

    /** 老师介绍 */
    @Excel(name = "老师介绍")
    private String introduce;

    /** 头像 */
    @Excel(name = "头像")
    private String headPortrait;

}
