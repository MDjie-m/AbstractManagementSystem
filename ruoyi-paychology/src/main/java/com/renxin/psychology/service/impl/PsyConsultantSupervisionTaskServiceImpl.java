package com.renxin.psychology.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantSupervisionTaskMapper;
import com.renxin.psychology.domain.PsyConsultantSupervisionTask;
import com.renxin.psychology.service.IPsyConsultantSupervisionTaskService;

/**
 * 团队督导(组织)任务Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-26
 */
@Service
public class PsyConsultantSupervisionTaskServiceImpl implements IPsyConsultantSupervisionTaskService 
{
    @Autowired
    private PsyConsultantSupervisionTaskMapper psyConsultantSupervisionTaskMapper;

    /**
     * 查询团队督导(组织)任务
     * 
     * @param taskId 团队督导(组织)任务主键
     * @return 团队督导(组织)任务
     */
    @Override
    public PsyConsultantSupervisionTask selectPsyConsultantSupervisionTaskByTaskId(Long taskId)
    {
        return psyConsultantSupervisionTaskMapper.selectPsyConsultantSupervisionTaskByTaskId(taskId);
    }

    /**
     * 查询团队督导(组织)任务列表
     * 
     * @param psyConsultantSupervisionTask 团队督导(组织)任务
     * @return 团队督导(组织)任务
     */
    @Override
    public List<PsyConsultantSupervisionTask> selectPsyConsultantSupervisionTaskList(PsyConsultantSupervisionTask psyConsultantSupervisionTask)
    {
        return psyConsultantSupervisionTaskMapper.selectPsyConsultantSupervisionTaskList(psyConsultantSupervisionTask);
    }

    /**
     * 新增团队督导(组织)任务
     * 
     * @param psyConsultantSupervisionTask 团队督导(组织)任务
     * @return 结果
     */
    @Override
    public int insertPsyConsultantSupervisionTask(PsyConsultantSupervisionTask psyConsultantSupervisionTask)
    {
        psyConsultantSupervisionTask.setCreateTime(DateUtils.getNowDate());
        return psyConsultantSupervisionTaskMapper.insertPsyConsultantSupervisionTask(psyConsultantSupervisionTask);
    }

    /**
     * 修改团队督导(组织)任务
     * 
     * @param psyConsultantSupervisionTask 团队督导(组织)任务
     * @return 结果
     */
    @Override
    public int updatePsyConsultantSupervisionTask(PsyConsultantSupervisionTask psyConsultantSupervisionTask)
    {
        psyConsultantSupervisionTask.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantSupervisionTaskMapper.updatePsyConsultantSupervisionTask(psyConsultantSupervisionTask);
    }

    /**
     * 批量删除团队督导(组织)任务
     * 
     * @param taskIds 需要删除的团队督导(组织)任务主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantSupervisionTaskByTaskIds(Long[] taskIds)
    {
        return psyConsultantSupervisionTaskMapper.deletePsyConsultantSupervisionTaskByTaskIds(taskIds);
    }

    /**
     * 删除团队督导(组织)任务信息
     * 
     * @param taskId 团队督导(组织)任务主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantSupervisionTaskByTaskId(Long taskId)
    {
        return psyConsultantSupervisionTaskMapper.deletePsyConsultantSupervisionTaskByTaskId(taskId);
    }
}
