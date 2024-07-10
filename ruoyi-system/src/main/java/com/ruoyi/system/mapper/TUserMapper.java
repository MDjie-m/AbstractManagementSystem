package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.TUser;

import java.util.List;

/**
 * 用户Mapper接口
 * 
 * @author tz
 * @date 2024-07-09
 */
public interface TUserMapper extends BaseMapper<TUser>
{
    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    public TUser selectTUserById(String id);

    /**
     * 查询用户列表
     * 
     * @param tUser 用户
     * @return 用户集合
     */
    public List<TUser> selectTUserList(TUser tUser);

    /**
     * 新增用户
     * 
     * @param tUser 用户
     * @return 结果
     */
    public int insertTUser(TUser tUser);

    /**
     * 修改用户
     * 
     * @param tUser 用户
     * @return 结果
     */
    public int updateTUser(TUser tUser);

    /**
     * 删除用户
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteTUserById(String id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTUserByIds(String[] ids);
}
