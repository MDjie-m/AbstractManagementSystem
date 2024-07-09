package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.SysStudent;

import java.util.List;

/**
 * test学生信息Service接口
 * 
 * @author ruoyi
 * @date 2024-07-09
 */
public interface ISysStudentService extends IService<SysStudent>
{
    /**
     * 查询test学生信息
     * 
     * @param studentId test学生信息主键
     * @return test学生信息
     */
    public SysStudent selectSysStudentByStudentId(Long studentId);

    /**
     * 查询test学生信息列表
     * 
     * @param sysStudent test学生信息
     * @return test学生信息集合
     */
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent);

    /**
     * 新增test学生信息
     * 
     * @param sysStudent test学生信息
     * @return 结果
     */
    public int insertSysStudent(SysStudent sysStudent);

    /**
     * 修改test学生信息
     * 
     * @param sysStudent test学生信息
     * @return 结果
     */
    public int updateSysStudent(SysStudent sysStudent);

    /**
     * 批量删除test学生信息
     * 
     * @param studentIds 需要删除的test学生信息主键集合
     * @return 结果
     */
    public int deleteSysStudentByStudentIds(Long[] studentIds);

    /**
     * 删除test学生信息信息
     * 
     * @param studentId test学生信息主键
     * @return 结果
     */
    public int deleteSysStudentByStudentId(Long studentId);
}
