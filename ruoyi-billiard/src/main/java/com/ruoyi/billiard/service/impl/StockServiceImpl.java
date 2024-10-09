package com.ruoyi.billiard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ruoyi.billiard.domain.Goods;
import com.ruoyi.billiard.domain.GoodsCategory;
import com.ruoyi.billiard.domain.StockLog;
import com.ruoyi.billiard.enums.StockChangeType;
import com.ruoyi.billiard.mapper.GoodsCategoryMapper;
import com.ruoyi.billiard.mapper.GoodsMapper;
import com.ruoyi.billiard.mapper.StockLogMapper;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ExceptionCodeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.ArrayUtil;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StockMapper;
import com.ruoyi.billiard.domain.Stock;
import com.ruoyi.billiard.service.IStockService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 库存Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@Service
@Slf4j
public class StockServiceImpl implements IStockService {
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StockLogMapper stockLogMapper;

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

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
        return stockMapper.insert(stock);
    }

    @Transactional(rollbackFor = Exception.class)
    public int editStock(StockLog req) {
        updateStock(req.getStoreId(), req.getGoodsId(), req.getChangeCount(), StockChangeType.valueOf(req.getChangeType()), req.getRemark(), null);
        return 1;
    }

    /**
     * 修改库存
     *
     * @return 结果
     */

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updateStock(Long storeId, Long goodsId, Long changeCount, StockChangeType changeType, String remark, Long orderId) {
        AssertUtil.notNullOrEmpty(changeType, ExceptionCodeEnum.CHECK_STOCK_ERROR, "操作类型错误");
        AssertUtil.notNullOrEmpty(changeCount, ExceptionCodeEnum.CHECK_STOCK_ERROR, "数量不能为空");
        AssertUtil.isTrue(!Objects.equals(changeCount, 0L), ExceptionCodeEnum.CHECK_STOCK_ERROR, "数量不能为0");
        Stock stock = stockMapper.selectOne(stockMapper.query().eq(Stock::getGoodsId, goodsId).eq(Stock::getStoreId, storeId));
        Goods goods = goodsMapper.selectById(goodsId);
        AssertUtil.notNullOrEmpty(goods, ExceptionCodeEnum.CHECK_STOCK_ERROR, "商品不存在");
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
        if (!Objects.equals(StockChangeType.CHECK, changeType)) {
            changeCount = Math.abs(changeCount);
        }
        if (Objects.equals(StockChangeType.OUT, changeType)) {
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
        addChangeLog(goodsId, changeCount, changeType, currentCount, stock.getStockId(), remark, orderId);

        updateWrapper.lambda()
                .set(Stock::getUpdateBy, user.getRealName())
                .set(Stock::getUpdateById, user.getUserId())
                .eq(Stock::getStockId, stock.getStockId())
                .eq(Stock::getTotal, currentCount);
        int res = stockMapper.update(null, updateWrapper);
        AssertUtil.isTrue(res > 0, ExceptionCodeEnum.CHECK_STOCK_ERROR,
                StringUtils.format("操作失败，{}库存为：{}。", goods.getGoodsName(), currentCount));
        return true;
    }

    private void addChangeLog(Long goodsId, Long changeCount, StockChangeType changeType, Long currentCount, Long stockId, String remark, Long orderId) {
        StockLog log = StockLog.builder()
                .beforeCount(currentCount)
                .currentCount(currentCount + changeCount)
                .changeType(changeType.getValue())
                .changeCount(changeCount)
                .goodsId(goodsId)
                .orderId(orderId)
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

    @Override
    public List<String> checkStock(List<StockLog> list) {
        List<StockLog> checkList = list.stream().filter(p -> Objects.nonNull(p.getChangeCount()) && !Objects.equals(p.getChangeCount(), 0L)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        List<String> msgList = Lists.newArrayList();
        for (StockLog stockLog : checkList) {
            try {
                SpringUtils.getBean(StockServiceImpl.class).editStock(stockLog);
            } catch (ServiceException e) {
                if (Objects.equals(e.getCode(), ExceptionCodeEnum.CHECK_STOCK_ERROR.getCode())) {
                    msgList.add(StringUtils.format("商品:【{}】{}", stockLog.getGoodsName(), e.getMessage()));
                } else {
                    msgList.add(StringUtils.format("商品:【{}】盘点失败.", stockLog.getGoodsName()));
                }
                log.error("盘点库存异常,goodsId:{}", stockLog.getGoodsId(), e);

            } catch (Exception e) {
                log.error("盘点库存异常,goodsId:{}", stockLog.getGoodsId(), e);
                msgList.add(StringUtils.format("商品:【{}】盘点失败，", stockLog.getGoodsName()));
            }
        }
        if (CollectionUtils.isNotEmpty(msgList)) {
            msgList.add(0, "其他商品已盘点成功,以下是盘点失败的商品。");
        }
        return msgList;
    }

    @Override
    public List<GoodsCategory> getCategoryStock(Stock stock) {
        Map<Long, List<Stock>> stocks = ArrayUtil.groupBy(stockMapper.selectStockList(stock), Stock::getCategoryId);
        List<GoodsCategory> categories = goodsCategoryMapper.selectGoodsCategoryList(GoodsCategory.builder().storeId(stock.getStoreId())
                .build());
        categories.forEach(p -> {
            p.setGoodsStocks(stocks.getOrDefault(p.getGoodsCategoryId(), Lists.newArrayList()));
        });
        return categories;
    }
}
