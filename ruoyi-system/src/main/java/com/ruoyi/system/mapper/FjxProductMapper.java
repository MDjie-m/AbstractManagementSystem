package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FjxProduct;

/**
 * 商品Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public interface FjxProductMapper 
{
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    public FjxProduct selectFjxProductById(String id);

    /**
     * 查询商品列表
     * 
     * @param fjxProduct 商品
     * @return 商品集合
     */
    public List<FjxProduct> selectFjxProductList(FjxProduct fjxProduct);

    /**
     * 新增商品
     * 
     * @param fjxProduct 商品
     * @return 结果
     */
    public int insertFjxProduct(FjxProduct fjxProduct);

    /**
     * 修改商品
     * 
     * @param fjxProduct 商品
     * @return 结果
     */
    public int updateFjxProduct(FjxProduct fjxProduct);

    /**
     * 删除商品
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteFjxProductById(String id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFjxProductByIds(String[] ids);
}
