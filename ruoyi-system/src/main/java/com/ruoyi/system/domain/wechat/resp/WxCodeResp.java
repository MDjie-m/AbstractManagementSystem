package com.ruoyi.system.domain.wechat.resp;

import lombok.Data;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;

import java.io.Serializable;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class WxCodeResp extends UsersLoginResp implements Serializable {

    private static final long serialVersionUID = 2621609267080102065L;

    private Boolean bindingStatus = false;

    private WxOAuth2UserInfo userInfo;

}
