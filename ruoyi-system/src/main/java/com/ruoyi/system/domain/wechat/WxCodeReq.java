package com.ruoyi.system.domain.wechat;

import com.ruoyi.common.enums.wechat.ClientTypeEnum;
import com.ruoyi.common.enums.wechat.LoginAuthTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信授权
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class WxCodeReq implements Serializable {

    private static final long serialVersionUID = -2877781106821535513L;

    private String code;

    /**
     * @see LoginAuthTypeEnum
     */
    private Integer loginAuthType;

    /**
     * @see ClientTypeEnum
     */
    private Integer clientType;
}
