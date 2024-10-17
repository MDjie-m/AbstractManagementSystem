package com.renxin.consultant.common.dcloud;

import java.util.HashMap;
import java.util.Map;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.renxin.common.core.domain.dto.ConsultLoginDTO;
import com.renxin.common.utils.http.HttpUtils;
import com.renxin.common.wechat.wxMsg.NoticeMessage;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.service.IPsyConsultService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CloudFunctions {

    private static String secret = "4/81I10MHq3zI7XDVLJ8OA==";
    private static String appid = "__UNI__401C9B1";
    private static String requestUrl = "https://fc-mp-b531bf3e-ef6d-4478-b2c9-350c3814888d.next.bspapp.com";
    
    @Autowired
    private static IPsyConsultService consultService;

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

    public static boolean sendSms(String phone, String code)  {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("phone", phone); // 客户端传到自己服务器的参数
            params.put("code", code);
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

    public static String sendGeTuiMessage(NoticeMessage notice) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("push_clientid", "f47f97fc871927931d848343a9c83f9a");
        params.put("title", "分账通知");
        params.put("content", "服务完成, 已分账5.00元到账户");
        HashMap<String, String> payloadMap = new HashMap<>();
            payloadMap.put("text","服务完成, 已分账5.00元到账户***");
        params.put("payload", payloadMap);
        String url = requestUrl + "/pushSendMessage";
        Map<String, Object> requestParams = SignUtil.signMap(secret, params);
        String res = HttpUtil.get(url, requestParams);
        JSONObject jsonObject = new JSONObject(res);
        System.out.println(jsonObject);
        return null;
    }
    
    //查询咨询师的clientId
    public static String getClientIdByConsultantId(Long consutantId){
        PsyConsult one = consultService.getById(consutantId);
        if (ObjectUtils.isEmpty(one) || ObjectUtils.isEmpty(one.getPushClientId())){
            log.error("查询咨询师的clientId , 咨询师consutantId没有维护clientId, 无法向其发送通知");
            return null;
        }
        return one.getPushClientId();
    }


}