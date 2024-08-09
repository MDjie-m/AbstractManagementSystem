package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

/**
 * 用户-优惠券发行对象 psy_coupon
 * 
 * @author renxin
 * @date 2024-08-02
 */
@Data
public class PsyCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 优惠券编号 */
    private String couponNo;

    /** 优惠券模版id */
    @Excel(name = "优惠券模版id")
    private Long templateId;

    /** 来访者id */
    @Excel(name = "来访者id")
    private Long userId;

    /** 咨询师id */
    @Excel(name = "咨询师id")
    private Long consultantId;

    /** 是否已用  0未使用   1已消耗 */
    @Excel(name = "是否已用  0未使用   1已消耗")
    private Integer isUsable;

    /** 是否过期  0未过期   1已过期 */
    private Integer isExpire;

    /** 到期日期 */
    @Excel(name = "到期日期")
    private String expireDate;
    
    /** 优惠券模版名 */
    @TableField(exist = false)
    private String couponName;

    /** 订单服务类型 */
    @TableField(exist = false)
    private String orderServerType;

    /** 订单服务id */
    @TableField(exist = false)
    private String orderServerId;

    /** 是否达标可用 */
    @TableField(exist = false)
    private Boolean isQualify;

    /** 优惠后价格 */
    @TableField(exist = false)
    private BigDecimal payAmount;

    /** 最大抵扣金额 */
    @TableField(exist = false)
    private BigDecimal maxDeductionPrice;

    /** 折扣比例 */
    @TableField(exist = false)
    private BigDecimal discountRate;

    /** 使用门槛金额 */
    @TableField(exist = false)
    private BigDecimal useThresholdPrice;

    /** 券类型  1.抵扣券   2.折扣券 */
    @TableField(exist = false)
    private Integer couponType;


}
