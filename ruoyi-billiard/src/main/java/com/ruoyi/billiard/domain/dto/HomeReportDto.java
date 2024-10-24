package com.ruoyi.billiard.domain.dto;

import com.ruoyi.billiard.enums.OrderType;
import com.ruoyi.billiard.enums.ReportTimeType;
import com.ruoyi.common.core.domain.MyBaseEntity;
import lombok.Data;


/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-21:54
 * @className: HomeReportDto 小程序首页报表Dti
 */
@Data
public class HomeReportDto extends MyBaseEntity {

    /** 门店Id */
    private Long storeId;

    /** 时间类型 */
    private ReportTimeType timeType;

    /** 查询开始时间 */
    private String startTime;

    /** 查询结束时间 */
    private String endTime;

    /** 订单类型枚举 */
    private OrderType orderType;


//    /** 台桌Id */
//    private Long deskId;
//
//    /** 消费渠道类型 */
//    private Integer channelType;
}
