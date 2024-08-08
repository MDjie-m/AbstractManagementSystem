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
public class PsyWorkTimeRes implements Serializable
{
    
    private Long consultId;
    
    //团督时长
    private int teamSupTime;
    //个督时长
    private int personSupTime;
    //个人体验时长
    private int personExpTime;
    //咨询时长
    private int consultTime;
    //倾听时长
    private int listenTime;
    

}
