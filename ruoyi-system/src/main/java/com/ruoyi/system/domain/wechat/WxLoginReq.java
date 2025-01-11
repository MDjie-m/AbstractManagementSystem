package com.ruoyi.system.domain.wechat;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信登录
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class WxLoginReq implements Serializable {

    private static final long serialVersionUID = -2877781106821535513L;

    private Integer loginAuthType;

    private String redirectUrl;
}
