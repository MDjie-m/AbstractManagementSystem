package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.TutorWorkPlan;
import com.ruoyi.billiard.service.ITutorWorkPlanService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.TutorWorkPlanMapper;
/**
 * 教练排班计划Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-01
 */
@Service
public class TutorWorkPlanServiceImpl extends  ServiceImpl<TutorWorkPlanMapper,TutorWorkPlan> implements ITutorWorkPlanService
{


    /**
     * 查询教练排班计划
     * 
     * @param tutorWorkPlanId 教练排班计划主键
     * @return 教练排班计划
     */
    @Override
    public TutorWorkPlan selectTutorWorkPlanByTutorWorkPlanId(Long tutorWorkPlanId)
    {
        return baseMapper.selectById(tutorWorkPlanId);
    }

    /**
     * 查询教练排班计划列表
     * 
     * @param tutorWorkPlan 教练排班计划
     * @return 教练排班计划
     */
    @Override
    public List<TutorWorkPlan> selectTutorWorkPlanList(TutorWorkPlan tutorWorkPlan)
    {
        return baseMapper.selectTutorWorkPlanList(tutorWorkPlan);
    }

    /**
     * 新增教练排班计划
     * 
     * @param tutorWorkPlan 教练排班计划
     * @return 结果
     */
    @Override
    public int insertTutorWorkPlan(TutorWorkPlan tutorWorkPlan)
    {
        SecurityUtils.fillCreateUser(tutorWorkPlan);
        tutorWorkPlan.setTutorWorkPlanId(IdUtils.singleNextId());
        return baseMapper.insert(tutorWorkPlan);
    }

    /**
     * 修改教练排班计划
     * 
     * @param tutorWorkPlan 教练排班计划
     * @return 结果
     */
    @Override
    public int updateTutorWorkPlan(TutorWorkPlan tutorWorkPlan)
    {
        SecurityUtils.fillUpdateUser(tutorWorkPlan);

        return baseMapper.updateById(tutorWorkPlan);
    }

    /**
     * 批量删除教练排班计划
     * 
     * @param tutorWorkPlanIds 需要删除的教练排班计划主键
     * @return 结果
     */
    @Override
    public int deleteTutorWorkPlanByTutorWorkPlanIds(Long[] tutorWorkPlanIds)
    {
        return baseMapper.deleteTutorWorkPlanByTutorWorkPlanIds(tutorWorkPlanIds);
    }

    /**
     * 删除教练排班计划信息
     * 
     * @param tutorWorkPlanId 教练排班计划主键
     * @return 结果
     */
    @Override
    public int deleteTutorWorkPlanByTutorWorkPlanId(Long tutorWorkPlanId)
    {
        return baseMapper.deleteTutorWorkPlanByTutorWorkPlanId(tutorWorkPlanId);
    }
}
