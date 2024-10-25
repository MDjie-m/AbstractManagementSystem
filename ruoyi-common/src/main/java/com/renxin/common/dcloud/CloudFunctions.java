package com.renxin.common.dcloud;

import java.util.HashMap;
import java.util.Map;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.renxin.common.core.domain.dto.ConsultLoginDTO;
import com.renxin.common.wechat.wxMsg.NoticeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

@Slf4j
public class CloudFunctions {

    private static String secret = "4/81I10MHq3zI7XDVLJ8OA==";
    private static String appid = "__UNI__401C9B1";
    private static String requestUrl = "https://fc-mp-b531bf3e-ef6d-4478-b2c9-350c3814888d.next.bspapp.com";
    


    public static String getPhoneNumber(ConsultLoginDTO consultLoginDTO) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", consultLoginDTO.getAccess_token()); // 客户端传到自己服务器的参数
        params.put("openid", consultLoginDTO.getOpenid());
        params.put("appid", appid);
        String url = requestUrl + "/getphonenumber";
        //String secret="4/81I10MHq3zI7XDVLJ8OA==";
        Map<String, Object> requestParams = SignUtil.signMap(secret, params);
        String res = HttpUtil.get(url, requestParams);
        JSONObject jsonObject = new JSONObject(res);
        String phoneNumber = jsonObject.getStr("phoneNumber");
        return phoneNumber;
    }

    public static boolean sendSms(Map<String, Object> params)  {
        try {
            //Map<String, Object> params = new HashMap<>();
            //params.put("phone", phone); // 客户端传到自己服务器的参数
            //params.put("code", code);
            //params.put("templateId", templateId);
            String url = requestUrl + "/sendsms";
            Map<String, Object> requestParams = SignUtil.signMap(secret, params);
            String res = HttpUtil.get(url, requestParams);
            JSONObject jsonObject = new JSONObject(res);
            if (jsonObject.getInt("code") == 0) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    //发送个推通知
    public static boolean sendGeTuiMessage(NoticeMessage notice)  {
        if (ObjectUtils.isEmpty(notice.getPush_clientid())){
            return false;
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("push_clientid", notice.getPush_clientid());
            params.put("title", notice.getTitle());
            params.put("content", notice.getContent());
            HashMap<String, String> payloadMap = new HashMap<>();
            payloadMap.put("text",notice.getContent());
            params.put("payload", payloadMap);
            String url = requestUrl + "/pushSendMessage";
            Map<String, Object> requestParams = SignUtil.signMap(secret, params);
            String res = HttpUtil.get(url, requestParams);
            JSONObject jsonObject = new JSONObject(res);
            System.out.println(jsonObject);
            return true;
        } catch (Exception e) {
            log.error("发送个推通知失败, notice:" + notice);
            return false;
        }
    }


}