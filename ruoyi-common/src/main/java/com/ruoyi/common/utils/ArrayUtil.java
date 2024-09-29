package com.ruoyi.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayUtil {
    /**
     * List去重，不打乱原来顺序，泛型list对象
     * 对象重写hashCode和equals
     *
     * @param <T>
     * @param list
     * @return
     */
    public static <T> List<T> distinctBySetOrder(List<T> list) {
        Set<T> set = new HashSet<T>();
        List<T> newList = new ArrayList<T>();
        for (T t : list) {
            if (set.add(t)) {
                newList.add(t);
            }
        }
        return newList;
    }

    /**
     * List去重，可能打乱原来顺序，泛型list对象
     * 对象重写hashCode和equals
     *
     * @param list
     * @return
     */
    public static <T> List<T> distinctBySet(List<T> list) {
        return new ArrayList<T>(new HashSet<T>(list));
    }

    /**
     * list转为字符串，专用于sql中in函数
     *
     * @param list
     * @return String
     */

    public static <T, K, V> Map<K, V> toMap(List<T> list, Function<? super T, ? extends K>
            keyMapper,     Function<? super T, ? extends V> valueMapper) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, (a, b) -> b));
    }
    public static <T, K, V> Map<K, List<V>> groupByValue(List<T> list, Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends V> valueMapper) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }

        Map<K,List<T>>  itemValue=  groupBy(list,keyMapper);
        Map<K,List<V>> resMap=new HashMap<>();
        itemValue.forEach((k,v)->{
            resMap.put(k,v.stream().map(valueMapper).collect(Collectors.toList()));
        });
        return resMap;
    }
    public static <T, K, V> Map<K, List<T>> groupBy (List<T> list, Function<? super T, ? extends K> keyMapper ) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }

       return   list.stream().collect(Collectors.groupingBy(keyMapper));
    }
    public static String strListToSqlJoin(List<String> list) {
        if (null == list || list.size() < 1) {
            return "";
        }
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                temp.append(",");
            }
            temp.append("'").append(list.get(i)).append("'");
        }
        return temp.toString();
    }
}