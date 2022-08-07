package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WorkProcess;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2022-08-05
 */
public interface WorkProcessMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param processId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public WorkProcess selectWorkProcessByProcessId(Long processId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param workProcess 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<WorkProcess> selectWorkProcessList(WorkProcess workProcess);

    /**
     * 新增【请填写功能名称】
     * 
     * @param workProcess 【请填写功能名称】
     * @return 结果
     */
    public int insertWorkProcess(WorkProcess workProcess);

    /**
     * 修改【请填写功能名称】
     * 
     * @param workProcess 【请填写功能名称】
     * @return 结果
     */
    public int updateWorkProcess(WorkProcess workProcess);

    /**
     * 删除【请填写功能名称】
     * 
     * @param processId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteWorkProcessByProcessId(Long processId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param processIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkProcessByProcessIds(Long[] processIds);
}
