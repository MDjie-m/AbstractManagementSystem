//package com.renxin.framework.config;
//
//import com.getui.push.v2.sdk.ApiHelper;
//import com.getui.push.v2.sdk.GtApiConfiguration;
//import com.getui.push.v2.sdk.api.PushApi;
//import lombok.Data;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * 个推应用信息
// */
//
//@Data
//@Component
//public class GeTuiConfig {
//
//    @Value("${uniPush.AppID}")
//    private String appID;
//    @Value("${uniPush.AppKey}")
//    private String appKey;
//    @Value("${uniPush.AppSecret}")
//    private String appSecret;
//    @Value("${uniPush.MasterSecret}")
//    private String masterSecret;
//    @Value("${uniPush.Host}")
//    private String host;
//
//    /**
//     * 个推接口实例化
//     * @return
//     */
//    @Bean(name = "onlyPushApi")
//    public PushApi pushApi() {
//        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
//        //填写应用配置，参数在“Uni Push”下的“应用配置”页面中获取
//        apiConfiguration.setAppId(appID);
//        apiConfiguration.setAppKey(appKey);
//        apiConfiguration.setMasterSecret(masterSecret);
//        //实例化ApiHelper对象，用于创建接口对象
//        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
//        //创建对象，建议复用。目前有PushApi、StatisticApi、UserApi
//        PushApi pushApi = apiHelper.creatApi(PushApi.class);
//        return pushApi;
//    }
//
//}