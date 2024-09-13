package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.JobChangeApplication;
import com.ruoyi.system.service.IJobChangeApplicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 调岗申请Controller
 * 
 * @author KK
 * @date 2024-09-13
 */
@RestController
@RequestMapping("/system/application")
public class JobChangeApplicationController extends BaseController
{
    @Autowired
    private IJobChangeApplicationService jobChangeApplicationService;

    /**
     * 查询调岗申请列表
     */
    @PreAuthorize("@ss.hasPermi('system:application:list')")
    @GetMapping("/list")
    public TableDataInfo list(JobChangeApplication jobChangeApplication)
    {
        startPage();
        List<JobChangeApplication> list = jobChangeApplicationService.selectJobChangeApplicationList(jobChangeApplication);
        return getDataTable(list);
    }

    /**
     * 导出调岗申请列表
     */
    @PreAuthorize("@ss.hasPermi('system:application:export')")
    @Log(title = "调岗申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JobChangeApplication jobChangeApplication)
    {
        List<JobChangeApplication> list = jobChangeApplicationService.selectJobChangeApplicationList(jobChangeApplication);
        ExcelUtil<JobChangeApplication> util = new ExcelUtil<JobChangeApplication>(JobChangeApplication.class);
        util.exportExcel(response, list, "调岗申请数据");
    }

    /**
     * 获取调岗申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:application:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jobChangeApplicationService.selectJobChangeApplicationById(id));
    }

    /**
     * 新增调岗申请
     */
    @PreAuthorize("@ss.hasPermi('system:application:add')")
    @Log(title = "调岗申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JobChangeApplication jobChangeApplication)
    {
        return toAjax(jobChangeApplicationService.insertJobChangeApplication(jobChangeApplication));
    }

    /**
     * 修改调岗申请
     */
    @PreAuthorize("@ss.hasPermi('system:application:edit')")
    @Log(title = "调岗申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JobChangeApplication jobChangeApplication)
    {
        return toAjax(jobChangeApplicationService.updateJobChangeApplication(jobChangeApplication));
    }

    /**
     * 删除调岗申请
     */
    @PreAuthorize("@ss.hasPermi('system:application:remove')")
    @Log(title = "调岗申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jobChangeApplicationService.deleteJobChangeApplicationByIds(ids));
    }
}
