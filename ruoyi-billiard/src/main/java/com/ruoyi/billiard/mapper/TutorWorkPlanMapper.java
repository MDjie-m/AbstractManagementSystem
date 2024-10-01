package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.TutorWorkPlan;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 教练排班计划Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-01
 */
@Mapper
public interface TutorWorkPlanMapper extends MyBaseMapper<TutorWorkPlan>
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
     * 删除教练排班计划
     * 
     * @param tutorWorkPlanId 教练排班计划主键
     * @return 结果
     */
    public int deleteTutorWorkPlanByTutorWorkPlanId(Long tutorWorkPlanId);

    /**
     * 批量删除教练排班计划
     * 
     * @param tutorWorkPlanIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTutorWorkPlanByTutorWorkPlanIds(Long[] tutorWorkPlanIds);
}
