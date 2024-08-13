package com.renxin.psychology.dto;


import com.renxin.psychology.domain.PsyConsultOrderItem;
import com.renxin.psychology.domain.PsyConsultantSchedule;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 近期工作安排
 */
@Data
public class RecentWorkDTO {

    private String date;//日期
    private List<PsyConsultantSchedule> scheduleList;//任务清单

}
