package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.DeskDeviceRelation;

/**
 * 桌子设备关联关系Service接口
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
public interface IDeskDeviceRelationService 
{
    /**
     * 查询桌子设备关联关系
     * 
     * @param deskDeviceRelationId 桌子设备关联关系主键
     * @return 桌子设备关联关系
     */
    public DeskDeviceRelation selectDeskDeviceRelationByDeskDeviceRelationId(Long deskDeviceRelationId);

    /**
     * 查询桌子设备关联关系列表
     * 
     * @param deskDeviceRelation 桌子设备关联关系
     * @return 桌子设备关联关系集合
     */
    public List<DeskDeviceRelation> selectDeskDeviceRelationList(DeskDeviceRelation deskDeviceRelation);

    /**
     * 新增桌子设备关联关系
     * 
     * @param deskDeviceRelation 桌子设备关联关系
     * @return 结果
     */
    public int insertDeskDeviceRelation(DeskDeviceRelation deskDeviceRelation);

    /**
     * 修改桌子设备关联关系
     * 
     * @param deskDeviceRelation 桌子设备关联关系
     * @return 结果
     */
    public int updateDeskDeviceRelation(DeskDeviceRelation deskDeviceRelation);

    /**
     * 批量删除桌子设备关联关系
     * 
     * @param deskDeviceRelationIds 需要删除的桌子设备关联关系主键集合
     * @return 结果
     */
    public int deleteDeskDeviceRelationByDeskDeviceRelationIds(Long[] deskDeviceRelationIds);

    /**
     * 删除桌子设备关联关系信息
     * 
     * @param deskDeviceRelationId 桌子设备关联关系主键
     * @return 结果
     */
    public int deleteDeskDeviceRelationByDeskDeviceRelationId(Long deskDeviceRelationId);

      void bindDevice(Long deskId, List<Long> deviceIds);
}
