package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.SysStudent;
import com.ruoyi.system.mapper.SysStudentMapper;
import com.ruoyi.system.service.ISysStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * test学生信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-09
 */
@Service
public class SysStudentServiceImpl extends ServiceImpl<SysStudentMapper, SysStudent> implements ISysStudentService
{
    @Autowired
    private SysStudentMapper sysStudentMapper;

    /**
     * 查询test学生信息
     * 
     * @param studentId test学生信息主键
     * @return test学生信息
     */
    @Override
    public SysStudent selectSysStudentByStudentId(Long studentId)
    {
        return sysStudentMapper.selectSysStudentByStudentId(studentId);
    }

    /**
     * 查询test学生信息列表
     * 
     * @param sysStudent test学生信息
     * @return test学生信息
     */
    @Override
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent)
    {
        return sysStudentMapper.selectSysStudentList(sysStudent);
    }

    /**
     * 新增test学生信息
     * 
     * @param sysStudent test学生信息
     * @return 结果
     */
    @Override
    public int insertSysStudent(SysStudent sysStudent)
    {
        return sysStudentMapper.insertSysStudent(sysStudent);
    }

    /**
     * 修改test学生信息
     * 
     * @param sysStudent test学生信息
     * @return 结果
     */
    @Override
    public int updateSysStudent(SysStudent sysStudent)
    {
        return sysStudentMapper.updateSysStudent(sysStudent);
    }

    /**
     * 批量删除test学生信息
     * 
     * @param studentIds 需要删除的test学生信息主键
     * @return 结果
     */
    @Override
    public int deleteSysStudentByStudentIds(Long[] studentIds)
    {
        return sysStudentMapper.deleteSysStudentByStudentIds(studentIds);
    }

    /**
     * 删除test学生信息信息
     * 
     * @param studentId test学生信息主键
     * @return 结果
     */
    @Override
    public int deleteSysStudentByStudentId(Long studentId)
    {
        return sysStudentMapper.deleteSysStudentByStudentId(studentId);
    }
}
