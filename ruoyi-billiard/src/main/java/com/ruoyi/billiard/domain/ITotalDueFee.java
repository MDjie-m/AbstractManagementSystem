package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

public interface ITotalDueFee {
    BigDecimal getTotalAmountDue();

    BigDecimal getTotalAmount();

    BigDecimal getDiscountValue();

    void setTotalAmountDue(BigDecimal val);

    void setTotalAmount(BigDecimal val);

    void setDiscountValue(BigDecimal val);

    void setTotalDiscountAmount(BigDecimal val);

    BigDecimal getTotalDiscountAmount();

    Boolean getDiscountDisable();

    BigDecimal getPrice();

    Integer getNum();

    default RoundingMode getCalcMode() {
        return RoundingMode.HALF_UP;
    }

    void setTotalWipeZero(BigDecimal val);

    BigDecimal getTotalWipeZero();

    default BigDecimal calcFee() {
        int count = getNum();
        return Optional.ofNullable(this.getPrice()).orElse(BigDecimal.ZERO)
                .multiply(new BigDecimal(String.valueOf(count))).setScale(2, getCalcMode());
    }

    default void calcAndSetFee(BigDecimal discountPercent) {
        this.setDiscountValue(Optional.ofNullable(discountPercent).orElse(BigDecimal.ZERO));
        this.setTotalAmountDue(calcFee());
        this.setTotalDiscountAmount(calcDiscountAmount(this.getTotalAmountDue(), this.getDiscountValue()));
        this.setTotalAmount(this.getTotalAmountDue().subtract(this.getTotalDiscountAmount()));
        this.setTotalWipeZero(BigDecimal.ZERO);
    }

    default BigDecimal calcDiscountAmount(BigDecimal total, BigDecimal discountPercent) {
        //禁止折扣直接返回0
        if (Objects.equals(getDiscountDisable(), Boolean.TRUE)) {
            return BigDecimal.ZERO;
        }
        total = Optional.ofNullable(total).orElse(BigDecimal.ZERO);
        return total.multiply(Optional.ofNullable(discountPercent).orElse(BigDecimal.ZERO))
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

    }

    default BigDecimal calcFeeSubDiscount(BigDecimal total, BigDecimal discountPercent) {

        BigDecimal subValue = calcDiscountAmount(total, discountPercent);
        return total.subtract(subValue);

    }
}
