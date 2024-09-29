package com.ruoyi.billiard.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.billiard.domain.StoreSchedule;
import com.ruoyi.common.core.domain.model.Tuple;
import org.springframework.stereotype.Service;

/**
 * 门店班次Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-22
 */
public interface IStoreScheduleService  extends IService<StoreSchedule>
{
    /**
     * 查询门店班次
     * 
     * @param storeScheduleId 门店班次主键
     * @return 门店班次
     */
    public StoreSchedule selectStoreScheduleByStoreScheduleId(Long storeScheduleId);

    /**
     * 查询门店班次列表
     * 
     * @param storeSchedule 门店班次
     * @return 门店班次集合
     */
    public List<StoreSchedule> selectStoreScheduleList(StoreSchedule storeSchedule);

    /**
     * 新增门店班次
     * 
     * @param storeSchedule 门店班次
     * @return 结果
     */
    public int insertStoreSchedule(StoreSchedule storeSchedule);

    /**
     * 修改门店班次
     * 
     * @param storeSchedule 门店班次
     * @return 结果
     */
    public int updateStoreSchedule(StoreSchedule storeSchedule);

    /**
     * 批量删除门店班次
     * 
     * @param storeScheduleIds 需要删除的门店班次主键集合
     * @return 结果
     */
    public int deleteStoreScheduleByStoreScheduleIds(Long[] storeScheduleIds);

    /**
     * 删除门店班次信息
     * 
     * @param storeScheduleId 门店班次主键
     * @return 结果
     */
    public int deleteStoreScheduleByStoreScheduleId(Long storeScheduleId);


    Tuple<Date,Date> getDaySchedule(Long storeId,Date date);

    Tuple<Date,Date> getDaySchedule(Long storeId,Date start,Date end);
}
