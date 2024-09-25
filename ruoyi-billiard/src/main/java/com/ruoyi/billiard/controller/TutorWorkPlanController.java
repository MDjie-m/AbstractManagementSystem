package com.ruoyi.billiard.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
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
import com.ruoyi.billiard.domain.TutorWorkPlan;
import com.ruoyi.billiard.service.ITutorWorkPlanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教练排班计划Controller
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@RestController
@RequestMapping("/billiard/tutor/plan")
public class TutorWorkPlanController extends BaseController
{
    @Autowired
    private ITutorWorkPlanService tutorWorkPlanService;

    /**
     * 查询教练排班计划列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:plan:list')")
    @GetMapping("/list")
    public PageResVo<TutorWorkPlan> list(TutorWorkPlan tutorWorkPlan)
    {
        startPage();
        List<TutorWorkPlan> list = tutorWorkPlanService.selectTutorWorkPlanList(tutorWorkPlan);
        return PageResVo.success(list);
    }

    /**
     * 导出教练排班计划列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:plan:export')")
    @Log(title = "教练排班计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TutorWorkPlan tutorWorkPlan)
    {
        List<TutorWorkPlan> list = tutorWorkPlanService.selectTutorWorkPlanList(tutorWorkPlan);
        ExcelUtil<TutorWorkPlan> util = new ExcelUtil<TutorWorkPlan>(TutorWorkPlan.class);
        util.exportExcel(response, list, "教练排班计划数据");
    }

    /**
     * 获取教练排班计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:plan:query')")
    @GetMapping(value = "/{tutorWorkPlanId}")
    public ResultVo<TutorWorkPlan> getInfo(@PathVariable("tutorWorkPlanId") Long tutorWorkPlanId)
    {
        return ResultVo.success(tutorWorkPlanService.selectTutorWorkPlanByTutorWorkPlanId(tutorWorkPlanId));
    }

    /**
     * 新增教练排班计划
     */
    @PreAuthorize("@ss.hasPermi('billiard:plan:add')")
    @Log(title = "教练排班计划", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody TutorWorkPlan tutorWorkPlan)
    {
        return ResultVo.success(tutorWorkPlanService.insertTutorWorkPlan(tutorWorkPlan));
    }

    /**
     * 修改教练排班计划
     */
    @PreAuthorize("@ss.hasPermi('billiard:plan:edit')")
    @Log(title = "教练排班计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody TutorWorkPlan tutorWorkPlan)
    {
        return ResultVo.success(tutorWorkPlanService.updateTutorWorkPlan(tutorWorkPlan));
    }

    /**
     * 删除教练排班计划
     */
    @PreAuthorize("@ss.hasPermi('billiard:plan:remove')")
    @Log(title = "教练排班计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tutorWorkPlanIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] tutorWorkPlanIds)
    {
        return  ResultVo.success(tutorWorkPlanService.deleteTutorWorkPlanByTutorWorkPlanIds(tutorWorkPlanIds));
    }
}
