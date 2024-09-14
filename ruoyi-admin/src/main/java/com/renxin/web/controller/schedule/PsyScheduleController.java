package com.renxin.web.controller.schedule;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.renxin.psychology.domain.PsyConsultantSchedule;
import com.renxin.psychology.service.IPsyConsultantScheduleService;
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
import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 咨询师排班任务Controller
 *
 * @author renxin
 * @date 2024-09-14
 */
@RestController
@RequestMapping("/system/schedule")
public class PsyScheduleController extends BaseController
{
    @Autowired
    private IPsyConsultantScheduleService psyConsultantScheduleService;

    /**
     * 查询咨询师排班任务列表
     */
    //@PreAuthorize("@ss.hasPermi('system:schedule:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantSchedule psyConsultantSchedule)
    {
        startPage();
        List<PsyConsultantSchedule> list = psyConsultantScheduleService.selectPsyConsultantScheduleList(psyConsultantSchedule);
        return getDataTable(list);
    }

    /**
     * 导出咨询师排班任务列表
     */
    //@PreAuthorize("@ss.hasPermi('system:schedule:export')")
    @Log(title = "咨询师排班任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantSchedule psyConsultantSchedule)
    {
        List<PsyConsultantSchedule> list = psyConsultantScheduleService.selectPsyConsultantScheduleList(psyConsultantSchedule);
        ExcelUtil<PsyConsultantSchedule> util = new ExcelUtil<PsyConsultantSchedule>(PsyConsultantSchedule.class);
        util.exportExcel(response, list, "咨询师排班任务数据");
    }

    /**
     * 获取咨询师排班任务详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:schedule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultantScheduleService.selectPsyConsultantScheduleById(id));
    }

    /**
     * 新增咨询师排班任务
     */
    //@PreAuthorize("@ss.hasPermi('system:schedule:add')")
    @Log(title = "咨询师排班任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantSchedule psyConsultantSchedule)
    {
        return toAjax(psyConsultantScheduleService.insertPsyConsultantSchedule(psyConsultantSchedule));
    }

    /**
     * 修改咨询师排班任务
     */
    //@PreAuthorize("@ss.hasPermi('system:schedule:edit')")
    @Log(title = "咨询师排班任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantSchedule psyConsultantSchedule)
    {
        return toAjax(psyConsultantScheduleService.updatePsyConsultantSchedule(psyConsultantSchedule));
    }

    /**
     * 确认任务完成  ***后续核销在定时器consultBillTask.batchAdd中执行
     */
    //@PreAuthorize("@ss.hasPermi('system:schedule:edit')")
    @Log(title = "咨询师排班任务", businessType = BusinessType.UPDATE)
    @PostMapping("/confirm")
    public AjaxResult confirm(@RequestBody PsyConsultantSchedule psyConsultantSchedule)
    {
        psyConsultantSchedule.setStatus("1");//已完成
        return toAjax(psyConsultantScheduleService.updatePsyConsultantSchedule(psyConsultantSchedule));
    }

    /**
     * 删除咨询师排班任务
     */
    //@PreAuthorize("@ss.hasPermi('system:schedule:remove')")
    @Log(title = "咨询师排班任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyConsultantScheduleService.deletePsyConsultantScheduleByIds(ids));
    }
}
