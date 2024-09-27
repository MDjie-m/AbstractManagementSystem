//package com.renxin.common.task;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import com.getui.push.v2.sdk.api.PushApi;
//import com.getui.push.v2.sdk.common.ApiResult;
//import com.getui.push.v2.sdk.dto.req.Audience;
//import com.getui.push.v2.sdk.dto.req.AudienceDTO;
//import com.getui.push.v2.sdk.dto.req.Settings;
//import com.getui.push.v2.sdk.dto.req.message.PushChannel;
//import com.getui.push.v2.sdk.dto.req.message.PushDTO;
//import com.getui.push.v2.sdk.dto.req.message.PushMessage;
//import com.getui.push.v2.sdk.dto.req.message.android.AndroidDTO;
//import com.getui.push.v2.sdk.dto.req.message.android.GTNotification;
//import com.getui.push.v2.sdk.dto.req.message.android.ThirdNotification;
//import com.getui.push.v2.sdk.dto.req.message.android.Ups;
//import com.getui.push.v2.sdk.dto.req.message.ios.Alert;
//import com.getui.push.v2.sdk.dto.req.message.ios.Aps;
//import com.getui.push.v2.sdk.dto.req.message.ios.IosDTO;
//import com.getui.push.v2.sdk.dto.res.TaskIdDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 个推工具类
// */
//@Slf4j
//@Service
//public class GeTuiUtils {
//
//    @Autowired
//    PushApi pushApi;
//
//    /**
//     * 单cid推送
//     *
//     * @param cid
//     * @param title
//     * @param content
//     * @return
//     */
//    public boolean pushToSingleByCid(String cid, String title, String content,String payload) {
//        //推送消息体
//        PushDTO<Audience> pushDTO = this.buildPushDTO(title, content,payload);
//        //设置接收人信息
//        Audience audience = new Audience();
//        pushDTO.setAudience(audience);
//        audience.addCid(cid);// cid
//        //进行cid单推
//        ApiResult<Map<String, Map<String, String>>> apiResult = pushApi.pushToSingleByCid(pushDTO);
//        if (apiResult.isSuccess()) {
//            log.info("Push succeeded. cid:" + cid + ",msg:" + apiResult.getMsg() + ",data:" + apiResult.getData());
//            return true;
//        } else {
//            log.info("Push failed. cid:" + cid + ",code:" + apiResult.getCode() + ", msg: " + apiResult.getMsg());
//            return false;
//        }
//    }
//
//    /**
//     * cid批量推送
//     *
//     * @param cidList
//     * @param title
//     * @param content
//     * @return
//     */
//    public boolean pushListByCid(List<String> cidList, String title, String content, String payload) {
//        //批量发送
//        AudienceDTO audienceDTO = new AudienceDTO();
//        PushDTO<Audience> pushDTO = this.buildPushDTO(title, content,payload);
//
//        //创建消息
//        ApiResult<TaskIdDTO> createApiResult = pushApi.createMsg(pushDTO);
//        if (!createApiResult.isSuccess()) {
//            log.info("批量推送：创建消息失败" + createApiResult.getMsg());
//            return false;
//        }
//        // 设置接收人信息
//        Audience audience = new Audience();
//        pushDTO.setAudience(audience);
//        audience.setCid(cidList);
//
//        audienceDTO.setAudience(audience);
//        audienceDTO.setTaskid(createApiResult.getData().getTaskId());
//        audienceDTO.setAsync(true);
//
//        ApiResult<Map<String, Map<String, String>>> apiResult = pushApi.pushListByCid(audienceDTO);
//        if (apiResult.isSuccess()) {
//            System.out.println("Batch push succeeded. msg:" + apiResult.getMsg() + ",data:" + apiResult.getData());
//            return true;
//        } else {
//            System.out.println("Batch push failed. code:" + apiResult.getCode() + ", msg: " + apiResult.getMsg());
//            return false;
//        }
//    }
//
//    /**
//     * 构建推送消息体
//     *
//     * @param title
//     * @param content
//     * @return
//     */
//    private PushDTO<Audience> buildPushDTO(String title, String content,String payload) {
//        PushDTO<Audience> pushDTO = new PushDTO<>();
//        // 设置推送参数
//        pushDTO.setRequestId(System.currentTimeMillis() + "");
//        pushDTO.setGroupName("test-group");
//
//        //配置推送条件
//        Settings settings = new Settings();
//        pushDTO.setSettings(settings);
//        //消息有效期，走厂商消息需要设置该值
//        settings.setTtl(3600000);
//
//        //安卓在线通道走个推推送时的消息体（在线通道不支持ios）
//        PushMessage pushMessage = new PushMessage();
//        pushDTO.setPushMessage(pushMessage);
//        //通知消息
//        JSONObject trans = new JSONObject();
//        trans.put("title", title);
//        trans.put("content", content);
//        trans.put("payload", payload);
//        pushMessage.setTransmission(JSON.toJSONString(trans));
//
//        //设置离线推送时的消息体
//        PushChannel pushChannel = new PushChannel();
//
//        //安卓离线厂商通道推送的消息体
//        AndroidDTO androidDTO = new AndroidDTO();
//        Ups ups = new Ups();
//        //通知消息
//        ThirdNotification thirdNotification = new ThirdNotification();
//        ups.setNotification(thirdNotification);
//        thirdNotification.setTitle(title);
//        thirdNotification.setBody(content);
//        thirdNotification.setClickType("intent");
//        thirdNotification.setIntent("intent://com.getui.push/detail?#Intent;scheme=gtpushscheme;launchFlags=0x4000000;package=com.getui.demo;component=com.getui.demo/com.getui.demo.DemoActivity;S.payload=payloadStr;end");
//
//
//        androidDTO.setUps(ups);
//        pushChannel.setAndroid(androidDTO);
//
//        //ios离线apn通道推送的消息体
//        Alert alert = new Alert();
//        alert.setTitle(title);
//        alert.setBody(content);
//        Aps aps = new Aps();
//        aps.setContentAvailable(0);//0表示普通通知消息(默认为0);1表示静默推送(无通知栏消息)，静默推送时不需要填写其他参数。苹果建议1小时最多推送3条静默消息
//        aps.setSound("default");//自定义铃声:系统铃声设置为：default; 无声设置为：com.gexin.ios.silence，或不填
//        aps.setAlert(alert);
//        IosDTO iosDTO = new IosDTO();
//        iosDTO.setAps(aps);
//        iosDTO.setType("notify");
//        iosDTO.setPayload(payload);
//        pushChannel.setIos(iosDTO);
//
//        pushDTO.setPushChannel(pushChannel);
//        return pushDTO;
//    }
//}