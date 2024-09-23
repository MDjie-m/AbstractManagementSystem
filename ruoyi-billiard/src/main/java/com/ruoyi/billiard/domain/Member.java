package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import java.math.BigDecimal;

/**
 * 门店会员对象 t_member
 *
 * @author zhoukeu
 * @date 2024-09-14
 */
@TableName("t_member")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends MyBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */

    @TableId("member_id")
    private Long memberId;

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
     * 当前金额
     */
    @Excel(name = "当前金额")

    @TableField("current_amount")
    private BigDecimal currentAmount;

    /**
     * 历史总金额
     */
    @Excel(name = "历史总金额")

    @TableField("total_amount")
    private BigDecimal totalAmount;

    @TableField("pay_password")
    private String payPassword;

    /**
     * 性别（0=男，1=女，2=未知）
     */
    @Excel(name = "性别", readConverterExp = "0==男，1=女，2=未知")

    @TableField("sex")
    private String sex;

    /**
     * 门店
     */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /**
     * 会员等级
     */
    @Excel(name = "会员等级")

    @TableField("level_id")
    private Long levelId;

    @TableField(exist = false)
    private String levelName;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")

    @TableField("status")
    private Integer status;

    /**
     * 删
     * 除标志（0代表存在 2代表删除）
     */

    @TableField("del_flag")
    private String delFlag;

    /**
     * 创建者Id
     */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /**
     * 更新者Id
     */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;

    @TableField(exist = false)
    private String storeName;


    @TableField(exist = false)
    private String keyword;


}
