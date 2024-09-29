package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.enums.PreferentialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreferentialVo {

    private PreferentialType type;

    private BigDecimal amount;

}
