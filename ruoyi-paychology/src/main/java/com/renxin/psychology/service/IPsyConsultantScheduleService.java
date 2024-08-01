package com.renxin.psychology.service;

import com.renxin.psychology.domain.PsyConsultantSchedule;
import com.renxin.psychology.request.PsyWorkReq;

import java.util.List;

/**
 * 咨询师排班任务Service接口
 * 
 * @author renxin
 * @date 2024-07-25
 */
public interface IPsyConsultantScheduleService 
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

    /**
     * 修改咨询师排班任务
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 结果
     */
    public int updatePsyConsultantSchedule(PsyConsultantSchedule psyConsultantSchedule);

    /**
     * 批量删除咨询师排班任务
     * 
     * @param ids 需要删除的咨询师排班任务主键集合
     * @return 结果
     */
    public int deletePsyConsultantScheduleByIds(Long[] ids);

    /**
     * 删除咨询师排班任务信息
     * 
     * @param id 咨询师排班任务主键
     * @return 结果
     */
    public int deletePsyConsultantScheduleById(Long id);

    /**
     * 批量预约服务 (个督/体验)
     * @param consultantScheduleList
     */
    public void reservationServerBatch(List<PsyConsultantSchedule> consultantScheduleList);

    /**
     * 查询本次任务, 是[收费咨询师-付费咨询师]之间的第几次
     * @param req
     * @return
     */
    public int getTimeNumForConsultant(PsyWorkReq req);
}
