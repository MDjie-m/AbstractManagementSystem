package com.ruoyi.psychology.dto;

import com.ruoyi.common.core.domain.BaseValObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 咨询订单对象 psy_consult_order
 * 
 * @author ruoyi
 * @date 2023-06-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderListDTO extends BaseValObj implements Serializable
{

    private static final long serialVersionUID = 7123849582002656723L;

    /** 订单号 */
    private String orderNo;

    /** 咨询师 */
    private Long consultId;

    /** 咨询师 */
    private String consultName;

    /** 服务 */
    private Long serveId;

    /** 服务 */
    private String serveName;

    /** 客户id */
    private Integer userId;

    /** 客户 */
    private String nickName;

    /** 下次咨询时间 */
    private String orderTime;

    /** 应付费用 */
    private BigDecimal amount;

    /** 可预约数量 */
    private Integer num;

    /** 已预约数量 */
    private Integer buyNum;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 订单状态0-待付款 1-进行中 2-已完成 3-已取消 */
    private String status;
    private String statusName;

    private String avatar;

    /** 咨询形式 语音咨询、视频咨询、面对面咨询 */
    private Integer mode;
    private String modeName;

    /** 服务类型 单次咨询 套餐咨询 */
    private Integer type;
    private String typeName;

    /** 服务介绍 */
    private String info;

    private String remark;

    /** 价格 */
    private BigDecimal price;

    /** 时长 */
    private Integer time;

    /** 服务次数 */
    private Integer serveNum;

    /** 限购 0-不限制 1-限制 */
    private Integer bound;
    private String boundName;

    /** 有效期 */
    private Date end;

}
