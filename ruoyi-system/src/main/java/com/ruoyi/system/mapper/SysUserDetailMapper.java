package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.entity.SysUserDetail;

/**
 * 用户详细信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
public interface SysUserDetailMapper 
{
    /**
     * 查询用户详细信息
     * 
     * @param userDetailsId 用户详细信息主键
     * @return 用户详细信息
     */
    public SysUserDetail selectSysUserDetailByUserDetailsId(String userDetailsId);

    /**
     * 查询用户详细信息列表
     * 
     * @param sysUserDetail 用户详细信息
     * @return 用户详细信息集合
     */
    public List<SysUserDetail> selectSysUserDetailList(SysUserDetail sysUserDetail);

    /**
     * 新增用户详细信息
     * 
     * @param sysUserDetail 用户详细信息
     * @return 结果
     */
    public int insertSysUserDetail(SysUserDetail sysUserDetail);

    /**
     * 修改用户详细信息
     * 
     * @param sysUserDetail 用户详细信息
     * @return 结果
     */
    public int updateSysUserDetail(SysUserDetail sysUserDetail);

    /**
     * 删除用户详细信息
     * 
     * @param userDetailsId 用户详细信息主键
     * @return 结果
     */
    public int deleteSysUserDetailByUserDetailsId(String userDetailsId);

    /**
     * 批量删除用户详细信息
     * 
     * @param userDetailsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserDetailByUserDetailsIds(String[] userDetailsIds);


    public int insertBoth(SysUser sysUser);
}



