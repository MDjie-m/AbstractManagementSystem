package com.ruoyi.billiard.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.domain.vo.IAdd;
import com.ruoyi.billiard.domain.vo.IQuery;
import com.ruoyi.billiard.enums.BookingStatus;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 球桌预约对象 t_desk_booking
 *
 * @author ruoyi
 * @date 2024-09-25
 */
@TableName("t_desk_booking")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeskBooking extends MyBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */

    @TableId("desk_booking_id")
    private Long deskBookingId;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")

    @TableField("start_time")
    @NotNull(groups = {IQuery.class,IAdd.class}, message = "月份不能为空")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(groups ={IQuery.class,IAdd.class}, message = "月份不能为空")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")

    @TableField("end_time")
    private Date endTime;

    /**
     * 球桌id
     */
    @Excel(name = "球桌id")

    @TableField("desk_id")
    @NotNull(groups = {IQuery.class,IAdd.class}, message = "台桌不能为空")
    private Long deskId;

    /**
     * 预约人姓名
     */
    @Excel(name = "预约人姓名")

    @TableField("booking_user_name")
    @NotNull(groups = { IAdd.class}, message = "姓名不能为空")
    private String bookingUserName;

    /**
     * 预约人手机号
     */
    @Excel(name = "预约人手机号")

    @TableField("booking_user_mobile")
    @NotNull(groups = { IAdd.class}, message = "手机号不能为空")
    private String bookingUserMobile;

    /**
     * 0=有效，1=预约生效,2=过期
     */
    @TableField("status")
    private BookingStatus status;

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


    @TableField("store_id")
    private Long storeId;
    @TableField(exist = false)
    private String deskTitle;

    @TableField(exist = false)
    private String keyword;
}
