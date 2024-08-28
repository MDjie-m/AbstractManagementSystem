package com.renxin.psychology.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.renxin.psychology.domain.PsyConsultantSupervisionMember;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;

/**
 * 督导成员Service接口
 * 
 * @author renxin
 * @date 2024-06-26
 */
public interface IPsyConsultantSupervisionMemberService extends IService<PsyConsultantSupervisionMember>
{
    /**
     * 查询督导成员
     * 
     * @param id 督导成员主键
     * @return 督导成员
     */
    public PsyConsultantSupervisionMember selectPsyConsultantSupervisionMemberById(Long id);

    /**
     * 查询督导成员列表
     * 
     * @param psyConsultantSupervisionMember 督导成员
     * @return 督导成员集合
     */
    public List<PsyConsultantSupervisionMember> selectPsyConsultantSupervisionMemberList(PsyConsultantSupervisionMember psyConsultantSupervisionMember);

    /**
     * 新增督导成员
     * 
     * @param psyConsultantSupervisionMember 督导成员
     * @return 结果
     */
    public int insertPsyConsultantSupervisionMember(PsyConsultantSupervisionMember psyConsultantSupervisionMember);

    /**
     * 修改督导成员
     * 
     * @param psyConsultantSupervisionMember 督导成员
     * @return 结果
     */
    public int updatePsyConsultantSupervisionMember(PsyConsultantSupervisionMember psyConsultantSupervisionMember);

    /**
     * 批量删除督导成员
     * 
     * @param ids 需要删除的督导成员主键集合
     * @return 结果
     */
    public int deletePsyConsultantSupervisionMemberByIds(Long[] ids);

    /**
     * 删除督导成员信息
     * 
     * @param id 督导成员主键
     * @return 结果
     */
    public int deletePsyConsultantSupervisionMemberById(Long id);
}
