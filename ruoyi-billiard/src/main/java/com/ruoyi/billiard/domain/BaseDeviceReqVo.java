package com.ruoyi.billiard.domain;

import com.ruoyi.common.utils.AESUtils;
import com.ruoyi.common.utils.AssertUtil;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BaseDeviceReqVo {

    @NotNull(message = "非法请求")
    private String apiKey;

    private Long  storeId;

}
