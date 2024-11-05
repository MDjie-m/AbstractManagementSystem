package com.ruoyi.generator.filter;


import com.ruoyi.generator.domain.bo.GenCustomBo;
import org.apache.commons.lang3.ObjectUtils;



/**
 * @Description TODO
 * @Author nuoqin
 * @DATA 2024/8/19 11:24
 */

public class SingleDateFilter extends DateDataFilter {

    @Override
    public void doFilter(DataFilterChain openFilterChain, GenCustomBo customBo) throws Exception {
        if (ObjectUtils.isNotEmpty(customBo.getSingleData())){
            super.handleSingleDateData(customBo.getColumns(), customBo.getSingleData());
        }
        openFilterChain.doFilter(openFilterChain,customBo);
    }

}
