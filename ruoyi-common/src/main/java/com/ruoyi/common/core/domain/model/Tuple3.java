package com.ruoyi.common.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tuple3<T, T1,T2> {
    private T value;
    private T1 value1;
    private T2 value2;

}
