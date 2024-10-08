package com.renxin.common.wxMsg;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息发送者管理器
 *
 * @author donglanlan
 * @date 2021/7/22 2:33 下午
 **/
@Component
public class MessageSenderManager {
    private static final ConcurrentHashMap<String, MessageService<? extends NoticeMessage>> SENDER_MAP =
            new ConcurrentHashMap<>();

    public static void registrySender(String sendMethod, MessageService<? extends NoticeMessage> messageSender) {
        SENDER_MAP.put(sendMethod, messageSender);
    }

    public MessageService getMessageSender(NoticeMessage messsage) {
        if (messsage.getNoticeMethod() == null) {
            throw new RuntimeException("没有指定消息的发送方式！");
        }
        return getMessageSender(messsage.getNoticeMethod());
    }

    public  MessageService getMessageSender(NoticeMethodEnum methodEnum) {
        return SENDER_MAP.get(methodEnum.getName());
    }
}