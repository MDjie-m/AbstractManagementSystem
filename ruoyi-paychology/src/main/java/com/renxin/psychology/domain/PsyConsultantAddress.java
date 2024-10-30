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
 * 咨询师地址对象 psy_consultant_address
 * 
 * @author renxin
 * @date 2024-06-20
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_consultant_address")
public class PsyConsultantAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地址ID */
    private Long addressId;

    /** 咨询师ID */
    @Excel(name = "咨询师ID")
    private Long consultantId;

    /** 1 默认 0 未默认 */
    @Excel(name = "1 默认 0 未默认")
    private String status;

    /** 联系人名 */
    @Excel(name = "联系人名")
    private String contactName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactTelephone;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String contactAddress;
    
    //地区
    private String region;

    /** 0 未删除 1 删除 */
    private String delFlag;


}
