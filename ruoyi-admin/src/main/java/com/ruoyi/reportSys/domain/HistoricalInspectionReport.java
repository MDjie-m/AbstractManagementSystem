package com.ruoyi.reportSys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 历史检测报告对象 historical_inspection_report
 * 
 * @author GG
 * @date 2024-07-13
 */
public class HistoricalInspectionReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报告ID */
    private Long hId;

    /** 风机编号 */
    @Excel(name = "风机编号")
    private String turbineCode;

    /** 检测位置 */
    @Excel(name = "检测位置")
    private String inspectionPosition;

    /** 检测方式 */
    @Excel(name = "检测方式")
    private String inspectionMethod;

    /** 检测时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectionDate;

    /** 报告时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报告时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportDate;

    /** 检测人 */
    @Excel(name = "检测人")
    private String inspectorId;

    /** 报告人 */
    @Excel(name = "报告人")
    private String reporterId;

    /** 缺陷问题汇总 */
    @Excel(name = "缺陷问题汇总")
    private String defectSummary;

    /** 检测总结 */
    @Excel(name = "检测总结")
    private String inspectionSummary;

    /** 备用1 */
    private Long hReserve1;

    /** 备用2 */
    private String hReserve2;

    /** 备用3 */
    private String hReserve3;

    /** 备用4 */
    private Date hReserve4;

    public void sethId(Long hId) 
    {
        this.hId = hId;
    }

    public Long gethId() 
    {
        return hId;
    }
    public void setTurbineCode(String turbineCode) 
    {
        this.turbineCode = turbineCode;
    }

    public String getTurbineCode() 
    {
        return turbineCode;
    }
    public void setInspectionPosition(String inspectionPosition) 
    {
        this.inspectionPosition = inspectionPosition;
    }

    public String getInspectionPosition() 
    {
        return inspectionPosition;
    }
    public void setInspectionMethod(String inspectionMethod) 
    {
        this.inspectionMethod = inspectionMethod;
    }

    public String getInspectionMethod() 
    {
        return inspectionMethod;
    }
    public void setInspectionDate(Date inspectionDate) 
    {
        this.inspectionDate = inspectionDate;
    }

    public Date getInspectionDate() 
    {
        return inspectionDate;
    }
    public void setReportDate(Date reportDate) 
    {
        this.reportDate = reportDate;
    }

    public Date getReportDate() 
    {
        return reportDate;
    }
    public void setInspectorId(String inspectorId) 
    {
        this.inspectorId = inspectorId;
    }

    public String getInspectorId() 
    {
        return inspectorId;
    }
    public void setReporterId(String reporterId) 
    {
        this.reporterId = reporterId;
    }

    public String getReporterId() 
    {
        return reporterId;
    }
    public void setDefectSummary(String defectSummary) 
    {
        this.defectSummary = defectSummary;
    }

    public String getDefectSummary() 
    {
        return defectSummary;
    }
    public void setInspectionSummary(String inspectionSummary) 
    {
        this.inspectionSummary = inspectionSummary;
    }

    public String getInspectionSummary() 
    {
        return inspectionSummary;
    }
    public void sethReserve1(Long hReserve1) 
    {
        this.hReserve1 = hReserve1;
    }

    public Long gethReserve1() 
    {
        return hReserve1;
    }
    public void sethReserve2(String hReserve2) 
    {
        this.hReserve2 = hReserve2;
    }

    public String gethReserve2() 
    {
        return hReserve2;
    }
    public void sethReserve3(String hReserve3) 
    {
        this.hReserve3 = hReserve3;
    }

    public String gethReserve3() 
    {
        return hReserve3;
    }
    public void sethReserve4(Date hReserve4) 
    {
        this.hReserve4 = hReserve4;
    }

    public Date gethReserve4() 
    {
        return hReserve4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("hId", gethId())
            .append("turbineCode", getTurbineCode())
            .append("inspectionPosition", getInspectionPosition())
            .append("inspectionMethod", getInspectionMethod())
            .append("inspectionDate", getInspectionDate())
            .append("reportDate", getReportDate())
            .append("inspectorId", getInspectorId())
            .append("reporterId", getReporterId())
            .append("defectSummary", getDefectSummary())
            .append("inspectionSummary", getInspectionSummary())
            .append("hReserve1", gethReserve1())
            .append("hReserve2", gethReserve2())
            .append("hReserve3", gethReserve3())
            .append("hReserve4", gethReserve4())
            .toString();
    }
}
