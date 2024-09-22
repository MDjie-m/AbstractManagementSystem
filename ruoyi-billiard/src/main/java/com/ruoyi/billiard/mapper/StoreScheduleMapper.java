package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.StoreSchedule;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 门店班次Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-22
 */
@Mapper
public interface StoreScheduleMapper extends MyBaseMapper<StoreSchedule>
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
     * 删除门店班次
     * 
     * @param storeScheduleId 门店班次主键
     * @return 结果
     */
    public int deleteStoreScheduleByStoreScheduleId(Long storeScheduleId);

    /**
     * 批量删除门店班次
     * 
     * @param storeScheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreScheduleByStoreScheduleIds(Long[] storeScheduleIds);
}
