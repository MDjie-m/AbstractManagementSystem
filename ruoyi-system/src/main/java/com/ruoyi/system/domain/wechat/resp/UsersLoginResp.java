/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.ruoyi.system.domain.wechat.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UsersLoginResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * token，设置有效期为1天
     */
    private String token;
}
