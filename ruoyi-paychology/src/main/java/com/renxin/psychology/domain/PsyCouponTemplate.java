package com.renxin.psychology.domain;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 优惠券模版对象 psy_coupon_template
 * 
 * @author renxin
 * @date 2024-08-02
 */
@Data
public class PsyCouponTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 面向用户类型  1.来访者   2.咨询师 */
    @Excel(name = "面向用户类型  1.来访者   2.咨询师")
    private Integer userType;

    /** 优惠券模版名称 */
    @Excel(name = "优惠券模版名称")
    private String couponName;

    /** 发行上限数量 */
    @Excel(name = "发行上限数量")
    private Integer totalNum;

    /** 已发行数量 */
    @Excel(name = "已发行数量")
    private Integer usedNum;

    /** 券类型  1.抵扣券   2.折扣券 */
    @Excel(name = "券类型  1.抵扣券   2.折扣券")
    private Integer couponType;

    /** 服务类型  11.倾诉  12.咨询  13.测评  14.来访者课程    21.团督  22.个督  23.体验  24.咨询师课程    */
    @Excel(name = "服务类型  11.倾诉  12.咨询  13.测评  14.来访者课程    21.团督  22.个督  23.体验  24.咨询师课程   ")
    private Integer serverType;

    /** 最大抵扣金额 */
    @Excel(name = "最大抵扣金额")
    private BigDecimal maxDeductionPrice;

    /** 折扣比例 (0.8即8折) */
    @Excel(name = "折扣比例 (0.8即8折)")
    private BigDecimal discountRate;

    /** 有效天数 */
    @Excel(name = "有效天数")
    private Integer validityDay;

    /** 模版状态 */
    @Excel(name = "模版状态")
    private Integer templateStatus;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;
    
    //使用门槛金额
    private BigDecimal useThresholdPrice;
    //备注
    private String remark;
    //是否支持免费领取
    private String isFreeGet;
    
    @TableField(exist = false)
    private List<Long> idList;

}
