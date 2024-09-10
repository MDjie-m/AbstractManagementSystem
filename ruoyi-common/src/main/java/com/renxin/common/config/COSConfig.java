package com.renxin.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 * 
 * @author renxin
 */
@Component
@ConfigurationProperties(prefix = "cos")
@Data
public class COSConfig
{
    private String secretId;

    private String secretKey;

    private String appId;

    private String bucket;

    private String zxBucket;

    private String gaugeBucket;
    
    private String region;
}
