package com.renxin.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 咨询服务对象 psy_consult
 *
 * @author renxin
 * @date 2023-06-25
 */
@Data
public class PsyUserLikedConsultVO implements Serializable
{

    private static final long serialVersionUID = -7888885872715060085L;

    /** 用户 */
    private Long userId;

    /** 咨询师 */
    private Long consultId;
    private String consultName;

    /** 创建时间 */
    private Date createTime;

    /** 头像 */
    private String avatar;

    /** 介绍文案 */
    private String info;

    /** 咨询方式 */
    private String mode;
    
}
