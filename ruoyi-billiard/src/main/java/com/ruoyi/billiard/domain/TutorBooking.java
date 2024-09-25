package com.ruoyi.billiard.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 教练预约对象 t_tutor_booking
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@TableName("t_tutor_booking")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorBooking extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("tutor_booking_id")
    private Long tutorBookingId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("start_time")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("end_time")
    private Date endTime;

    /** 助教id */
    @Excel(name = "助教id")

    @TableField("tutor_id")
    private Long tutorId;

    /** 预约人姓名 */
    @Excel(name = "预约人姓名")

    @TableField("booking_user_name")
    private String bookingUserName;

    /** 预约人手机号 */
    @Excel(name = "预约人手机号")

    @TableField("booking_user_mobile")
    private String bookingUserMobile;

    /** 0=有效，1=预约生效,2=过期 */
    @Excel(name = "0=有效，1=预约生效,2=过期")

    @TableField("status")
    private Integer status;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;





    @TableField("store_id")
    private Long storeId;






}
