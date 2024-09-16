package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.domain.BaseFee;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.StoreDesk;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeskQueryResVo extends StoreDesk {

    /**
     * 最后活跃的订单
     */
    private Order lastActiveOrder;

    /**
     * 台费
     */
    private BigDecimal deskTotalTimeAmount = BigDecimal.ZERO;
    /**
     * 其他费用
     */
    private BigDecimal otherTotalAmount = BigDecimal.ZERO;

    public void calcFees() {

        if (Objects.isNull(lastActiveOrder)) {
            return;
        }
        //台桌费用
        this.setDeskTotalTimeAmount(BaseFee.calcFees(lastActiveOrder.getOrderDeskTimes()));

        //教练费用
        this.setOtherTotalAmount(BaseFee.calcFees(lastActiveOrder.getOrderTutorTimes()));
        //商品费用
        this.setOtherTotalAmount(this.getOtherTotalAmount().add(BaseFee.calcGoodsFee(lastActiveOrder.getOrderGoods())));
    }
}
