package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.enums.OrderPayType;
import com.ruoyi.billiard.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayDetailVo implements Serializable {

    private OrderPayType type;

    private BigDecimal amount;

    private Long count;
}
