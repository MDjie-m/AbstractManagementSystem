package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.enums.PreferentialType;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Data
public class StoreDashboardResVo implements Serializable {
    private Long totalOrderCount;

    private BigDecimal totalAmount;

    private BigDecimal rechargeAmount;
    private Long rechargeOrderCount;

    private BigDecimal refundAmount;

    private Long refundOrderCount;
    private BigDecimal suspendAmount;
    private Long suspendOrderCount;

    private BigDecimal preferentialTotal = BigDecimal.ZERO.setScale(0, RoundingMode.DOWN);
    private List<PayDetailVo> payList;

    private BigDecimal payTotalAmount;
    private List<PreferentialVo> preferentialList = Lists.newArrayList();

    private List<PayDetailVo> refundList;

    private List<OrderSubTotalVo> orderSubItems = Lists.newArrayList();

    private Date starTime;
    private Date endTime;

}
