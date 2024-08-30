package com.ruoyi.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SysInquiryDTO extends SysProDuctDTO {

    /** 根据询价状态查找，0-待报价，1-已报价，2已拒绝 */
    private Integer feedbackStatus;

    /** 是否查历史询价 */
    private boolean history;

    /** 起始的询价时间-年月日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startInquiryDate;

    /** 结束的询价时间-年月日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endInquiryDate;
}
