package com.ruoyi.billiard.domain.vo;

import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class FinishOrderReqVo {

    @NotNull(message = "订单不能为空")
    private Long orderId;
    /**
     * 0=现金支付，1=会员支付
     */
    @NotNull(message = "结算方式不能为空")
    private Integer type;

    private String password;

    private Long storeId;

    @AssertTrue(message = "密码不能为空")
    public boolean isPwdOk() {
        if (!Objects.equals(type, 1)) {
            return true;
        }
        return StringUtils.isNotEmpty(password);
    }
}
