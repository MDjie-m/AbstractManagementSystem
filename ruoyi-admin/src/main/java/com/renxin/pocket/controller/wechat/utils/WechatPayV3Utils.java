package com.renxin.pocket.controller.wechat.utils;


import com.alibaba.fastjson2.JSONObject;
import com.renxin.pocket.controller.wechat.constant.WechatMCHConstants;


import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.*;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.constant.WechatPayHttpHeaders;
import com.wechat.pay.contrib.apache.httpclient.notification.Notification;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationHandler;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationRequest;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

/**
 * 微信V3支付工具类
 */
@Component
public class WechatPayV3Utils {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //商户证书私钥
    private PrivateKey merchantPrivateKey;

    //证书
    private Verifier verifier;

    //请求客户端
    private CloseableHttpClient httpClient;

    /**
     * 获取商户证书私钥
     */
    private void setMerchantPrivateKey() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("cer/apiclient_key.pem");
        InputStream certStream = classPathResource.getInputStream();
        //读取文件形式加载商户私钥
        merchantPrivateKey = PemUtil.loadPrivateKey(certStream);
        //直接把商户私钥以字符串形式配置
        //merchantPrivateKey = PemUtil.loadPrivateKey(new ByteArrayInputStream(WechatConstants.WECHAT_MCH_PRIVATE_KEY.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 获取微信证书
     *
     * @throws Exception
     */
    private void setVerifier() throws Exception {
        if (merchantPrivateKey == null) {
            setMerchantPrivateKey();
        }
        // 获取证书管理器实例
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(WechatMCHConstants.WECHAT_MCH_ID,
                new WechatPay2Credentials(WechatMCHConstants.WECHAT_MCH_ID, new PrivateKeySigner(WechatMCHConstants.WECHAT_MCH_SERIAL_NUM, merchantPrivateKey)),
                WechatMCHConstants.WECHAT_MCH_SECRET_V3.getBytes(StandardCharsets.UTF_8));
        // ... 若有多个商户号，可继续调用putMerchant添加商户信息

        // 从证书管理器中获取verifier
        verifier = certificatesManager.getVerifier(WechatMCHConstants.WECHAT_MCH_ID);
    }

    /**
     * 创建请求客户端
     *
     * @throws Exception
     */
    private void setHttpClient() throws Exception {
        if (verifier == null) {
            setVerifier();
        }
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(WechatMCHConstants.WECHAT_MCH_ID, WechatMCHConstants.WECHAT_MCH_SERIAL_NUM, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier));
        // ... 接下来，你仍然可以通过builder设置各种参数，来配置你的HttpClient

        httpClient = builder.build();
    }

    /**
     * 发送POST请求
     *
     * @param url    请求地址
     * @param params json参数
     * @return
     */
    public JSONObject sendPost(String url, JSONObject params) {
        try {
            if (httpClient == null) {
                setHttpClient();
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setEntity(new StringEntity(params.toJSONString(), StandardCharsets.UTF_8));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String bodyAsString = EntityUtils.toString(response.getEntity());
            logger.info("微信返回的内容：" + bodyAsString);
            if (StringUtils.isEmpty(bodyAsString)) {
                return null;
            }
            return JSONObject.parseObject(bodyAsString);
        } catch (Exception e) {
            logger.error("微信支付V3请求失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送get请求
     *
     * @param url 请求地址 参数直接在地址上拼接
     * @return
     */
    public JSONObject sendGet(String url) {
        try {
            if (httpClient == null) {
                setHttpClient();
            }
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("Accept", "application/json");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String bodyAsString = EntityUtils.toString(response.getEntity());
            logger.info("微信返回的内容：" + bodyAsString);
            if (StringUtils.isEmpty(bodyAsString)) {
                return null;
            }
            return JSONObject.parseObject(bodyAsString);
        } catch (Exception e) {
            logger.error("微信支付V3请求失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 回调通知验签与解密
     *
     * @param request
     * @return
     */
    public JSONObject getCallbackData(HttpServletRequest request) {
        try {
            if (verifier == null) {
                setVerifier();
            }
            String wechatPaySerial = request.getHeader(WechatPayHttpHeaders.WECHAT_PAY_SERIAL);
            String nonce = request.getHeader(WechatPayHttpHeaders.WECHAT_PAY_NONCE);
            String timestamp = request.getHeader(WechatPayHttpHeaders.WECHAT_PAY_TIMESTAMP);
            String signature = request.getHeader(WechatPayHttpHeaders.WECHAT_PAY_SIGNATURE);
            String body;
            BufferedReader reader = request.getReader();
            String line ;
            StringBuilder inputString = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            body = inputString.toString();
            reader.close();
            // 构建request，传入必要参数
            NotificationRequest res = new NotificationRequest.Builder().withSerialNumber(wechatPaySerial)
                    .withNonce(nonce)
                    .withTimestamp(timestamp)
                    .withSignature(signature)
                    .withBody(body)
                    .build();
            NotificationHandler handler = new NotificationHandler(verifier, WechatMCHConstants.WECHAT_MCH_SECRET_V3.getBytes(StandardCharsets.UTF_8));
            // 验签和解析请求体
            Notification notification = handler.parse(res);
            logger.info("回调通知数据：" + notification.toString());
            // 解析开数据
            String decryptData = notification.getDecryptData();
            logger.info("回调解析数据：" + decryptData);
            if (StringUtils.isEmpty(decryptData)) {
                return null;
            }
            return JSONObject.parseObject(decryptData);
        } catch (Exception e) {
            logger.error("微信支付V3回调失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 微信支付v3签名 RSA签名
     *
     * @param message 要签名的字符串
     * @return
     */
    public String signRSA(String message) {
        try {
            if (merchantPrivateKey == null) {
                setMerchantPrivateKey();
            }
            Signer signer = new PrivateKeySigner(WechatMCHConstants.WECHAT_MCH_SERIAL_NUM, merchantPrivateKey);
            Signer.SignatureResult signature = signer.sign(message.getBytes(StandardCharsets.UTF_8));
            return signature.getSign();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}