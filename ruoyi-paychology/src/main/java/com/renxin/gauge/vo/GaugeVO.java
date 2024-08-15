package com.renxin.gauge.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GaugeVO implements Serializable {
    private Long id;

    /** 测评标题 */
    private String title;

    /** 子标题 */
    private String subtitle;

    /** 头部图片 */
    private String headPicture;

    /** 测评描述 */
    private String introduce;

    /** 测评分类 */
    private Integer gaugeClass;

    /** 测评题数 */
    private Integer gaugeNum;

    /** 价格 */
    private BigDecimal price;

    /** 测评次数 */
    private Integer num;

    /** 测评类型 */
    private Integer type;

    private String gaugeDes;

    private String listShowPicture;

    /** 测评是否已购买 */
    private Integer isBuy;
    private Integer isCompleted;
    private Integer size;

    /** 测评订单ID */
    private Long orderId;
    private String orderNo;
    /** 测评已回答的问题数量 */
    private Integer finishedNum;
}
