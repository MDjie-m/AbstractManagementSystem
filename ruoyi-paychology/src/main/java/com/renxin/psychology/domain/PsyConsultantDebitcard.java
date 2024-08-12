package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 客户银行卡对象 psy_consultant_debitcard
 * 
 * @author renxin
 * @date 2024-06-20
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_consultant_debitcard")
public class PsyConsultantDebitcard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借记卡ID */
    private String cardNumber;

    /** 咨询师ID */
    @Excel(name = "咨询师ID")
    private Long consultantId;

    /** 借记卡所属银行 */
    @Excel(name = "借记卡所属银行")
    private String bankName;
    
    private String bankCode;

    /** 1 正常 0 未激活 */
    @Excel(name = "1 正常 0 未激活")
    private String status;

    /** 0 未删除 1 删除 */
    private String delFlag;
    
    //支行名称
    private String branchName;
    //持卡人姓名
    private String cardUserName;
    //持卡人身份证号
    private String idCard;

  
}
