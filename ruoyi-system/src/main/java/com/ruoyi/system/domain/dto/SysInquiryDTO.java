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

    public Integer getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(Integer feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    public Date getStartInquiryDate() {
        return startInquiryDate;
    }

    public void setStartInquiryDate(Date startInquiryDate) {
        this.startInquiryDate = startInquiryDate;
    }

    public Date getEndInquiryDate() {
        return endInquiryDate;
    }

    public void setEndInquiryDate(Date endInquiryDate) {
        this.endInquiryDate = endInquiryDate;
    }
}
