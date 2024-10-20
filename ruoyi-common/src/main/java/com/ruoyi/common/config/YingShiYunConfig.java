package com.ruoyi.common.config;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-10-19:30
 * @className: YinShiYunConfig
 */
@Component
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

    public YingShiYunEntity returnAppTokenData() {

        YingShiYunEntity tokenData = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(tokenData)) {
            try {
                lock.lock();
                // 再次检查，因为可能在等待锁的过程中其他线程已经获取了数据
                tokenData = redisCache.getCacheObject(redisKey);
                if (Objects.isNull(tokenData)) {
                    tokenData = getAppToken();
                    redisCache.setCacheObject(redisKey, tokenData, 7, TimeUnit.DAYS);
                }
            } finally {
                lock.unlock();
            }
        } else {
            long newCurrentTime = System.currentTimeMillis();
            if (newCurrentTime > tokenData.getExpireTime()) {
                try {
                    lock.lock();
                    // 再次检查，因为可能在等待锁的过程中其他线程已经更新了数据
                    tokenData = redisCache.getCacheObject(redisKey);
                    if (newCurrentTime > tokenData.getExpireTime()) {
                        tokenData = getAppToken();
                        redisCache.setCacheObject(redisKey, tokenData, 7, TimeUnit.DAYS);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
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

