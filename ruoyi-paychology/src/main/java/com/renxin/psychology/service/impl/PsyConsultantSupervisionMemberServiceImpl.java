package com.renxin.psychology.service.impl;

import java.util.List;

import com.renxin.common.core.domain.model.LoginUser;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.SecurityUtils;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.mapper.PsyConsultantTeamSupervisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantSupervisionMemberMapper;
import com.renxin.psychology.domain.PsyConsultantSupervisionMember;
import com.renxin.psychology.service.IPsyConsultantSupervisionMemberService;

/**
 * 督导成员Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-26
 */
@Service
public class PsyConsultantSupervisionMemberServiceImpl implements IPsyConsultantSupervisionMemberService 
{
    @Autowired
    private PsyConsultantSupervisionMemberMapper psyConsultantSupervisionMemberMapper;

    @Autowired
    private PsyConsultantTeamSupervisionMapper psyConsultantTeamSupervisionMapper;

    /**
     * 查询督导成员
     * 
     * @param id 督导成员主键
     * @return 督导成员
     */
    @Override
    public PsyConsultantSupervisionMember selectPsyConsultantSupervisionMemberById(Long id)
    {
        return psyConsultantSupervisionMemberMapper.selectPsyConsultantSupervisionMemberById(id);
    }

    /**
     * 查询督导成员列表
     * 
     * @param psyConsultantSupervisionMember 督导成员
     * @return 督导成员
     */
    @Override
    public List<PsyConsultantSupervisionMember> selectPsyConsultantSupervisionMemberList(PsyConsultantSupervisionMember psyConsultantSupervisionMember)
    {
        return psyConsultantSupervisionMemberMapper.selectPsyConsultantSupervisionMemberList(psyConsultantSupervisionMember);
    }

    /**
     * 新增督导成员
     * 
     * @param psyConsultantSupervisionMember 督导成员
     * @return 结果
     */
    @Override
    public int insertPsyConsultantSupervisionMember(PsyConsultantSupervisionMember req)
    {
        //LoginUser loginUser = SecurityUtils.getLoginUser();
        req.setCreateBy(req.getMemberId());
        req.setUpdateBy(req.getMemberId());
        req.setCreateTime(DateUtils.getNowDate());
        req.setUpdateTime(DateUtils.getNowDate());

        PsyConsultantTeamSupervision team = psyConsultantTeamSupervisionMapper.selectPsyConsultantTeamSupervisionById(req.getTeamSupervisionId());
        req.setSupervisionId(Long.valueOf(team.getConsultantId()));//督导师id
        req.setSupervisionType("1");
        
        return psyConsultantSupervisionMemberMapper.insertPsyConsultantSupervisionMember(req);
    }

    /**
     * 修改督导成员
     * 
     * @param psyConsultantSupervisionMember 督导成员
     * @return 结果
     */
    @Override
    public int updatePsyConsultantSupervisionMember(PsyConsultantSupervisionMember psyConsultantSupervisionMember)
    {
        psyConsultantSupervisionMember.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantSupervisionMemberMapper.updatePsyConsultantSupervisionMember(psyConsultantSupervisionMember);
    }

    /**
     * 批量删除督导成员
     * 
     * @param ids 需要删除的督导成员主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantSupervisionMemberByIds(Long[] ids)
    {
        return psyConsultantSupervisionMemberMapper.deletePsyConsultantSupervisionMemberByIds(ids);
    }

    /**
     * 删除督导成员信息
     * 
     * @param id 督导成员主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantSupervisionMemberById(Long id)
    {
        return psyConsultantSupervisionMemberMapper.deletePsyConsultantSupervisionMemberById(id);
    }
}
