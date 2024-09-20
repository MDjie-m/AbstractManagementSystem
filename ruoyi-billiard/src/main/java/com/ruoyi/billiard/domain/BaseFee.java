package com.ruoyi.billiard.domain;

import com.ruoyi.common.core.domain.MyBaseEntity;
import com.ruoyi.common.utils.DateUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BaseFee extends MyBaseEntity implements IFee {

    public BigDecimal calcFee() {
        int minutes =cacTime();
        return Optional.ofNullable(this.getPrice()).orElse(BigDecimal.ZERO)
                .multiply(new BigDecimal(String.valueOf(minutes)));
    }
    public Integer cacTime() {
        int minutes = DateUtils.deskTimeDiffMinutes(this.getStartTime(), Optional.ofNullable(this.getEndTime()).orElse(new Date()));
        if (minutes <= 0) {
            minutes = 1;
        }
       return  minutes;
    }
    public static <T extends BaseFee> BigDecimal calcFees(List<T> fees) {
        if (CollectionUtils.isEmpty(fees)) {
            return BigDecimal.ZERO;
        }
        return fees.stream().map(BaseFee::calcFee).
                reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_DOWN);
    }

    public  static  BigDecimal calcGoodsFee(List<OrderGoods> fees){
        if (CollectionUtils.isEmpty(fees)) {
            return BigDecimal.ZERO;
        }
        return  fees.stream().map(BaseFee::calcGoodsFee)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_DOWN);
    }
    public  static  BigDecimal calcGoodsFee( OrderGoods p){
        if (Objects.isNull(p)) {
            return BigDecimal.ZERO;
        }
        return   p.getPrice().multiply(new BigDecimal(String.valueOf(p.getNum())));
    }
    public  static  BigDecimal sumTotalFees( List<BigDecimal> amounts){
        if (CollectionUtils.isEmpty(amounts)) {
            return BigDecimal.ZERO;
        }
        return  amounts.stream()
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_DOWN);
    }
}
