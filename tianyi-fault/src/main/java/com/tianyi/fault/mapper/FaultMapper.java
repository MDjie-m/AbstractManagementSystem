package com.tianyi.fault.mapper;

import org.apache.ibatis.annotations.Param;

public interface FaultMapper {
    String cardStopStateQuery(@Param("req")String billingNbr);

    String cardCutStateQuery(@Param("req")String billingNbr);
}
