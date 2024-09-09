package com.ruoyi.billiard.domain.dto;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class LightDeviceExtendData  implements Serializable {
    private String subTopic;
    private String pubTopic;

}
