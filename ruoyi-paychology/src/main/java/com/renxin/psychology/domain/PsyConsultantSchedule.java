package com.renxin.psychology.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 咨询师排班任务对象 psy_consultant_schedule
 * 
 * @author renxin
 * @date 2024-07-25
 */
@Data
public class PsyConsultantSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderId;
    /** 团督号 */
    private Long teamId;
    
    /** 任务类型  1.团督开课   2.咨询服务 */
    private Integer scheduleType;
    
    //同一任务的第几次执行
    private Integer timeNum;

    /** 排班 */
    @Excel(name = "排班")
    private Long workId;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String timeStart;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String timeEnd;

    /** 天 */
    @Excel(name = "天")
    private String day;

    /** 周 */
    @Excel(name = "周")
    private String week;

    /** 状态（0待办  1已完成） */
    @Excel(name = "状态", readConverterExp = "0=待办,1=已完成")
    private String status;

    /** 开始时间(整点) */
    @Excel(name = "开始时间(整点)")
    private Integer time;

    /** 实际咨询开始时间 */
    @Excel(name = "实际咨询开始时间")
    private String realTime;
    /**
     * 服务名 / 团队标题
     */
    private String serverName;

    /** 督导师 */
    @Excel(name = "督导师")
    private Long consultId;
    
    //付款方用户名
    private String userNickName;
    private String userId;
    
}
