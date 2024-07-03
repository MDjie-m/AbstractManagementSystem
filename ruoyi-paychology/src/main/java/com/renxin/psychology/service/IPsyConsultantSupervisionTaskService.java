package com.renxin.psychology.service;

import java.util.List;
import com.renxin.psychology.domain.PsyConsultantSupervisionTask;

/**
 * 团队督导(组织)任务Service接口
 * 
 * @author renxin
 * @date 2024-06-26
 */
public interface IPsyConsultantSupervisionTaskService 
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
     * 批量删除团队督导(组织)任务
     * 
     * @param taskIds 需要删除的团队督导(组织)任务主键集合
     * @return 结果
     */
    public int deletePsyConsultantSupervisionTaskByTaskIds(Long[] taskIds);

    /**
     * 删除团队督导(组织)任务信息
     * 
     * @param taskId 团队督导(组织)任务主键
     * @return 结果
     */
    public int deletePsyConsultantSupervisionTaskByTaskId(Long taskId);
}
