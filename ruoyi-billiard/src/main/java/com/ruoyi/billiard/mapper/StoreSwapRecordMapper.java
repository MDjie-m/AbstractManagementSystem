package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.StoreSwapRecord;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 交班记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
@Mapper
public interface StoreSwapRecordMapper extends MyBaseMapper<StoreSwapRecord>
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
     * 删除交班记录
     * 
     * @param swapRecordId 交班记录主键
     * @return 结果
     */
    public int deleteStoreSwapRecordBySwapRecordId(Long swapRecordId);

    /**
     * 批量删除交班记录
     * 
     * @param swapRecordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreSwapRecordBySwapRecordIds(Long[] swapRecordIds);
}
