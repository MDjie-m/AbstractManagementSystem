package com.ruoyi.common.enums;

import com.ruoyi.common.utils.EnumUtil;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EnumConvertFactory implements ConverterFactory<String, IEnum> {
    @Override
    public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToIEum<>(targetType);
    }

    private static class StringToIEum<T extends IEnum> implements Converter<String, T> {
        private Class<T> targetType;

        /**
         * Instantiates a new String to i eum.
         *
         * @param targetType the targer type
         */
        public StringToIEum(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            return (T) EnumUtil.getIEnum(this.targetType, Integer.parseInt(source));
        }
    }
}