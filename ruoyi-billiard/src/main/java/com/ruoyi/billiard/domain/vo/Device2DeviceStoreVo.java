package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.domain.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-10-21:22
 * @className: Device2DeviceStoreVo
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device2DeviceStoreVo extends Device {

    /** 门店照片 */
    private String storeImg;

    /** 门店地址 */
    private String storeAddress;

    /** 门店状态（0正常 1闭店） */
    private Integer storeStatus;

}
