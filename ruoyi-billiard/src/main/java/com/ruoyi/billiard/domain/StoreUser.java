package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import java.util.List;

/**
 * 门店员工对象 t_store_user
 *
 * @author ruoyi
 * @date 2024-09-06
 */
@TableName("t_store_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class StoreUser extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工id */

    @TableId("store_user_id")
    private Long storeUserId;

    /** 姓名 */
    @Excel(name = "姓名")

    @TableField("real_name")
    private String realName;

    /** 手机号 */
    @Excel(name = "手机号")

    @TableField("mobile")
    private String mobile;

    /** 头像 */
    @Excel(name = "头像")

    @TableField("user_img")
    private String userImg;

    /** 门店状态（0正常 1停用） */
    @Excel(name = "门店状态", readConverterExp = "0=正常,1=停用")

    @TableField("status")
    private Long status;

    /** 删除标志（0代表存在 2代表删除） */

    @TableField("del_flag")
    private String delFlag;

    /** 登录账户id */
    @Excel(name = "登录账户id")

    @TableField("login_user_id")
    private Long loginUserId;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    @TableField("sex")
    private String sex;

    @TableField(exist = false)
    private List<Long> roleIds;

    @TableField("store_id")
    private Long storeId;

    @TableField(exist = false)
    private String storeName;

    @TableField(exist = false)
    private String account;

}
