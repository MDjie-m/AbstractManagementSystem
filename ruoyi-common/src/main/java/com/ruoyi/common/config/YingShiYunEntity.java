package com.ruoyi.common.config;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "YingShiYunEntity", description = "萤石云实体")
public class YingShiYunEntity {
    private String appKey;
    private String appSecret;
    private String accessToken;
    private Long expireTime;
}
