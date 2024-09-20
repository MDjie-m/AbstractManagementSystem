package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public interface ITotalDueFee {
    BigDecimal getTotalAmountDue();

    BigDecimal getTotalAmount ();

    BigDecimal getDiscountValue ();

    void setTotalAmountDue( BigDecimal val);

    void setTotalAmount (BigDecimal val);

    void setDiscountValue (BigDecimal val);

    BigDecimal getPrice();

    Integer getNum();

    default BigDecimal calcFee() {
        int count = getNum();
        return Optional.ofNullable(this.getPrice()).orElse(BigDecimal.ZERO)
                .multiply(new BigDecimal(String.valueOf(count)));
    }

    default  void calcAndSetFee(BigDecimal discountPercent){
        this.setDiscountValue(Optional.ofNullable(discountPercent).orElse(BigDecimal.ZERO));
        this.setTotalAmountDue(calcFee());
        this.setTotalAmount(calcFeeSubDiscount(this.getTotalAmount(),this.getDiscountValue()));
    }

    default BigDecimal calcFeeSubDiscount(BigDecimal total, BigDecimal discountPercent) {
        total = Optional.ofNullable(total).orElse(BigDecimal.ZERO);
        BigDecimal subValue = total.multiply(Optional.ofNullable(discountPercent).orElse(BigDecimal.ZERO)).divide(
                new BigDecimal("100"), RoundingMode.DOWN).setScale(2, RoundingMode.DOWN);
        return total.subtract(subValue);

    }
}
