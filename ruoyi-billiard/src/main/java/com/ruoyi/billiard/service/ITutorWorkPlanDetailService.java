package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.TutorWorkPlanDetail;

/**
 * 教练排班计划详细Service接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
public interface ITutorWorkPlanDetailService  extends IService<TutorWorkPlanDetail>
{
    /**
     * 查询教练排班计划详细
     * 
     * @param tutorWorkPlanDetailId 教练排班计划详细主键
     * @return 教练排班计划详细
     */
    public TutorWorkPlanDetail selectTutorWorkPlanDetailByTutorWorkPlanDetailId(Long tutorWorkPlanDetailId);

    /**
     * 查询教练排班计划详细列表
     * 
     * @param tutorWorkPlanDetail 教练排班计划详细
     * @return 教练排班计划详细集合
     */
    public List<TutorWorkPlanDetail> selectTutorWorkPlanDetailList(TutorWorkPlanDetail tutorWorkPlanDetail);

    /**
     * 新增教练排班计划详细
     * 
     * @param tutorWorkPlanDetail 教练排班计划详细
     * @return 结果
     */
    public int insertTutorWorkPlanDetail(TutorWorkPlanDetail tutorWorkPlanDetail);

    /**
     * 修改教练排班计划详细
     * 
     * @param tutorWorkPlanDetail 教练排班计划详细
     * @return 结果
     */
    public int updateTutorWorkPlanDetail(TutorWorkPlanDetail tutorWorkPlanDetail);

    /**
     * 批量删除教练排班计划详细
     * 
     * @param tutorWorkPlanDetailIds 需要删除的教练排班计划详细主键集合
     * @return 结果
     */
    public int deleteTutorWorkPlanDetailByTutorWorkPlanDetailIds(Long[] tutorWorkPlanDetailIds);

    /**
     * 删除教练排班计划详细信息
     * 
     * @param tutorWorkPlanDetailId 教练排班计划详细主键
     * @return 结果
     */
    public int deleteTutorWorkPlanDetailByTutorWorkPlanDetailId(Long tutorWorkPlanDetailId);
}
