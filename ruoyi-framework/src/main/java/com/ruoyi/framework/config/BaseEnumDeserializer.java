package com.ruoyi.framework.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import com.ruoyi.common.enums.EnumConvertFactory;
import com.ruoyi.common.enums.IEnum;
import com.ruoyi.common.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import java.io.IOException;
import java.util.Objects;

/**
 * IEnum反序列化
 */
@Slf4j
public class BaseEnumDeserializer extends JsonDeserializer<IEnum> {
    public static JsonDeserializer<?> instance=new BaseEnumDeserializer();

    @Override
    public IEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String currentName = p.currentName();
        Object currentValue = p.getCurrentValue();
        Class<? extends  IEnum> findPropertyType = (Class<? extends  IEnum>) BeanUtils.findPropertyType(currentName, currentValue.getClass());
        if (findPropertyType == null) {
            log.info("在" + currentValue.getClass() + "实体类中找不到" + currentName + "字段");
            return null;
        }

        Integer asInt = node.asInt();
        if (Objects.isNull( asInt)) {
            return null;
        }

        return EnumUtil.getIEnum(findPropertyType,asInt);


    }
}
