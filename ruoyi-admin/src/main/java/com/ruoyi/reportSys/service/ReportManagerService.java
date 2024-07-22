package com.ruoyi.reportSys.service;

import com.ruoyi.reportSys.domain.HistoricalInspectionReport;
import com.ruoyi.windSys.domain.WindTurbineInfo;

import java.util.List;

/**
 * 
 * @author GG
 * @date 2024-07-15
 */
public interface ReportManagerService
{
    public WindTurbineInfo selectReportManagerById(Long wid);
}
