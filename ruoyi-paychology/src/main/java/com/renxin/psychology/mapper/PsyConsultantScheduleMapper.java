package com.renxin.psychology.mapper;

import com.renxin.psychology.domain.PsyConsultantSchedule;
import com.renxin.psychology.request.PsyWorkReq;

import java.util.List;

/**
 * 咨询师排班任务Mapper接口
 * 
 * @author renxin
 * @date 2024-07-25
 */
public interface PsyConsultantScheduleMapper 
{
    /**
     * 查询咨询师排班任务
     * 
     * @param id 咨询师排班任务主键
     * @return 咨询师排班任务
     */
    public PsyConsultantSchedule selectPsyConsultantScheduleById(Long id);

    /**
     * 查询咨询师排班任务列表
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 咨询师排班任务集合
     */
    public List<PsyConsultantSchedule> selectPsyConsultantScheduleList(PsyConsultantSchedule psyConsultantSchedule);

    /**
     * 新增咨询师排班任务
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 结果
     */
    public int insertPsyConsultantSchedule(PsyConsultantSchedule psyConsultantSchedule);
    
    public int insertPsyConsultantScheduleBatch(List<PsyConsultantSchedule> list);
    

    /**
     * 修改咨询师排班任务
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 结果
     */
    public int updatePsyConsultantSchedule(PsyConsultantSchedule psyConsultantSchedule);

    /**
     * 删除咨询师排班任务
     * 
     * @param id 咨询师排班任务主键
     * @return 结果
     */
    public int deletePsyConsultantScheduleById(Long id);

    /**
     * 批量删除咨询师排班任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultantScheduleByIds(Long[] ids);

    /**
     * 查询本次任务, 是[收费咨询师-付费咨询师]之间的第几次
     * @param req
     * @return
     */
    public int getTimeNumForConsultant(PsyWorkReq req);


    /**
     * 查询咨询师工作时长
     */
    public int querySumTime(PsyConsultantSchedule psyConsultantSchedule);
}
