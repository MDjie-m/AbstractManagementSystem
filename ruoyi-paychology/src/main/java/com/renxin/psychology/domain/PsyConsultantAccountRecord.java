package com.renxin.psychology.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 账户明细流水对象 psy_consultant_account_record
 * 
 * @author renxin
 * @date 2024-06-20
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_consultant_account_record")
public class PsyConsultantAccountRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 账户ID */
    @Excel(name = "账户ID")
    private Long consultantId;

    /** 1 成功 0 失败 */
    private String status;

    /** 0 分成 1 提现 */
    @Excel(name = "0 分成 1 提现")
    private String payType;

    /** 分成来源BillItemID */
    @Excel(name = "分成来源BillItemID")
    private String orderId;

    /** 支付前账户余额 */
    private String accountAmount;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal payAmount;

    /** 支付消息结果 */
    private String payMessage;

    /** 0 未删除 1 删除 */
    private String delFlag;

    
    @TableField(exist = false)
    private String createTimeStart;
    @TableField(exist = false)
    private String createTimeEnd;
}
