package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.billiard.domain.vo.IBuy;
import com.ruoyi.billiard.enums.OrderPayType;
import com.ruoyi.billiard.enums.OrderType;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单对象 t_order
 *
 * @author zhoukeu
 * @date 2024-09-13
 */
@TableName("t_order")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends MyBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */

    @TableId("order_id")
    private Long orderId;

    /**
     * 订单编码
     */
    @Excel(name = "订单编码")

    @TableField("order_no")
    private String orderNo;

    /**
     * 类型：1=球桌费用，2=会员充值，3=商品购买,4=陪练费用，5=教学费用
     */
    @TableField("order_type")
    private OrderType orderType;

    @Excel(name = "类型：1=球桌费用，2=会员充值，3=商品购买,4=陪练费用，5=教学费用")
    @TableField(exist = false)
    private String orderTypeText;

    /**
     * 应付总金额
     */
    @Excel(name = "应付总金额 ")

    @TableField("total_amount_due")
    private BigDecimal totalAmountDue;

    /**
     * 折扣金额
     */
    @Excel(name = "折扣金额")

    @TableField("total_discount_amount")
    private BigDecimal totalDiscountAmount;

    /**
     * 实际支付金额
     */
    @Excel(name = "实际支付金额")

    @TableField("total_amount")
    private BigDecimal totalAmount;


    /**
     * 预付费
     */
    @TableField("pre_pay_amount")
    private BigDecimal prePayAmount;

    /**
     * '退款金额'
     */
    @TableField("refund_amount")
    private BigDecimal refundAmount;

    /**
     * '补缴金额'
     */
    @TableField("repay_amount")
    private BigDecimal repayAmount;


    /**
     * 当前折扣
     */
    @Excel(name = "当前折扣")

    @TableField("discount_value")
    private BigDecimal discountValue;

    /**
     * 抹零金额
     */
    @Excel(name = "抹零金额")

    @TableField("total_wipe_zero")
    private BigDecimal totalWipeZero;

    /**
     * 支付方式：0=扫码，1=现金，2=会员
     */
    @TableField("pay_type")
    private OrderPayType payType;

    @Excel(name = "支付方式：0=扫码，1=现金，2=会员")
    @TableField(exist = false)
    private String payTypeText;


    /**
     * 订单状态：0=计费中,1=待结算,2=已结算，3=作废
     */
    @Excel(name = "订单状态：0=计费中,1=待结算,2=已结算，3=作废", readConverterExp = "0=计费中,1=待结算,2=已结算,3=作废")

    @TableField("status")
    private Integer status;

    /**
     * 支付会员id
     */
    @Excel(name = "支付会员id")

    @TableField("member_id")
    private Long memberId;

    /**
     * 门店
     */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;
    //支付时间
    @TableField("pay_time")
    private Date payTime;

    /**
     * 创建者Id
     */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /**
     * 更新者Id
     */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;

    /**
     * 球桌计时详情
     */
    @TableField(exist = false)
    @Valid()
    private List<OrderDeskTime> orderDeskTimes = Lists.newArrayList();

    /**
     * 会员订单详情
     */
    @TableField(exist = false)
    private List<OrderRecharge> orderRecharges = Lists.newArrayList();

    /**
     * 商品订单详情
     */
    @TableField(exist = false)
    @Valid()
    private List<OrderGoods> orderGoods = Lists.newArrayList();

    /**
     * 教练计时详情
     */
    @TableField(exist = false)
    private List<OrderTutorTime> orderTutorTimes = Lists.newArrayList();

    /**
     * 会员
     */
    @TableField(exist = false)
    private Member member;

    @TableField(exist = false)
    private String storeName;


    @TableField(exist = false)
    private Date createStart;


    @TableField(exist = false)
    private Date createEnd;

    @TableField(exist = false)
    private Long deskId;

    @AssertTrue(groups = {IBuy.class}, message = "商品和教练不能为空")
    @JsonIgnore
    public boolean isOk() {
        return !(CollectionUtils.isEmpty(orderGoods) && CollectionUtils.isEmpty(orderTutorTimes));
    }


}
