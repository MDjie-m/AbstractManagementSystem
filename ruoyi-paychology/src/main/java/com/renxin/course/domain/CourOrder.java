package com.renxin.course.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程订单对象 cour_order
 * 
 * @author renxin
 * @date 2023-03-17
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderId;

    /** 订单编号 */
    @Excel(name = "下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 订单状态（0-创建 1-完成 2-关闭） */
    @Excel(name = "订单状态", readConverterExp = "0=-创建,1=-完成,2=-关闭")
    private Integer status;

    /** 用户编号 */
    @Excel(name = "用户ID")
    private Long userId;

    /** 应付金额 */
    @Excel(name = "应付金额")
    private BigDecimal amount;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 课程信息 */
    @Excel(name = "课程信息")
    private CourCourse courseInfo;
  
}
