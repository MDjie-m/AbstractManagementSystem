package com.ruoyi.billiard.task;

import com.ruoyi.billiard.service.IDeviceService;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 灯光设备状态检查任务
 *
 * @author ruoyi
 */
@Component("lightTask")
@Slf4j
public class LightTask
{


    @Resource
    IDeviceService deviceService;
    public void checkStatus(Long time )
    {
        log.info("灯光设备状态检查任务开始：{}",time );
        deviceService.lightStatusCheckJob(time);
        log.info("灯光设备状态检查任务检查完成" );
    }

}
