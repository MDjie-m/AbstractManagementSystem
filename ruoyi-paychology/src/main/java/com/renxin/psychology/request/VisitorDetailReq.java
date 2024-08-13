package com.renxin.psychology.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 来访者详情查询请求体
 * 
 * @author renxin
 * @date 2023-06-26
 */
@Data
public class VisitorDetailReq implements Serializable
{
    //付款来访者id
    private Long payUserId;
    //付款咨询师id
    private Long payConsultantId;
    //收款咨询师id
    private Long chargeConsultantId;
    

}
