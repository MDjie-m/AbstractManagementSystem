package com.ruoyi.common.utils;

import com.ruoyi.common.enums.IEnum;

public class EnumUtil {

    /**
     * Gets i enum.
     *
     * @param <T>        the type parameter
     * @param targetType the targer type
     * @param source     the source
     * @return the i enum
     */
    public static <T extends IEnum> IEnum getIEnum(Class<T> targetType, Integer source) {
        for (T enumObj : targetType.getEnumConstants()) {
            if (source.equals(enumObj.getValue())) {
                return enumObj;
            }
        }
        return null;
    }
    public static IEnum   getIEnumByClass(Class<? extends IEnum> targetType, Integer source) {
        for (IEnum enumObj : targetType.getEnumConstants()) {

            if (source.equals(enumObj.getValue())) {
                return  enumObj;
            }
        }
        return null;
    }
}
