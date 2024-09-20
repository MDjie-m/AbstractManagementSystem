package com.ruoyi.billiard.domain;

import com.ruoyi.common.core.domain.MyBaseEntity;
import com.ruoyi.common.utils.ArrayUtil;
import com.ruoyi.common.utils.DateUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class BaseFee extends MyBaseEntity implements IFee {



    public Integer getNum() {
        int minutes = DateUtils.deskTimeDiffMinutes(this.getStartTime(), Optional.ofNullable(this.getEndTime()).orElse(new Date()));
        if (minutes <= 0) {
            minutes = 1;
        }
        return minutes;
    }

    public static <T extends ITotalDueFee> BigDecimal calcFees(List<T> fees) {
        if (CollectionUtils.isEmpty(fees)) {
            return BigDecimal.ZERO;
        }
        return fees.stream().map(ITotalDueFee::calcFee).
                reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.DOWN);
    }


    public static <T extends ITotalDueFee> BigDecimal sumTotalFees(List<T> amounts, Function<T,BigDecimal> valueFunc) {
        if (CollectionUtils.isEmpty(amounts)) {

            return BigDecimal.ZERO;
        }
        return amounts.stream().map(valueFunc)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.DOWN);
    }
}
