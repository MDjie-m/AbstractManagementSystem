package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.common.core.mapper.MyBaseMapper;

/**
 * 门店Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-05
 */
public interface StoreMapper extends MyBaseMapper<Store>
{
    /**
     * 查询门店
     * 
     * @param storeId 门店主键
     * @return 门店
     */
    public Store selectStoreByStoreId(Long storeId);

    /**
     * 查询门店列表
     * 
     * @param store 门店
     * @return 门店集合
     */
    public List<Store> selectStoreList(Store store);

    /**
     * 新增门店
     * 
     * @param store 门店
     * @return 结果
     */
    public int insertStore(Store store);

    /**
     * 修改门店
     * 
     * @param store 门店
     * @return 结果
     */
    public int updateStore(Store store);

    /**
     * 删除门店
     * 
     * @param storeId 门店主键
     * @return 结果
     */
    public int deleteStoreByStoreId(Long storeId);

    /**
     * 批量删除门店
     * 
     * @param storeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreByStoreIds(Long[] storeIds);
}
