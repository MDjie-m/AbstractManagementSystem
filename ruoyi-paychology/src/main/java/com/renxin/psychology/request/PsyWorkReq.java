package com.renxin.psychology.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 咨询订单对象 psy_consult_order
 * 
 * @author renxin
 * @date 2023-06-26
 */
@Data
public class PsyWorkReq implements Serializable
{

    private static final long serialVersionUID = 269755586237920868L;

    /** 月份 */
    private String month;
    private String day;
    private String week;
    private String realTime;
    //起止日期
    private String start;
    private String end;
    private String status;
    //咨询师id
    private Long consultId;
    private Long userId;
    private Long payConsultId;
    private Long chargeConsultId;
    //排程任务id
    private Long scheduleId;
    private Integer scheduleType;
    //请假时间点(逗号分隔)
    private String hours;
    
    //收费人工作记录
    private String workRecord;
    //订单编号
    private String orderNo;

    /** 咨询师 */
    private List<Long> ids;
    
    //来源类型 1来访者   2咨询师
    private Integer sourceType;

}
