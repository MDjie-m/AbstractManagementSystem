package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StockLogMapper;
import com.ruoyi.billiard.domain.StockLog;
import com.ruoyi.billiard.service.IStockLogService;

/**
 * 库存日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
@Service
public class StockLogServiceImpl implements IStockLogService 
{
    @Autowired
    private StockLogMapper stockLogMapper;

    /**
     * 查询库存日志
     * 
     * @param stockLogId 库存日志主键
     * @return 库存日志
     */
    @Override
    public StockLog selectStockLogByStockLogId(Long stockLogId)
    {
        return stockLogMapper.selectById(stockLogId);
    }

    /**
     * 查询库存日志列表
     * 
     * @param stockLog 库存日志
     * @return 库存日志
     */
    @Override
    public List<StockLog> selectStockLogList(StockLog stockLog)
    {
        return stockLogMapper.selectStockLogList(stockLog);
    }

    /**
     * 新增库存日志
     * 
     * @param stockLog 库存日志
     * @return 结果
     */
    @Override
    public int insertStockLog(StockLog stockLog)
    {
        SecurityUtils.fillCreateUser(stockLog);
        stockLog.setStockLogId(IdUtils.singleNextId());
        return stockLogMapper.insert(stockLog);
    }


}
