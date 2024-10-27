package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FjxUserPurchassesMapper;
import com.ruoyi.system.domain.FjxUserPurchasses;
import com.ruoyi.system.service.IFjxUserPurchassesService;

/**
 * 用户购买商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@Service
public class FjxUserPurchassesServiceImpl implements IFjxUserPurchassesService 
{
    @Autowired
    private FjxUserPurchassesMapper fjxUserPurchassesMapper;

    /**
     * 查询用户购买商品
     * 
     * @param id 用户购买商品主键
     * @return 用户购买商品
     */
    @Override
    public FjxUserPurchasses selectFjxUserPurchassesById(String id)
    {
        return fjxUserPurchassesMapper.selectFjxUserPurchassesById(id);
    }

    /**
     * 查询用户购买商品列表
     * 
     * @param fjxUserPurchasses 用户购买商品
     * @return 用户购买商品
     */
    @Override
    public List<FjxUserPurchasses> selectFjxUserPurchassesList(FjxUserPurchasses fjxUserPurchasses)
    {
        return fjxUserPurchassesMapper.selectFjxUserPurchassesList(fjxUserPurchasses);
    }

    /**
     * 新增用户购买商品
     * 
     * @param fjxUserPurchasses 用户购买商品
     * @return 结果
     */
    @Override
    public int insertFjxUserPurchasses(FjxUserPurchasses fjxUserPurchasses)
    {
        return fjxUserPurchassesMapper.insertFjxUserPurchasses(fjxUserPurchasses);
    }

    /**
     * 修改用户购买商品
     * 
     * @param fjxUserPurchasses 用户购买商品
     * @return 结果
     */
    @Override
    public int updateFjxUserPurchasses(FjxUserPurchasses fjxUserPurchasses)
    {
        return fjxUserPurchassesMapper.updateFjxUserPurchasses(fjxUserPurchasses);
    }

    /**
     * 批量删除用户购买商品
     * 
     * @param ids 需要删除的用户购买商品主键
     * @return 结果
     */
    @Override
    public int deleteFjxUserPurchassesByIds(String[] ids)
    {
        return fjxUserPurchassesMapper.deleteFjxUserPurchassesByIds(ids);
    }

    /**
     * 删除用户购买商品信息
     * 
     * @param id 用户购买商品主键
     * @return 结果
     */
    @Override
    public int deleteFjxUserPurchassesById(String id)
    {
        return fjxUserPurchassesMapper.deleteFjxUserPurchassesById(id);
    }
}
