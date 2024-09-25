package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.TutorWorkPlanDetail;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 教练排班计划详细Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@Mapper
public interface TutorWorkPlanDetailMapper extends MyBaseMapper<TutorWorkPlanDetail>
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
     * 删除教练排班计划详细
     * 
     * @param tutorWorkPlanDetailId 教练排班计划详细主键
     * @return 结果
     */
    public int deleteTutorWorkPlanDetailByTutorWorkPlanDetailId(Long tutorWorkPlanDetailId);

    /**
     * 批量删除教练排班计划详细
     * 
     * @param tutorWorkPlanDetailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTutorWorkPlanDetailByTutorWorkPlanDetailIds(Long[] tutorWorkPlanDetailIds);
}
