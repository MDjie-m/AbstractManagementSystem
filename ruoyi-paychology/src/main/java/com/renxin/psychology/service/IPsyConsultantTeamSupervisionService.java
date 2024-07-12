package com.renxin.psychology.service;

import java.util.List;

import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;

/**
 * 团队督导(组织)Service接口
 * 
 * @author renxin
 * @date 2024-06-26
 */
public interface IPsyConsultantTeamSupervisionService 
{
    /**
     * 查询团队督导(组织)
     * 
     * @param id 团队督导(组织)主键
     * @return 团队督导(组织)
     */
    public PsyConsultantTeamSupervision selectPsyConsultantTeamSupervisionById(Long id);

    /**
     * 查询团队督导(组织)列表
     * 
     * @param psyConsultantTeamSupervision 团队督导(组织)
     * @return 团队督导(组织)集合
     */
    public List<PsyConsultantTeamSupervision> selectPsyConsultantTeamSupervisionList(PsyConsultantTeamSupervision psyConsultantTeamSupervision);

    /**
     * 新增团队督导(组织)
     * 
     * @param psyConsultantTeamSupervision 团队督导(组织)
     * @return 结果
     */
    public int insertPsyConsultantTeamSupervision(PsyConsultantTeamSupervision psyConsultantTeamSupervision);

    /**
     * 修改团队督导(组织)
     * 
     * @param psyConsultantTeamSupervision 团队督导(组织)
     * @return 结果
     */
    public int updatePsyConsultantTeamSupervision(PsyConsultantTeamSupervision psyConsultantTeamSupervision);

    /**
     * 批量删除团队督导(组织)
     * 
     * @param ids 需要删除的团队督导(组织)主键集合
     * @return 结果
     */
    public int deletePsyConsultantTeamSupervisionByIds(Long[] ids);

    /**
     * 删除团队督导(组织)信息
     * 
     * @param id 团队督导(组织)主键
     * @return 结果
     */
    public int deletePsyConsultantTeamSupervisionById(Long id);

    /**
     * 付款完成后, 处理订单
     */
    public void handleOrder(PsyConsultantOrder consultantOrder);
}
