package com.ruoyi.billiard.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@ApiModel(value = "YingShiYunEntity", description = "萤石云实体")
public class YingShiYunVo {
    private String appKey;
    private String accessToken;
    private List<Device2DeviceStoreVo> device2DeviceStoreVos;
}