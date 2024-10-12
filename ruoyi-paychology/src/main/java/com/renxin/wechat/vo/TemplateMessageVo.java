package com.renxin.wechat.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class TemplateMessageVo implements Serializable {
    private static final long serialVersionUID = 8350226685020022459L;

    // OPENID
    private String touser;

    // 模板ID
    private String template_id;

    // 模板跳转链接（海外帐号没有跳转能力）
    private String url;

    // 防重入id。对于同一个openid + client_msg_id, 只发送一条消息,10分钟有效,超过10分钟不保证效果。若无防重入需求，可不填
    private String client_msg_id;

    // 发送内容 keyword1 keyword2
    private HashMap<String, TemplateMessageItemOldVo> data;
}
