package com.ruoyi.billiard.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.MemberMapper;
import com.ruoyi.billiard.domain.Member;
import com.ruoyi.billiard.service.IMemberService;

/**
 * 门店会员Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@Service
public class MemberServiceImpl implements IMemberService 
{
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询门店会员
     * 
     * @param memberId 门店会员主键
     * @return 门店会员
     */
    @Override
    public Member selectMemberByMemberId(Long memberId)
    {
        return memberMapper.selectById(memberId);
    }

    /**
     * 查询门店会员列表
     * 
     * @param member 门店会员
     * @return 门店会员
     */
    @Override
    public List<Member> selectMemberList(Member member)
    {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增门店会员
     * 
     * @param member 门店会员
     * @return 结果
     */
    @Override
    public int insertMember(Member member)
    {
        SecurityUtils.fillCreateUser(member);
        member.setMemberId(IdUtils.singleNextId());
        return memberMapper.insertMember(member);
    }

    /**
     * 修改门店会员
     * 
     * @param member 门店会员
     * @return 结果
     */
    @Override
    public int updateMember(Member member)
    {
        SecurityUtils.fillUpdateUser(member);

        return memberMapper.updateMember(member);
    }

    /**
     * 批量删除门店会员
     * 
     * @param memberIds 需要删除的门店会员主键
     * @return 结果
     */
    @Override
    public int deleteMemberByMemberIds(Long[] memberIds)
    {
        return memberMapper.deleteMemberByMemberIds(memberIds);
    }

    /**
     * 删除门店会员信息
     * 
     * @param memberId 门店会员主键
     * @return 结果
     */
    @Override
    public int deleteMemberByMemberId(Long memberId)
    {
        return memberMapper.deleteMemberByMemberId(memberId);
    }

    @Override
    public List<Member> selectMemberListByMemberLevelId(Long memberLevelId) {
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Member::getLevelId,memberLevelId);
        return Optional.ofNullable(memberMapper.selectList(wrapper)).orElse(Collections.emptyList());
    }
}
