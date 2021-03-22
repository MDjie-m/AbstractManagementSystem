package com.stdiet.custom.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.stdiet.custom.domain.SysCommision;
import com.stdiet.custom.domain.SysCommissionDayDetail;
import com.stdiet.custom.domain.SysCustomer;
import com.stdiet.custom.domain.SysOrder;
import com.stdiet.custom.dto.request.SysOrderCommision;
import org.apache.ibatis.annotations.Param;

/**
 * 销售订单Mapper接口
 *
 * @author wonder
 * @date 2020-09-24
 */
public interface SysOrderMapper
{
    /**
     * 查询销售订单
     *
     * @param orderId 销售订单ID
     * @return 销售订单
     */
    public SysOrder selectSysOrderById(Long orderId);

    /**
     * 查询销售订单列表
     *
     * @param sysOrder 销售订单
     * @return 销售订单集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 新增销售订单
     *
     * @param sysOrder 销售订单
     * @return 结果
     */
    public int insertSysOrder(SysOrder sysOrder);

    /**
     * 修改销售订单
     *
     * @param sysOrder 销售订单
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

    /**
     * 删除销售订单
     *
     * @param orderId 销售订单ID
     * @return 结果
     */
    public int deleteSysOrderById(Long orderId);

    /**
     * 批量删除销售订单
     *
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysOrderByIds(Long[] orderIds);

    /**
     * 计算所有订单总额
     * @param sysOrder
     * @return
     */
    public BigDecimal selectAllOrderAmount(SysOrder sysOrder);

    /**
     * 获取订单信息（按天提成计算）
     * @return
     */
    List<SysOrder> selectSimpleOrderMessage(SysCommision sysCommision);

    /**
     * 查询客户订单数量
     * @param sysCustomer
     * @return
     */
    int getOrderCountByCustomer(SysCustomer sysCustomer);
}