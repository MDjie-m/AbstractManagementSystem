package com.ruoyi.billiard.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.mapper.GoodsMapper;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.GoodsCategoryMapper;
import com.ruoyi.billiard.domain.GoodsCategory;
import com.ruoyi.billiard.service.IGoodsCategoryService;

import javax.annotation.Resource;

/**
 * 商品分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-08
 */
@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService 
{
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 查询商品分类
     * 
     * @param goodsCategoryId 商品分类主键
     * @return 商品分类
     */
    @Override
    public GoodsCategory selectGoodsCategoryByGoodsCategoryId(Long goodsCategoryId)
    {
        return goodsCategoryMapper.selectById(goodsCategoryId);
    }

    /**
     * 查询商品分类列表
     * 
     * @param goodsCategory 商品分类
     * @return 商品分类
     */
    @Override
    public List<GoodsCategory> selectGoodsCategoryList(GoodsCategory goodsCategory)
    {
        return goodsCategoryMapper.selectGoodsCategoryList(goodsCategory);
    }

    /**
     * 新增商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    @Override
    public int insertGoodsCategory(GoodsCategory goodsCategory)
    {
        AssertUtil.isTrue(!goodsCategoryMapper.exists( goodsCategoryMapper.query().eq(GoodsCategory::getGoodsCategoryName,goodsCategory.getGoodsCategoryName())
                .eq(GoodsCategory::getStoreId,goodsCategory.getStoreId())),"商品分类名称重复.");

        goodsCategory.setCreateBy(SecurityUtils.getUsername());
        goodsCategory.setUpdateBy(SecurityUtils.getUsername());
        goodsCategory.setGoodsCategoryId(IdUtils.singleNextId());
        goodsCategory.setCreateTime(DateUtils.getNowDate());
        return goodsCategoryMapper.insertGoodsCategory(goodsCategory);
    }

    /**
     * 修改商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    @Override
    public int updateGoodsCategory(GoodsCategory goodsCategory)
    {
        AssertUtil.isTrue(!goodsCategoryMapper.exists( goodsCategoryMapper.query().eq(GoodsCategory::getGoodsCategoryName,goodsCategory.getGoodsCategoryName())
                .eq(GoodsCategory::getStoreId,goodsCategory.getStoreId())
                .notIn(GoodsCategory::getGoodsCategoryId,goodsCategory.getGoodsCategoryId())),"商品分类名称重复.");
        goodsCategory.setUpdateBy(SecurityUtils.getUsername());
        goodsCategory.setUpdateTime(DateUtils.getNowDate());
        return goodsCategoryMapper.updateGoodsCategory(goodsCategory);
    }

    /**
     * 批量删除商品分类
     * 
     * @param goodsCategoryIds 需要删除的商品分类主键
     * @return 结果
     */
    @Override
    public int deleteGoodsCategoryByGoodsCategoryIds(Long[] goodsCategoryIds)
    {
        AssertUtil.isTrue(!goodsMapper.existsIn(Goods::getCategoryId, Arrays.asList(goodsCategoryIds)),"分类正在被使用，无法删除.");
        return goodsCategoryMapper.deleteGoodsCategoryByGoodsCategoryIds(goodsCategoryIds);
    }

    /**
     * 删除商品分类信息
     * 
     * @param goodsCategoryId 商品分类主键
     * @return 结果
     */
    @Override
    public int deleteGoodsCategoryByGoodsCategoryId(Long goodsCategoryId)
    {
        return goodsCategoryMapper.deleteGoodsCategoryByGoodsCategoryId(goodsCategoryId);
    }
}
