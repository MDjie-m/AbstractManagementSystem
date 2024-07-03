package com.renxin.psychology.mapper;

import java.util.List;
import com.renxin.psychology.domain.PsyConsultantSupervisionTask;

/**
 * 团队督导(组织)任务Mapper接口
 * 
 * @author renxin
 * @date 2024-06-26
 */
public interface PsyConsultantSupervisionTaskMapper 
{
    /**
     * 查询团队督导(组织)任务
     * 
     * @param taskId 团队督导(组织)任务主键
     * @return 团队督导(组织)任务
     */
    public PsyConsultantSupervisionTask selectPsyConsultantSupervisionTaskByTaskId(Long taskId);

    /**
     * 查询团队督导(组织)任务列表
     * 
     * @param psyConsultantSupervisionTask 团队督导(组织)任务
     * @return 团队督导(组织)任务集合
     */
    public List<PsyConsultantSupervisionTask> selectPsyConsultantSupervisionTaskList(PsyConsultantSupervisionTask psyConsultantSupervisionTask);

    /**
     * 新增团队督导(组织)任务
     * 
     * @param psyConsultantSupervisionTask 团队督导(组织)任务
     * @return 结果
     */
    public int insertPsyConsultantSupervisionTask(PsyConsultantSupervisionTask psyConsultantSupervisionTask);

    /**
     * 修改团队督导(组织)任务
     * 
     * @param psyConsultantSupervisionTask 团队督导(组织)任务
     * @return 结果
     */
    public int updatePsyConsultantSupervisionTask(PsyConsultantSupervisionTask psyConsultantSupervisionTask);

    /**
     * 删除团队督导(组织)任务
     * 
     * @param taskId 团队督导(组织)任务主键
     * @return 结果
     */
    public int deletePsyConsultantSupervisionTaskByTaskId(Long taskId);

    /**
     * 批量删除团队督导(组织)任务
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultantSupervisionTaskByTaskIds(Long[] taskIds);
}
