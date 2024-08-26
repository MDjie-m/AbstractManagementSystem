package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserDetailMapper;
import com.ruoyi.common.core.domain.entity.SysUserDetail;
import com.ruoyi.system.service.ISysUserDetailService;

/**
 * 用户详细信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@Service
public class SysUserDetailServiceImpl implements ISysUserDetailService 
{
    @Autowired
    private SysUserDetailMapper sysUserDetailMapper;

    /**
     * 查询用户详细信息
     * 
     * @param userDetailsId 用户详细信息主键
     * @return 用户详细信息
     */
    @Override
    public SysUserDetail selectSysUserDetailByUserDetailsId(String userDetailsId)
    {
        return sysUserDetailMapper.selectSysUserDetailByUserDetailsId(userDetailsId);
    }

    /**
     * 查询用户详细信息列表
     * 
     * @param sysUserDetail 用户详细信息
     * @return 用户详细信息
     */
    @Override
    public List<SysUserDetail> selectSysUserDetailList(SysUserDetail sysUserDetail)
    {
        return sysUserDetailMapper.selectSysUserDetailList(sysUserDetail);
    }

    /**
     * 新增用户详细信息
     * 
     * @param sysUserDetail 用户详细信息
     * @return 结果
     */
    @Override
    public int insertSysUserDetail(SysUserDetail sysUserDetail)
    {
        return sysUserDetailMapper.insertSysUserDetail(sysUserDetail);
    }

    /**
     * 修改用户详细信息
     * 
     * @param sysUserDetail 用户详细信息
     * @return 结果
     */
    @Override
    public int updateSysUserDetail(SysUserDetail sysUserDetail)
    {
        return sysUserDetailMapper.updateSysUserDetail(sysUserDetail);
    }

    /**
     * 批量删除用户详细信息
     * 
     * @param userDetailsIds 需要删除的用户详细信息主键
     * @return 结果
     */
    @Override
    public int deleteSysUserDetailByUserDetailsIds(String[] userDetailsIds)
    {
        return sysUserDetailMapper.deleteSysUserDetailByUserDetailsIds(userDetailsIds);
    }

    /**
     * 删除用户详细信息信息
     * 
     * @param userDetailsId 用户详细信息主键
     * @return 结果
     */
    @Override
    public int deleteSysUserDetailByUserDetailsId(String userDetailsId)
    {
        return sysUserDetailMapper.deleteSysUserDetailByUserDetailsId(userDetailsId);
    }
}
