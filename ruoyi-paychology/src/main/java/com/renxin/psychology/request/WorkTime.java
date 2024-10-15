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
public class WorkTime implements Serializable
{
    //工作日
    private Long workId;
    //时间点
    private Integer time;
}
