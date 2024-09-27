package com.ruoyi.framework.config;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ruoyi.common.enums.IEnum;

import java.io.IOException;
import java.math.BigDecimal;

public class BaseEnumSerializer extends JsonSerializer<IEnum> {
    public static JsonSerializer<?> instance=new BaseEnumSerializer();

    @Override
    public void serialize(IEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getValue());
        // 增加一个字段，格式为【枚举类名称+Text】，存储枚举的name
        gen.writeStringField(gen.getOutputContext().getCurrentName() + "Text", value.getDesc());
    }
}