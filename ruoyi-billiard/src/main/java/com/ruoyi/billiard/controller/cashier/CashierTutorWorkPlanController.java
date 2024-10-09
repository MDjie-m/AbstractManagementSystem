package com.ruoyi.billiard.controller.cashier;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.domain.TutorWorkPlanDetail;
import com.ruoyi.billiard.domain.vo.IAdd;
import com.ruoyi.billiard.enums.EmployeeStatus;
import com.ruoyi.billiard.service.ITutorWorkPlanDetailService;
import com.ruoyi.billiard.service.ITutorWorkPlanService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.utils.ArrayUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cashier/tutor/work-plan")
@PreAuthorize("@ss.hasRole('cashier')")
public class CashierTutorWorkPlanController extends BaseController {

    @Resource
    private ITutorWorkPlanService tutorWorkPlanService;

    @Resource
    private ITutorWorkPlanDetailService tutorWorkPlanDetailService;

    @GetMapping("list")
    public ResultVo<List<TutorWorkPlanDetail>> list(TutorWorkPlanDetail plan) {
        plan.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(tutorWorkPlanDetailService.selectTutorWorkPlanDetailList(plan));
    }

    @GetMapping("map")
    public ResultVo<Map<String, List<TutorWorkPlanDetail>>> map(TutorWorkPlanDetail plan) {
        plan.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(ArrayUtil.groupBy(tutorWorkPlanDetailService.selectTutorWorkPlanDetailList(plan), p ->
                LocalDateTimeUtil.format(p.getStartTime(), DatePattern.NORM_DATE_PATTERN)));
    }

    @PostMapping("")
    public ResultVo<TutorWorkPlanDetail> add(@RequestBody @Validated(IAdd.class) TutorWorkPlanDetail plan) {
        plan.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(tutorWorkPlanService.addPlan(plan));
    }

    @DeleteMapping("/{detailId}")
    public ResultVo<Boolean> removePlan(@PathVariable Long detailId) {

        return ResultVo.success(tutorWorkPlanService.removePlan(getStoreIdWithThrow(), detailId));
    }
}
