package com.renxin.psychology.dto;


import com.renxin.psychology.domain.PsyConsultOrderItem;

import lombok.Data;
import lombok.EqualsAndHashCode;



/**
 * 咨询子订单对象，咨询师APP用
 * 
 * @author Jack
 * @date 2024-05-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderItemDTO extends PsyConsultOrderItem {

    /** 客户 */
    private Long userId;
    private String userAvatar;
    private String userNickName;
    private String payUsername;
    
    private String teamId;
    private Integer scheduleType;
    private String consultantName;
    private String consultantAvatar;
    
    private String serverName;
    private String orderDir;
    private String orderBy;
    private int timeNum;
    private Integer rowNum;
    //咨询形式   1语音咨询    2视频咨询   3面对面咨询
    private Integer mode;

}
