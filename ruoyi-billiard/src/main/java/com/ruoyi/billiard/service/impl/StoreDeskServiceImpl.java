package com.ruoyi.billiard.service.impl;

import java.util.List;

import com.ruoyi.billiard.domain.Store;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.service.IStoreDeskService;

/**
 * 球桌Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@Service
public class StoreDeskServiceImpl implements IStoreDeskService 
{
    @Autowired
    private StoreDeskMapper storeDeskMapper;

    /**
     * 查询球桌
     * 
     * @param deskId 球桌主键
     * @return 球桌
     */
    @Override
    public StoreDesk selectStoreDeskByDeskId(Long deskId)
    {
        return storeDeskMapper.selectById(deskId);
    }

    /**
     * 查询球桌列表
     * 
     * @param storeDesk 球桌
     * @return 球桌
     */
    @Override
    public List<StoreDesk> selectStoreDeskList(StoreDesk storeDesk)
    {
        return storeDeskMapper.selectStoreDeskList(storeDesk);
    }

    /**
     * 新增球桌
     * 
     * @param storeDesk 球桌
     * @return 结果
     */
    @Override
    public int insertStoreDesk(StoreDesk storeDesk)
    {
        storeDesk.setCreateTime(DateUtils.getNowDate());
        storeDesk.setDeskId(IdUtils.singleNextId());
        return storeDeskMapper.insertStoreDesk(storeDesk);
    }

    /**
     * 修改球桌
     * 
     * @param storeDesk 球桌
     * @return 结果
     */
    @Override
    public int updateStoreDesk(StoreDesk storeDesk)
    {
        storeDesk.setUpdateTime(DateUtils.getNowDate());
        storeDesk.setStatus(null);
        return storeDeskMapper.updateStoreDesk(storeDesk);
    }

    /**
     * 批量删除球桌
     * 
     * @param deskIds 需要删除的球桌主键
     * @return 结果
     */
    @Override
    public int deleteStoreDeskByDeskIds(Long[] deskIds)
    {
        return storeDeskMapper.deleteStoreDeskByDeskIds(deskIds);
    }

    /**
     * 删除球桌信息
     * 
     * @param deskId 球桌主键
     * @return 结果
     */
    @Override
    public int deleteStoreDeskByDeskId(Long deskId)
    {
        return storeDeskMapper.deleteStoreDeskByDeskId(deskId);
    }

    @Override
    public Store getByLoginUserId(Long loginUserId) {
        return storeDeskMapper.selectStoreByLoginUserId(loginUserId);
    }
}
