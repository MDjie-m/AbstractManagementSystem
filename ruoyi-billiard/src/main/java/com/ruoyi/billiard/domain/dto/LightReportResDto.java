package com.ruoyi.billiard.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class LightReportResDto  extends BaseLightResDto implements Serializable {

    private Integer current;
    private Integer voltage;
    private Integer power;
    private Integer energy;
}
