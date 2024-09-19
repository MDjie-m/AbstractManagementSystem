package com.ruoyi.billiard.task;

import com.ruoyi.billiard.service.IDeviceService;
import com.ruoyi.billiard.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 订单定时计费
 */
@Component("orderTime")
@Slf4j
public class OrderTimerTask
{


    @Resource
    IOrderService orderService;
    public void checkStatus( )
    {
        log.info("订单计费时间检查" );
        orderService.checkOrderTimer();
        log.info("订单计费时间检查任务完成" );
    }

}
