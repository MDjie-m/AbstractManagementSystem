package com.renxin.common.wechat.wxMsg;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通知方式
 *
 * @author changXT
 * @date 2022-8-10
 **/
@AllArgsConstructor
@Getter
public enum NoticeMethodEnum {
    WECHAT("微信", "WECHAT"),
    EMAIL("邮件", "EMAIL"),
    TENCENT_SMS("腾讯短信", "TENCENT_SMS"),
    IN_MSG("站内消息", "IN_MSG"),
    DINGTALK("钉钉", "DINGTALK");
    private String cnName;
    private String name;

    public static NoticeMethodEnum getEnum(String name) {
        for (NoticeMethodEnum noticeMethodEnum : NoticeMethodEnum.values()) {
            if (name.equals(noticeMethodEnum.name())) {
                return noticeMethodEnum;
            }
        }
        return null;
    }
}