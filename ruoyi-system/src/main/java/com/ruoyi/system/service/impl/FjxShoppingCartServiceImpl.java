package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FjxShoppingCartMapper;
import com.ruoyi.system.domain.FjxShoppingCart;
import com.ruoyi.system.service.IFjxShoppingCartService;

/**
 * 购物车Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@Service
public class FjxShoppingCartServiceImpl implements IFjxShoppingCartService 
{
    @Autowired
    private FjxShoppingCartMapper fjxShoppingCartMapper;

    /**
     * 查询购物车
     * 
     * @param id 购物车主键
     * @return 购物车
     */
    @Override
    public FjxShoppingCart selectFjxShoppingCartById(String id)
    {
        return fjxShoppingCartMapper.selectFjxShoppingCartById(id);
    }

    /**
     * 查询购物车列表
     * 
     * @param fjxShoppingCart 购物车
     * @return 购物车
     */
    @Override
    public List<FjxShoppingCart> selectFjxShoppingCartList(FjxShoppingCart fjxShoppingCart)
    {
        return fjxShoppingCartMapper.selectFjxShoppingCartList(fjxShoppingCart);
    }

    /**
     * 新增购物车
     * 
     * @param fjxShoppingCart 购物车
     * @return 结果
     */
    @Override
    public int insertFjxShoppingCart(FjxShoppingCart fjxShoppingCart)
    {
        return fjxShoppingCartMapper.insertFjxShoppingCart(fjxShoppingCart);
    }

    /**
     * 修改购物车
     * 
     * @param fjxShoppingCart 购物车
     * @return 结果
     */
    @Override
    public int updateFjxShoppingCart(FjxShoppingCart fjxShoppingCart)
    {
        return fjxShoppingCartMapper.updateFjxShoppingCart(fjxShoppingCart);
    }

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的购物车主键
     * @return 结果
     */
    @Override
    public int deleteFjxShoppingCartByIds(String[] ids)
    {
        return fjxShoppingCartMapper.deleteFjxShoppingCartByIds(ids);
    }

    /**
     * 删除购物车信息
     * 
     * @param id 购物车主键
     * @return 结果
     */
    @Override
    public int deleteFjxShoppingCartById(String id)
    {
        return fjxShoppingCartMapper.deleteFjxShoppingCartById(id);
    }
}
