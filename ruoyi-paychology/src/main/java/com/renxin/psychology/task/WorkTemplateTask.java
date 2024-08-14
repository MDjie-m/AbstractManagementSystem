package com.renxin.psychology.task;

import com.renxin.common.utils.NewDateUtil;
import com.renxin.psychology.domain.PsyConsultantWorkTemplate;
import com.renxin.psychology.service.IPsyConsultBillItemService;
import com.renxin.psychology.service.IPsyConsultBillService;
import com.renxin.psychology.service.IPsyConsultantWorkTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component("WorkTemplateTask")
public class WorkTemplateTask {

    @Autowired
    private IPsyConsultantWorkTemplateService psyConsultantWorkTemplateService;
    
    public void refresh()
    {
        PsyConsultantWorkTemplate req = new PsyConsultantWorkTemplate();
        req.setIsAutoFill("Y");
        //下下个月的起止日期
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = sdf.format(calendar.getTime()).substring(0, 8) + "01";
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = sdf.format(calendar.getTime());
        req.setStartDate(startDate);
        req.setEndDate(endDate);
        //执行生成排程
        psyConsultantWorkTemplateService.executeConsultantWorkTemplate(req);
    }

}
