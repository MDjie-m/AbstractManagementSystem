package com.ruoyi.billiard.task;

import com.ruoyi.billiard.service.IDeskBookingService;
import com.ruoyi.billiard.service.ITutorBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("tutorBookingCheck")
@Slf4j
public class TutorBookingCheckTask extends BaseTask<Integer>  {

    @Resource
    private ITutorBookingService tutorBookingService;
    @Override
    protected void exec(Integer time) {
        tutorBookingService.checkBookingExpire(time);
    }

    @Override
    protected String getTaskName() {
        return "教练预约过期检查";
    }
}
