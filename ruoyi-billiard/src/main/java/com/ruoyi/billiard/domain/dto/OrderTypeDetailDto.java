package com.ruoyi.billiard.domain.dto;

import com.ruoyi.billiard.enums.OrderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-10-22:25
 * @className: OrderTypeDetaildto
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderTypeDetailDto implements Serializable {

    /** 订单id */
    private Long orderId;

    /** 订单类型 */
    private OrderType orderType;

}
