package com.ruoyi.billiard.service;

import com.ruoyi.billiard.service.impl.MQTTServiceImpl;
import lombok.SneakyThrows;

public interface IMQTTService {

    void  connect();

    @SneakyThrows
    void subDevice(Long storeId, Long deviceId, String subTopic, MQTTServiceImpl.DeviceCallbackEvent event);

    @SneakyThrows
    void sendMsg(String pubTopic,int msgType, String msg);
}
