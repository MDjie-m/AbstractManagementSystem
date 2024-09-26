package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.domain.vo.miniappdomain.HomeReportVoConsume;
import com.ruoyi.billiard.domain.vo.miniappdomain.HomeReportVoConsumeDetail;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-21:10
 * @className: HomeReportVo 报表统计Vo
 */
@Data
public class HomeReportVo implements Serializable {

    /** 消费统计 */
    private HomeReportVoConsume consume;

    /** 挂起订单统计 */
    private HomeReportVoConsumeDetail suspend;

}
