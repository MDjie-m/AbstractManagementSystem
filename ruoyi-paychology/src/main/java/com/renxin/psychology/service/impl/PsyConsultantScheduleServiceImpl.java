package com.renxin.psychology.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyConsultantSchedule;
import com.renxin.psychology.mapper.PsyConsultantScheduleMapper;
import com.renxin.psychology.service.IPsyConsultantScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 咨询师排班任务Service业务层处理
 * 
 * @author renxin
 * @date 2024-07-25
 */
@Service
public class PsyConsultantScheduleServiceImpl implements IPsyConsultantScheduleService 
{
    @Autowired
    private PsyConsultantScheduleMapper psyConsultantScheduleMapper;

    /**
     * 查询咨询师排班任务
     * 
     * @param id 咨询师排班任务主键
     * @return 咨询师排班任务
     */
    @Override
    public PsyConsultantSchedule selectPsyConsultantScheduleById(Long id)
    {
        return psyConsultantScheduleMapper.selectPsyConsultantScheduleById(id);
    }

    /**
     * 查询咨询师排班任务列表
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 咨询师排班任务
     */
    @Override
    public List<PsyConsultantSchedule> selectPsyConsultantScheduleList(PsyConsultantSchedule psyConsultantSchedule)
    {
        return psyConsultantScheduleMapper.selectPsyConsultantScheduleList(psyConsultantSchedule);
    }

    /**
     * 新增咨询师排班任务
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 结果
     */
    @Override
    public int insertPsyConsultantSchedule(PsyConsultantSchedule psyConsultantSchedule)
    {
        psyConsultantSchedule.setCreateTime(DateUtils.getNowDate());
        return psyConsultantScheduleMapper.insertPsyConsultantSchedule(psyConsultantSchedule);
    }

    /**
     * 修改咨询师排班任务
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 结果
     */
    @Override
    public int updatePsyConsultantSchedule(PsyConsultantSchedule psyConsultantSchedule)
    {
        psyConsultantSchedule.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantScheduleMapper.updatePsyConsultantSchedule(psyConsultantSchedule);
    }

    /**
     * 批量删除咨询师排班任务
     * 
     * @param ids 需要删除的咨询师排班任务主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantScheduleByIds(Long[] ids)
    {
        return psyConsultantScheduleMapper.deletePsyConsultantScheduleByIds(ids);
    }

    /**
     * 删除咨询师排班任务信息
     * 
     * @param id 咨询师排班任务主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantScheduleById(Long id)
    {
        return psyConsultantScheduleMapper.deletePsyConsultantScheduleById(id);
    }
}
