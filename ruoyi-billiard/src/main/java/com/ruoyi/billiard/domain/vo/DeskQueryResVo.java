package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.StoreDesk;
import lombok.*;

import java.math.BigDecimal;

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
    private BigDecimal deskTotalTimeAmount=BigDecimal.ZERO;
    /**
     * 其他费用
     */
    private BigDecimal otherTotalAmount=BigDecimal.ZERO;

}
