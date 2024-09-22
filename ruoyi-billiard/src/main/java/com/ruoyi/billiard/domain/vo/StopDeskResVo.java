package com.ruoyi.billiard.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StopDeskResVo {
    private Long orderId;
    private Boolean hasOtherDesk;

}
