package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.TutorWorkPlanDetail;
import com.ruoyi.billiard.service.ITutorWorkPlanDetailService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.TutorWorkPlanDetailMapper;
/**
 * 教练排班计划详细Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-01
 */
@Service
public class TutorWorkPlanDetailServiceImpl extends  ServiceImpl<TutorWorkPlanDetailMapper,TutorWorkPlanDetail> implements ITutorWorkPlanDetailService
{


    /**
     * 查询教练排班计划详细
     * 
     * @param tutorWorkPlanDetailId 教练排班计划详细主键
     * @return 教练排班计划详细
     */
    @Override
    public TutorWorkPlanDetail selectTutorWorkPlanDetailByTutorWorkPlanDetailId(Long tutorWorkPlanDetailId)
    {
        return baseMapper.selectById(tutorWorkPlanDetailId);
    }

    /**
     * 查询教练排班计划详细列表
     * 
     * @param tutorWorkPlanDetail 教练排班计划详细
     * @return 教练排班计划详细
     */
    @Override
    public List<TutorWorkPlanDetail> selectTutorWorkPlanDetailList(TutorWorkPlanDetail tutorWorkPlanDetail)
    {
        return baseMapper.selectTutorWorkPlanDetailList(tutorWorkPlanDetail);
    }

    /**
     * 新增教练排班计划详细
     * 
     * @param tutorWorkPlanDetail 教练排班计划详细
     * @return 结果
     */
    @Override
    public int insertTutorWorkPlanDetail(TutorWorkPlanDetail tutorWorkPlanDetail)
    {
        SecurityUtils.fillCreateUser(tutorWorkPlanDetail);
        tutorWorkPlanDetail.setTutorWorkPlanDetailId(IdUtils.singleNextId());
        return baseMapper.insert(tutorWorkPlanDetail);
    }

    /**
     * 修改教练排班计划详细
     * 
     * @param tutorWorkPlanDetail 教练排班计划详细
     * @return 结果
     */
    @Override
    public int updateTutorWorkPlanDetail(TutorWorkPlanDetail tutorWorkPlanDetail)
    {
        SecurityUtils.fillUpdateUser(tutorWorkPlanDetail);

        return baseMapper.updateById(tutorWorkPlanDetail);
    }

    /**
     * 批量删除教练排班计划详细
     * 
     * @param tutorWorkPlanDetailIds 需要删除的教练排班计划详细主键
     * @return 结果
     */
    @Override
    public int deleteTutorWorkPlanDetailByTutorWorkPlanDetailIds(Long[] tutorWorkPlanDetailIds)
    {
        return baseMapper.deleteTutorWorkPlanDetailByTutorWorkPlanDetailIds(tutorWorkPlanDetailIds);
    }

    /**
     * 删除教练排班计划详细信息
     * 
     * @param tutorWorkPlanDetailId 教练排班计划详细主键
     * @return 结果
     */
    @Override
    public int deleteTutorWorkPlanDetailByTutorWorkPlanDetailId(Long tutorWorkPlanDetailId)
    {
        return baseMapper.deleteTutorWorkPlanDetailByTutorWorkPlanDetailId(tutorWorkPlanDetailId);
    }
}
