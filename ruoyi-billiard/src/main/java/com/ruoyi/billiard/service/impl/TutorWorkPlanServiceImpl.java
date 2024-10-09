package com.ruoyi.billiard.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.ruoyi.billiard.domain.TutorBooking;
import com.ruoyi.billiard.domain.TutorWorkPlanDetail;
import com.ruoyi.billiard.enums.BookingStatus;
import com.ruoyi.billiard.mapper.TutorWorkPlanDetailMapper;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.TutorWorkPlan;
import com.ruoyi.billiard.service.ITutorWorkPlanService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.TutorWorkPlanMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 教练排班计划Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-01
 */
@Service
public class TutorWorkPlanServiceImpl extends ServiceImpl<TutorWorkPlanMapper, TutorWorkPlan> implements ITutorWorkPlanService {

    @Resource
    private TutorWorkPlanDetailMapper tutorWorkPlanDetailMapper;

    /**
     * 查询教练排班计划
     *
     * @param tutorWorkPlanId 教练排班计划主键
     * @return 教练排班计划
     */
    @Override
    public TutorWorkPlan selectTutorWorkPlanByTutorWorkPlanId(Long tutorWorkPlanId) {
        return baseMapper.selectById(tutorWorkPlanId);
    }

    /**
     * 查询教练排班计划列表
     *
     * @param tutorWorkPlan 教练排班计划
     * @return 教练排班计划
     */
    @Override
    public List<TutorWorkPlan> selectTutorWorkPlanList(TutorWorkPlan tutorWorkPlan) {
        return baseMapper.selectTutorWorkPlanList(tutorWorkPlan);
    }

    /**
     * 新增教练排班计划
     *
     * @param tutorWorkPlan 教练排班计划
     * @return 结果
     */
    @Override
    public int insertTutorWorkPlan(TutorWorkPlan tutorWorkPlan) {
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
    public int updateTutorWorkPlan(TutorWorkPlan tutorWorkPlan) {
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
    public int deleteTutorWorkPlanByTutorWorkPlanIds(Long[] tutorWorkPlanIds) {
        return baseMapper.deleteTutorWorkPlanByTutorWorkPlanIds(tutorWorkPlanIds);
    }

    /**
     * 删除教练排班计划信息
     *
     * @param tutorWorkPlanId 教练排班计划主键
     * @return 结果
     */
    @Override
    public int deleteTutorWorkPlanByTutorWorkPlanId(Long tutorWorkPlanId) {
        return baseMapper.deleteTutorWorkPlanByTutorWorkPlanId(tutorWorkPlanId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TutorWorkPlanDetail addPlan(TutorWorkPlanDetail plan) {

        AssertUtil.isTrue(!tutorWorkPlanDetailMapper.exists(tutorWorkPlanDetailMapper.query()
                        .eq(TutorWorkPlanDetail::getTutorId, plan.getTutorId())
                        .gt(TutorWorkPlanDetail::getStartTime, plan.getStartTime())
                        .lt(TutorWorkPlanDetail::getEndTime, plan.getStartTime())),
                "当前时间段已存在排课");
        AssertUtil.isTrue(!tutorWorkPlanDetailMapper.exists(tutorWorkPlanDetailMapper.query()
                        .eq(TutorWorkPlanDetail::getTutorId, plan.getTutorId())
                        .ge(TutorWorkPlanDetail::getStartTime, plan.getEndTime())
                        .le(TutorWorkPlanDetail::getEndTime, plan.getEndTime())),
                "当前时间段已存在排课");

        TutorWorkPlan master = baseMapper.selectOne(baseMapper.query().eq(TutorWorkPlan::getDay, plan.getStartTime().toLocalDate())
                .eq(TutorWorkPlan::getTutorId, plan.getTutorId()).last(" limit 1"));
        if (Objects.isNull(master)) {
            master = TutorWorkPlan.builder().tutorId(plan.getTutorId())
                    .storeId(plan.getStoreId())
                    .day(plan.getStartTime().toLocalDate())
                    .count(1)
                    .tutorWorkPlanId(IdUtils.singleNextId())
                    .build();
            SecurityUtils.fillCreateUser(master);
            baseMapper.insert(master);
        } else {
            master.setCount(Optional.ofNullable(master.getCount()).orElse(0) + 1);
            SecurityUtils.fillUpdateUser(master);
            baseMapper.updateById(master);
        }
        plan.setTutorWorkPlanDetailId(IdUtils.singleNextId());
        plan.setTutorWorkPlanId(master.getTutorWorkPlanId());
        tutorWorkPlanDetailMapper.insert(plan);
        return plan;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removePlan(Long storeId, Long detailId) {
        TutorWorkPlanDetail planDetail = tutorWorkPlanDetailMapper.selectById(detailId);
        AssertUtil.notNullOrEmpty(planDetail, "排课不存在");
        AssertUtil.equal(storeId, planDetail.getStoreId(), "非法参数");
        TutorWorkPlan master = baseMapper.selectById(planDetail.getTutorWorkPlanId());
        AssertUtil.notNullOrEmpty(master, "排课不存在");

        master.setCount(Optional.ofNullable(master.getCount()).orElse(0) - 1);
        if (master.getCount() < 0) {
            master.setCount(0);
        }
        SecurityUtils.fillUpdateUser(master);
        baseMapper.updateById(master);
        tutorWorkPlanDetailMapper.deleteById(detailId);
        return Boolean.TRUE;
    }
}
