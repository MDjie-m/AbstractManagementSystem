package com.ruoyi.generator.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import com.ruoyi.generator.constants.CustomConstants;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.domain.bo.GenCustomBo;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 日期格式处理
 * @Description TODO
 * @Author nuoqin
 * @DATA 2024/8/19 10:23
 */

public class DateDataFilter implements DataFilter {



    @Override
    public void doFilter(DataFilterChain openFilterChain, GenCustomBo customBo) throws Exception {
        if (CollectionUtil.isNotEmpty(customBo.getData())){
            handleDateData(customBo.getColumns(),customBo.getData());
        }
        openFilterChain.doFilter(openFilterChain,customBo);
    }

    public void handleDateData(List<GenTableColumn> tableColumns, List<Map<String, Object>> list) {
        List<GenTableColumn> dictColumns = tableColumns.stream()
                .filter(d -> d.getJavaType().equals(CustomConstants.DATE))
                .collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(dictColumns)){
            for (Map<String, Object> map : list) {
                handleSingleDateData(dictColumns,map);
            }
        }
    }

    public void handleSingleDateData(List<GenTableColumn> dictColumns, Map<String, Object> map) {
        if (CollectionUtil.isNotEmpty(dictColumns)){
            for (GenTableColumn column : dictColumns) {
                Object o = map.get(column.getJavaField());
                if (ObjectUtils.isEmpty(o)){
                    continue;
                }
                if (o instanceof Date) {
                    Date date= (Date) o;
                    map.put(column.getJavaField(), DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN));
                }else if(o instanceof LocalDate){
                    LocalDate date= (LocalDate) o;
                    map.put(column.getJavaField(), date.toString());
                }else if(o instanceof LocalDateTime){
                    LocalDateTime date= (LocalDateTime) o;
                    map.put(column.getJavaField(), DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN));
                }
            }
        }
    }

}
