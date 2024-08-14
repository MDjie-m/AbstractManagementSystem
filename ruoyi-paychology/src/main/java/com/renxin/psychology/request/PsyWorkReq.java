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
    private String start;
    private String end;
    private String week;
    private String status;
    private Long consultId;
    private String userId;
    private String realTime;
    private Long payConsultId;
    private Long chargeConsultId;

    /** 咨询师 */
    private List<Long> ids;

}
