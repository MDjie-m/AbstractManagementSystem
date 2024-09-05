package com.ruoyi.billiard.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
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


    /**
     * 查询门店员工
     * 
     * @param storeUserId 门店员工主键
     * @return 门店员工
     */
    @Override
    public StoreUser selectStoreUserByStoreUserId(Long storeUserId)
    {
        return storeUserMapper.selectStoreUserByStoreUserId(storeUserId);
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
        return storeUserMapper.selectStoreUserList(storeUser);
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

        storeUser.setCreateTime(DateUtils.getNowDate());
        int res= storeUserMapper.insertStoreUser(storeUser);
        SysUser sysUser=new SysUser();
        sysUser.setAvatar(storeUser.getUserImg());
        sysUser.setDeptId(100L);
        sysUser.setUserName(storeUser.getMobile());
        sysUser.setPhonenumber(storeUser.getMobile());
        sysUser.setNickName(storeUser.getRealName());

        sysUser.setPassword(SecurityUtils.encryptPassword(storeUser.getMobile()));
       // sysUserService.insertUser()
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
        storeUser.setUpdateTime(DateUtils.getNowDate());
        return storeUserMapper.updateStoreUser(storeUser);
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
