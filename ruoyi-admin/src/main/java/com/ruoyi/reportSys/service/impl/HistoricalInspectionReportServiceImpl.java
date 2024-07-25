package com.ruoyi.reportSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.reportSys.mapper.HistoricalInspectionReportMapper;
import com.ruoyi.reportSys.domain.HistoricalInspectionReport;
import com.ruoyi.reportSys.service.IHistoricalInspectionReportService;

/**
 * 历史检测报告Service业务层处理
 * 
 * @author GG
 * @date 2024-07-13
 */
@Service
public class HistoricalInspectionReportServiceImpl implements IHistoricalInspectionReportService 
{
    @Autowired
    private HistoricalInspectionReportMapper historicalInspectionReportMapper;

    /**
     * 查询历史检测报告
     * 
     * @param hId 历史检测报告主键
     * @return 历史检测报告
     */
    @Override
    public HistoricalInspectionReport selectHistoricalInspectionReportByHId(Long hId)
    {
        return historicalInspectionReportMapper.selectHistoricalInspectionReportByHId(hId);
    }

    /**
     * 查询历史检测报告列表
     * 
     * @param historicalInspectionReport 历史检测报告
     * @return 历史检测报告
     */
    @Override
    public List<HistoricalInspectionReport> selectHistoricalInspectionReportList(HistoricalInspectionReport historicalInspectionReport)
    {
        return historicalInspectionReportMapper.selectHistoricalInspectionReportList(historicalInspectionReport);
    }

    /**
     * 新增历史检测报告
     * 
     * @param historicalInspectionReport 历史检测报告
     * @return 结果
     */
    @Override
    public int insertHistoricalInspectionReport(HistoricalInspectionReport historicalInspectionReport)
    {
        return historicalInspectionReportMapper.insertHistoricalInspectionReport(historicalInspectionReport);
    }

    /**
     * 修改历史检测报告
     * 
     * @param historicalInspectionReport 历史检测报告
     * @return 结果
     */
    @Override
    public int updateHistoricalInspectionReport(HistoricalInspectionReport historicalInspectionReport)
    {
        return historicalInspectionReportMapper.updateHistoricalInspectionReport(historicalInspectionReport);
    }

    /**
     * 批量删除历史检测报告
     * 
     * @param hIds 需要删除的历史检测报告主键
     * @return 结果
     */
    @Override
    public int deleteHistoricalInspectionReportByHIds(Long[] hIds)
    {
        return historicalInspectionReportMapper.deleteHistoricalInspectionReportByHIds(hIds);
    }

    /**
     * 删除历史检测报告信息
     * 
     * @param hId 历史检测报告主键
     * @return 结果
     */
    @Override
    public int deleteHistoricalInspectionReportByHId(Long hId)
    {
        return historicalInspectionReportMapper.deleteHistoricalInspectionReportByHId(hId);
    }
}
