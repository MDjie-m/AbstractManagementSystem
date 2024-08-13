package com.renxin.psychology.vo;

import com.renxin.common.core.domain.BaseValObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 咨询订单对象 psy_consult_order
 * 
 * @author renxin
 * @date 2023-06-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PsyConsultOrderVO extends BaseValObj implements Serializable
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
    private Integer userId;

    /** 客户 */
    private String nickName;

    /** 应付费用 */
    private BigDecimal amount;

    /** 实际支付 */
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

    private String payStatus;

    private String orderTime;

    private String remark;
    private String memo1;
    private String reason;

    private Integer time;
    private Long workId;

    /** 下单方式0,H5 1,MP 2,DY 5,PC */
    private String source;

    private String channel;
    
    //用户自己填写的标签
    private String userFillLabel;
    //咨询师为用户填写的标签
    private String consultantFillLabel;
    //客服为用户填写的标签
    private String adminFillLabel;
    //用户订单次数
    private Integer userOrderCount;
    //咨询形式   1语音咨询    2视频咨询   3面对面咨询
    private String mode;
    private String userSex;
    private String userAvatar;

}
