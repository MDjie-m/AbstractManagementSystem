package com.renxin.psychology.service;

import java.util.List;
import com.renxin.psychology.domain.PsyAppointment;

/**
 * 咨询师预约Service接口
 * 
 * @author renxin
 * @date 2022-08-26
 */
public interface IPsyAppointmentService 
{
    /**
     * 查询咨询师预约
     * 
     * @param id 咨询师预约主键
     * @return 咨询师预约
     */
    public PsyAppointment selectPsyAppointmentById(Long id);

    /**
     * 查询咨询师预约列表
     * 
     * @param psyAppointment 咨询师预约
     * @return 咨询师预约集合
     */
    public List<PsyAppointment> selectPsyAppointmentList(PsyAppointment psyAppointment);

    /**
     * 新增咨询师预约
     * 
     * @param psyAppointment 咨询师预约
     * @return 结果
     */
    public int insertPsyAppointment(PsyAppointment psyAppointment);

    /**
     * 修改咨询师预约
     * 
     * @param psyAppointment 咨询师预约
     * @return 结果
     */
    public int updatePsyAppointment(PsyAppointment psyAppointment);

    /**
     * 批量删除咨询师预约
     * 
     * @param ids 需要删除的咨询师预约主键集合
     * @return 结果
     */
    public int deletePsyAppointmentByIds(Long[] ids);

    /**
     * 删除咨询师预约信息
     * 
     * @param id 咨询师预约主键
     * @return 结果
     */
    public int deletePsyAppointmentById(Long id);
}
