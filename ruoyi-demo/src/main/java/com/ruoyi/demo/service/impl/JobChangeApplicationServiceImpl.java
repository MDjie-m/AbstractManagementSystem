package com.ruoyi.demo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demo.mapper.JobChangeApplicationMapper;
import com.ruoyi.demo.domain.JobChangeApplication;
import com.ruoyi.demo.service.IJobChangeApplicationService;

/**
 * 调岗申请Service业务层处理
 * 
 * @author KK
 * @date 2024-09-13
 */
@Service
public class JobChangeApplicationServiceImpl implements IJobChangeApplicationService 
{
    @Autowired
    private JobChangeApplicationMapper jobChangeApplicationMapper;

    /**
     * 查询调岗申请
     * 
     * @param id 调岗申请主键
     * @return 调岗申请
     */
    @Override
    public JobChangeApplication selectJobChangeApplicationById(Long id)
    {
        return jobChangeApplicationMapper.selectJobChangeApplicationById(id);
    }

    /**
     * 查询调岗申请列表
     * 
     * @param jobChangeApplication 调岗申请
     * @return 调岗申请
     */
    @Override
    public List<JobChangeApplication> selectJobChangeApplicationList(JobChangeApplication jobChangeApplication)
    {
        return jobChangeApplicationMapper.selectJobChangeApplicationList(jobChangeApplication);
    }

    /**
     * 新增调岗申请
     * 
     * @param jobChangeApplication 调岗申请
     * @return 结果
     */
    @Override
    public int insertJobChangeApplication(JobChangeApplication jobChangeApplication)
    {
        jobChangeApplication.setCreateTime(DateUtils.getNowDate());
        return jobChangeApplicationMapper.insertJobChangeApplication(jobChangeApplication);
    }

    /**
     * 修改调岗申请
     * 
     * @param jobChangeApplication 调岗申请
     * @return 结果
     */
    @Override
    public int updateJobChangeApplication(JobChangeApplication jobChangeApplication)
    {
        jobChangeApplication.setUpdateTime(DateUtils.getNowDate());
        return jobChangeApplicationMapper.updateJobChangeApplication(jobChangeApplication);
    }

    /**
     * 批量删除调岗申请
     * 
     * @param ids 需要删除的调岗申请主键
     * @return 结果
     */
    @Override
    public int deleteJobChangeApplicationByIds(Long[] ids)
    {
        return jobChangeApplicationMapper.deleteJobChangeApplicationByIds(ids);
    }

    /**
     * 删除调岗申请信息
     * 
     * @param id 调岗申请主键
     * @return 结果
     */
    @Override
    public int deleteJobChangeApplicationById(Long id)
    {
        return jobChangeApplicationMapper.deleteJobChangeApplicationById(id);
    }
}
