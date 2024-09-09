package com.ruoyi.billiard.service.impl;

import java.util.List;
import java.util.Objects;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.domain.StockLog;
import com.ruoyi.billiard.enums.StockChangeType;
import com.ruoyi.billiard.mapper.GoodsMapper;
import com.ruoyi.billiard.mapper.StockLogMapper;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StockMapper;
import com.ruoyi.billiard.domain.Stock;
import com.ruoyi.billiard.service.IStockService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@Service
public class StockServiceImpl implements IStockService {
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StockLogMapper stockLogMapper;

    /**
     * 查询库存
     *
     * @param stockId 库存主键
     * @return 库存
     */
    @Override
    public Stock selectStockByStockId(Long stockId) {
        return stockMapper.selectById(stockId);
    }

    /**
     * 查询库存列表
     *
     * @param stock 库存
     * @return 库存
     */
    @Override
    public List<Stock> selectStockList(Stock stock) {
        return stockMapper.selectStockList(stock);
    }

    /**
     * 新增库存
     *
     * @param stock 库存
     * @return 结果
     */
    @Override
    public int insertStock(Stock stock) {
        SecurityUtils.fillCreateUser(stock);
        stock.setStockId(IdUtils.singleNextId());
        return stockMapper.insertStock(stock);
    }

    @Transactional(rollbackFor = Exception.class)
    public int editStock(StockLog req) {
        updateStock(req.getStoreId(), req.getGoodsId(), req.getChangeCount(), StockChangeType.valueOf(req.getChangeType()), req.getRemark());
        return 1;
    }

    /**
     * 修改库存
     *
     * @return 结果
     */

    private boolean updateStock(Long storeId, Long goodsId, Long changeCount, StockChangeType changeType, String remark) {
        AssertUtil.notNullOrEmpty(changeType, "操作类型错误");
        AssertUtil.notNullOrEmpty(changeCount, "数量不能为空");
        AssertUtil.isTrue(!Objects.equals(changeCount, 0L), "数量不能为0");
        Stock stock = stockMapper.selectOne(stockMapper.query().eq(Stock::getGoodsId, goodsId).eq(Stock::getStoreId, storeId));
        Goods goods = goodsMapper.selectById(goodsId);
        AssertUtil.notNullOrEmpty(goods, "商品不存在");
        if (Objects.isNull(stock)) {
            stock = Stock.builder().stockId(IdUtils.singleNextId())
                    .total(0L)
                    .totalIn(0L)
                    .totalOut(0L)
                    .goodsId(goodsId)
                    .storeId(storeId)
                    .goodsName(goods.getGoodsName())
                    .build();
            SecurityUtils.fillCreateUser(stock);
            stockMapper.insert(stock);
        }
        if (!Objects.equals(StockChangeType.CHECK , changeType)) {
            changeCount = Math.abs(changeCount);
        }
        if (Objects.equals(StockChangeType.OUT , changeType)) {
            changeCount = -Math.abs(changeCount);
        }
        Long currentCount = stock.getTotal();

        LoginUser user = SecurityUtils.getLoginUser();

        UpdateWrapper<Stock> updateWrapper = new UpdateWrapper<>();
        if (changeCount >= 0L) {
            updateWrapper.setSql(StrUtil.format("total = total + {},total_in=total_in+{}", changeCount, changeCount));
        } else {
            updateWrapper.setSql(StrUtil.format("total = total - {},total_out=total_out+{}", Math.abs(changeCount), Math.abs(changeCount)));
            updateWrapper.last(StrUtil.format(" and (total - {} >= 0)   ", Math.abs(changeCount)));
        }
        addChangeLog(goodsId, changeCount, changeType, currentCount, stock.getStockId(), remark);

        updateWrapper.lambda()
                .set(Stock::getUpdateBy, user.getRealName())
                .set(Stock::getUpdateById, user.getUserId())
                .eq(Stock::getStockId, stock.getStockId())
                .eq(Stock::getTotal, currentCount);
        int res = stockMapper.update(null, updateWrapper);
        AssertUtil.isTrue(res > 0,
                StringUtils.format("商品:[{}]{}失败，当前库存为：{}。", goods.getGoodsName(),   changeType.getDesc(),currentCount));
        return true;
    }

    private void addChangeLog(Long goodsId, Long changeCount, StockChangeType changeType, Long currentCount, Long stockId, String remark) {
        StockLog log = StockLog.builder()
                .beforeCount(currentCount)
                .currentCount(currentCount + changeCount)
                .changeType(changeType.getValue())
                .changeCount(changeCount)
                .goodsId(goodsId)
                .stockId(stockId).build();
        log.setRemark(remark);
        SecurityUtils.fillCreateUser(log);
        stockLogMapper.insert(log);
    }

    /**
     * 批量删除库存
     *
     * @param stockIds 需要删除的库存主键
     * @return 结果
     */
    @Override
    public int deleteStockByStockIds(Long[] stockIds) {
        return stockMapper.deleteStockByStockIds(stockIds);
    }

    /**
     * 删除库存信息
     *
     * @param stockId 库存主键
     * @return 结果
     */
    @Override
    public int deleteStockByStockId(Long stockId) {
        return stockMapper.deleteStockByStockId(stockId);
    }
}
