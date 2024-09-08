package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.GoodsCategory;

/**
 * 商品分类Service接口
 * 
 * @author ruoyi
 * @date 2024-09-08
 */
public interface IGoodsCategoryService 
{
    /**
     * 查询商品分类
     * 
     * @param goodsCategoryId 商品分类主键
     * @return 商品分类
     */
    public GoodsCategory selectGoodsCategoryByGoodsCategoryId(Long goodsCategoryId);

    /**
     * 查询商品分类列表
     * 
     * @param goodsCategory 商品分类
     * @return 商品分类集合
     */
    public List<GoodsCategory> selectGoodsCategoryList(GoodsCategory goodsCategory);

    /**
     * 新增商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    public int insertGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 修改商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    public int updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 批量删除商品分类
     * 
     * @param goodsCategoryIds 需要删除的商品分类主键集合
     * @return 结果
     */
    public int deleteGoodsCategoryByGoodsCategoryIds(Long[] goodsCategoryIds);

    /**
     * 删除商品分类信息
     * 
     * @param goodsCategoryId 商品分类主键
     * @return 结果
     */
    public int deleteGoodsCategoryByGoodsCategoryId(Long goodsCategoryId);
}
