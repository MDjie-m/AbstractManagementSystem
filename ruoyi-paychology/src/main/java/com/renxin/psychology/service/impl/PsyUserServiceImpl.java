package com.renxin.psychology.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.renxin.common.constant.IntegralRecordConstants;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.domain.vo.LoginVO;
import com.renxin.common.event.publish.IntegralPublisher;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.mapper.PsyUserMapper;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.user.domain.PsyUserIntegralRecord;
import com.renxin.user.service.IPsyUserIntegralRecordService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 用户Service业务层处理
 *
 * @author renxin
 * @date 2022-08-26
 */
@Service
public class PsyUserServiceImpl implements IPsyUserService {
    @Resource
    private PsyUserMapper psyUserMapper;

    @Resource
    private IntegralPublisher integralPublisher;

    @Resource
    private IPsyUserIntegralRecordService psyUserIntegralRecordService;

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public PsyUser selectPsyUserById(Integer id) {
        PsyUser psyUser = psyUserMapper.selectPsyUserById(id);
        if (ObjectUtils.isEmpty(psyUser.getName())){
            psyUser.setName("");
        }
        if (ObjectUtils.isEmpty(psyUser.getAvatar())){
            psyUser.setAvatar("");
        }
        if (ObjectUtils.isEmpty(psyUser.getPhone())){
            psyUser.setPhone("");
        }
        return psyUser;
    }

    /**
     * 查询用户列表
     *
     * @param psyUser 用户
     * @return 用户
     */
    @Override
    public List<PsyUser> selectPsyUserList(PsyUser psyUser) {
        return psyUserMapper.selectPsyUserList(psyUser);
    }

    /**
     * 新增用户
     *
     * @param psyUser 用户
     * @return 结果
     */
    @Override
    public int insertPsyUser(PsyUser psyUser) {
        psyUser.setCreateTime(DateUtils.getNowDate());
        return psyUserMapper.insertPsyUser(psyUser);
    }

    /**
     * 修改用户
     *
     * @param psyUser 用户
     * @return 结果
     */
    @Override
    public int updatePsyUser(PsyUser psyUser) {
        return psyUserMapper.updatePsyUser(psyUser);
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deletePsyUserByIds(Integer[] ids) {
        return psyUserMapper.deletePsyUserByIds(ids);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deletePsyUserById(Integer id) {
        return psyUserMapper.deletePsyUserById(id);
    }

    @Override
    public PsyUser queryUserByAccount(String account) {
        return psyUserMapper.queryUserByAccount(account);
    }

    @Override
    public LoginVO checkPsyUser(String openId, JSONObject userInfo) {
        //去数据库查询此微信是否绑定过手机
        PsyUser user = psyUserMapper.queryUserByAccount(openId);

        String nickname = userInfo.getString("nickname");
        String headImgUrl = userInfo.getString("headimgurl");

        //用户信息为空，则插入一条数据
        if (user == null) {
            int result = psyUserMapper.insertPsyUser(PsyUser.builder().wxOpenid(openId).name(nickname).avatar(headImgUrl).build());
            if (result != 1) {
                System.out.println("用户信息插入失败");
            }
            user = psyUserMapper.queryUserByAccount(openId);
            // 注册送积分
            int integral = psyUserIntegralRecordService.getIntegral(new BigDecimal("0"), IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_REGISTER);
            if (integral > 0) {
                PsyUserIntegralRecord record = new PsyUserIntegralRecord();
                record.setIntegral(integral);
                record.setLinkId(String.valueOf(user.getId()));
                record.setUid(user.getId());
                record.setDelFlag(0);
                record.setFrozenTime(0);
                record.setMark(StrUtil.format("用户付款成功,订单增加{}积分", record.getIntegral()));
                record.setType(IntegralRecordConstants.INTEGRAL_RECORD_TYPE_ADD);
                record.setLinkType(IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_REGISTER);
                record.setTitle(IntegralRecordConstants.BROKERAGE_RECORD_TITLE_REGISTER);
                record.setStatus(IntegralRecordConstants.INTEGRAL_RECORD_STATUS_COMPLETE);
                integralPublisher.publish(record);
            }
        }

        return LoginVO.builder().userId(user.getId()).name(nickname).avatar(headImgUrl).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindPhone(LoginDTO loginDTO) {
        PsyUser user = psyUserMapper.selectPsyUserById(loginDTO.getUserId());
        PsyUser phoneUser = psyUserMapper.queryUserByAccount(loginDTO.getPhone());
        //若该手机号已经存在用户，直接将微信信息更新至该用户,并删除此微信用户
        if (phoneUser != null) {
            psyUserMapper.updatePsyUser(PsyUser.builder().id(phoneUser.getId()).wxOpenid(user.getWxOpenid()).avatar(user.getAvatar()).name(user.getName()).build());
            psyUserMapper.deletePsyUserById(user.getId());
            loginDTO.setUserId(phoneUser.getId());
        } else {
            psyUserMapper.updatePsyUser(PsyUser.builder().id(user.getId()).phone(loginDTO.getPhone()).build());
            loginDTO.setUserId(user.getId());
        }
    }
}
