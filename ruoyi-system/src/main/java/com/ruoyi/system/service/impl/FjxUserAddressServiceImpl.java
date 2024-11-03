package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FjxUserAddressMapper;
import com.ruoyi.system.domain.FjxUserAddress;
import com.ruoyi.system.service.IFjxUserAddressService;

/**
 * 用户地址Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-03
 */
@Service
public class FjxUserAddressServiceImpl implements IFjxUserAddressService 
{
    @Autowired
    private FjxUserAddressMapper fjxUserAddressMapper;

    /**
     * 查询用户地址
     * 
     * @param id 用户地址主键
     * @return 用户地址
     */
    @Override
    public FjxUserAddress selectFjxUserAddressById(Long id)
    {
        return fjxUserAddressMapper.selectFjxUserAddressById(id);
    }

    /**
     * 查询用户地址列表
     * 
     * @param fjxUserAddress 用户地址
     * @return 用户地址
     */
    @Override
    public List<FjxUserAddress> selectFjxUserAddressList(FjxUserAddress fjxUserAddress)
    {
        return fjxUserAddressMapper.selectFjxUserAddressList(fjxUserAddress);
    }

    /**
     * 新增用户地址
     * 
     * @param fjxUserAddress 用户地址
     * @return 结果
     */
    @Override
    public int insertFjxUserAddress(FjxUserAddress fjxUserAddress)
    {
        return fjxUserAddressMapper.insertFjxUserAddress(fjxUserAddress);
    }

    /**
     * 修改用户地址
     * 
     * @param fjxUserAddress 用户地址
     * @return 结果
     */
    @Override
    public int updateFjxUserAddress(FjxUserAddress fjxUserAddress)
    {
        return fjxUserAddressMapper.updateFjxUserAddress(fjxUserAddress);
    }

    /**
     * 批量删除用户地址
     * 
     * @param ids 需要删除的用户地址主键
     * @return 结果
     */
    @Override
    public int deleteFjxUserAddressByIds(Long[] ids)
    {
        return fjxUserAddressMapper.deleteFjxUserAddressByIds(ids);
    }

    /**
     * 删除用户地址信息
     * 
     * @param id 用户地址主键
     * @return 结果
     */
    @Override
    public int deleteFjxUserAddressById(Long id)
    {
        return fjxUserAddressMapper.deleteFjxUserAddressById(id);
    }
}
