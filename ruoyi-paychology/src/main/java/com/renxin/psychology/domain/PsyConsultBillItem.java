package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BasePlusEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 咨询订单对象 psy_consult_bill_item
 *
 * @author renxin
 * @date 2023-06-26
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_consult_bill_item")
public class PsyConsultBillItem extends BasePlusEntity implements Serializable
{
    private static final long serialVersionUID = 8139491001632435295L;

    /** 账单 */
    private Long billId;
    
    /** 订单id */
    private String orderId;
    //团督id
    private String teamId;
    
    //标识id
    @TableField(exist = false)
    private String keyId;
    
    /** 所属咨询师 */
    private Long consultId;

    /** 账单日期 */
    @Excel(name = "账单日期", width = 30)
    private String billTime;

    /** 核销时间 */
    @Excel(name = "核销时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    /** 所属咨询师 */
    @Excel(name = "所属咨询师")
    private String consultName;

    /** 签约类型 */
    @Excel(name = "签约类型")
    private String type;

    /** 结算比例 */
    @Excel(name = "结算比例")
    private BigDecimal ratio;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /** 下单时间 */
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 订单状态0-待付款 1-进行中 2-已完成 3-已取消 */
    @TableField(exist = false)
    @Excel(name = "订单状态")
    private String status;

    /** 订单状态0-待结算 1-部分结算 2-结算完成 */
    @TableField(exist = false)
    @Excel(name = "结算单状态")
    private String billStatus;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serveName;

    /** 服务类型 */
    @Excel(name = "服务类型")
    private String serveType;

    /** 咨询次数 */
    private Integer buyNum;
    @Excel(name = "咨询次数")
    private String buyNumStr;

    /** 服务总次数 */
    @Excel(name = "服务总次数")
    private Integer orderNum;

    /** 剩余次数 */
    @Excel(name = "剩余次数")
    private Integer num;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 分期结算款项(单价) */
    @Excel(name = "分期结算款项(单价)")
    private BigDecimal price;

    /** 分期结算佣金 */
    @Excel(name = "分期结算佣金")
    private BigDecimal brokerage;

    /** 订单实付金额 */
    @Excel(name = "订单实付金额")
    private BigDecimal orderTotal;

    @TableField(exist = false)
    //咨询师分成比例
    private BigDecimal consultantRatio;

    /** 订单任务类型  12.咨询  21.团督  22.个督  23.体验 */
    private Integer scheduleType;
    
    @TableField(exist = false)
    private String realTime;

    /** 已结算金额 */
    @Excel(name = "已结算金额")
    @TableField(exist = false)
    private BigDecimal total;

}
