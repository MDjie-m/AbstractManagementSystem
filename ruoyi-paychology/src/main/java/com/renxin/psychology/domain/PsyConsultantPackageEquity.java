package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 咨询师套餐权益对象 psy_consultant_package_equity
 * 
 * @author renxin
 * @date 2024-07-10
 */
@NoArgsConstructor
@TableName("psy_consultant_package_equity")
@Data
public class PsyConsultantPackageEquity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 咨询师id */
    private Long consultantId;

    /** 团队督导次数 */
    @Excel(name = "团队督导次数")
    private Integer teamSupNum;

    /** 个人督导次数 */
    @Excel(name = "个人督导次数")
    private Integer personSupNum;

    /** 个人体验次数 */
    @Excel(name = "个人体验次数")
    private Integer personExpNum;

    /** 课程次数 */
    @Excel(name = "课程次数")
    private Integer courseNum;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

  
}
