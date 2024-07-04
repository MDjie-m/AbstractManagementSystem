package com.renxin.psychology.mapper;

import java.util.List;
import com.renxin.psychology.domain.PsyConsultantSupervisionMember;

/**
 * 督导成员Mapper接口
 * 
 * @author renxin
 * @date 2024-06-26
 */
public interface PsyConsultantSupervisionMemberMapper 
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
     * 删除督导成员
     * 
     * @param id 督导成员主键
     * @return 结果
     */
    public int deletePsyConsultantSupervisionMemberById(Long id);

    /**
     * 批量删除督导成员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultantSupervisionMemberByIds(Long[] ids);

    /**
     * 查询指定团队的成员人数
     */
    
    int queryMemberCount(String teamId);
}
