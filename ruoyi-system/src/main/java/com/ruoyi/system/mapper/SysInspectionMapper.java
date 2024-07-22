package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysInspection;

/**
 * 考察情况Mapper接口
 * 
 * @author tyc
 * @date 2024-07-21
 */
public interface SysInspectionMapper 
{
    /**
     * 查询考察情况
     * 
     * @param inspectionId 考察情况主键
     * @return 考察情况
     */
    public SysInspection selectSysInspectionByInspectionId(String inspectionId);

    /**
     * 查询考察情况列表
     * 
     * @param sysInspection 考察情况
     * @return 考察情况集合
     */
    public List<SysInspection> selectSysInspectionList(SysInspection sysInspection);

    /**
     * 新增考察情况
     * 
     * @param sysInspection 考察情况
     * @return 结果
     */
    public int insertSysInspection(SysInspection sysInspection);

    /**
     * 修改考察情况
     * 
     * @param sysInspection 考察情况
     * @return 结果
     */
    public int updateSysInspection(SysInspection sysInspection);

    /**
     * 删除考察情况
     * 
     * @param inspectionId 考察情况主键
     * @return 结果
     */
    public int deleteSysInspectionByInspectionId(String inspectionId);

    /**
     * 批量删除考察情况
     * 
     * @param inspectionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysInspectionByInspectionIds(String[] inspectionIds);
}
