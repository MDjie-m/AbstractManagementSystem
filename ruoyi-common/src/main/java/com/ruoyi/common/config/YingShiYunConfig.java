package com.ruoyi.common.config;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.YingshiPic;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static com.ruoyi.common.constant.CacheConstants.YINGSHI_DEVICE_PIC_KEY;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-10-19:30
 * @className: YinShiYunConfig
 */
@Component
@Data
@Slf4j
public class YingShiYunConfig {

    @Autowired
    private RedisCache redisCache;

    @Value("${yingshi.appKey}")
    private String appKey;
    @Value("${yingshi.template}")
    private String template;
    @Value("${yingshi.secret}")
    private String appSecret;

    @Value("${yingshi.redisKey}")
    private String redisKey;

    @Value("${yingshi.tokenUrl}")
    private String tokenUrl;
    @Value("${yingshi.captureUrl}")
    private String captureUrl;


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

    public String getDeviceCaptureImg(String serialNum) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(serialNum)) {
            return "";
        }
        String url = redisCache.getCacheObject(YINGSHI_DEVICE_PIC_KEY.concat(serialNum));
        if (Objects.isNull(url)) {
            YingshiPic pic = getAppPic(serialNum);

            AtomicInteger expireSeconds = new AtomicInteger(20 * 60 * 60);
            try {
                Arrays.stream(pic.getPicUrl().split("&")).filter(p -> p.toLowerCase().startsWith("expires")).findFirst().ifPresent(p -> {
                    Date expireDate = DateUtils.getDateTime(Long.parseLong(p.split("=")[1]), false);
                    log.info("过期时间:{}", expireDate);
                    expireSeconds.set(DateUtils.diffSeconds(new Date(), expireDate));
                });
            } catch (Exception e) {

            }

            if (Objects.nonNull(pic)) {

                redisCache.setCacheObject(YINGSHI_DEVICE_PIC_KEY.concat(serialNum), pic.getPicUrl(), expireSeconds.get() - 60, TimeUnit.SECONDS);
                return pic.getPicUrl();
            } else {
                return "";
            }
        }
        return url;
    }

    private YingShiYunEntity getAppToken() {

        String sendParam = "appKey=" + appKey + "&appSecret=" + appSecret;
        String res = HttpUtils.sendPost(tokenUrl, sendParam);

        JSONObject pres = JSONObject.parseObject(res);
        String code = pres.getString("code");
        AssertUtil.equal(code, "200", pres.getString("msg"));
        return pres.getObject("data", YingShiYunEntity.class);
    }

    private YingshiPic getAppPic(String serialNum) {

        String sendParam = StringUtils.format("appKey={}&accessToken={}&deviceSerial={}&channelNo=1&quality=0&appSecret={}", appKey, getAppToken().getAccessToken(),
                serialNum.toUpperCase(), appSecret);
        String res = HttpUtils.sendPost(captureUrl, sendParam);

        JSONObject pres = JSONObject.parseObject(res);
        String code = pres.getString("code");
        AssertUtil.equal(code, "200", pres.getString("msg"));
        return pres.getObject("data", YingshiPic.class);
    }
}

