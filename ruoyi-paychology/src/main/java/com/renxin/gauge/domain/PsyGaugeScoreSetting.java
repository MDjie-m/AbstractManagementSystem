package com.renxin.gauge.domain;

import com.renxin.common.annotation.Excel;
import lombok.Data;

/**
 * 心理测评普通设置对象 psy_gauge_score_setting
 * 
 * @author renxin
 * @date 2022-09-10
 */
@Data
public class PsyGaugeScoreSetting
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 测评表单ID */
    @Excel(name = "测评表单ID")
    private Long gaugeId;

    /** 得分范围start */
    @Excel(name = "得分范围start")
    private Integer start;

    /** 得分范围end */
    @Excel(name = "得分范围end")
    private Integer end;

    /** 建议 */
    @Excel(name = "建议")
    private String proposal;

    @Excel(name = "结论")
    private String result;
    private String params;

    @Excel(name = "纬度")
    private String lat;

    private String memo1;
    private String memo2;
    private String memo3;
    private String memo4;
    private String memo5;
    private String memo6;
    private String memo7;

}
