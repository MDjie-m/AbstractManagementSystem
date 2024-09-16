package com.ruoyi.common.config;


import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.ruoyi.common.core.mapper.InsertBatchMethod;
import com.ruoyi.common.core.mapper.UpdateAllWithIdMethod;
import com.ruoyi.common.core.mapper.UpdateBatchMethod;
import com.ruoyi.common.core.mapper.UpdateWithNullMethod;

import java.util.List;

public class CustomizedSqlInjector extends DefaultSqlInjector {
    /**
     * 如果只需增加方法，保留mybatis plus自带方法，
     * 可以先获取super.getMethodList()，再添加add
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass,tableInfo);
        methodList.add( new InsertBatchMethod());
        methodList.add( new UpdateBatchMethod());
        methodList.add( new UpdateWithNullMethod());
        methodList.add(new UpdateAllWithIdMethod());
        return methodList;
    }
}