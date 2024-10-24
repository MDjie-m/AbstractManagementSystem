package com.ruoyi.common.config;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.http.HttpUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-10-19:30
 * @className: YinShiYunConfig
 */
@Component
@Data
public class YingShiYunConfig {

    @Autowired
    private RedisCache redisCache;

    @Value("${yingshi.appKey}")
    private String appKey;

    @Value("${yingshi.secret}")
    private String appSecret;

    @Value("${yingshi.redisKey}")
    private String redisKey;

    @Value("${yingshi.tokenUrl}")
    private String tokenUrl;

    private final ReentrantLock lock = new ReentrantLock();

    public synchronized YingShiYunEntity returnAppTokenData() {
        YingShiYunEntity tokenData = redisCache.getCacheObject(redisKey);
        long newCurrentTime = System.currentTimeMillis() - 60 * 1000 * 30;//提前三十分钟刷新
        if (Objects.nonNull(tokenData)) {
            if (newCurrentTime > tokenData.getExpireTime()) {
                tokenData.setAppKey(appKey);
                return tokenData;
            }
        }
        tokenData = getAppToken();
        redisCache.setCacheObject(redisKey, tokenData, tokenData.getExpireTime() - newCurrentTime, TimeUnit.MICROSECONDS);
        tokenData.setAppKey(appKey);
        return tokenData;
    }

    private YingShiYunEntity getAppToken() {

        String sendParam = "appKey=" + appKey + "&appSecret=" + appSecret;
        String res = HttpUtils.sendPost(tokenUrl, sendParam);

        JSONObject pres = JSONObject.parseObject(res);
        String code = pres.getString("code");
        AssertUtil.equal(code, "200", pres.getString("msg"));
        return pres.getObject("data", YingShiYunEntity.class);
    }

}

