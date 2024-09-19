package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.domain.BaseFee;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.StoreDesk;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
public class OrderCommandResVo {
    private StoreDesk desk;
    private List<StoreDesk > desks;

    private Order order;

    /**
     * 台费
     */
    private BigDecimal deskTotalTimeAmount = BigDecimal.ZERO;
    /**
     * 其他费用
     */
    private BigDecimal otherTotalAmount = BigDecimal.ZERO;

    public void calcFees() {

        if (Objects.isNull(order)) {
            return;
        }
        //台桌费用
        this.setDeskTotalTimeAmount(BaseFee.calcFees(order.getOrderDeskTimes()));

        //教练费用
        this.setOtherTotalAmount(BaseFee.calcFees(order.getOrderTutorTimes()));
        //商品费用
        this.setOtherTotalAmount(this.getOtherTotalAmount().add(BaseFee.calcGoodsFee(order.getOrderGoods())));
    }
}
