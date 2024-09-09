package com.renxin.psychology.dto;

import com.renxin.common.core.domain.BaseValObj;
import com.renxin.psychology.domain.PsyConsultOrderItem;
import com.renxin.psychology.domain.PsyConsultOrderServe;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 咨询订单对象 psy_consult_order
 * 
 * @author renxin
 * @date 2023-06-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDTO extends BaseValObj implements Serializable
{

    private static final long serialVersionUID = 7123849582002656723L;

    /** 订单号 */
    private String orderNo;

    /** 咨询师 */
    private Long consultId;

    /** 咨询师 */
    private String consultName;

    private Long refConsultId;
    private String refConsultName;

    /** 服务 */
    private Long serveId;

    /** 服务 */
    private String serveName;

    /** 客户id */
    private Long userId;

    /** 客户 */
    private String nickName;
    private String phone;

    /** 应付费用 */
    private BigDecimal amount;
    private BigDecimal pay;

    private Date payTime;

    /** 可预约数量 */
    private Integer num;

    /** 已预约数量 */
    private Integer buyNum;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 订单状态0-待付款 1-进行中 2-已完成 3-已取消 */
    private String status;
    private String statusName;

    /** 下单方式0,H5 1,MP 2,DY 5,PC */
    private String source;

    private String channel;

    private String payStatus;
    private String payStatusName;

    private String avatar;
    private String wxCard;
    private String remark;
    private String memo1;
    private String reason;

    /** 下次咨询时间 */
    private String orderTime;

    private List<PsyConsultOrderItem> items;

    private PsyConsultOrderServe serve;

    private BigDecimal originalPrice;//原价
    private String couponNo;//使用优惠券编号

}
