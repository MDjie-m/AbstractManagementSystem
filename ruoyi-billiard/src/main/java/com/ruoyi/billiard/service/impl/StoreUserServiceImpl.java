package com.ruoyi.billiard.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import com.ruoyi.common.utils.ArrayUtil;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreUserMapper;
import com.ruoyi.billiard.domain.StoreUser;
import com.ruoyi.billiard.service.IStoreUserService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 门店员工Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-05
 */
@Service
public class StoreUserServiceImpl implements IStoreUserService {
    @Resource
    private StoreUserMapper storeUserMapper;

    @Resource
    private ISysUserService sysUserService;


    /**
     * 查询门店员工
     *
     * @param storeUserId 门店员工主键
     * @return 门店员工
     */
    @Override
    public StoreUser selectStoreUserByStoreUserId(Long storeUserId) {
        StoreUser user = storeUserMapper.selectById(storeUserId);
        if (Objects.nonNull(user)) {
            user.setRoleIds(storeUserMapper.selectRoleIds(user.getLoginUserId()));
        }
        return user;
    }

    /**
     * 查询门店员工列表
     *
     * @param storeUser 门店员工
     * @return 门店员工
     */
    @Override
    public List<StoreUser> selectStoreUserList(StoreUser storeUser) {

        List<StoreUser> users = storeUserMapper.selectStoreUserList(storeUser);
        if (CollectionUtils.isEmpty(users)) {
            return users;
        }
        List<KeyValueVo<Long, Long>> roleIds = storeUserMapper.selectRoleIdsByUserIds(users.stream().map(StoreUser::getLoginUserId).collect(Collectors.toList()));
        Map<Long, List<Long>> roleIdMap = ArrayUtil.groupByValue(roleIds, KeyValueVo::getKey, KeyValueVo::getValue);
        users.forEach(u -> {
            u.setRoleIds(roleIdMap.getOrDefault(u.getLoginUserId(), Lists.newArrayList()));
        });
        return users;
    }

    /**
     * 新增门店员工
     *
     * @param storeUser 门店员工
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertStoreUser(StoreUser storeUser) {
        AssertUtil.isTrue(!storeUserMapper.exists(storeUserMapper.query().eq(StoreUser::getMobile, storeUser.getMobile())
                        .eq(StoreUser::getStoreId, storeUser.getStoreId())),
                "手机号已被其他用户使用");
        storeUser.setCreateTime(DateUtils.getNowDate());

        SysUser sysUser = sysUserService.selectUserByMobile(storeUser.getMobile(), null);
        if (Objects.isNull(sysUser)) {
            sysUser = new SysUser();
            sysUser.setAvatar(storeUser.getUserImg());
            sysUser.setDeptId(100L);
            sysUser.setUserName(storeUser.getMobile());
            sysUser.setPhonenumber(storeUser.getMobile());
            sysUser.setNickName(storeUser.getRealName());
            sysUser.setPassword(SecurityUtils.encryptPassword(storeUser.getMobile()));
            sysUser.setRoleIds(storeUser.getRoleIds());
            sysUser.setSex(storeUser.getSex());
            sysUserService.insertUser(sysUser);
        } else {
            sysUserService.addUserRoles(sysUser.getUserId(), storeUser.getRoleIds());
        }
        storeUser.setStoreUserId(IdUtils.singleNextId());
        storeUser.setLoginUserId(sysUser.getUserId());
        SecurityUtils.fillCreateUser(storeUser);


        int res = storeUserMapper.insert(storeUser);
        return res;

    }

    /**
     * 修改门店员工
     *
     * @param storeUser 门店员工
     * @return 结果
     */
    @Override
    public int updateStoreUser(StoreUser storeUser) {
        AssertUtil.isTrue(!storeUserMapper.exists(storeUserMapper.query().eq(StoreUser::getMobile, storeUser.getMobile())
                        .eq(StoreUser::getStoreId, storeUser.getStoreId())
                        .notIn(StoreUser::getStoreUserId, storeUser.getStoreUserId())),
                "手机号已被其他用户使用");

        SysUser user = sysUserService.selectUserById(storeUser.getLoginUserId());
        user.setSex(storeUser.getSex());
        user.setNickName(storeUser.getRealName());
        user.setPhonenumber(storeUser.getMobile());
        user.setUpdateBy(SecurityUtils.getUsername());
        user.setRoleIds(storeUser.getRoleIds());
        sysUserService.updateUser(user);

        SecurityUtils.fillUpdateUser(storeUser);
        return storeUserMapper.updateById(storeUser);
    }

    /**
     * 批量删除门店员工
     *
     * @param storeUserIds 需要删除的门店员工主键
     * @return 结果
     */
    @Override
    public int deleteStoreUserByStoreUserIds(Long[] storeUserIds) {
        return storeUserMapper.deleteStoreUserByStoreUserIds(storeUserIds);
    }

    /**
     * 删除门店员工信息
     *
     * @param storeUserId 门店员工主键
     * @return 结果
     */
    @Override
    public int deleteStoreUserByStoreUserId(Long storeUserId) {
        return storeUserMapper.deleteStoreUserByStoreUserId(storeUserId);
    }
}
