package com.ruoyi.reportSys.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.reportSys.domain.HistoricalInspectionReport;
import com.ruoyi.reportSys.service.IHistoricalInspectionReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 历史检测报告Controller
 * 
 * @author GG
 * @date 2024-07-13
 */
@RestController
@RequestMapping("/reportSys/historyreport")
public class HistoricalInspectionReportController extends BaseController
{
    @Autowired
    private IHistoricalInspectionReportService historicalInspectionReportService;

    /**
     * 查询历史检测报告列表
     */
    @PreAuthorize("@ss.hasPermi('reportSys:historyreport:list')")
    @GetMapping("/list")
    public TableDataInfo list(HistoricalInspectionReport historicalInspectionReport)
    {
        startPage();
        List<HistoricalInspectionReport> list = historicalInspectionReportService.selectHistoricalInspectionReportList(historicalInspectionReport);
        return getDataTable(list);
    }

    /**
     * 导出历史检测报告列表
     */
    @PreAuthorize("@ss.hasPermi('reportSys:historyreport:export')")
    @Log(title = "历史检测报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HistoricalInspectionReport historicalInspectionReport)
    {
        List<HistoricalInspectionReport> list = historicalInspectionReportService.selectHistoricalInspectionReportList(historicalInspectionReport);
        ExcelUtil<HistoricalInspectionReport> util = new ExcelUtil<HistoricalInspectionReport>(HistoricalInspectionReport.class);
        util.exportExcel(response, list, "历史检测报告数据");
    }

    /**
     * 获取历史检测报告详细信息
     */
    @PreAuthorize("@ss.hasPermi('reportSys:historyreport:query')")
    @GetMapping(value = "/{hId}")
    public AjaxResult getInfo(@PathVariable("hId") Long hId)
    {
        return success(historicalInspectionReportService.selectHistoricalInspectionReportByHId(hId));
    }

    /**
     * 新增历史检测报告
     */
    @PreAuthorize("@ss.hasPermi('reportSys:historyreport:add')")
    @Log(title = "历史检测报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HistoricalInspectionReport historicalInspectionReport)
    {
        return toAjax(historicalInspectionReportService.insertHistoricalInspectionReport(historicalInspectionReport));
    }

    /**
     * 修改历史检测报告
     */
    @PreAuthorize("@ss.hasPermi('reportSys:historyreport:edit')")
    @Log(title = "历史检测报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HistoricalInspectionReport historicalInspectionReport)
    {
        return toAjax(historicalInspectionReportService.updateHistoricalInspectionReport(historicalInspectionReport));
    }

    /**
     * 删除历史检测报告
     */
    @PreAuthorize("@ss.hasPermi('reportSys:historyreport:remove')")
    @Log(title = "历史检测报告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{hIds}")
    public AjaxResult remove(@PathVariable Long[] hIds)
    {
        return toAjax(historicalInspectionReportService.deleteHistoricalInspectionReportByHIds(hIds));
    }
}
