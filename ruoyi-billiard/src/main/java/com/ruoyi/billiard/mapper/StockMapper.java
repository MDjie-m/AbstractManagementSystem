package com.ruoyi.billiard.mapper;

import java.util.List;

import com.ruoyi.billiard.domain.Stock;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 库存Mapper接口
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@Mapper
public interface StockMapper extends MyBaseMapper<Stock> {
    /**
     * 查询库存
     *
     * @param stockId 库存主键
     * @return 库存
     */
    public Stock selectStockByStockId(Long stockId);

    /**
     * 查询库存列表
     *
     * @param stock 库存
     * @return 库存集合
     */
    public List<Stock> selectStockList(Stock stock);

    public List<Stock> selectGoodsStockList(Stock stock);


    /**
     * 删除库存
     *
     * @param stockId 库存主键
     * @return 结果
     */
    public int deleteStockByStockId(Long stockId);

    /**
     * 批量删除库存
     *
     * @param stockIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockByStockIds(Long[] stockIds);


}
