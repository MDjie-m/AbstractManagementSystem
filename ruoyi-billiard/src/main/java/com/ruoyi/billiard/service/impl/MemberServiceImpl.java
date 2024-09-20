package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.ruoyi.billiard.domain.LevelDiscountPermission;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.utils.ArrayUtil;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.MemberMapper;
import com.ruoyi.billiard.domain.Member;
import com.ruoyi.billiard.service.IMemberService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 门店会员Service业务层处理
 *
 * @author zhoukeu
 * @date 2024-09-14
 */
@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private IStoreService storeService;

    /**
     * 查询门店会员
     *
     * @param memberId 门店会员主键
     * @return 门店会员
     */
    @Override
    public Member selectMemberByMemberId(Long memberId) {
        return memberMapper.selectById(memberId);
    }

    /**
     * 查询门店会员列表
     *
     * @param member 门店会员
     * @return 门店会员
     */
    @Override
    public List<Member> selectMemberList(Member member) {
        List<Member> memberList = Optional.ofNullable(memberMapper.selectMemberList(member)).orElse(Collections.emptyList());
        memberList.forEach(p -> p.setStoreName(storeService.selectStoreByStoreId(p.getStoreId()).getStoreName()));
        return memberList;
    }

    /**
     * 新增门店会员
     *
     * @param member 门店会员
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertMember(Member member) {
        SecurityUtils.fillCreateUser(member);
        member.setMemberId(IdUtils.singleNextId());
        // 查询当前门店是否存在会员
        Long count = memberMapper.selectCount(memberMapper.query().eq(Member::getMobile, member.getMobile())
                .eq(Member::getStoreId, member.getStoreId()));
        AssertUtil.isTrue(count == 0, "当前手机号已经是门店会员了，无法重复添加.");
        return memberMapper.insertMember(member);
    }

    /**
     * 修改门店会员
     *
     * @param member 门店会员
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateMember(Member member) {
        SecurityUtils.fillUpdateUser(member);

        return memberMapper.updateMember(member);
    }

    /**
     * 批量删除门店会员
     *
     * @param memberIds 需要删除的门店会员主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteMemberByMemberIds(Long[] memberIds) {
        return memberMapper.deleteMemberByMemberIds(memberIds);
    }

    /**
     * 删除门店会员信息
     *
     * @param memberId 门店会员主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteMemberByMemberId(Long memberId) {
        return memberMapper.deleteMemberByMemberId(memberId);
    }

    @Override
    public List<Member> selectMemberListByMemberLevelId(Long memberLevelId) {
        return Optional.ofNullable(memberMapper.selectList(memberMapper.query().eq(Member::getLevelId, memberLevelId))).orElse(Collections.emptyList());
    }

    @Override
    public Map<Integer, BigDecimal> getOrderMemberDisCountValue(Long memberId) {
        if(Objects.isNull(memberId)){
            return  new HashMap<>();
        }
        List<LevelDiscountPermission> permissions = memberMapper.selectMemberPermissions(memberId);
        return ArrayUtil.toMap(permissions, LevelDiscountPermission::getValue, p -> {
            return Optional.ofNullable(p.getDiscount()).orElse(BigDecimal.ZERO);
        });
    }
}
