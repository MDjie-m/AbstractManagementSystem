package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysInspectionMapper;
import com.ruoyi.system.domain.SysInspection;
import com.ruoyi.system.service.ISysInspectionService;

/**
 * 考察情况Service业务层处理
 * 
 * @author tyc
 * @date 2024-07-21
 */
@Service
public class SysInspectionServiceImpl implements ISysInspectionService 
{
    @Autowired
    private SysInspectionMapper sysInspectionMapper;

    /**
     * 查询考察情况
     * 
     * @param inspectionId 考察情况主键
     * @return 考察情况
     */
    @Override
    public SysInspection selectSysInspectionByInspectionId(String inspectionId)
    {
        return sysInspectionMapper.selectSysInspectionByInspectionId(inspectionId);
    }

    /**
     * 查询考察情况列表
     * 
     * @param sysInspection 考察情况
     * @return 考察情况
     */
    @Override
    public List<SysInspection> selectSysInspectionList(SysInspection sysInspection)
    {
        return sysInspectionMapper.selectSysInspectionList(sysInspection);
    }

    /**
     * 新增考察情况
     * 
     * @param sysInspection 考察情况
     * @return 结果
     */
    @Override
    public int insertSysInspection(SysInspection sysInspection)
    {
        sysInspection.setInspectionId(UUID.randomUUID().toString());
        return sysInspectionMapper.insertSysInspection(sysInspection);
    }

    /**
     * 修改考察情况
     * 
     * @param sysInspection 考察情况
     * @return 结果
     */
    @Override
    public int updateSysInspection(SysInspection sysInspection)
    {
        return sysInspectionMapper.updateSysInspection(sysInspection);
    }

    /**
     * 批量删除考察情况
     * 
     * @param inspectionIds 需要删除的考察情况主键
     * @return 结果
     */
    @Override
    public int deleteSysInspectionByInspectionIds(String[] inspectionIds)
    {
        return sysInspectionMapper.deleteSysInspectionByInspectionIds(inspectionIds);
    }

    /**
     * 删除考察情况信息
     * 
     * @param inspectionId 考察情况主键
     * @return 结果
     */
    @Override
    public int deleteSysInspectionByInspectionId(String inspectionId)
    {
        return sysInspectionMapper.deleteSysInspectionByInspectionId(inspectionId);
    }
}
