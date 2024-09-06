package com.ruoyi.billiard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
public class StoreUserServiceImpl implements IStoreUserService 
{
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
    public StoreUser selectStoreUserByStoreUserId(Long storeUserId)
    {
        StoreUser user= storeUserMapper.selectById(storeUserId);
        if(Objects.nonNull(user)){
            user.setRoleIds(storeUserMapper.selectRoleIds(user.getLoginUserId()));
        }
        return  user;
    }

    /**
     * 查询门店员工列表
     * 
     * @param storeUser 门店员工
     * @return 门店员工
     */
    @Override
    public List<StoreUser> selectStoreUserList(StoreUser storeUser)
    {

        List<StoreUser>  users= storeUserMapper.selectStoreUserList(storeUser);
        if(CollectionUtils.isEmpty(users)){
            return  users;
        }
        List<KeyValueVo<Long, Long>> roleIds=  storeUserMapper.selectRoleIdsByUserIds(users.stream().map(StoreUser::getLoginUserId).collect(Collectors.toList()));
        Map<Long,List<Long>> roleIdMap=   ArrayUtil.groupByValue(roleIds,KeyValueVo::getKey,KeyValueVo::getValue);
        users.forEach(u->{
            u.setRoleIds(roleIdMap.getOrDefault(u.getLoginUserId(), Lists.newArrayList()));
        });
        return  users;
    }

    /**
     * 新增门店员工
     * 
     * @param storeUser 门店员工
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertStoreUser(StoreUser storeUser)
    {
        AssertUtil.isTrue(!storeUserMapper.existsWithDelFlag(StoreUser::getMobile,storeUser.getMobile()),
                "手机号已被其他用户使用");
        storeUser.setCreateTime(DateUtils.getNowDate());


        SysUser sysUser=new SysUser();
        sysUser.setAvatar(storeUser.getUserImg());
        sysUser.setDeptId(100L);
        sysUser.setUserName(storeUser.getMobile());
        sysUser.setPhonenumber(storeUser.getMobile());
        sysUser.setNickName(storeUser.getRealName());
        sysUser.setPassword(SecurityUtils.encryptPassword(storeUser.getMobile()));
        sysUser.setRoleIds(storeUser.getRoleIds().toArray(new Long[0]));
        sysUser.setSex(storeUser.getSex());
        sysUserService.insertUser(sysUser);
        storeUser.setStoreUserId(IdUtils.singleNextId());
        storeUser.setLoginUserId(sysUser.getUserId());
        int res= storeUserMapper.insert(storeUser);
        return  res;

    }

    /**
     * 修改门店员工
     * 
     * @param storeUser 门店员工
     * @return 结果
     */
    @Override
    public int updateStoreUser(StoreUser storeUser)
    {
        AssertUtil.isTrue(!storeUserMapper.existsWithDelFlagExcludeId(StoreUser::getMobile,storeUser.getMobile(),
                        StoreUser::getStoreUserId,storeUser.getStoreUserId()),
                "手机号已被其他用户使用");
        storeUser.setUpdateTime(DateUtils.getNowDate());
        SysUser user=sysUserService.selectUserById(storeUser.getLoginUserId());
        user.setSex(storeUser.getSex());
        user.setNickName(storeUser.getRealName());
        user.setPhonenumber(storeUser.getMobile());
        user.setUserName(storeUser.getMobile());
        return storeUserMapper.updateById(storeUser);
    }

    /**
     * 批量删除门店员工
     * 
     * @param storeUserIds 需要删除的门店员工主键
     * @return 结果
     */
    @Override
    public int deleteStoreUserByStoreUserIds(Long[] storeUserIds)
    {
        return storeUserMapper.deleteStoreUserByStoreUserIds(storeUserIds);
    }

    /**
     * 删除门店员工信息
     * 
     * @param storeUserId 门店员工主键
     * @return 结果
     */
    @Override
    public int deleteStoreUserByStoreUserId(Long storeUserId)
    {
        return storeUserMapper.deleteStoreUserByStoreUserId(storeUserId);
    }
}
