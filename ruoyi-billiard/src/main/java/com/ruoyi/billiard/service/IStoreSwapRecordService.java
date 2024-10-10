package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.StoreSwapRecord;

/**
 * 交班记录Service接口
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
public interface IStoreSwapRecordService  extends IService<StoreSwapRecord>
{
    /**
     * 查询交班记录
     * 
     * @param swapRecordId 交班记录主键
     * @return 交班记录
     */
    public StoreSwapRecord selectStoreSwapRecordBySwapRecordId(Long swapRecordId);

    /**
     * 查询交班记录列表
     * 
     * @param storeSwapRecord 交班记录
     * @return 交班记录集合
     */
    public List<StoreSwapRecord> selectStoreSwapRecordList(StoreSwapRecord storeSwapRecord);

    /**
     * 新增交班记录
     * 
     * @param storeSwapRecord 交班记录
     * @return 结果
     */
    public int insertStoreSwapRecord(StoreSwapRecord storeSwapRecord);

    /**
     * 修改交班记录
     * 
     * @param storeSwapRecord 交班记录
     * @return 结果
     */
    public int updateStoreSwapRecord(StoreSwapRecord storeSwapRecord);

    /**
     * 批量删除交班记录
     * 
     * @param swapRecordIds 需要删除的交班记录主键集合
     * @return 结果
     */
    public int deleteStoreSwapRecordBySwapRecordIds(Long[] swapRecordIds);

    /**
     * 删除交班记录信息
     * 
     * @param swapRecordId 交班记录主键
     * @return 结果
     */
    public int deleteStoreSwapRecordBySwapRecordId(Long swapRecordId);
}
