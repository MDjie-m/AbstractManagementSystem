package com.ruoyi.generator.filter;

import cn.hutool.core.collection.CollectionUtil;

import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.generator.constants.CustomConstants;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.domain.bo.GenCustomBo;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据字典处理
 * @Description TODO
 * @Author nuoqin
 * @DATA 2024/8/19 10:22
 */

public class DictDataFilter implements DataFilter {

    @Override
    public void doFilter(DataFilterChain openFilterChain, GenCustomBo customBo) throws Exception {
        if (CollectionUtil.isNotEmpty(customBo.getData())){
            handleDictData(customBo.getColumns(),customBo.getData());
        }
        openFilterChain.doFilter(openFilterChain,customBo);
    }

    public void handleDictData(List<GenTableColumn> tableColumns, List<Map<String, Object>> list) {
        List<GenTableColumn> dictColumns = tableColumns.stream()
                .filter(d -> d.getHtmlType().equals(CustomConstants.SELECT)
                    || d.getHtmlType().equals(CustomConstants.CHECKBOX)
                    || d.getHtmlType().equals(CustomConstants.RADIO)
                )
                .collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(dictColumns)){
            for (Map<String, Object> map : list) {
                for (GenTableColumn column : dictColumns) {
                    Object o = map.get(column.getJavaField());
                    if (ObjectUtils.isEmpty(o)){
                        continue;
                    }
                    String dictValue = DictUtils.getDictLabel(column.getDictType(), o.toString());
                    map.put(column.getJavaField(),dictValue);
                }
            }
        }
    }
}
