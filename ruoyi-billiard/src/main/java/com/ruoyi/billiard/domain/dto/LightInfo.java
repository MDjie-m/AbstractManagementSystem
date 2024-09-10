package com.ruoyi.billiard.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class LightInfo implements Serializable {
    private Integer signal;
    private String imei;
    private String iccid;
    private String type;
    private String version;
    private Integer key;
    private Integer resetLock;
    private Integer keyLock;
    private Integer timerEnable;
    private Integer timerInterval;
}
