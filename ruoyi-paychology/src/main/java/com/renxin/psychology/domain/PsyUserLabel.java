package com.renxin.psychology.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 用户标签对象 psy_user_label
 * 
 * @author renxin
 * @date 2024-08-09
 */
@Data
public class PsyUserLabel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;
    
    /** 用户自己填写的标签 */
    @Excel(name = "用户自己填写的标签")
    private String userFillLabel;

    /** 咨询师为用户填写的标签 */
    @Excel(name = "咨询师为用户填写的标签")
    private String consultantFillLabel;

    /** 客服为用户填写的标签 */
    @Excel(name = "客服为用户填写的标签")
    private String adminFillLabel;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String paramNo1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String paramNo2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String paramNo3;

}
