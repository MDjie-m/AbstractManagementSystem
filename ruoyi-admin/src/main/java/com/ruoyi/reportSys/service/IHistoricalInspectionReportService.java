package com.ruoyi.reportSys.service;

import java.util.List;
import com.ruoyi.reportSys.domain.HistoricalInspectionReport;

/**
 * 历史检测报告Service接口
 * 
 * @author GG
 * @date 2024-07-13
 */
public interface IHistoricalInspectionReportService 
{
    /**
     * 查询历史检测报告
     * 
     * @param hId 历史检测报告主键
     * @return 历史检测报告
     */
    public HistoricalInspectionReport selectHistoricalInspectionReportByHId(Long hId);

    /**
     * 查询历史检测报告列表
     * 
     * @param historicalInspectionReport 历史检测报告
     * @return 历史检测报告集合
     */
    public List<HistoricalInspectionReport> selectHistoricalInspectionReportList(HistoricalInspectionReport historicalInspectionReport);

    /**
     * 新增历史检测报告
     * 
     * @param historicalInspectionReport 历史检测报告
     * @return 结果
     */
    public int insertHistoricalInspectionReport(HistoricalInspectionReport historicalInspectionReport);

    /**
     * 修改历史检测报告
     * 
     * @param historicalInspectionReport 历史检测报告
     * @return 结果
     */
    public int updateHistoricalInspectionReport(HistoricalInspectionReport historicalInspectionReport);

    /**
     * 批量删除历史检测报告
     * 
     * @param hIds 需要删除的历史检测报告主键集合
     * @return 结果
     */
    public int deleteHistoricalInspectionReportByHIds(Long[] hIds);

    /**
     * 删除历史检测报告信息
     * 
     * @param hId 历史检测报告主键
     * @return 结果
     */
    public int deleteHistoricalInspectionReportByHId(Long hId);
}
