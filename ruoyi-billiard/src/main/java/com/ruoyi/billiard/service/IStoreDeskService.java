package com.ruoyi.billiard.service;

import java.util.List;

import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.domain.StoreDesk;

/**
 * 球桌Service接口
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
public interface IStoreDeskService 
{
    /**
     * 查询球桌
     * 
     * @param deskId 球桌主键
     * @return 球桌
     */
    public StoreDesk selectStoreDeskByDeskId(Long deskId);

    /**
     * 查询球桌列表
     * 
     * @param storeDesk 球桌
     * @return 球桌集合
     */
    public List<StoreDesk> selectStoreDeskList(StoreDesk storeDesk);

    /**
     * 新增球桌
     * 
     * @param storeDesk 球桌
     * @return 结果
     */
    public int insertStoreDesk(StoreDesk storeDesk);

    /**
     * 修改球桌
     * 
     * @param storeDesk 球桌
     * @return 结果
     */
    public int updateStoreDesk(StoreDesk storeDesk);

    /**
     * 批量删除球桌
     * 
     * @param deskIds 需要删除的球桌主键集合
     * @return 结果
     */
    public int deleteStoreDeskByDeskIds(Long[] deskIds);

    /**
     * 删除球桌信息
     * 
     * @param deskId 球桌主键
     * @return 结果
     */
    public int deleteStoreDeskByDeskId(Long deskId);

    Store getByLoginUserId(Long loginUserId);
}
