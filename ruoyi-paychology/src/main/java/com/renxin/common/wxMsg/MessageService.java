package com.renxin.common.wxMsg;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author : dyg
 * @className : MessageService
 * @description : 消息发送
 * @date : 2023/8/30 10:34
 */
public interface MessageService<T extends NoticeMessage>  extends InitializingBean {
    /**
     * 发送消息
     * @param message
     * @return
     */
    String send(NoticeMessage message);

    /**
     * 获取发送方法
     *
     * @return 发送方法
     */
    String getNoticeMethod();

    @Override
    default void afterPropertiesSet() {
        MessageSenderManager.registrySender(getNoticeMethod(), this);
    }
}