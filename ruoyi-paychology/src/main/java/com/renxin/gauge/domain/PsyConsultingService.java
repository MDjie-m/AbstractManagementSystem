package com.renxin.gauge.domain;

import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 咨询服务对象 psy_consulting_service
 * 
 * @author renxin
 * @date 2023-01-31
 */
@Data
public class PsyConsultingService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 咨询师id */
    @Excel(name = "咨询师id")
    private Integer consultantId;

    /** 咨询服务id */
    @Excel(name = "咨询服务id")
    private Integer serviceId;

    /** 咨询类型0不限 1面对面 2视频 3语音 */
    @Excel(name = "咨询类型0不限 1面对面 2视频 3语音")
    private Integer type;

    /** 价格 */
    @Excel(name = "价格")
    private String price;

    /** 描述 */
    @Excel(name = "描述")
    private String describes;

}
