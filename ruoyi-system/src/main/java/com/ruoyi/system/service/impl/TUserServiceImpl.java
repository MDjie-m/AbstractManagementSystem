package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.aspectj.QueryAspect;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TUser;
import com.ruoyi.system.mapper.TUserMapper;
import com.ruoyi.system.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户Service业务层处理
 * 
 * @author tz
 * @date 2024-07-10
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService
{
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public TUser selectTUserById(String id)
    {
        return tUserMapper.selectById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param tUser 用户
     * @return 用户
     */
    @Override
    public List<TUser> selectTUserList(TUser tUser)
    {
        QueryWrapper queryWrapper = new QueryWrapper<>(new TUser());
        QueryAspect.wrapper(queryWrapper, tUser);
        return  tUserMapper.selectList(queryWrapper);
    }

    /**
     * 新增用户
     * 
     * @param tUser 用户
     * @return 结果
     */
    @Override
    public int insertTUser(TUser tUser)
    {
        tUser.setCreateTime(DateUtils.getNowDate());
        return tUserMapper.insert(tUser);
    }

    /**
     * 修改用户
     * 
     * @param tUser 用户
     * @return 结果
     */
    @Override
    public int updateTUser(TUser tUser)
    {
        tUser.setUpdateTime(DateUtils.getNowDate());
        return tUserMapper.updateById(tUser);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteTUserByIds(String[] ids)
    {
        return tUserMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteTUserById(String id)
    {
        return tUserMapper.deleteById(id);
    }
}
