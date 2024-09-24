package com.ruoyi.billiard.domain.vo;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderPrePayReqVo {

    @NotNull(message = "订单不能为空")
    private Long orderId;

    private Long storeId;

    @DecimalMin(value = "10", message = "预付费金额不能低于10元")
    @DecimalMax(value = "9999",message = "预付费金额不能超过9999元")
    private BigDecimal amount;
}
