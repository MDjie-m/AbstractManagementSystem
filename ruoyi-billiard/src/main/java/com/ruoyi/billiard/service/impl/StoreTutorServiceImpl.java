package com.ruoyi.billiard.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.billiard.mapper.StoreUserMapper;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import com.ruoyi.common.utils.ArrayUtil;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreTutorMapper;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.service.IStoreTutorService;

import javax.annotation.Resource;

/**
 * 门店助教Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-06
 */
@Service
public class StoreTutorServiceImpl implements IStoreTutorService {
    @Autowired
    private StoreTutorMapper storeTutorMapper;

    @Resource
    private StoreUserMapper storeUserMapper;

    @Resource
    private ISysUserService sysUserService;


    /**
     * 查询门店助教
     *
     * @param storeTutorId 门店助教主键
     * @return 门店助教
     */
    @Override
    public StoreTutor selectStoreTutorByStoreTutorId(Long storeTutorId) {
        StoreTutor user = storeTutorMapper.selectById(storeTutorId);
        if (Objects.nonNull(user)) {
            user.setRoleIds(storeUserMapper.selectRoleIds(user.getLoginUserId()));
        }
        return user;
    }

    /**
     * 查询门店助教列表
     *
     * @param storeTutor 门店助教
     * @return 门店助教
     */
    @Override
    public List<StoreTutor> selectStoreTutorList(StoreTutor storeTutor) {

        List<StoreTutor> users = storeTutorMapper.selectStoreTutorList(storeTutor);
        if (CollectionUtils.isEmpty(users)) {
            return users;
        }
        List<KeyValueVo<Long, Long>> roleIds = storeUserMapper.selectRoleIdsByUserIds(users.stream()
                .map(StoreTutor::getLoginUserId).collect(Collectors.toList()));
        Map<Long, List<Long>> roleIdMap = ArrayUtil.groupByValue(roleIds, KeyValueVo::getKey, KeyValueVo::getValue);
        users.forEach(u -> {
            u.setRoleIds(roleIdMap.getOrDefault(u.getLoginUserId(), Lists.newArrayList()));
        });
        return users;
    }

    /**
     * 新增门店助教
     *
     * @param storeTutor 门店助教
     * @return 结果
     */
    @Override
    public int insertStoreTutor(StoreTutor storeTutor) {
        AssertUtil.isTrue(!storeTutorMapper.exists(storeTutorMapper.query().eq(StoreTutor::getMobile, storeTutor.getMobile())
                .eq(StoreTutor::getStoreId, storeTutor.getStoreId())), "手机号已被其他用户使用");
        storeTutor.setCreateTime(DateUtils.getNowDate());

        //手机号重复加字母
        List<String> nameSubList = Lists.newArrayList();
        nameSubList.add("");
        nameSubList.addAll(Arrays.asList("ABCDEFGHIGKLMNOPQRSTUVWXYZ".split("")));

        String nameSub = "";
        for (String s : nameSubList) {
            nameSub = s;
            SysUser sysUser = sysUserService.selectUserByUserName(storeTutor.getMobile() + nameSub);
            if (Objects.isNull(sysUser)) {
                break;
            }
        }

        SysUser sysUser = new SysUser();
        sysUser.setAvatar(storeTutor.getUserImg());
        sysUser.setDeptId(100L);
        sysUser.setUserName(storeTutor.getMobile()+nameSub);
        sysUser.setPhonenumber(storeTutor.getMobile());
        sysUser.setNickName(storeTutor.getRealName());
        sysUser.setPassword(SecurityUtils.encryptPassword(storeTutor.getMobile()));
        sysUser.setRoleIds(storeTutor.getRoleIds());
        sysUser.setSex(storeTutor.getSex());
        sysUser.setCreateBy(SecurityUtils.getUsername());
        sysUserService.insertUser(sysUser);

        storeTutor.setStoreTutorId(IdUtils.singleNextId());
        storeTutor.setLoginUserId(sysUser.getUserId());
        SecurityUtils.fillCreateUser(storeTutor);
        return storeTutorMapper.insertStoreTutor(storeTutor);
    }

    /**
     * 修改门店助教
     *
     * @param storeTutor 门店助教
     * @return 结果
     */
    @Override
    public int updateStoreTutor(StoreTutor storeTutor) {
        AssertUtil.isTrue(!storeTutorMapper.exists(storeTutorMapper.query().eq(StoreTutor::getMobile, storeTutor.getMobile())
                        .eq(StoreTutor::getStoreId, storeTutor.getStoreId()).notIn(StoreTutor::getStoreTutorId, storeTutor.getStoreTutorId())),
                "手机号已被其他用户使用");

        SysUser user = sysUserService.selectUserById(storeTutor.getLoginUserId());
        user.setSex(storeTutor.getSex());
        user.setPhonenumber(storeTutor.getMobile());
        user.setUpdateTime(DateUtils.getNowDate());
        user.setUpdateBy(SecurityUtils.getUsername());
        user.setRoleIds(storeTutor.getRoleIds());
        sysUserService.updateUser(user);

        SecurityUtils.fillUpdateUser(storeTutor);
        storeTutor.setWorkStatus(null);
        return storeTutorMapper.updateStoreTutor(storeTutor);
    }

    /**
     * 批量删除门店助教
     *
     * @param storeTutorIds 需要删除的门店助教主键
     * @return 结果
     */
    @Override
    public int deleteStoreTutorByStoreTutorIds(Long[] storeTutorIds) {
        return storeTutorMapper.deleteStoreTutorByStoreTutorIds(storeTutorIds);
    }

    /**
     * 删除门店助教信息
     *
     * @param storeTutorId 门店助教主键
     * @return 结果
     */
    @Override
    public int deleteStoreTutorByStoreTutorId(Long storeTutorId) {
        return storeTutorMapper.deleteStoreTutorByStoreTutorId(storeTutorId);
    }
}
