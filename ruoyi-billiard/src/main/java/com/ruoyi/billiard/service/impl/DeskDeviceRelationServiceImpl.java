package com.ruoyi.billiard.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.DeskDeviceRelationMapper;
import com.ruoyi.billiard.domain.DeskDeviceRelation;
import com.ruoyi.billiard.service.IDeskDeviceRelationService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 桌子设备关联关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@Service
public class DeskDeviceRelationServiceImpl implements IDeskDeviceRelationService 
{
    @Autowired
    private DeskDeviceRelationMapper deskDeviceRelationMapper;

    /**
     * 查询桌子设备关联关系
     * 
     * @param deskDeviceRelationId 桌子设备关联关系主键
     * @return 桌子设备关联关系
     */
    @Override
    public DeskDeviceRelation selectDeskDeviceRelationByDeskDeviceRelationId(Long deskDeviceRelationId)
    {
        return deskDeviceRelationMapper.selectById(deskDeviceRelationId);
    }

    /**
     * 查询桌子设备关联关系列表
     * 
     * @param deskDeviceRelation 桌子设备关联关系
     * @return 桌子设备关联关系
     */
    @Override
    public List<DeskDeviceRelation> selectDeskDeviceRelationList(DeskDeviceRelation deskDeviceRelation)
    {
        return deskDeviceRelationMapper.selectDeskDeviceRelationList(deskDeviceRelation);
    }

    /**
     * 新增桌子设备关联关系
     * 
     * @param deskDeviceRelation 桌子设备关联关系
     * @return 结果
     */
    @Override
    public int insertDeskDeviceRelation(DeskDeviceRelation deskDeviceRelation)
    {

        SecurityUtils.fillCreateUser(deskDeviceRelation);
        return deskDeviceRelationMapper.insertDeskDeviceRelation(deskDeviceRelation);
    }

    /**
     * 修改桌子设备关联关系
     * 
     * @param deskDeviceRelation 桌子设备关联关系
     * @return 结果
     */
    @Override
    public int updateDeskDeviceRelation(DeskDeviceRelation deskDeviceRelation)
    {
        SecurityUtils.fillUpdateUser(deskDeviceRelation);
        return deskDeviceRelationMapper.updateDeskDeviceRelation(deskDeviceRelation);
    }

    /**
     * 批量删除桌子设备关联关系
     * 
     * @param deskDeviceRelationIds 需要删除的桌子设备关联关系主键
     * @return 结果
     */
    @Override
    public int deleteDeskDeviceRelationByDeskDeviceRelationIds(Long[] deskDeviceRelationIds)
    {
        return deskDeviceRelationMapper.deleteDeskDeviceRelationByDeskDeviceRelationIds(deskDeviceRelationIds);
    }

    /**
     * 删除桌子设备关联关系信息
     * 
     * @param deskDeviceRelationId 桌子设备关联关系主键
     * @return 结果
     */
    @Override
    public int deleteDeskDeviceRelationByDeskDeviceRelationId(Long deskDeviceRelationId)
    {
        return deskDeviceRelationMapper.deleteDeskDeviceRelationByDeskDeviceRelationId(deskDeviceRelationId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindDevice(Long deskId, List<Long> deviceIds) {
        deviceIds=deviceIds.stream().filter(Objects::nonNull).collect(Collectors.toList());
        String userName=SecurityUtils.getUsername();
        deskDeviceRelationMapper.delByDeskId(deskId);
        if(CollectionUtils.isNotEmpty(deviceIds)){
            deskDeviceRelationMapper.insertBatch(deviceIds.stream().map(p-> {
                DeskDeviceRelation relation=      DeskDeviceRelation.builder().deskId(deskId).deviceId(p).build();
                SecurityUtils.fillUpdateUser(relation);
                return  relation;
            }).collect(Collectors.toList()));

        }

    }
}
