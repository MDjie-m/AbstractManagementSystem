package com.renxin.gauge.domain;

import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 心理测评多维设置对象 psy_gauge_multi_setting
 * 
 * @author renxin
 * @date 2022-09-10
 */
@Data
public class PsyGaugeMultiSetting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 测评表单ID */
    @Excel(name = "测评表单ID")
    private Long gaugeId;

    /** 纬度名 */
    @Excel(name = "纬度名")
    private String name;

    /** x */
    private Integer x;
    /** y */
    private Integer y;
    /** 描述 */
    private String info;
    /** 背景色 */
    private String line;
    /** 颜色 */
    private String prd;

    /** 绑定问题ID */
    @Excel(name = "绑定问题ID")
    private String questionIds;

    @Excel(name = "得分设置ID")
    private String scoreSettingIds;

}
