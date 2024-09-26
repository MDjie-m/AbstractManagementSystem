package com.ruoyi.billiard.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.OrderRecharge;
import com.ruoyi.billiard.domain.dto.HomeReportDto;
import com.ruoyi.billiard.domain.vo.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
public interface IOrderService 
{
    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    public Order selectOrderByOrderId(Long orderId);
    public Order selectRelationOrderWithDetail(Long deskId);
    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增订单
     * 
     * @param order 订单
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单
     * 
     * @param order 订单
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteOrderByOrderIds(Long[] orderIds);

    /**
     * 删除订单信息
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    public int deleteOrderByOrderId(Long orderId);

    Order createOrder(Long deskId);

    Order swapToNewDesk(Long deskId, Long orderId, Long newDeskId);

    Order mergeToNewDesk(Long deskId, Long orderId, Long newDeskId);

    Order pauseCalcFee(Long deskId);

    Order resumeCalcFee(Long deskId);

    OrderCommandResVo suspendOrder(Long orderId, Long storeId);

    BigDecimal prePayAmount(OrderPrePayReqVo reqVo);

    OrderCommandResVo stopOrder(Long orderId, Long storeId,boolean stopByTimer);

    Boolean voidOrder(Long orderId, Long storeId,String remark,boolean needRevertGoods);

    void calOrderFees(Order order);

    void checkOrderTimer();

    /**
     * 根据会员id查询订单
     * @param memberIds
     * @return
     */
    List<Order> selectOrderByMemberIds(Long[] memberIds);

    Boolean fillMember(Long orderId, Long memberId);

    Boolean finishOrder(FinishOrderReqVo reqVo);

    Long orderShopping(Order reqVo);

    StopDeskResVo stopDesk(Long orderId, Long storeId, Long deskId);

    @Transactional(rollbackFor = Exception.class)
    void memberRecharge(OrderRecharge recharge);

    List<OrderRecharge> selectRechargeOrderList(Order order);

    List<Order> selectDeductOrderList(Order order);

    OrderRecharge getPreRecharge(Long storeId, Long memberId, BigDecimal recharge);

    HomeReportVo selectOrderData2Report(HomeReportDto dto);

    /**
     *
     * @param payStatus 支付状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 订单集合
     */
    List<Order> selectOrderByPayStatus(Integer payStatus, Long storeId, String startTime, String endTime);
}
