package com.ruoyi.system.domain.wechat;

import com.ruoyi.common.enums.wechat.RegisterSourceEnum;
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
public class WxBindingReq implements Serializable {

    private static final long serialVersionUID = -2877781106821535513L;

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 手机验证码
     */
    private String code;
    /**
     * UnionId
     */
    private String unionId;
    /**
     * OpenId
     */
    private String openId;

    private Integer RegisterSource = RegisterSourceEnum.SYS_PC_WX.getCode();
}
