package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;

/**
 * 设备信息对象 t_device
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@TableName("t_device")
@Data
@EqualsAndHashCode(callSuper = true)
public class Device extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备id */

    @TableId("device_id")
    private Long deviceId;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 设备名称 */
    @Excel(name = "设备名称")

    @TableField("device_name")
    private String deviceName;

    /** 设备编码 */
    @Excel(name = "设备编码")

    @TableField("device_serial_num")
    private String deviceSerialNum;

    /** 设备类型：0=摄像头,1=灯光 */
    @Excel(name = "设备类型：0=摄像头,1=灯光")

    @TableField("device_type")
    private Integer deviceType;

    /** json扩展配置 */

    @TableField("extend_data")
    private String extendData;

    /** 变化类型：0=未知，1=在线，2=掉线 */
    @Excel(name = "变化类型：0=未知，1=在线，2=掉线")

    @TableField("status")
    private Integer status;



    @TableField(exist = false)
    private String storeName;


    @TableField(exist = false)
    private Long deskId;




}
