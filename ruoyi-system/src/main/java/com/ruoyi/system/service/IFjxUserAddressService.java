package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FjxUserAddress;

/**
 * 用户地址Service接口
 * 
 * @author ruoyi
 * @date 2024-11-03
 */
public interface IFjxUserAddressService 
{
    /**
     * 查询用户地址
     * 
     * @param id 用户地址主键
     * @return 用户地址
     */
    public FjxUserAddress selectFjxUserAddressById(Long id);

    /**
     * 查询用户地址列表
     * 
     * @param fjxUserAddress 用户地址
     * @return 用户地址集合
     */
    public List<FjxUserAddress> selectFjxUserAddressList(FjxUserAddress fjxUserAddress);

    /**
     * 新增用户地址
     * 
     * @param fjxUserAddress 用户地址
     * @return 结果
     */
    public int insertFjxUserAddress(FjxUserAddress fjxUserAddress);

    /**
     * 修改用户地址
     * 
     * @param fjxUserAddress 用户地址
     * @return 结果
     */
    public int updateFjxUserAddress(FjxUserAddress fjxUserAddress);

    /**
     * 批量删除用户地址
     * 
     * @param ids 需要删除的用户地址主键集合
     * @return 结果
     */
    public int deleteFjxUserAddressByIds(Long[] ids);

    /**
     * 删除用户地址信息
     * 
     * @param id 用户地址主键
     * @return 结果
     */
    public int deleteFjxUserAddressById(Long id);
}
