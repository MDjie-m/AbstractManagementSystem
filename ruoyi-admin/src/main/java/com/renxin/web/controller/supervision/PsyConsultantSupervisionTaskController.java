package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantSupervisionTask;
import com.renxin.psychology.service.IPsyConsultantSupervisionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 团队督导(组织)任务Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/system/task")
public class PsyConsultantSupervisionTaskController extends BaseController
{
    @Autowired
    private IPsyConsultantSupervisionTaskService psyConsultantSupervisionTaskService;

    /**
     * 查询团队督导(组织)任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantSupervisionTask psyConsultantSupervisionTask)
    {
        startPage();
        List<PsyConsultantSupervisionTask> list = psyConsultantSupervisionTaskService.selectPsyConsultantSupervisionTaskList(psyConsultantSupervisionTask);
        return getDataTable(list);
    }

    /**
     * 导出团队督导(组织)任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:export')")
    @Log(title = "团队督导(组织)任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantSupervisionTask psyConsultantSupervisionTask)
    {
        List<PsyConsultantSupervisionTask> list = psyConsultantSupervisionTaskService.selectPsyConsultantSupervisionTaskList(psyConsultantSupervisionTask);
        ExcelUtil<PsyConsultantSupervisionTask> util = new ExcelUtil<PsyConsultantSupervisionTask>(PsyConsultantSupervisionTask.class);
        util.exportExcel(response, list, "团队督导(组织)任务数据");
    }

    /**
     * 获取团队督导(组织)任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(psyConsultantSupervisionTaskService.selectPsyConsultantSupervisionTaskByTaskId(taskId));
    }

    /**
     * 新增团队督导(组织)任务
     */
    @PreAuthorize("@ss.hasPermi('system:task:add')")
    @Log(title = "团队督导(组织)任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantSupervisionTask psyConsultantSupervisionTask)
    {
        return toAjax(psyConsultantSupervisionTaskService.insertPsyConsultantSupervisionTask(psyConsultantSupervisionTask));
    }

    /**
     * 修改团队督导(组织)任务
     */
    @PreAuthorize("@ss.hasPermi('system:task:edit')")
    @Log(title = "团队督导(组织)任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantSupervisionTask psyConsultantSupervisionTask)
    {
        return toAjax(psyConsultantSupervisionTaskService.updatePsyConsultantSupervisionTask(psyConsultantSupervisionTask));
    }

    /**
     * 删除团队督导(组织)任务
     */
    @PreAuthorize("@ss.hasPermi('system:task:remove')")
    @Log(title = "团队督导(组织)任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(psyConsultantSupervisionTaskService.deletePsyConsultantSupervisionTaskByTaskIds(taskIds));
    }
}
