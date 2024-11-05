package com.ruoyi.generator.filter;


import com.ruoyi.generator.domain.bo.GenCustomBo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author nuoqin
 * @DATA 2024/8/19 10:20
 */

public class DataFilterChain implements DataFilter {

    private int size;

    private final List<DataFilter> openFilters=new ArrayList<>();

    private AtomicInteger offset=new AtomicInteger(0);


    public void add(DataFilter filter){
        size++;
        openFilters.add(filter);
    }

    @Override
    public void doFilter(DataFilterChain openFilterChain, GenCustomBo customBo) throws Exception {
        if (size==0) return;
        if (offset.get()>=size) {
            offset.set(0);
            return;
        }
        DataFilter filter = openFilters.get(offset.get());
        offset.getAndIncrement();
        filter.doFilter(openFilterChain,customBo);
    }


}
