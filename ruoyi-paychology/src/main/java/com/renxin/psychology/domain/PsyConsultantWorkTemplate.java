package com.renxin.psychology.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 咨询师排班模版对象 psy_consultant_work_template
 * 
 * @author renxin
 * @date 2024-08-13
 */
@Data
public class PsyConsultantWorkTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 咨询师id */
    @Excel(name = "咨询师id")
    private Long consultantId;

    /** 咨询师name */
    @Excel(name = "咨询师name")
    private String consultantName;

    /** 开始日期 */
    @Excel(name = "开始日期")
    private String startDate;

    /** 结束日期 */
    @Excel(name = "结束日期")
    private String endDate;

    /** 周一工作时间 */
    @Excel(name = "周一工作时间")
    private String monday;

    /** 周二工作时间 */
    @Excel(name = "周二工作时间")
    private String tuesday;

    /** 周三工作时间 */
    @Excel(name = "周三工作时间")
    private String wednesday;

    /** 周四工作时间 */
    @Excel(name = "周四工作时间")
    private String thursday;

    /** 周五工作时间 */
    @Excel(name = "周五工作时间")
    private String friday;

    /** 周六工作时间 */
    @Excel(name = "周六工作时间")
    private String saturday;

    /** 周日工作时间 */
    @Excel(name = "周日工作时间")
    private String sunday;

    /** 是否自动延续  N/Y */
    @Excel(name = "是否自动延续  N/Y")
    private String isAutoFill;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

}
