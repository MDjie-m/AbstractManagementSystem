package com.ruoyi.framework.config;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase;

import java.math.BigDecimal;


@JacksonStdImpl
public class ToPlainTextString extends ToStringSerializerBase {
    public static final ToPlainTextString instance = new ToPlainTextString(BigDecimal.class);

    public ToPlainTextString() {
        super(Object.class);
    }


    public ToPlainTextString(Class<BigDecimal> handledType) {
        super(handledType);
    }

    public final String valueToString(Object value) {
        return ((BigDecimal)value).toPlainString();
    }
}
