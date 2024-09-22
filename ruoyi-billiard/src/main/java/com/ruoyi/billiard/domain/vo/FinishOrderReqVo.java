package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.enums.OrderPayType;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class FinishOrderReqVo {

    @NotNull(message = "订单不能为空")
    private Long orderId;

    @NotNull(message = "支付方式不能为空")
    private OrderPayType payType;

    private String password;

    private Long storeId;

    @AssertTrue(message = "密码不能为空")
    public boolean isPwdOk() {
        if (!Objects.equals(payType, OrderPayType.MEMBER)) {
            return true;
        }
        return StringUtils.isNotEmpty(password);
    }
}
