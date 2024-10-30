package com.renxin.psychology.vo;

import com.renxin.common.core.domain.BaseValObj;
import com.renxin.psychology.domain.PsyConsultantSchedule;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *  与咨询师的关联信息
 */
@Data
public class ConsultRelationDetail implements Serializable
{
    //是否已有交易订单
    private Boolean isExistOrder;
   
}
