package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.StoreUser;

/**
 * 门店员工Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-05
 */
public interface StoreUserMapper 
{
    /**
     * 查询门店员工
     * 
     * @param storeUserId 门店员工主键
     * @return 门店员工
     */
    public StoreUser selectStoreUserByStoreUserId(Long storeUserId);

    /**
     * 查询门店员工列表
     * 
     * @param storeUser 门店员工
     * @return 门店员工集合
     */
    public List<StoreUser> selectStoreUserList(StoreUser storeUser);

    /**
     * 新增门店员工
     * 
     * @param storeUser 门店员工
     * @return 结果
     */
    public int insertStoreUser(StoreUser storeUser);

    /**
     * 修改门店员工
     * 
     * @param storeUser 门店员工
     * @return 结果
     */
    public int updateStoreUser(StoreUser storeUser);

    /**
     * 删除门店员工
     * 
     * @param storeUserId 门店员工主键
     * @return 结果
     */
    public int deleteStoreUserByStoreUserId(Long storeUserId);

    /**
     * 批量删除门店员工
     * 
     * @param storeUserIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreUserByStoreUserIds(Long[] storeUserIds);
}
