package com.ruoyi.billiard.domain.dto;

import lombok.Data;


/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-21:54
 * @className: HomeReportDto 小程序首页报表Dti
 */
@Data
public class HomeReportDto {

    /** 门店Id */
    private Long storeId;

    /** 时间类型 */
    private Integer timeType;

    /** 查询开始时间 */
    private String startTime;

    /** 查询结束时间 */
    private String endTime;


//    /** 台桌Id */
//    private Long deskId;
//
//    /** 消费渠道类型 */
//    private Integer channelType;
}
