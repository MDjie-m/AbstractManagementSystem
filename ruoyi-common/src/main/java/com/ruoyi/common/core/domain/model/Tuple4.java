package com.ruoyi.common.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tuple4<T, T1,T2,T3> {
    private T value;
    private T1 value1;
    private T2 value2;
    private T3 value3;

}
