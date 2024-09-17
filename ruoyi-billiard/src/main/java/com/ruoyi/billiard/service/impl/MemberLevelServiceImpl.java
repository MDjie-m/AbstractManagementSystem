package com.ruoyi.billiard.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ruoyi.billiard.domain.Member;
import com.ruoyi.billiard.service.IMemberService;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.MemberLevelMapper;
import com.ruoyi.billiard.domain.MemberLevel;
import com.ruoyi.billiard.service.IMemberLevelService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 门店会员等级Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@Service
public class MemberLevelServiceImpl implements IMemberLevelService 
{
    @Autowired
    private MemberLevelMapper memberLevelMapper;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IStoreService storeService;

    /**
     * 查询门店会员等级
     * 
     * @param memberLevelId 门店会员等级主键
     * @return 门店会员等级
     */
    @Override
    public MemberLevel selectMemberLevelByMemberLevelId(Long memberLevelId)
    {
        return memberLevelMapper.selectById(memberLevelId);
    }

    /**
     * 查询门店会员等级列表
     * 
     * @param memberLevel 门店会员等级
     * @return 门店会员等级
     */
    @Override
    public List<MemberLevel> selectMemberLevelList(MemberLevel memberLevel)
    {
        List<MemberLevel> memberLevels = Optional.ofNullable(memberLevelMapper.selectMemberLevelList(memberLevel)).orElse(Collections.emptyList());
        memberLevels.forEach(level -> level.setStoreName(storeService.selectStoreByStoreId(level.getStoreId()).getStoreName()));
        return memberLevels;
    }

    /**
     * 新增门店会员等级
     * 
     * @param memberLevel 门店会员等级
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMemberLevel(MemberLevel memberLevel)
    {
        SecurityUtils.fillCreateUser(memberLevel);
        memberLevel.setMemberLevelId(IdUtils.singleNextId());
        return memberLevelMapper.insertMemberLevel(memberLevel);
    }

    /**
     * 修改门店会员等级
     * 
     * @param memberLevel 门店会员等级
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMemberLevel(MemberLevel memberLevel)
    {
        SecurityUtils.fillUpdateUser(memberLevel);

        return memberLevelMapper.updateMemberLevel(memberLevel);
    }

    /**
     * 批量删除门店会员等级
     * 
     * @param memberLevelIds 需要删除的门店会员等级主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMemberLevelByMemberLevelIds(Long[] memberLevelIds)
    {
        return memberLevelMapper.deleteMemberLevelByMemberLevelIds(memberLevelIds);
    }

    /**
     * 删除门店会员等级信息
     * 
     * @param memberLevelId 门店会员等级主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMemberLevelByMemberLevelId(Long memberLevelId)
    {
        return memberLevelMapper.deleteMemberLevelByMemberLevelId(memberLevelId);
    }

    @Override
    public Boolean isUsed(Long memberLevelId) {
        List<Member> memberList = memberService.selectMemberListByMemberLevelId(memberLevelId);
        return CollectionUtils.isNotEmpty(memberList);
    }
}
