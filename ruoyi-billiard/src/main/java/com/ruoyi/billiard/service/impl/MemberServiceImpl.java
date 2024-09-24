package com.ruoyi.billiard.service.impl;

import java.math.BigDecimal;
import java.util.*;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ruoyi.billiard.domain.*;
import com.ruoyi.billiard.domain.vo.MemberPwdReqVo;
import com.ruoyi.billiard.enums.OrderType;
import com.ruoyi.billiard.mapper.OrderMemberDeductMapper;
import com.ruoyi.billiard.service.IOrderRechargeService;
import com.ruoyi.billiard.service.IOrderService;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.utils.ArrayUtil;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.MemberMapper;
import com.ruoyi.billiard.service.IMemberService;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;

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

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderRechargeService orderRechargeService;

    @Autowired
    private OrderMemberDeductMapper orderMemberDeductMapper;

    /**
     * 查询门店会员
     *
     * @param memberId 门店会员主键
     * @return 门店会员
     */
    @Override
    public Member selectMemberByMemberId(Long memberId) {
        Member member = memberMapper.selectById(memberId);
        if (Objects.nonNull(member)) {
            member.setPayPassword(null);
        }

        return member;
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
        memberList.forEach(p -> p.setPayPassword(null));
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
        if (StringUtils.isNotEmpty(member.getPayPassword())) {
            member.setPayPassword(SecurityUtils.encryptPassword(member.getPayPassword()));
        }
        // 查询当前门店是否存在会员
        Long count = memberMapper.selectCount(memberMapper.query().eq(Member::getMobile, member.getMobile())
                .eq(Member::getStoreId, member.getStoreId()));
        AssertUtil.isTrue(count == 0, "当前手机号已经是门店会员了，无法重复添加.");
        return memberMapper.insert(member);
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
        if (StringUtils.isNotEmpty(member.getPayPassword())) {
            member.setPayPassword(SecurityUtils.encryptPassword(member.getPayPassword()));
        }
        return memberMapper.updateById(member);
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
        // 根据会员id查询订单
        List<Order> orders = orderService.selectOrderByMemberIds(memberIds);
        AssertUtil.isNullOrEmpty(orders, "会员已存在订单，无法删除.");

        // 根据会员id查询会员充值记录表
        List<OrderRecharge> orderRecharges = orderRechargeService.selectOrderRechargeByMemberIds(memberIds);
        AssertUtil.isNullOrEmpty(orderRecharges, "会员已存在充值记录，无法删除.");

        // 删除会员数据
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
        // 根据会员id查询订单
        List<Order> orders = orderService.selectOrderByMemberIds(new Long[]{memberId});
        AssertUtil.isNullOrEmpty(orders, "会员已存在订单，无法删除.");

        // 根据会员id查询会员充值记录表
        List<OrderRecharge> orderRecharges = orderRechargeService.selectOrderRechargeByMemberIds(new Long[]{memberId});
        AssertUtil.isNullOrEmpty(orderRecharges, "会员已存在充值记录，无法删除.");
        // 删除会员数据
        return memberMapper.deleteMemberByMemberId(memberId);
    }

    @Override
    public List<Member> selectMemberListByMemberLevelId(Long memberLevelId) {
        return Optional.ofNullable(memberMapper.selectList(memberMapper.query().eq(Member::getLevelId, memberLevelId))).orElse(Collections.emptyList());
    }

    @Override
    public Map<Integer, BigDecimal> getOrderMemberDisCountValue(Long memberId) {
        if (Objects.isNull(memberId)) {
            return new HashMap<>();
        }
        List<LevelDiscountPermission> permissions = memberMapper.selectMemberPermissions(memberId);
        return ArrayUtil.toMap(permissions, LevelDiscountPermission::getValue, p -> {
            return Optional.ofNullable(p.getDiscount()).orElse(BigDecimal.ZERO);
        });
    }

    @Override
    public BigDecimal getMemberDisCountValue(OrderType type, Long memberId) {

        return Optional.ofNullable(memberMapper.selectDiscountByType(memberId, type.getValue())).orElse(BigDecimal.ZERO);
    }

    @Override
    public Boolean checkPwd(Long memberId, String password) {
        Member member = memberMapper.selectById(memberId);
        if (Objects.isNull(member)) {
            return false;
        }
        if (StringUtils.isEmpty(member.getPayPassword())) {
            return false;
        }
        return SecurityUtils.matchesPassword(password, member.getPayPassword());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deductAmount(Long memberId, Long orderId, BigDecimal deductAmount) {

        Member member = memberMapper.selectById(memberId);
        BigDecimal nowMoney = Optional.ofNullable(member.getCurrentAmount()).orElse(BigDecimal.ZERO);

        AssertUtil.isTrue(nowMoney.compareTo(deductAmount) >= 0,
                StringUtils.format("余额不足:当前余额{}", nowMoney.toPlainString()));
        UpdateWrapper<Member> updateWrapper = memberMapper.edit();
        updateWrapper.lambda().eq(Member::getMemberId, memberId).eq(Member::getCurrentAmount, nowMoney);
        updateWrapper.setSql(StrUtil.format("current_amount = current_amount - {}", deductAmount));
        updateWrapper.last(StrUtil.format(" and (current_amount - {} >= 0)   ", deductAmount));
        AssertUtil.isTrue(memberMapper.update(null, updateWrapper) > 0, "余额不足");

        OrderMemberDeduct deduct = new OrderMemberDeduct();
        deduct.setOrderMemberDeductId(IdUtils.singleNextId());
        deduct.setMemberId(memberId);
        deduct.setTotalAmount(deductAmount);
        deduct.setOrderId(orderId);
        SecurityUtils.fillCreateUser(deduct);
        orderMemberDeductMapper.insert(deduct);
    }

    @Override
    public Boolean updatePayPwd(MemberPwdReqVo reqVo) {
        Member member = memberMapper.selectById(reqVo.getMemberId());
        AssertUtil.notNullOrEmpty(member, "会员不存在");
        AssertUtil.equal(member.getStoreId(), reqVo.getStoreId(), "会员不合法");
        AssertUtil.isTrue(SecurityUtils.matchesPassword(reqVo.getOldPwd(),member.getPayPassword()),"旧密码不正确");
        member = new Member();
        member.setPayPassword(SecurityUtils.encryptPassword(reqVo.getPwd()));
        member.setMemberId(reqVo.getMemberId());
        SecurityUtils.fillUpdateUser(member);
        memberMapper.updateById(member);
        return Boolean.TRUE;
    }
}
