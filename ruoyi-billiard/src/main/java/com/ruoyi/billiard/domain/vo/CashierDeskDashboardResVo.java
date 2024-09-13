package com.ruoyi.billiard.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashierDeskDashboardResVo implements Serializable {

    private  Integer deskWaitCount=0;

    private  Integer deskBusyCount=0;

    private  Integer deskStopCount=0;

    private  Integer deskLightOnCount=0;

    private Integer tutorWaitCount=0;
    private Integer tutorBusyCount=0;
    private Integer tutorStopCount=0;
}
