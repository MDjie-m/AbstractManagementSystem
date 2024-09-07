package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.DeskDeviceRelation;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 桌子设备关联关系Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@Mapper
public interface DeskDeviceRelationMapper extends MyBaseMapper<DeskDeviceRelation>
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
     * 删除桌子设备关联关系
     * 
     * @param deskDeviceRelationId 桌子设备关联关系主键
     * @return 结果
     */
    public int deleteDeskDeviceRelationByDeskDeviceRelationId(Long deskDeviceRelationId);

    /**
     * 批量删除桌子设备关联关系
     * 
     * @param deskDeviceRelationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeskDeviceRelationByDeskDeviceRelationIds(Long[] deskDeviceRelationIds);

    void delByDeskId(@Param("deskId") Long deskId);
}
