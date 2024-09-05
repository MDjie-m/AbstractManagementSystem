package com.ruoyi.billiard.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 门店员工对象 t_store_user
 * 
 * @author ruoyi
 * @date 2024-09-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StoreUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工id */
    private Long storeUserId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String realName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 头像 */
    @Excel(name = "头像")
    private String userImg;

    /** 门店状态（0正常 1停用） */
    @Excel(name = "门店状态", readConverterExp = "0=正常,1=停用")
    private Long status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 登录账户id */
    @Excel(name = "登录账户id")
    private Long loginUserId;


}
