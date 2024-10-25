package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.enums.DeskStatus;
import com.ruoyi.common.utils.StringUtils;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 球桌对象 t_store_desk
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@TableName("t_store_desk")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDesk extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 球桌编码 */

    @TableId("desk_id")
    private Long deskId;

    /** 球桌名 */
    @Excel(name = "球桌名")

    @TableField("desk_name")
    private String deskName;

    /** 编号 */
    @Excel(name = "编号")

    @TableField("desk_num")
    private Integer deskNum;

    /** 球桌类型：0=中式，1=美式，2=斯诺克，3=棋牌 */
    @Excel(name = "球桌类型：0=中式，1=美式，2=斯诺克，3=棋牌")

    @TableField("desk_type")
    private Long  deskType;

    /** 位置：0=大厅，1=包厢 */
    @Excel(name = "位置：0=大厅，1=包厢")

    @TableField("place_type")
    private Long  placeType;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 价格/分钟 */
    @Excel(name = "价格/分钟")

    @TableField(exist = false)
    private BigDecimal price;

    /** 状态：0=空闲，1=计时中， 3=已停止 */
    @Excel(name = "状态：0=空闲，1=计时中， 3=已停止")

    @TableField("status")
    private DeskStatus status;

    @TableField("current_order_id")
    private Long currentOrderId;

    @TableField(exist = false)
    private List<Integer> statusList;
    @TableField("enable")
    private Boolean enable;


    /** 灯光id */
    @Excel(name = "灯光id")

    @TableField("light_device_id")
    private Long lightDeviceId;

    /** 摄像头设备id */
    @Excel(name = "摄像头设备id")
    @TableField("camera_device_id")
    private Long cameraDeviceId;



    @TableField(exist = false)
    private String storeName;

    @TableField(exist = false)
    private Boolean lightStatus;




    @TableField(exist = false)
    private Date bookingStart;

    @TableField(exist = false)
    private Date bookingEnd;


    @TableField(exist = false)
    private Long bookingCount;


    @TableField(exist = false)
    private Boolean queryLastBooking;

    @TableField(exist = false)
    private DeskBooking booking;


    @TableField(exist = false)
    private String placeTypeName;

    @TableField(exist = false)
    private String deskTypeName;
    public String getLongTitle() {
        return StringUtils.format("{}({})/{}", deskName, deskNum, Objects.nonNull(placeTypeName)?placeTypeName:"");
    }
    public String getTitle() {
        return getLongTitle();
    }
    public String getShortTitle() {
        return StringUtils.format("{}({})", deskName, deskNum);
    }


    @TableField(exist = false)
    private Boolean queryTime;
    @TableField(exist = false)
    private Long minutes;
}
