package com.renxin.common.enums;

/**
 * 用户状态
 * 
 * @author renxin
 */
public enum ServiceObjectEnum
{
    CONSULTED("1", "来访者"),
    PERSON_SUP("2", "个案督导"),
    PERSON_EXP("3", "个人体验");

    private final String key;
    private final String value;

    // 构造函数
    ServiceObjectEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // 获取键
    public String getKey() {
        return key;
    }

    // 获取值
    public String getValue() {
        return value;
    }

    // 根据键获取对应的枚举实例
    public static ServiceObjectEnum fromKey(String key) {
        for (ServiceObjectEnum e : values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        throw new IllegalArgumentException("No enum constant with key " + key);
    }

    // 根据值获取对应的枚举实例
    public static ServiceObjectEnum fromValue(String value) {
        for (ServiceObjectEnum e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("No enum constant with value " + value);
    }
}
