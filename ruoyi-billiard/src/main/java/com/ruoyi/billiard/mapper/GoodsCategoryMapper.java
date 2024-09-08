package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.GoodsCategory;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-08
 */
@Mapper
public interface GoodsCategoryMapper extends MyBaseMapper<GoodsCategory>
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
     * 删除商品分类
     * 
     * @param goodsCategoryId 商品分类主键
     * @return 结果
     */
    public int deleteGoodsCategoryByGoodsCategoryId(Long goodsCategoryId);

    /**
     * 批量删除商品分类
     * 
     * @param goodsCategoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsCategoryByGoodsCategoryIds(Long[] goodsCategoryIds);
}
