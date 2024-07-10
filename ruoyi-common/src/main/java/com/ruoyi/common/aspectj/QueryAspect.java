package com.ruoyi.common.aspectj;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.ruoyi.common.annotation.Query;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class QueryAspect {
    /**
     * @param queryWrapper QueryWrapper userQueryWrapper = new QueryWrapper<>(new User());
     * @param query        查询对象
     * @param <Q>
     */
    @SneakyThrows
    public static <Q> void wrapper(QueryWrapper queryWrapper, Q query) {
        List<Field> fields = getAllFields(query.getClass(), new ArrayList<>());
        for (Field field : fields) {
            boolean accessible = field.isAccessible();
            // 设置对象的访问权限，保证对private的属性的访
            field.setAccessible(true);

            //排序
            sortHelp(queryWrapper, query, field);

            Query q = field.getAnnotation(Query.class);
            if (q != null) {
                String propName = q.propName();
                String attributeName = isBlank(propName) ? getColumn(queryWrapper, field.getName()) : propName;
                if (StringUtils.isEmpty(attributeName)) {
                    continue;
                }
                Object val = field.get(query);
                if (ObjectUtil.isNull(val) || "".equals(val)) {
                    continue;
                }
                switch (q.type()) {
                    case EQUAL:
                        queryWrapper.eq(attributeName, val);
                        break;
                    case GREATER_THAN:
                        queryWrapper.gt(attributeName, val);
                        break;
                    case LESS_THAN:
                        queryWrapper.le(attributeName, val);
                        break;
                    case LESS_THAN_NQ:
                        queryWrapper.lt(attributeName, val);
                        break;
                    case INNER_LIKE:
                        queryWrapper.like(attributeName, val);
                        break;
                    case LEFT_LIKE:
                        queryWrapper.likeLeft(attributeName, val);
                        break;
                    case RIGHT_LIKE:
                        queryWrapper.likeRight(attributeName, val);
                        break;
                    case IN:
                        queryWrapper.in(attributeName, val);
                        break;
                    case NOT_IN:
                        queryWrapper.notIn(attributeName, val);
                        break;
                    case NOT_EQUAL:
                        queryWrapper.ne(attributeName, val);
                        break;
                    case NOT_NULL:
                        queryWrapper.isNotNull(attributeName);
                        break;
                    case IS_NULL:
                        queryWrapper.isNull(attributeName);
                        break;
                    case BETWEEN:
                        List<Object> between = new ArrayList<>((List<Object>) val);
                        if (!between.isEmpty()) {
                            queryWrapper.between(attributeName, between.get(0), between.get(1));
                        }
                        break;
                    default:
                        break;
                }
            }
            field.setAccessible(accessible);
        }
    }

    public static void main(String[] args) {
        String msg = "tv_status,desc,id,desc";
        String[] split = msg.split(",");
        if (split.length % 2 != 0) {
            return;
        }
        for (int i = 0; i < split.length - 1; i += 2) {
            System.out.println("列：" + split[i] + "  值：" + split[i + 1]);
        }
    }

    @SneakyThrows
    private static <Q> void sortHelp(QueryWrapper queryWrapper, Q query, Field field) {
        String fieldName = field.getName();
        if (!"sortStr".equals(fieldName)) {
            return;
        }
        String sortV = (String) field.get(query);
        if (StringUtils.isEmpty(sortV)) {
            return;
        }
        String[] split = sortV.split(",");
        if (split.length % 2 != 0) {
            return;
        }
        for (int i = 0; i < split.length - 1; i += 2) {
            String column = getColumn(queryWrapper, split[i]);
            if (column == null) {
                column = split[i];
            }

            if ("desc".equals(split[i + 1])) {
                queryWrapper.orderByDesc(column);
            } else {
                queryWrapper.orderByAsc(column);
            }
        }
    }


    private static <T> String getColumn(QueryWrapper<T> queryWrapper, String fieldName) {
        T entity = queryWrapper.getEntity();
        if (entity == null) {
            return null;
        }
        Class<T> aClass = (Class<T>) entity.getClass();
        Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(aClass);
        if (columnMap == null) {
            return null;
        }
        ColumnCache columnCache = columnMap.get(LambdaUtils.formatKey(fieldName));
        if (columnCache == null) {
            return null;
        }
        return columnCache.getColumn();

    }

    private static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static List<Field> getAllFields(Class clazz, List<Field> fields) {
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }
}
