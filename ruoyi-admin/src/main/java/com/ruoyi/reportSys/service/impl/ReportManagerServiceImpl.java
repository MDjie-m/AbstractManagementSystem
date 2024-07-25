package com.ruoyi.reportSys.service.impl;

import com.ruoyi.reportSys.domain.HistoricalInspectionReport;
import com.ruoyi.reportSys.mapper.HistoricalInspectionReportMapper;
import com.ruoyi.reportSys.mapper.ReportManagerMapper;
import com.ruoyi.reportSys.service.IHistoricalInspectionReportService;
import com.ruoyi.reportSys.service.ReportManagerService;
import com.ruoyi.windSys.domain.WindTurbineInfo;
import com.ruoyi.reportSys.service.IHistoricalInspectionReportService;
import com.ruoyi.reportSys.service.ReportManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author GG
 * @date 2024-07-15
 */
@Service
public class ReportManagerServiceImpl implements ReportManagerService
{

    @Autowired
    private ReportManagerMapper reportManagerMapper;

    @Override
    public WindTurbineInfo selectReportManagerById(Long wid) {
        return reportManagerMapper.selectReportManagerByHId(wid);
    }


}
