package com.ruoyi.demo.service;

import java.util.List;
import com.ruoyi.demo.domain.JobChangeApplication;

/**
 * 调岗申请Service接口
 *
 * @author KK
 * @date 2024-09-13
 */
public interface IJobChangeApplicationService
{
    /**
     * 查询调岗申请
     *
     * @param id 调岗申请主键
     * @return 调岗申请
     */
    public JobChangeApplication selectJobChangeApplicationById(Long id);

    /**
     * 查询调岗申请列表
     *
     * @param jobChangeApplication 调岗申请
     * @return 调岗申请集合
     */
    public List<JobChangeApplication> selectJobChangeApplicationList(JobChangeApplication jobChangeApplication);

    /**
     * 新增调岗申请
     *
     * @param jobChangeApplication 调岗申请
     * @return 结果
     */
    public int insertJobChangeApplication(JobChangeApplication jobChangeApplication);

    /**
     * 修改调岗申请
     *
     * @param jobChangeApplication 调岗申请
     * @return 结果
     */
    public int updateJobChangeApplication(JobChangeApplication jobChangeApplication);

    /**
     * 批量删除调岗申请
     *
     * @param ids 需要删除的调岗申请主键集合
     * @return 结果
     */
    public int deleteJobChangeApplicationByIds(Long[] ids);

    /**
     * 删除调岗申请信息
     *
     * @param id 调岗申请主键
     * @return 结果
     */
    public int deleteJobChangeApplicationById(Long id);
}
