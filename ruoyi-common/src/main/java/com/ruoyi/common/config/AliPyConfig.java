package com.ruoyi.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.ruoyi.common.annotation.DataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author:
 * @description:
 * @create: 2024-10-30 14:29
 **/
@Configuration
public class AliPyConfig {



    @Resource
    private AliPayProperties aliPayProperties;



    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(aliPayProperties.gatewayUrl,
                aliPayProperties.appId,
                aliPayProperties.merchantPrivateKey,
                "json",
                aliPayProperties.charset,
                aliPayProperties.alipayPublicKey,
                aliPayProperties.sign_type);
    }
}
