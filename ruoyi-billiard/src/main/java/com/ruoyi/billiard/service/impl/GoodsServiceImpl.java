package com.ruoyi.billiard.service.impl;

import java.util.List;

import com.ruoyi.billiard.domain.GoodsCategory;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.GoodsMapper;
import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.service.IGoodsService;

/**
 * 商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-08
 */
@Service
public class GoodsServiceImpl implements IGoodsService 
{
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询商品
     * 
     * @param goodsId 商品主键
     * @return 商品
     */
    @Override
    public Goods selectGoodsByGoodsId(Long goodsId)
    {
        return goodsMapper.selectById(goodsId);
    }

    /**
     * 查询商品列表
     * 
     * @param goods 商品
     * @return 商品
     */
    @Override
    public List<Goods> selectGoodsList(Goods goods)
    {
        return goodsMapper.selectGoodsList(goods);
    }

    /**
     * 新增商品
     * 
     * @param goods 商品
     * @return 结果
     */
    @Override
    public int insertGoods(Goods goods)
    {
        AssertUtil.isTrue(!goodsMapper.exists( goodsMapper.query().eq(Goods::getGoodsName,goods.getGoodsName())
                .eq(Goods::getStoreId,goods.getStoreId())),"商品名称重复.");

        goods.setGoodsId(IdUtils.singleNextId());
        SecurityUtils.fillCreateUser(goods);
        return goodsMapper.insertGoods(goods);
    }

    /**
     * 修改商品
     * 
     * @param goods 商品
     * @return 结果
     */
    @Override
    public int updateGoods(Goods goods)
    {
        AssertUtil.isTrue(!goodsMapper.exists( goodsMapper.query().eq(Goods::getGoodsName,goods.getGoodsName())
                .eq(Goods::getStoreId,goods.getStoreId()).notIn(Goods::getGoodsId,goods.getGoodsId())),"商品名称重复.");
        SecurityUtils.fillUpdateUser(goods);
        return goodsMapper.updateGoods(goods);
    }

    /**
     * 批量删除商品
     * 
     * @param goodsIds 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteGoodsByGoodsIds(Long[] goodsIds)
    {
        return goodsMapper.deleteGoodsByGoodsIds(goodsIds);
    }

    /**
     * 删除商品信息
     * 
     * @param goodsId 商品主键
     * @return 结果
     */
    @Override
    public int deleteGoodsByGoodsId(Long goodsId)
    {
        return goodsMapper.deleteGoodsByGoodsId(goodsId);
    }
}
