package com.ruoyi.billiard.task;

import com.ruoyi.billiard.service.IDeskBookingService;
import com.ruoyi.billiard.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("deskBookingCheck")
@Slf4j
public class DeskBookingCheckTask extends BaseTask<Integer>  {

    @Resource
    private IDeskBookingService deskBookingService;
    @Override
    protected void exec(Integer time) {
        deskBookingService.checkBookingExpire(time);
    }

    @Override
    protected String getTaskName() {
        return "台桌预约过期检查";
    }
}
