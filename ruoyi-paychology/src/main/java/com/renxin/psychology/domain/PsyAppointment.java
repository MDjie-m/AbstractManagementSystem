package com.renxin.psychology.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;

import java.util.Date;

/**
 * 咨询师预约对象 psy_appointment
 * 
 * @author renxin
 * @date 2022-08-26
 */
@Data
public class PsyAppointment
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 咨询师ID */
    @Excel(name = "咨询师ID")
    private Integer consultantId;

    /** 那一天 */
    @Excel(name = "那一天")
    private String day;

    /** 时段 */
    @Excel(name = "时段")
    private String timeInterval;


    /** 创建时间 */
    @Excel(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("consultantId", getConsultantId())
            .append("day", getDay())
            .append("timeInterval", getTimeInterval())
            .append("createTime", getCreateTime())
            .toString();
    }


}
