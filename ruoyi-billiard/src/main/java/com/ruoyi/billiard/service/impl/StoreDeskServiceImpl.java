package com.ruoyi.billiard.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.billiard.domain.DeskDeviceRelation;
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.service.IDeskDeviceRelationService;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.service.IStoreDeskService;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IDeskDeviceRelationService deskDeviceRelationService;

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
    @Transactional(rollbackFor = Exception.class)
    public int insertStoreDesk(StoreDesk storeDesk)
    {
        storeDesk.setCreateTime(DateUtils.getNowDate());
        storeDesk.setDeskId(IdUtils.singleNextId());
        storeDesk.setCreateBy(SecurityUtils.getUsername());
        storeDesk.setUpdateBy(storeDesk.getCreateBy());
        checkDevice( storeDesk.getCameraDeviceId(),null,"摄像头已绑定到其他桌");
        checkDevice( storeDesk.getLightDeviceId(),null,"摄像头已绑定到其他桌");
        deskDeviceRelationService.bindDevice(storeDesk.getDeskId(),
                Arrays.asList(storeDesk.getCameraDeviceId(), storeDesk.getLightDeviceId()));


        return storeDeskMapper.insertStoreDesk(storeDesk);
    }
    private  void checkDevice( Long deviceId,Long deskId,String msg){
        if(Objects.isNull(deviceId)){
            return;
        }
        AssertUtil.isTrue(Objects.isNull( storeDeskMapper.checkDeviceBind( deviceId,deskId)),msg);
    }

    /**
     * 修改球桌
     * 
     * @param storeDesk 球桌
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStoreDesk(StoreDesk storeDesk)
    {
        deskDeviceRelationService.bindDevice(storeDesk.getDeskId(),
                Arrays.asList(storeDesk.getCameraDeviceId(), storeDesk.getLightDeviceId()));
        storeDeskMapper.updateStoreDesk(storeDesk);
        checkDevice( storeDesk.getCameraDeviceId(),storeDesk.getDeskId(),"摄像头已绑定到其他桌");
        checkDevice( storeDesk.getLightDeviceId(),storeDesk.getDeskId(),"摄像头已绑定到其他桌");
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
    public   List<Store> getByLoginUserId(Long loginUserId) {
        return storeDeskMapper.selectStoreByLoginUserId(loginUserId);
    }
}
