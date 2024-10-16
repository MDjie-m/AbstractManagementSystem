package com.renxin.common.wechat.wechatProgram;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.renxin.common.constant.Constants;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.utils.RestTemplateUtil;
import com.renxin.common.wechat.wxMsg.WxMsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 微信Token工具类
 */
@Component
public class WechatProgramUtils {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RestTemplateUtil restTemplateUtil;

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

    /**
     * 获取token
     * "+ appId +"&secret=" + appIdSecret
     */
    @Value(value = "${wechat.token_uri}")
    private String tokenUri;

    private String grant_type = "client_credential";

    private static String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";

    private static String getPhoneNumberUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=ACCESS_TOKEN";

    @Autowired
    private RedisCache redisCache;
    
    @Resource
    private WxMsgUtils wxMsgUtils;
    
    /**
     * 获取Token
     */
    public String getAccessToken() {
        String access_token = redisCache.getCacheObject(Constants.WECHAT_PROGRAM_ACCESS_TOKEN_KEY);

        if (access_token != null) {
            return access_token;
        } else {
            String requestUrl = this.tokenUri + this.appid +"&secret=" + this.secret;
            String res = HttpUtil.get(requestUrl);
            JSONObject jsonObject = JSONObject.parseObject(res);
            String accessToken = jsonObject.getString("access_token");
            Integer expires_in = jsonObject.getInteger("expires_in");
            // 更新缓存
            redisCache.setCacheObject(Constants.WECHAT_PROGRAM_ACCESS_TOKEN_KEY, accessToken, expires_in,
                    TimeUnit.SECONDS);
            return accessToken;
        }
    }
    
    /**
     * 获取Token
     */
 /*   public String getAccessToken() throws Exception {
        return wxMsgUtils.getAccessToken();
        
        String access_token = redisCache.getCacheObject(Constants.WECHAT_PROGRAM_ACCESS_TOKEN_KEY);

        if (access_token != null) {
            return access_token;
        } else {

            Map<String, String> params = new HashMap<String, String>();
            params.put("appid", appid);
            params.put("secret", secret);
            params.put("grant_type", grant_type);
            // 构建URL
            StringJoiner urlJoiner = new StringJoiner("&", getAccessTokenUrl + "?", "");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlJoiner.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            String urlString = urlJoiner.toString();
            // 发起请求
            JSONObject data = restTemplateUtil.getData(urlString);

            access_token = data.getString("access_token");
            Integer expires_in = data.getInteger("expires_in");

            // 更新缓存
            redisCache.setCacheObject(Constants.WECHAT_PROGRAM_ACCESS_TOKEN_KEY, access_token, expires_in,
                    TimeUnit.SECONDS);

            return access_token;

        }
    }*/

    public String getPhonenumber(String code) throws Exception {

        String accessToken = this.getAccessToken();

        String getPhoneNumberUrl2 = getPhoneNumberUrl.replace("ACCESS_TOKEN", accessToken);

        JSONObject param = new JSONObject();
       // param.put("access_token", accessToken);
        param.put("code", code);
        JSONObject data = restTemplateUtil.postJsonDataAndReturnJson(getPhoneNumberUrl2, param);
        
        if (data.getIntValue("errcode") == 0) {
            JSONObject phone_info = data.getJSONObject("phone_info");
            String phoneNumber = phone_info.getString("phoneNumber");
            return phoneNumber;
        } else {
            throw new Exception(data.getString("errmsg"));
        }
    }

}