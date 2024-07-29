package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.mapper.SysSupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysInspectionMapper;
import com.ruoyi.system.domain.SysInspection;
import com.ruoyi.system.service.ISysInspectionService;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private SysSupplierMapper sysSupplierMapper;

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
     * @param rate 评级
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysInspection(SysInspection sysInspection,String rate)
    {
        //获取uuid
        String inspectionId = UUID.randomUUID().toString();
        //给考察对象的id赋值
        sysInspection.setInspectionId(inspectionId);
        //把此公司之前的考察记录设置为已删除状态
        sysInspectionMapper.deleteHistory(sysInspection.getSupplierId());
        //先调用考察表的添加方法把这个考察信息存到考察表里面
        sysInspection.setFutureField1(null);
        //不知道什么原因下面这个字段为0,并不为null，因此这里添加的时候设置一下，后续根据情况更改
        sysInspection.setFutureField3(null);
        sysInspectionMapper.insertSysInspection(sysInspection);
        //再调用供应商mapper里面的更新考察评级的方法把上面新增的供应商的考察信息的id和评级存到供应商表中以供关联
        return sysSupplierMapper.updateInspectRate(sysInspection.getSupplierId(),inspectionId,rate);
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
