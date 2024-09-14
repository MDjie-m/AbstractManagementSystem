package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.Member;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 门店会员Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@Mapper
public interface MemberMapper extends MyBaseMapper<Member>
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
     * 删除门店会员
     * 
     * @param memberId 门店会员主键
     * @return 结果
     */
    public int deleteMemberByMemberId(Long memberId);

    /**
     * 批量删除门店会员
     * 
     * @param memberIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberByMemberIds(Long[] memberIds);
}
