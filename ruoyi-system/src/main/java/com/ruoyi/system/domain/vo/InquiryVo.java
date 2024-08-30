package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InquiryVo extends SysProductVO{
    /** 询价Id */
    private Long inquiryId;

    /** 询价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inquiryDate;

    /** 供应商被询价反馈时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate;

    /** 反馈状态 */
    private int feedbackStatus;
}
