package com.ruoyi.system.service.impl.scheduledTasks;

import com.ruoyi.system.mapper.SysProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class UpdateProductStatus {
    @Autowired
    private SysProductMapper sysProductMapper;

    /**
     * 定时任务每天零点1分自动更新数据库询价状态和报价状态。
     */
    @Scheduled(cron = "0 1 0 * * ?")
    public void updateQuotationAndInquiryStatus(){
        sysProductMapper.updateProductStatus();//执行mapper层的方法，调用sql更新
    }
}
