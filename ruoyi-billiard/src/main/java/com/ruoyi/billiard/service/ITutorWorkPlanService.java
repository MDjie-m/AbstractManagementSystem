package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.TutorWorkPlan;

/**
 * 教练排班计划Service接口
 * 
 * @author ruoyi
 * @date 2024-10-01
 */
public interface ITutorWorkPlanService  extends IService<TutorWorkPlan>
{
    /**
     * 查询教练排班计划
     * 
     * @param tutorWorkPlanId 教练排班计划主键
     * @return 教练排班计划
     */
    public TutorWorkPlan selectTutorWorkPlanByTutorWorkPlanId(Long tutorWorkPlanId);

    /**
     * 查询教练排班计划列表
     * 
     * @param tutorWorkPlan 教练排班计划
     * @return 教练排班计划集合
     */
    public List<TutorWorkPlan> selectTutorWorkPlanList(TutorWorkPlan tutorWorkPlan);

    /**
     * 新增教练排班计划
     * 
     * @param tutorWorkPlan 教练排班计划
     * @return 结果
     */
    public int insertTutorWorkPlan(TutorWorkPlan tutorWorkPlan);

    /**
     * 修改教练排班计划
     * 
     * @param tutorWorkPlan 教练排班计划
     * @return 结果
     */
    public int updateTutorWorkPlan(TutorWorkPlan tutorWorkPlan);

    /**
     * 批量删除教练排班计划
     * 
     * @param tutorWorkPlanIds 需要删除的教练排班计划主键集合
     * @return 结果
     */
    public int deleteTutorWorkPlanByTutorWorkPlanIds(Long[] tutorWorkPlanIds);

    /**
     * 删除教练排班计划信息
     * 
     * @param tutorWorkPlanId 教练排班计划主键
     * @return 结果
     */
    public int deleteTutorWorkPlanByTutorWorkPlanId(Long tutorWorkPlanId);
}
