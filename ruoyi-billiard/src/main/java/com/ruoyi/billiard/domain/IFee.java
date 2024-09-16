package com.ruoyi.billiard.domain;

import com.ruoyi.common.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface IFee extends ITotalDueFee{

    Date getStartTime();

    Date getEndTime();

    BigDecimal getPrice();


}
