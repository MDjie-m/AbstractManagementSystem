package com.ruoyi.billiard.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.StoreScheduleMapper;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.core.domain.model.Tuple;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.StoreSchedule;
import com.ruoyi.billiard.service.IStoreScheduleService;

/**
 * 门店班次Service业务层处理
 *
 * @author zhoukeu
 * @date 2024-09-22
 */
@Service
public class StoreScheduleServiceImpl extends ServiceImpl<StoreScheduleMapper, StoreSchedule> implements IStoreScheduleService {


    @Autowired
    private IStoreService storeService;

    /**
     * 查询门店班次
     *
     * @param storeScheduleId 门店班次主键
     * @return 门店班次
     */
    @Override
    public StoreSchedule selectStoreScheduleByStoreScheduleId(Long storeScheduleId) {
        StoreSchedule storeSchedule = baseMapper.selectById(storeScheduleId);
        storeSchedule.setStoreName(storeService.selectStoreByStoreId(storeSchedule.getStoreId()).getStoreName());
        return storeSchedule;
    }

    /**
     * 查询门店班次列表
     *
     * @param storeSchedule 门店班次
     * @return 门店班次
     */
    @Override
    public List<StoreSchedule> selectStoreScheduleList(StoreSchedule storeSchedule) {
        List<StoreSchedule> storeSchedules = baseMapper.selectStoreScheduleList(storeSchedule);
        return storeSchedules.stream().map(item -> {
            item.setStoreName(storeService.selectStoreByStoreId(item.getStoreId()).getStoreName());

            // 计算具体开始时间
            Date startTime = item.getStartTime();
            Integer startTimeOffsetDay = item.getStartTimeOffsetDay();

            Date endTime = item.getEndTime();
            Integer endTimeOffsetDay = item.getEndTimeOffsetDay();

            if (Objects.equals(Boolean.TRUE, item.getDefaultSchedule())) {
                item.setStartTimeStr(getOffsetDayStr(startTimeOffsetDay) + " " + DateUtils.parseDateToStr(DateUtils.HH_MM, startTime));
                item.setEndTimeStr(getOffsetDayStr(endTimeOffsetDay) + " " + DateUtils.parseDateToStr(DateUtils.HH_MM, endTime));
            } else {
                // 获取排班时间
                Date day = item.getDay();
                item.setStartTimeStr(getScheduleTimeStr(day, startTime, startTimeOffsetDay));
                // 计算具体结束时间
                item.setEndTimeStr(getScheduleTimeStr(day, endTime, endTimeOffsetDay));
            }
            return item;
        }).collect(Collectors.toList());
    }

    /**
     * 新增门店班次
     *
     * @param storeSchedule 门店班次
     * @return 结果
     */
    @Override
    public int insertStoreSchedule(StoreSchedule storeSchedule) {
        SecurityUtils.fillCreateUser(storeSchedule);
        storeSchedule.setStoreScheduleId(IdUtils.singleNextId());
        if (storeSchedule.getDefaultSchedule()) {
            storeSchedule.setDay(new Date(0));
        }
        return baseMapper.insert(storeSchedule);
    }

    /**
     * 修改门店班次
     *
     * @param storeSchedule 门店班次
     * @return 结果
     */
    @Override
    public int updateStoreSchedule(StoreSchedule storeSchedule) {
        SecurityUtils.fillUpdateUser(storeSchedule);
        if (storeSchedule.getDefaultSchedule()) {
            storeSchedule.setDay(new Date(0));
        }
        return baseMapper.updateById(storeSchedule);
    }

    /**
     * 批量删除门店班次
     *
     * @param storeScheduleIds 需要删除的门店班次主键
     * @return 结果
     */
    @Override
    public int deleteStoreScheduleByStoreScheduleIds(Long[] storeScheduleIds) {
        return baseMapper.deleteStoreScheduleByStoreScheduleIds(storeScheduleIds);
    }

    /**
     * 删除门店班次信息
     *
     * @param storeScheduleId 门店班次主键
     * @return 结果
     */
    @Override
    public int deleteStoreScheduleByStoreScheduleId(Long storeScheduleId) {
        return baseMapper.deleteStoreScheduleByStoreScheduleId(storeScheduleId);
    }

    @Override
    public Tuple<Date, Date> getDaySchedule(Long storeId, Date date) {
        QueryWrapper<StoreSchedule> queryWrapper = baseMapper.normalQuery();

        queryWrapper.eq("day", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, date))

                .lambda().eq(StoreSchedule::getStoreId, storeId).last(" limit 1");

        StoreSchedule schedule = baseMapper.selectOne(queryWrapper);
        Date start, end;
        if (Objects.nonNull(schedule)) {
            schedule.setStartTimeStr(getScheduleTimeStr(schedule.getDay(), schedule.getStartTime(), schedule.getStartTimeOffsetDay()));
            schedule.setEndTimeStr(getScheduleTimeStr(schedule.getDay(), schedule.getEndTime(), schedule.getEndTimeOffsetDay()));
            start = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM, schedule.getStartTimeStr());
            end = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM, schedule.getEndTimeStr());
            return Tuple.<Date, Date>builder().value(start).value1(end).build();
        }

        schedule = baseMapper.selectOne(baseMapper.query().eq(StoreSchedule::getStoreId, storeId)
                .eq(StoreSchedule::getDefaultSchedule, Boolean.TRUE));

        if (Objects.nonNull(schedule)) {
            schedule.setStartTimeStr(getScheduleTimeStr(date, schedule.getStartTime(), schedule.getStartTimeOffsetDay()));
            schedule.setEndTimeStr(getScheduleTimeStr(date, schedule.getEndTime(), schedule.getEndTimeOffsetDay()));
            start = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM, schedule.getStartTimeStr());
            end = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM, schedule.getEndTimeStr());
            return Tuple.<Date, Date>builder().value(start).value1(end).build();
        }
        start = DateUtils.toDate(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        end = DateUtils.toDate(LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        return Tuple.<Date, Date>builder().value(start).value1(end).build();
    }

    @Override
    public Tuple<Date, Date> getDaySchedule(Long storeId, Date start, Date end) {
        return Tuple.<Date, Date>builder()
                .value(getDaySchedule(storeId, start).getValue())
                .value1(getDaySchedule(storeId, end).getValue1())
                .build();
    }

    /**
     * 计算排班开始时间或者结束时间返回String类型
     */
    public String getScheduleTimeStr(Date day, Date time, Integer offsetDay) {
        String toStrTime = DateUtils.parseDateToStr(DateUtils.HH_MM, time);
        Date newDay = DateUtils.addDays(day, offsetDay);
        String toStrDay = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, newDay);
        return toStrDay + " " + toStrTime;
    }

    /**
     * 计算排班哪一天中文名
     */
    public String getOffsetDayStr(Integer offsetDay) {
        String dayStr = "";
        switch (offsetDay) {
            case -1:
                dayStr = "前一天";
                break;
            case 0:
                dayStr = "当天";
                break;
            case 1:
                dayStr = "后一天";
                break;
        }
        return dayStr;

    }

}
