package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.MyBaseEntity;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import java.util.List;

/**
 * 门店助教对象 t_store_tutor
 *
 * @author ruoyi
 * @date 2024-09-06
 */
@TableName("t_store_tutor")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreTutor extends MyBaseEntity {
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<Long> roleIds;

    @TableField(exist = false)
    private String storeName;

    /**
     * 员工id
     */

    @TableId("store_tutor_id")
    private Long storeTutorId;

    /**
     * 姓名
     */
    @Excel(name = "姓名")

    @TableField("real_name")
    private String realName;

    /**
     * 手机号
     */
    @Excel(name = "手机号")

    @TableField("mobile")
    private String mobile;

    /**
     * 头像
     */
    @Excel(name = "头像")

    @TableField("user_img")
    private String userImg;

    /**
     * 性别
     */
    @Excel(name = "性别")

    @TableField("sex")
    private String sex;

    /**
     * 助教等级(1=助教，2=教练，3=总教)
     */
    @Excel(name = "助教等级(1=助教，2=教练，3=总教)")

    @TableField("level")
    private Long level;

    /**
     * 门店状态（0正常 1停用）
     */
    @Excel(name = "教练状态", readConverterExp = "0=正常,1=停用")

    @TableField("status")
    private Integer status;
    @Excel(name = "计费状态", readConverterExp = "0=空闲,1=计费中，3=已停止")
    @TableField("work_status")
    private Integer workStatus;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @Excel(name = "删除标志", readConverterExp = "0=代表存在,2=代表删除")

    @TableField("del_flag")
    private String delFlag;

    /**
     * 登录账户id
     */
    @Excel(name = "登录账户id")

    @TableField("login_user_id")
    private Long loginUserId;

    /**
     * 门店
     */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /**
     * 资质
     */
    @TableField("aptitude")
    private String aptitude;

    @TableField(exist = false)
    private String account;

}
