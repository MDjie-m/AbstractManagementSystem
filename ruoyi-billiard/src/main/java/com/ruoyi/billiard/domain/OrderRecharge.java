package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.enums.OrderPayType;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 会员充值对象 t_order_recharge
 *
 * @author zhoukeu
 * @date 2024-09-13
 */
@TableName("t_order_recharge")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecharge extends MyBaseEntity implements ITotalDueFee {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */

    @TableId("order_recharge_id")
    private Long orderRechargeId;

    /**
     * 订单id
     */
    @Excel(name = "订单id")

    @TableField("order_id")
    private Long orderId;

    /**
     * 会员id
     */
    @Excel(name = "会员id")

    @TableField("member_id")
    @NotNull(groups = IRecharge.class, message = "会员ID不能为空")
    private Long memberId;

    /**
     * 充值金额
     */
    @Excel(name = "充值金额")

    @TableField("recharge_amount")
    @DecimalMin(groups = IRecharge.class, value = "1.00", message = "充值金额最小为1")
    private BigDecimal rechargeAmount;

    /**
     * 充值赠送金额
     */
    @Excel(name = "充值赠送金额")

    @TableField("give_amount")
    private BigDecimal giveAmount;

    /**
     * 支付金额
     */
    @Excel(name = "支付金额")

    @TableField("total_amount")
    @DecimalMin(groups = IRecharge.class, value = "1.00", message = "支付金额最小为1")
    private BigDecimal totalAmount;

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


    @Excel(name = "折扣金额")
    @TableField("total_discount_amount")

    private BigDecimal totalDiscountAmount;

    /**
     * 当前折扣
     */
    @Excel(name = "当前折扣")

    @TableField("discount_value")
    private BigDecimal discountValue;

    @TableField(exist = false)
    private String orderNo;

    @TableField(exist = false)
    private Long storeId;

    @TableField(exist = false)
    private OrderPayType payType;

    @TableField(exist = false)
    private String storeName;

    @Override
    public BigDecimal getTotalAmountDue() {
        return rechargeAmount;
    }

    @Override
    public void setTotalAmountDue(BigDecimal val) {

    }

    @Override
    public Boolean getDiscountDisable() {
        return false;
    }

    @Override
    public BigDecimal getPrice() {
        return rechargeAmount;
    }

    @Override
    public Integer getNum() {
        return 1;
    }

    public static interface IRecharge {

    }

    @TableField(exist = false)
    @NotNull(groups = IRecharge.class, message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度在6-20之前")
    private String pwd;
    @Override
    public RoundingMode getCalcMode() {
        return RoundingMode.DOWN;
    }

    @Override
    public void setTotalWipeZero(BigDecimal val) {

    }

    @Override
    public BigDecimal getTotalWipeZero() {
        return BigDecimal.ZERO;
    }
}
