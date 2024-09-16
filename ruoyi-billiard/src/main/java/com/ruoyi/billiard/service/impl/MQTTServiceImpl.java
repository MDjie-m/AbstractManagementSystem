package com.ruoyi.billiard.service.impl;

import com.ruoyi.billiard.service.IMQTTService;
import com.ruoyi.common.utils.spring.SpringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class MQTTServiceImpl implements IMQTTService, MqttCallback {


    @Value("${mqtt.qos}")
    int qos;
    @Value("${mqtt.broker}")
    String broker;
    @Value("${mqtt.clientId}")
    String clientId;

    @Value("${mqtt.username}")
    String username;

    @Value("${mqtt.password}")
    String password;
    @Value("${mqtt.cleanSession}")
    private boolean cleanSession;
    private int reconnectDelay = 2000; // 初始重连延迟2秒
    private int reconnectAttempts = 0;
    MqttClient client;

    final ConcurrentHashMap<String, MQTTDevice> DEVICE_MAP = new ConcurrentHashMap<>();

    @Override
    public void connect() {
//        try {
//            client = new MqttClient(broker, clientId);
//            MqttConnectOptions options = getMqttConnectOptions();
//            client.setCallback(this);
//            client.connect(options);
//        } catch (MqttException me) {
//            log.info("reason " + me.getReasonCode());
//            log.info("msg " + me.getMessage());
//            log.info("loc " + me.getLocalizedMessage());
//            log.info("cause " + me.getCause());
//            log.info("excep " + me);
//            log.error("MQTT init error:", me);
//        }
//        log.info("---MQTT connected Success");
    }

    private MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        // cleanSession为 false 时表示创建一个持久会话，在客户端断开连接时，会话仍然保持并保存离线消息，直到会话超时注销。
        // cleanSession为 true 时表示创建一个新的临时会话，在客户端断开时，会话自动销毁。
        // 注意：持久会话恢复的前提是客户端使用固定的 Client ID 再次连接，如果 Client ID 是动态的，那么连接成功后将会创建一个新的持久会话。
        options.setCleanSession(cleanSession);
        // 禁用Paho的自动重连，自己控制
        options.setAutomaticReconnect(true);
        if (username != null && !username.isEmpty()) {
            options.setUserName(username);
            options.setPassword(password.toCharArray());
        }
        return options;
    }

    @SneakyThrows
    @Override
    public void subDevice(Long storeId, Long deviceId, String subTopic, DeviceCallbackEvent event) {
        ///${appId}/${deviceKey}/${deviceMAC}/subscribe
//        checkLostContent();
//        client.subscribe(subTopic);
//        log.info("主题订阅成功:{}", subTopic);
//        DEVICE_MAP.put(subTopic, new MQTTDevice(storeId, deviceId, subTopic, event));
    }

    @SneakyThrows
    @Override
    public void sendMsg(String pubTopic, int msgType, String msg) {
        // 消息发布所需参数 /${appId}/${deviceKey}/${deviceMAC}/publish

        checkLostContent();
        MqttMessage message = new MqttMessage(msg.getBytes());
        message.setId(msgType);
        message.setQos(qos);
        client.publish(pubTopic, message);

        log.info("---MQTT published:{}", msg);
    }

    private void checkLostContent() {
        if (client != null && !client.isConnected()) {
            this.reconnectAttempts = 0;
            reconnect();
        }
    }


    // 断开连接的方法
    @PreDestroy
    public void disconnect() throws MqttException {
        if (client != null && client.isConnected()) {
            client.disconnect();
            log.info("---MQTT Disconnected");
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.info("---MQTT Connection lost! " + cause.getMessage());
        // 这里可以重新连接MQTT服务器
        // reconnect();
        log.error("MQTT掉线:", cause);
        DEVICE_MAP.values().forEach(item -> {
            subDevice(item.getStoreId(), item.getDeviceId(), item.getSubTopic(), item.getEvent());
        });
    }

    private void reconnect() {

//        // 最大重连尝试次数
//        int maxReconnectAttempts = 3;
//        if (reconnectAttempts < maxReconnectAttempts) {
//            reconnectAttempts++;
//            log.info("---MQTT Attempting to reconnect in " + reconnectDelay + "ms");
//
//            // 使用ScheduledExecutorService方式实现延迟重连
//            // 为简单起见，使用Thread.sleep
//            try {
//                Thread.sleep(reconnectDelay);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//
//            reconnectDelay *= 2; // 增大重连间隔
//            connect(); // 尝试重新连接
//        } else {
//            log.warn("---Max reconnect attempts reached");
//            // 可以考虑执行一些清理操作或通知操作
//        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // 当消息到达时调用
        String msg = new String(message.getPayload());
        log.info("---Message arrived. Topic: {},MsgId:{},Msg:{}", topic, message.getId(), msg);
        SpringUtils.getBean(MQTTServiceImpl.class).callbackRun(topic, message.getId(), msg);
        // 处理消息的逻辑
    }

    //执行回调
    @Async
    public void callbackRun(String topic, int msgId, String msg) {
        try {
            if (DEVICE_MAP.containsKey(topic)) {
                MQTTDevice mqttDevice = DEVICE_MAP.get(topic);
                mqttDevice.getEvent().lightMQTTCallback(msgId, mqttDevice.getStoreId(), mqttDevice.getDeviceId(), msg);
            } else {
                log.warn("---MQTT 设备不存在：{}", topic);
            }
        } catch (Exception e) {
            log.error("MQTT消息回调处理异常：", e);
        }
    }

    @Override
    @SneakyThrows
    public void deliveryComplete(IMqttDeliveryToken token) {
        try {
            // 当消息被完全传送出去后调用
            log.info("---MQTT Delivery complete:{}", Objects.isNull(token.getMessage()) ? null : new String(token.getMessage().getPayload()));
        } catch (Exception e) {
            log.error("MQTT消息送达异常", e);
        }

        // 可以在这里处理一些发送完成后的清理工作
    }

    @Data
    @ToString
    @AllArgsConstructor
    public static class MQTTDevice {
        private Long storeId;

        private Long deviceId;
        private String subTopic;
        private DeviceCallbackEvent event;

    }

    @FunctionalInterface
    public static interface DeviceCallbackEvent {
        void lightMQTTCallback(int msgId, Long storeId, Long deviceId, String msg);
    }
}
