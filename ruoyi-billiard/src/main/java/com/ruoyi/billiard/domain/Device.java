package com.ruoyi.billiard.domain;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.domain.dto.LightDeviceExtendData;
import com.ruoyi.common.core.domain.MyBaseEntity;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.Objects;

/**
 * 设备信息对象 t_device
 *
 * @author ruoyi
 * @date 2024-09-07
 */
@TableName("t_device")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device extends MyBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 设备id
     */

    @TableId("device_id")
    private Long deviceId;

    /**
     * 门店
     */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /**
     * 设备名称
     */
    @Excel(name = "设备名称")

    @TableField("device_name")
    private String deviceName;

    /**
     * 设备编码
     */
    @Excel(name = "设备编码")

    @TableField("device_serial_num")
    private String deviceSerialNum;

    /**
     * 设备类型：0=摄像头,1=灯光
     */
    @Excel(name = "设备类型：0=摄像头,1=灯光")

    @TableField("device_type")
    private Integer deviceType;

    /**
     * json扩展配置
     */

    @TableField("extend_data")
    private JSONObject extendData;

    /**
     * 变化类型：0=未知，1=在线，2=掉线
     */
    @Excel(name = "变化类型：0=未知，1=在线，2=掉线")

    @TableField("status")
    private Integer status;


    @TableField(exist = false)
    private String storeName;


    @TableField(exist = false)
    private Long deskId;

    @TableField("last_report_time")
    private Date lastReportTime;

    /**
     * 各种设备自定义状态：灯光（1=开，0=关）
     */
    @TableField("custom_status")
    private Integer customStatus;

    @Nullable
    public <T> T toCustomExtendData(@NonNull Class<T> cls) {
        if (Objects.isNull(extendData)) {
            return null;
        }
        return JSON.to(cls, extendData);
    }

    public LightDeviceExtendData toLightData(){
        return  toCustomExtendData(LightDeviceExtendData.class);
    }

}
