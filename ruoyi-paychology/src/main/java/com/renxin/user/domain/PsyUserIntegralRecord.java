package com.renxin.user.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BasePlusEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户积分记录对象 psy_user_integral_record
 * 
 * @author renxin
 * @date 2023-07-10
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_user_integral_record")
public class PsyUserIntegralRecord extends BasePlusEntity implements Serializable
{
    private static final long serialVersionUID = 9067749661646661L;

    /** 用户uid */
    @Excel(name = "用户uid")
    private Long uid;

    /** 关联单号 */
    @Excel(name = "关联单号")
    private String linkId;

    /** 关联类型（1-register,2-order,3-course,4-gauge,0-system） */
    @Excel(name = "关联类型", readConverterExp = "1-register,2-order,3-course,4-gauge,0-system")
    private Integer linkType;

    /** 类型：1-增加，2-扣减 */
    @Excel(name = "类型：1-增加，2-扣减")
    private Integer type;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 积分 */
    @Excel(name = "积分")
    private Integer integral;

    /** 剩余 */
    @Excel(name = "剩余")
    private Integer balance;

    /** 备注 */
    @Excel(name = "备注")
    private String mark;

    /** 冻结期时间（天） */
    @Excel(name = "冻结期时间", readConverterExp = "天=")
    private Integer frozenTime;

    /** 解冻时间 */
    @Excel(name = "解冻时间")
    private Date thawTime;

    /** 删除标志（0代表存在 1代表删除） */
    private Integer delFlag;

    /** 状态：1-订单创建，2-冻结期，3-完成，4-失效（订单退款） */
    @Excel(name = "状态：1-订单创建，2-冻结期，3-完成，4-失效", readConverterExp = "订=单退款")
    private Integer status;

}
