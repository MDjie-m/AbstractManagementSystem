package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FjxUserPurchasses;

/**
 * 用户购买商品Service接口
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public interface IFjxUserPurchassesService 
{
    /**
     * 查询用户购买商品
     * 
     * @param id 用户购买商品主键
     * @return 用户购买商品
     */
    public FjxUserPurchasses selectFjxUserPurchassesById(String id);

    /**
     * 查询用户购买商品列表
     * 
     * @param fjxUserPurchasses 用户购买商品
     * @return 用户购买商品集合
     */
    public List<FjxUserPurchasses> selectFjxUserPurchassesList(FjxUserPurchasses fjxUserPurchasses);

    /**
     * 新增用户购买商品
     * 
     * @param fjxUserPurchasses 用户购买商品
     * @return 结果
     */
    public int insertFjxUserPurchasses(FjxUserPurchasses fjxUserPurchasses);

    /**
     * 修改用户购买商品
     * 
     * @param fjxUserPurchasses 用户购买商品
     * @return 结果
     */
    public int updateFjxUserPurchasses(FjxUserPurchasses fjxUserPurchasses);

    /**
     * 批量删除用户购买商品
     * 
     * @param ids 需要删除的用户购买商品主键集合
     * @return 结果
     */
    public int deleteFjxUserPurchassesByIds(String[] ids);

    /**
     * 删除用户购买商品信息
     * 
     * @param id 用户购买商品主键
     * @return 结果
     */
    public int deleteFjxUserPurchassesById(String id);
}
