package com.ruoyi.billiard.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.ruoyi.billiard.service.IStoreScheduleService;
import com.ruoyi.common.core.domain.model.Tuple;
import com.ruoyi.common.core.domain.model.Tuple3;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.TutorPunchIn;
import com.ruoyi.billiard.service.ITutorPunchInService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.TutorPunchInMapper;

import javax.annotation.Resource;

/**
 * 教练打卡Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-01
 */
@Service
public class TutorPunchInServiceImpl extends ServiceImpl<TutorPunchInMapper, TutorPunchIn> implements ITutorPunchInService {


    @Resource
    private IStoreScheduleService storeScheduleService;


    /**
     * 查询教练打卡
     *
     * @param tutorPunchInId 教练打卡主键
     * @return 教练打卡
     */
    @Override
    public TutorPunchIn selectTutorPunchInByTutorPunchInId(Long tutorPunchInId) {
        return baseMapper.selectById(tutorPunchInId);
    }

    /**
     * 查询教练打卡列表
     *
     * @param tutorPunchIn 教练打卡
     * @return 教练打卡
     */
    @Override
    public List<TutorPunchIn> selectTutorPunchInList(TutorPunchIn tutorPunchIn) {
        return baseMapper.selectTutorPunchInList(tutorPunchIn);
    }

    /**
     * 新增教练打卡
     *
     * @param tutorPunchIn 教练打卡
     * @return 结果
     */
    @Override
    public int insertTutorPunchIn(TutorPunchIn tutorPunchIn) {
        SecurityUtils.fillCreateUser(tutorPunchIn);
        tutorPunchIn.setTutorPunchInId(IdUtils.singleNextId());
        return baseMapper.insert(tutorPunchIn);
    }

    /**
     * 修改教练打卡
     *
     * @param tutorPunchIn 教练打卡
     * @return 结果
     */
    @Override
    public int updateTutorPunchIn(TutorPunchIn tutorPunchIn) {
        SecurityUtils.fillUpdateUser(tutorPunchIn);

        return baseMapper.updateById(tutorPunchIn);
    }

    /**
     * 批量删除教练打卡
     *
     * @param tutorPunchInIds 需要删除的教练打卡主键
     * @return 结果
     */
    @Override
    public int deleteTutorPunchInByTutorPunchInIds(Long[] tutorPunchInIds) {
        return baseMapper.deleteTutorPunchInByTutorPunchInIds(tutorPunchInIds);
    }

    /**
     * 删除教练打卡信息
     *
     * @param tutorPunchInId 教练打卡主键
     * @return 结果
     */
    @Override
    public int deleteTutorPunchInByTutorPunchInId(Long tutorPunchInId) {
        return baseMapper.deleteTutorPunchInByTutorPunchInId(tutorPunchInId);
    }

    @Override
    public Boolean punchIn(Long storeId, Long tutorId, LocalDate scheduleTime, LocalDateTime time) {
        Tuple3<LocalDateTime, LocalDateTime, LocalDateTime> scheduleLocalTime = storeScheduleService.getDayScheduleLocalTime(storeId, DateUtils.toDate(scheduleTime));
        AssertUtil.isTrue(time.isAfter(scheduleLocalTime.getValue()), "当前班次还未到开始时间");
        AssertUtil.isTrue(time.isBefore(scheduleLocalTime.getValue1()), "打卡时间已超出当前班次结束时间");

        TutorPunchIn punchIn = baseMapper.selectOne(baseMapper.query().eq(TutorPunchIn::getStoreId, storeId)
                .eq(TutorPunchIn::getTutorId, tutorId)

                .eq(TutorPunchIn::getScheduleDay, scheduleTime));
        if (Objects.isNull(punchIn)) {
            punchIn = new TutorPunchIn();
            punchIn.setScheduleDay(scheduleTime);
            punchIn.setTutorPunchInId(IdUtils.singleNextId());
            punchIn.setTutorId(tutorId);
            punchIn.setStoreId(storeId);
            SecurityUtils.fillCreateUser(punchIn);
            baseMapper.insert(punchIn);
        }
        SecurityUtils.fillUpdateUser(punchIn);
        if (Objects.isNull(punchIn.getStartTime())) {
            punchIn.setStartTime(time);
        } else {
            punchIn.setEndTime(time);
        }
        baseMapper.updateById(punchIn);
        return Boolean.TRUE;
    }

    @Override
    public List<TutorPunchIn> queryCurrentPunchIn(Long storeId, LocalDate scheduleDay) {
        return baseMapper.selectList(baseMapper.query().eq(TutorPunchIn::getScheduleDay, scheduleDay).eq(TutorPunchIn::getStoreId, storeId));
    }
}
