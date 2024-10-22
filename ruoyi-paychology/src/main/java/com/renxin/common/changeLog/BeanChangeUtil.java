package com.renxin.common.changeLog;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author FengQing
 * @program yf-client
 * @description
 * @date 2023/11/01
 */
@Slf4j
public class BeanChangeUtil<T> {

    /**
     * 传入两个相同类型的对象，对比属性得到修改信息
     * @param oldBean
     * @param newBean
     * @return 属性修改信息
     */
    public static <aClass> String getChangeInfo(Object oldBean, Object newBean){
        Class aClass = oldBean.getClass();
        BeanChangeUtil<aClass> t = new BeanChangeUtil<>();
        ChangePropertyMsg cfs = t.contrastObj(oldBean, newBean);
        if (ObjectUtils.isNotEmpty(cfs.getChangeMsg())) {
            return cfs.getChangeMsg();
        }
        return null;
    }
    /**
     * 传入两个相同类型的对象，对比属性得到修改信息
     * @param oldBean
     * @param newBean
     * @return **完整属性修改信息**
     */
    public ChangePropertyMsg contrastObj(Object oldBean, Object newBean) {
        // 转换为传入的泛型T
        T oldPojo = (T) oldBean;
        // 通过反射获取类型及字段属性
        Field[] fields = oldPojo.getClass().getDeclaredFields();
        return jdk8OrAfter(Arrays.asList(fields), oldPojo, (T) newBean);
    }

    // lambda表达式，表达式内部的变量都是final修饰，需要传入final类型的数组
    private ChangePropertyMsg jdk8OrAfter(List<Field> fields, T oldBean, T newBean) {
        ChangePropertyMsg cf = new ChangePropertyMsg();
        // 创建字符串拼接对象
        StringBuilder str = new StringBuilder();
        List<String> fieldList = new ArrayList<>();
        fields.forEach(field -> {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ApiModelProperty.class)) {
                try {
                    // 获取属性值
                    Object newValue = field.get(newBean);
                    Object oldValue = field.get(oldBean);
                    if (ObjectUtils.isNotEmpty(newValue)) {
                        if (ObjectUtils.notEqual(oldValue, newValue)) {
                            boolean isOldValueArray = ArrayUtil.isArray(oldValue);
                            boolean isNewValueArray = ArrayUtil.isArray(newValue);

                            if (isOldValueArray && isNewValueArray) {
                                Object[] oldArray = (Object[]) oldValue;
                                Object[] newArray = (Object[]) newValue;
                                if (!Arrays.deepEquals(oldArray, newArray)) {
                                    fieldList.add(field.getName());
                                    str.append(field.getAnnotation(ApiModelProperty.class).value() + "：");
                                    str.append("修改前=【" + Arrays.toString(oldArray) + "】，修改后=【" + Arrays.toString(newArray) + "】;\n");
                                    cf.addChange(field.getAnnotation(ApiModelProperty.class).value(),
                                            Arrays.toString(oldArray),
                                            Arrays.toString(newArray),
                                            field.getName());
                                }
                            } else {
                                fieldList.add(field.getName());
                                str.append(field.getAnnotation(ApiModelProperty.class).value() + "：");
                                str.append("修改前=【" + formatPropertyValue(oldValue) + "】，修改后=【" + formatPropertyValue(newValue) + "】;\n");
                                cf.addChange(field.getAnnotation(ApiModelProperty.class).value(),
                                        formatPropertyValue(oldValue),
                                        formatPropertyValue(newValue),
                                        field.getName());
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("比对Bean属性是否变化失败，", e);
                }
            }
        });
        cf.setChangeMsg(str.toString());
        cf.setProperties(fieldList);
        return cf;
    }

    /**
     * 时间处理
     * @param value
     * @return
     */
    private static Object formatPropertyValue(Object value) {
        if (value instanceof Date) {
            return DateUtil.format((Date) value, "yyyy-MM-dd");
        } else if (value instanceof LocalDateTime) {
            LocalDateTime localDateTimeValue = (LocalDateTime) value;
            return localDateTimeValue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        return value;
    }
}