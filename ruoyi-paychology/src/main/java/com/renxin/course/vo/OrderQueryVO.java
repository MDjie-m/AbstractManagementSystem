package com.renxin.course.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renxin.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 管理端订单列表查询结果
 */
@Data
public class OrderQueryVO implements Serializable {
    /** 订单ID */
    @Excel(name = "订单ID")
    private Long id;
    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderId;
    /** 订单状态 */
    @Excel(name = "订单状态")
    private Integer status;
    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;
    /** 应付金额 */
    @Excel(name = "应付金额")
    private BigDecimal amount;
    /** 下单时间 */
    @Excel(name = "下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;
}
