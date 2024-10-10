package com.ruoyi.billiard.service;

import java.util.List;

import com.ruoyi.billiard.domain.GoodsCategory;
import com.ruoyi.billiard.domain.Stock;
import com.ruoyi.billiard.domain.StockLog;
import com.ruoyi.billiard.domain.vo.StockCheckRes;
import com.ruoyi.billiard.enums.StockChangeType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存Service接口
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
public interface IStockService 
{
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

    /**
     * 新增库存
     * 
     * @param stock 库存
     * @return 结果
     */
    public int insertStock(Stock stock);

    /**
     * 修改库存
     * 
     * @param stock 库存
     * @return 结果
     */
    public int editStock(StockLog stock);

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    boolean updateStock(Long storeId, Long goodsId, Long changeCount, StockChangeType changeType, String remark, Long orderId);

    /**
     * 批量删除库存
     * 
     * @param stockIds 需要删除的库存主键集合
     * @return 结果
     */
    public int deleteStockByStockIds(Long[] stockIds);

    /**
     * 删除库存信息
     * 
     * @param stockId 库存主键
     * @return 结果
     */
    public int deleteStockByStockId(Long stockId);

    List<String> checkStock(List<StockLog> list);
    StockCheckRes checkStockWithDetail(List<StockLog> list);

    List<GoodsCategory> getCategoryStock(Stock stock);
}
