package com.ruoyi.generator.filter;


import com.ruoyi.generator.domain.bo.GenCustomBo;

public interface DataFilter {

    void doFilter(DataFilterChain openFilterChain, GenCustomBo customBo) throws Exception;

}
