package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ruoyi.billiard.domain.LevelDiscountPermission;
import com.ruoyi.billiard.domain.Member;
import com.ruoyi.billiard.service.ILevelDiscountPermissionService;
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
public class MemberLevelServiceImpl implements IMemberLevelService {
    @Autowired
    private MemberLevelMapper memberLevelMapper;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IStoreService storeService;

    @Autowired
    private ILevelDiscountPermissionService levelDiscountPermissionService;

    /**
     * 查询门店会员等级
     *
     * @param memberLevelId 门店会员等级主键
     * @return 门店会员等级
     */
    @Override
    public MemberLevel selectMemberLevelByMemberLevelId(Long memberLevelId) {
        MemberLevel memberLevel = Optional.ofNullable(memberLevelMapper.selectById(memberLevelId)).orElse(new MemberLevel());
        // 查询折扣范围
        List<Integer> discountRanges = levelDiscountPermissionService.selectLevelDiscountPermissionListByMemberLevelId(memberLevel.getMemberLevelId()).stream()
                .map(LevelDiscountPermission::getValue)
                .collect(Collectors.toList());
        memberLevel.setDiscountRange(discountRanges);
        return memberLevel;
    }

    /**
     * 查询门店会员等级列表
     *
     * @param memberLevel 门店会员等级
     * @return 门店会员等级
     */
    @Override
    public List<MemberLevel> selectMemberLevelList(MemberLevel memberLevel) {
        List<MemberLevel> memberLevels = Optional.ofNullable(memberLevelMapper.selectMemberLevelList(memberLevel)).orElse(Collections.emptyList());
        memberLevels.forEach(level -> {
            level.setStoreName(storeService.selectStoreByStoreId(level.getStoreId()).getStoreName());
            level.setDiscountRange(levelDiscountPermissionService.selectLevelDiscountPermissionListByMemberLevelId(level.getMemberLevelId()).stream().map(LevelDiscountPermission::getValue).collect(Collectors.toList()));
        });
        return memberLevels;
    }

    /**
     * 新增门店会员等级
     *
     * @param memberLevel 门店会员等级
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertMemberLevel(MemberLevel memberLevel) {
        SecurityUtils.fillCreateUser(memberLevel);
        memberLevel.setMemberLevelId(IdUtils.singleNextId());
        memberLevelMapper.insertMemberLevel(memberLevel);
        Long memberLevelId = memberLevel.getMemberLevelId();
        BigDecimal discount = memberLevel.getDiscount();
        List<Integer> discountRange = memberLevel.getDiscountRange();
        // 添加等级折扣权限表
        addALevelDiscountPermissionTable(memberLevel);
        return 1;
    }

    /**
     * 修改门店会员等级
     *
     * @param memberLevel 门店会员等级
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateMemberLevel(MemberLevel memberLevel) {
        SecurityUtils.fillUpdateUser(memberLevel);
        memberLevelMapper.updateMemberLevel(memberLevel);
        // 先删除等级折扣权限表
        levelDiscountPermissionService.deleteLevelDiscountPermissionByMemberLevelId(memberLevel.getMemberLevelId());
        // 添加等级折扣权限表
        addALevelDiscountPermissionTable(memberLevel);
        return 1;

    }

    /**
     * 批量删除门店会员等级
     *
     * @param memberLevelIds 需要删除的门店会员等级主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteMemberLevelByMemberLevelIds(Long[] memberLevelIds) {
        return memberLevelMapper.deleteMemberLevelByMemberLevelIds(memberLevelIds);
    }

    /**
     * 删除门店会员等级信息
     *
     * @param memberLevelId 门店会员等级主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteMemberLevelByMemberLevelId(Long memberLevelId) {
        return memberLevelMapper.deleteMemberLevelByMemberLevelId(memberLevelId);
    }

    @Override
    public Boolean isUsed(Long memberLevelId) {
        List<Member> memberList = memberService.selectMemberListByMemberLevelId(memberLevelId);
        return CollectionUtils.isNotEmpty(memberList);
    }

    /**
     * 添加等级折扣权限表
     *
     * @param memberLevel 会员等级对象
     */
    public void addALevelDiscountPermissionTable(MemberLevel memberLevel) {
        Long memberLevelId = memberLevel.getMemberLevelId();
        BigDecimal discount = memberLevel.getDiscount();
        List<Integer> discountRange = memberLevel.getDiscountRange();
        // 添加等级折扣权限表
        List<LevelDiscountPermission> levelDiscountPermissions = discountRange.stream().map(range -> {
            LevelDiscountPermission levelDiscountPermission = LevelDiscountPermission.builder().levelDiscountPermissionId(IdUtils.singleNextId()).discount(discount)
                    .value(range).memberLevelId(memberLevelId).build();
            SecurityUtils.fillCreateUser(levelDiscountPermission);
            return levelDiscountPermission;
        }).collect(Collectors.toList());
        levelDiscountPermissionService.batchInsertLevelDiscountPermission(levelDiscountPermissions);
    }
}
