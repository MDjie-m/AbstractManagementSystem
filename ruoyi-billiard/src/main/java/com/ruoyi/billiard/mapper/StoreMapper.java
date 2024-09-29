package com.ruoyi.billiard.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.OrderPay;
import com.ruoyi.billiard.domain.OrderRefund;
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.common.core.domain.model.Tuple;
import com.ruoyi.common.core.domain.model.Tuple3;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 门店Mapper接口
 *
 * @author ruoyi
 * @date 2024-09-05
 */
public interface StoreMapper extends MyBaseMapper<Store> {
    /**
     * 查询门店
     *
     * @param storeId 门店主键
     * @return 门店
     */
    public Store selectStoreByStoreId(Long storeId);

    /**
     * 查询门店列表
     *
     * @param store 门店
     * @return 门店集合
     */
    public List<Store> selectStoreList(Store store);

    /**
     * 新增门店
     *
     * @param store 门店
     * @return 结果
     */
    public int insertStore(Store store);

    /**
     * 修改门店
     *
     * @param store 门店
     * @return 结果
     */
    public int updateStore(Store store);

    /**
     * 删除门店
     *
     * @param storeId 门店主键
     * @return 结果
     */
    public int deleteStoreByStoreId(Long storeId);

    /**
     * 批量删除门店
     *
     * @param storeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreByStoreIds(Long[] storeIds);

    Tuple<BigDecimal, Long> queryOrderTotal(@Param("amount") String amount, @Param(Constants.WRAPPER) QueryWrapper<Order> queryWrapper);

    <T> List<Tuple3<BigDecimal, Long, T>> queryPayTotal(@Param("amount") String amount,
                                                        @Param("groupColumn") String groupColumn,
                                                        @Param(Constants.WRAPPER) QueryWrapper<OrderPay> queryWrapper);

    <T> List<Tuple3<BigDecimal, Long, T>> queryRefundTotal(@Param("amount") String amount,
                                                           @Param("groupColumn") String groupColumn,
                                                           @Param(Constants.WRAPPER) QueryWrapper<OrderRefund> queryWrapper);
}
