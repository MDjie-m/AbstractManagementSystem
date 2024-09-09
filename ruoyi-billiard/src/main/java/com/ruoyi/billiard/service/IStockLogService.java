package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.StockLog;

/**
 * 库存日志Service接口
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
public interface IStockLogService 
{
    /**
     * 查询库存日志
     * 
     * @param stockLogId 库存日志主键
     * @return 库存日志
     */
    public StockLog selectStockLogByStockLogId(Long stockLogId);

    /**
     * 查询库存日志列表
     * 
     * @param stockLog 库存日志
     * @return 库存日志集合
     */
    public List<StockLog> selectStockLogList(StockLog stockLog);

    /**
     * 新增库存日志
     * 
     * @param stockLog 库存日志
     * @return 结果
     */
    public int insertStockLog(StockLog stockLog);


}
