package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FjxShoppingCart;

/**
 * 购物车Service接口
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public interface IFjxShoppingCartService 
{
    /**
     * 查询购物车
     * 
     * @param id 购物车主键
     * @return 购物车
     */
    public FjxShoppingCart selectFjxShoppingCartById(String id);

    /**
     * 查询购物车列表
     * 
     * @param fjxShoppingCart 购物车
     * @return 购物车集合
     */
    public List<FjxShoppingCart> selectFjxShoppingCartList(FjxShoppingCart fjxShoppingCart);

    /**
     * 新增购物车
     * 
     * @param fjxShoppingCart 购物车
     * @return 结果
     */
    public int insertFjxShoppingCart(FjxShoppingCart fjxShoppingCart);

    /**
     * 修改购物车
     * 
     * @param fjxShoppingCart 购物车
     * @return 结果
     */
    public int updateFjxShoppingCart(FjxShoppingCart fjxShoppingCart);

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的购物车主键集合
     * @return 结果
     */
    public int deleteFjxShoppingCartByIds(String[] ids);

    /**
     * 删除购物车信息
     * 
     * @param id 购物车主键
     * @return 结果
     */
    public int deleteFjxShoppingCartById(String id);
}
