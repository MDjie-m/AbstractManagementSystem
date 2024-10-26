package com.ruoyi.billiard.task;

import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.billiard.service.ITutorBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("appDashboardTask")
@Slf4j
public class AppDashboardTask extends BaseTask<String>  {

    @Resource
    private IStoreService storeService;
    @Override
    protected void exec(String any) {
        storeService.refreshStoreAppDashboard( );
    }

    @Override
    protected String getTaskName() {
        return "定时刷新门店概览";
    }
}
