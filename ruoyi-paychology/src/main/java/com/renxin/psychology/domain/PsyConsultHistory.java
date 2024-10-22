package com.renxin.psychology.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 心理咨询师-修改记录对象 psy_consult_history
 *
 * @author renxin
 * @date 2024-10-22
 */
@Data
public class PsyConsultHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 咨询师id */
    @Excel(name = "咨询师id")
    private Long consultantId;

    /** 执行状态  1审核中  2已完成  3审核拒绝 */
    @Excel(name = "执行状态  1审核中  2已完成  3审核拒绝")
    private Integer executeStatus;

    /** 修改字段 */
    @Excel(name = "修改字段")
    private String updateColumn;

    /** 修改详情 */
    @Excel(name = "修改详情")
    private String updateDetail;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String spare1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String spare2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String spare3;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String spare4;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String spare5;
    
}
