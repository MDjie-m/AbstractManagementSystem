package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.StockLog;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存日志Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
@Mapper
public interface StockLogMapper extends MyBaseMapper<StockLog>
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
     * 删除库存日志
     * 
     * @param stockLogId 库存日志主键
     * @return 结果
     */
    public int deleteStockLogByStockLogId(Long stockLogId);

    /**
     * 批量删除库存日志
     * 
     * @param stockLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockLogByStockLogIds(Long[] stockLogIds);
}
