package com.ruoyi.billiard.task;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
public abstract class  BaseTask<T> implements ITask<T> {
    private Class<T> type;

    public BaseTask() {
        this.type = getTypeClass();
    }

    @Override
    public void run(String params) {
//        log.info(StringUtils.format("-----【{}】任务开始,参数:{}---------",getTaskName(),params) );
        StopWatch watch=new StopWatch();
        watch.start();
        try {
            exec(StringUtils.isEmpty(params)?null:JSON.parseObject(params, this.type));
            watch.stop();
//            log.info(StringUtils.format("-----【{}】任务结束,耗时:{}秒--------",getTaskName(),watch.getTotalTimeSeconds()) );
        }catch (Exception e) {
            watch.stop();
            log.error(StringUtils.format("-----【{}】任务异常,耗时:{}秒--------", getTaskName(),
                    watch.getTotalTimeSeconds()));
        }
    }

    protected abstract void exec(T t);
    protected abstract String getTaskName();
    private Class<T> getTypeClass() {
        Type superClass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superClass;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        return (Class<T>) typeArguments[0];
    }
}
