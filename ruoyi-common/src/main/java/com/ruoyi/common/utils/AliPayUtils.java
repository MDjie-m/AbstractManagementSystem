package com.ruoyi.common.utils;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeCreateModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;

/**
 * @author:
 * @description:
 * @create: 2024-10-30 11:06
 **/
public class AliPayUtils {
    private static final String ALI_PAY_PRIVATE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgIDaVZ/nUPIddRDxCR8wvozvb1sUHX4Cz/8q97HG01Qge4CAgXM95WRfqgL6YfdjIniWxY7xG3I2pFng9aK+dFypWzSaZeQu8CAyn/+FV6sWzk5nk8L5q22pKnQY3IlVxGZAAc31m1ZN4hdp7cFY/pFGWcgALfU9mfTDnkn/SHHbS0GVf5TEi6Kyf6z8zimLgeXpBSy7JZb+tsS9+7qMYgddGCfJs1jQyTDCtTgsss9QeYh0+lvVIzb4Na4SPCJv25HObEoGxG+HJ15DpaxQ8yOtBrtxj06vU6mxwx3vawZpSzQCe0tbb+s/Of3xthN010qgDBRup8q/7KkA8qyQmQIDAQAB";
    private static final String ALI_PAY_PRIVATE_KEY2 = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDECiC3gx2bvcvy0IZntm1DNPCeGzN2V9f7hCXyhA7FPj0kQSHbu/vywh9vB5QEOiGHCJO7X/qtG9MyGMqZdl+3mnq0GunUEOUW0KUNQyvKRlc05yT/mzrKucb8Jx0u+H/0eh7BFP1lg6iQ3/xCywG0mwHAoPdYvQ3y/VY3TBwpiZmIV2wxqEwEM2KZGRDFsJ568/s6WFIe/6E8VJDLHpCfV/C9CsBnZP5V+mKFYOo/QtmtpohuF8fgGqNM7WTfDDm14ba7flZoRAZeiYdrSdS0woFHZ6f87Ms4SrDj4PkqnWXyjMsYuiHWxpGR/FVYPZLHZuSRrZhdUZCDjdUNvnPPAgMBAAECggEBAILHXtwNlAr5qrzpb0Bw6qMTBhHzg3lHUMZxef83KcNbqd4ttTCpT4K7Dc07+Pp47TTv+93cQZaKIXIiJ8YIugH6BDFyYtseCjYWKoWWcfgWxMZ+DKiYHbhwBtbgIQ/cpz+o2CtmnUTDeAht5BOK6jBZ1+lxeJCyTkPzzThGheMOxHKIMUlTKCErGo92kVahj21blyM/iJoy/3Rhnp/7lMKKNE45BQEx+XU8LmuBNMN96Y6zwWAnoUzhWc32cPKIB1y9vVv4ydpdo6oPO1iT0HiqcpxETQ+efoQlQsQ8EYgjwWJtFn1njLRuhHZPWf2rTzcfzGWzXqUVifO2OBP2o4ECgYEA86ECB+qXrTGC6kNqZbdgcR32aa8kNG59rERoF7ZUGRTW+MeuIeSsARLFN/h38vMz+jcLTvnm6sBLhfbedCwfeu1D1flgYA0WrU44nCT+I1Ud6PcEyBUB7o4kWydX9D1yKzt3aceDgn0P1+br4vSeirurgj2eAYjmCgkxDNQQ4ncCgYEAzf5+c34se9o2U7wfXnutCFD7GzlIMHauftUo1jJiEl62DJEt2bU51yLyQJGYuK/FuHZjSvNRgVTSBs71yqgBKG9Nk2jYFaESGLPUeI5Mk6YIHktJYkugZELiawqhWBiP1yLtItw1rFmB7Wbub8iJvGEWMXA3GYVG2joXkm+mN2kCgYEAroDfEYiJB/b0zJNpPF4y6BMMEvruNdgGkEOXQ5THfAefSybLpV9DjKlDNin00MI+srSGlxUMv4zpC5WeFRwdmFEmS7eJvoLhgVUL21vr5Kbs+5cjh6T0c0jA5cGykDEK+8hmahAMNIUbugufXjrU7I4r9OfpqzPBgeK/jW7AgVUCgYEAuaxqzi8saXaHAXLP05omnRhhPmijaQuQvUHlTSycuXIlJa62ZUW0nPYi5ZBW2CGXVm1fkm8/SBPsO7bQVZqZcJjik7wtCVHlvd5Z5VBeNbeyGZxCyRbhu32OGG28gGujO4d/t6xFtNP5CMN+p8NaYzIfjvBWueonswwpsxRv/gECgYAg6s4QwCfqXsLNCgFNkJ3zCbqzxK2P/9Rs6+icaJG5ISK3l1Xn+cg6rnnzrcIXbFnn07nJ2WTeMU4me99lx4ab3QUtHqDhJFtWVlFmp+zLjYJVhKcwpIl7AeBzlZn+AFeJ10apVz92klgh3xAl0go5zWkBy6knD7Uy64EFnxWUPQ==";
    private static final String ALI_PAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApOp0AOSMksvV0rGgGSUiPcWZVdakHu7gbLz0jxg7WQ+paZT/8QY12y0STs1iA0A2jU1gMfmN8Ts3hdGEtuX8sx0KOQTT9T7ZgzJ6It8BTpxroWkqK9LdjyLPE545+0EYeSVt/k7X6lVVM4ZReZX/3+g4h1TwQCX8MazsNayyezaQ4fn/kJw8VKLovIGWDKPEtQ1VoaRnro5iXC+VSdFn8es+1JuE8+p8xvWlapBeesEBZTwNOxVe2NB7H9sUnXjnAD1si5q2yUrlfVi6FOjnYdEtDlI+fLV8H0l53fAit/jrs+WgKCHP+wi+GHRaVY2aesVopGJPa6givGlkluXUuwIDAQAB";
    private static final String ALI_PAY_PUBLIC_KEY2 = "";
    private static final String APP_ID = "9021000141662885";

    private static AlipayConfig alipayConfig;

    static {
        alipayConfig = new AlipayConfig();
        //服务地址，固定写死
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId(APP_ID);
        alipayConfig.setPrivateKey(ALI_PAY_PRIVATE_KEY);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(ALI_PAY_PUBLIC_KEY);
        alipayConfig.setCharset("UTF8");
        alipayConfig.setSignType("RSA2");
    }


    public static String createAppTradePay(String orderId, String totalAmount, String subject) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setOutTradeNo(orderId);
        model.setTotalAmount(totalAmount);
        model.setSubject(subject);
        request.setBizModel(model);
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        if (response.isSuccess()) {
            return response.getBody();
        }
        return null;
    }

    public static String createTradeCreate(String orderId, String totalAmount, String subject,String buyerId) throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        AlipayTradeCreateModel model = new AlipayTradeCreateModel();
        model.setOutTradeNo(orderId);
        model.setTotalAmount(totalAmount);
        model.setSubject(subject);
        model.setBuyerId(buyerId);
        request.setBizModel(model);
        AlipayTradeCreateResponse response = alipayClient.execute(request);
        String body = alipayClient.pageExecute(request).getBody();

        if (response.isSuccess()) {
            System.out.println(body);
            //目前只知道这个sign有用，但是还没具体用到
            return JSONObject.parseObject(response.getBody()).getString("sign");
        }
        return null;
    }


}
