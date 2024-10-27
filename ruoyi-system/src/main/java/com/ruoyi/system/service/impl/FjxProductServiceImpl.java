package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FjxProductMapper;
import com.ruoyi.system.domain.FjxProduct;
import com.ruoyi.system.service.IFjxProductService;

/**
 * 商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@Service
public class FjxProductServiceImpl implements IFjxProductService 
{
    @Autowired
    private FjxProductMapper fjxProductMapper;

    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    @Override
    public FjxProduct selectFjxProductById(String id)
    {
        return fjxProductMapper.selectFjxProductById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param fjxProduct 商品
     * @return 商品
     */
    @Override
    public List<FjxProduct> selectFjxProductList(FjxProduct fjxProduct)
    {
        return fjxProductMapper.selectFjxProductList(fjxProduct);
    }

    /**
     * 新增商品
     * 
     * @param fjxProduct 商品
     * @return 结果
     */
    @Override
    public int insertFjxProduct(FjxProduct fjxProduct)
    {
        return fjxProductMapper.insertFjxProduct(fjxProduct);
    }

    /**
     * 修改商品
     * 
     * @param fjxProduct 商品
     * @return 结果
     */
    @Override
    public int updateFjxProduct(FjxProduct fjxProduct)
    {
        return fjxProductMapper.updateFjxProduct(fjxProduct);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteFjxProductByIds(String[] ids)
    {
        return fjxProductMapper.deleteFjxProductByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    @Override
    public int deleteFjxProductById(String id)
    {
        return fjxProductMapper.deleteFjxProductById(id);
    }
}
