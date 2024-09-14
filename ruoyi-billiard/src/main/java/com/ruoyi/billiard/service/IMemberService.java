package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.Member;

/**
 * 门店会员Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
public interface IMemberService 
{
    /**
     * 查询门店会员
     * 
     * @param memberId 门店会员主键
     * @return 门店会员
     */
    public Member selectMemberByMemberId(Long memberId);

    /**
     * 查询门店会员列表
     * 
     * @param member 门店会员
     * @return 门店会员集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增门店会员
     * 
     * @param member 门店会员
     * @return 结果
     */
    public int insertMember(Member member);

    /**
     * 修改门店会员
     * 
     * @param member 门店会员
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 批量删除门店会员
     * 
     * @param memberIds 需要删除的门店会员主键集合
     * @return 结果
     */
    public int deleteMemberByMemberIds(Long[] memberIds);

    /**
     * 删除门店会员信息
     * 
     * @param memberId 门店会员主键
     * @return 结果
     */
    public int deleteMemberByMemberId(Long memberId);

    /**
     * 根据会员等级查询会员列表
     * @param memberLevelId
     * @return
     */
    List<Member> selectMemberListByMemberLevelId(Long memberLevelId);
}
