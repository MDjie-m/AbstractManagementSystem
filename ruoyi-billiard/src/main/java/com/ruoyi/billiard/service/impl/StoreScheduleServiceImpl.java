package com.ruoyi.billiard.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreScheduleMapper;
import com.ruoyi.billiard.domain.StoreSchedule;
import com.ruoyi.billiard.service.IStoreScheduleService;

/**
 * 门店班次Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-22
 */
@Service
public class StoreScheduleServiceImpl implements IStoreScheduleService 
{
    @Autowired
    private StoreScheduleMapper storeScheduleMapper;

    @Autowired
    private IStoreService storeService;

    /**
     * 查询门店班次
     * 
     * @param storeScheduleId 门店班次主键
     * @return 门店班次
     */
    @Override
    public StoreSchedule selectStoreScheduleByStoreScheduleId(Long storeScheduleId)
    {
        StoreSchedule storeSchedule = storeScheduleMapper.selectById(storeScheduleId);
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
    public List<StoreSchedule> selectStoreScheduleList(StoreSchedule storeSchedule)
    {
        List<StoreSchedule> storeSchedules = storeScheduleMapper.selectStoreScheduleList(storeSchedule);
        return storeSchedules.stream().map(item -> {
            item.setStoreName(storeService.selectStoreByStoreId(item.getStoreId()).getStoreName());
            // 获取排班时间
            Date day = item.getDay();
            // 计算具体开始时间
            Date startTime = item.getStartTime();
            Integer startTimeOffsetDay = item.getStartTimeOffsetDay();
            item.setStartTimeStr(getScheduleTimeStr(day, startTime, startTimeOffsetDay));
            // 计算具体结束时间
            Date endTime = item.getEndTime();
            Integer endTimeOffsetDay = item.getEndTimeOffsetDay();
            item.setEndTimeStr(getScheduleTimeStr(day, endTime, endTimeOffsetDay));
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
    public int insertStoreSchedule(StoreSchedule storeSchedule)
    {
        SecurityUtils.fillCreateUser(storeSchedule);
        storeSchedule.setStoreScheduleId(IdUtils.singleNextId());
        return storeScheduleMapper.insertStoreSchedule(storeSchedule);
    }

    /**
     * 修改门店班次
     * 
     * @param storeSchedule 门店班次
     * @return 结果
     */
    @Override
    public int updateStoreSchedule(StoreSchedule storeSchedule)
    {
        SecurityUtils.fillUpdateUser(storeSchedule);

        return storeScheduleMapper.updateStoreSchedule(storeSchedule);
    }

    /**
     * 批量删除门店班次
     * 
     * @param storeScheduleIds 需要删除的门店班次主键
     * @return 结果
     */
    @Override
    public int deleteStoreScheduleByStoreScheduleIds(Long[] storeScheduleIds)
    {
        return storeScheduleMapper.deleteStoreScheduleByStoreScheduleIds(storeScheduleIds);
    }

    /**
     * 删除门店班次信息
     * 
     * @param storeScheduleId 门店班次主键
     * @return 结果
     */
    @Override
    public int deleteStoreScheduleByStoreScheduleId(Long storeScheduleId)
    {
        return storeScheduleMapper.deleteStoreScheduleByStoreScheduleId(storeScheduleId);
    }

    /**
     * 计算排班开始时间或者结束时间返回String类型
     */
    public String getScheduleTimeStr(Date day, Date time, Integer offsetDay) {
        String toStrTime = DateUtils.parseDateToStr("HH:mm", time);
        Date newDay = DateUtils.addDays(day, offsetDay);
        String toStrDay = DateUtils.parseDateToStr("yyy-MM-dd", newDay);
        return toStrDay + " " + toStrTime;
    }

}
