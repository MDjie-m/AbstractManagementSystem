package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSubTotalVo {

    private OrderType type;
    private Long count;
    private BigDecimal amount;
}
