package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    /** 咨询师ID */
    private Long consultantId;

    /** 1 成功 0 失败 */
    private String status;

    /** 0 分成 1 提现 */
    private String payType;

    /** 分成来源BillItemID */
    //@Excel(name = "分成来源BillItemID")
    private String billItemId;

    /** 支付前账户余额 */
    private String accountAmount;
    
    /** 支付消息结果 */
    private String payMessage;
    


    /** 0 未删除 1 删除 */
    private String delFlag;

    /** 生成时间 */
    //@Excel(name = "生成时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(exist = false)
    private Boolean isHideZero;//是否隐藏金额为0的记录
    
    @TableField(exist = false)
    private String createTimeStart;
    @TableField(exist = false)
    private String createTimeEnd;
    
    @TableField(exist = false)
    @Excel(name = "*账户")
    private String cardNumber;
    
    @TableField(exist = false)
    @Excel(name = "*户名")
    private String cardUserName;
    
    /** 金额 */
    @Excel(name = "*金额")
    private BigDecimal payAmount;


    @TableField(exist = false)
    @Excel(name = "开户行")
    private String bankName;
    
    @TableField(exist = false)
    @Excel(name = "开户地")
    private String branchName;

    @TableField(exist = false)
    @Excel(name = "汇款备注")
    private String remark;

    @TableField(exist = false)
    @Excel(name = "提现申请单ids")
    private String  recordIds;

    @TableField(exist = false)
    private List<String> recordIdList;

    @TableField(exist = false)
    //订单任务类型  12.咨询  21.团督  22.个督  23.体验
    private String scheduleType;
    @TableField(exist = false)
    private Long payUserId;//付款用户id
    @TableField(exist = false)
    private Integer payUserType;//付款用户类型
    @TableField(exist = false)
    private String payUserName;//付款用户名称
    @TableField(exist = false)
    private Integer payAndChargeNum;//收费人付费人之间第几次合作/团督第几次讲课
    @TableField(exist = false)
    private String serveName;//服务名称
    
    @TableField(exist = false)
    private String serverTitle;//服务类型标题
    @TableField(exist = false)
    private Date useTime;//使用时间
    
}
