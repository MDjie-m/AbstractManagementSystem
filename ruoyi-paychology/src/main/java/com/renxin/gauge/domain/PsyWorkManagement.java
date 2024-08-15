package com.renxin.gauge.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 排班管理对象 psy_work_management
 * 
 * @author renxin
 * @date 2023-01-30
 */
@Data
public class PsyWorkManagement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 咨询师id */
    @Excel(name = "咨询师id")
    private Integer consultantId;

    /** 可选班次0全天1上午2下午 3晚上 */
    @Excel(name = "可选班次0全天1上午2下午 3晚上")
    private Integer shift;

    /** 咨询时段 */
    @Excel(name = "咨询时段")
    private Integer timeInterval;

    /** 咨询时长(分钟) */
    @Excel(name = "咨询时长(分钟)")
    private Integer time;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("consultantId", getConsultantId())
            .append("shift", getShift())
            .append("timeInterval", getTimeInterval())
            .append("time", getTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
