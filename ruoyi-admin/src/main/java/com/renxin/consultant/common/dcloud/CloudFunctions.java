package com.renxin.consultant.common.dcloud;

import java.util.HashMap;
import java.util.Map;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.renxin.common.core.domain.dto.ConsultLoginDTO;
import com.renxin.common.utils.http.HttpUtils;

public class CloudFunctions {
    public static String getPhoneNumber(ConsultLoginDTO consultLoginDTO) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("access_token", consultLoginDTO.getAccess_token()); // 客户端传到自己服务器的参数
        params.put("openid", consultLoginDTO.getOpenid());
        params.put("appid","__UNI__401C9B1");
        String requestUrl="https://fc-mp-b531bf3e-ef6d-4478-b2c9-350c3814888d.next.bspapp.com/getphonenumber";
        String secret="4/81I10MHq3zI7XDVLJ8OA==";
        Map<String, Object>  requestParams=SignUtil.signMap(secret,params);
        String res= HttpUtil.get(requestUrl,requestParams);
        JSONObject jsonObject = new JSONObject(res);
        String phoneNumber=jsonObject.getStr("phoneNumber");
        return phoneNumber;
    }

  
}