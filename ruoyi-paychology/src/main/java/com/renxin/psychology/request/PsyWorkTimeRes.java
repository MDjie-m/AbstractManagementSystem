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

    //购买团督时长
    private int buyTeamSupTime;
    //购买个督时长
    private int buyPersonSupTime;
    //购买体验时长
    private int buyPersonExpTime;
    
    private int workTime;//服务时长: 个案咨询经历(入驻申请) + 本平台[提供]的团督时长 + 个督时长 + 个人体验 + 咨询
    private int buySupTime;//督导时长: 接受督导经历(入驻申请) + 本平台[购买]的团督时长 + 个督时长
    private int buyExpTime;//体验时长: 接受体验经历(入驻申请) + 本平台[购买]的体验时长
    
    

}
