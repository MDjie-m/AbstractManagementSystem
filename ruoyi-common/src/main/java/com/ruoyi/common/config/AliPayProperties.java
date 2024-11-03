package com.ruoyi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author:
 * @description:
 * @create: 2024-11-03 22:28
 **/
@Component
@Data
@ConfigurationProperties(prefix = "alipaymp")
public class AliPayProperties {
    public String appId;
    // 「沙箱环境」商户私钥，你的PKCS8格式RSA2私钥 -【秘钥工具】所创建的公户私钥
    public String merchantPrivateKey;
    // 「沙箱环境」支付宝公钥 -【秘钥填写】后提供给你的支付宝公钥
    public String alipayPublicKey;

    // 「沙箱环境」服务器异步通知回调地址
    public String notifyUrl;
    // 「沙箱环境」页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String returnUrl;
    // 「沙箱环境」
    public String gatewayUrl;
    // 签名方式
    public String sign_type = "RSA2";
    // 字符编码格式
    public String charset = "utf-8";
//        public static String app_id = "9021000141662885";
//        // 「沙箱环境」商户私钥，你的PKCS8格式RSA2私钥 -【秘钥工具】所创建的公户私钥
//        public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAgNpVn+dQ8h11EPEJHzC+jO9vWxQdfgLP/yr3scbTVCB7gICBcz3lZF+qAvph92MieJbFjvEbcjakWeD1or50XKlbNJpl5C7wIDKf/4VXqxbOTmeTwvmrbakqdBjciVXEZkABzfWbVk3iF2ntwVj+kUZZyAAt9T2Z9MOeSf9IcdtLQZV/lMSLorJ/rPzOKYuB5ekFLLsllv62xL37uoxiB10YJ8mzWNDJMMK1OCyyz1B5iHT6W9UjNvg1rhI8Im/bkc5sSgbEb4cnXkOlrFDzI60Gu3GPTq9TqbHDHe9rBmlLNAJ7S1tv6z85/fG2E3TXSqAMFG6nyr/sqQDyrJCZAgMBAAECggEAFvDqCadGcJkI0UF5Bpve4+XdSRAFg05fyipPzXCbglbeha3nQzUDs9Q7j+qAMMUtmZXVtOdT/AZj4ut40eGABt4UDghSGCaoio30+8NuVrxgkexFaYfBcu1c6gtX9Oc/vJRUkP3n/xdlSBvN+XgvHyNoX9bo0Uua98VOtw+W16PbMOv52RbDHY/x3dUFNC8gJCjMk1lHy9FOKYFq51ZSiQNofo+n/1vRRyU4FZdyvEnlmeMr7/Jfx4PqkaNLPsOoMFgxnpN64D/2Ht+ixZ411Xn0uS2rsfiWpdOVqNO/GmZd8QoLL+IB/bQuzkkm7yAkQQlY+ocxh4DDL7C0qq18kQKBgQC4w86C3e/3HoRVfOmxwE2qTIrweynENpnGgRmhkxD/QIOIRbc+jV7QEUUrZd5fVcAfrFFj7VLhr/Za4c7yWnRvH5DOcg9FBTLopFV+2Tgsv2d3Vmbz66bHRpVEdrjysXLnBzV6ECdCl1YVcDK7+ICCgq1QdeOFkDQWicJuK5pX/wKBgQCyDBAGh7ldkRxGepqd6Re8ukec0EApJZQQJp+5Z9kI0CpDlLvX7Rd7y9INOi5MAz+i2MBCm3gb7mbQ1/zzM1x0sz5PNhHwqhUB9iyHazIpjDlRMH8YNbkMDps4mf7DhsgaCmfQ7nwD2OKbxks6HErXssCgG+uBO5jg3W7WGFTXZwKBgA/6v02KLErPFNrf5sY8v3rPC+UogcA6iKomWDUlJv8scTPD88kxKNYN6FGMieLs46TZnmBqEhT6xu+tP9yt/gvuSvPP4YoynOiJ7er+lmEoXKZxjo3h/5aUgCxfqDc1KZ7ZfMKFyx9zPzqzbW/cWiDvdFXVHExc4GRinbIxizO5AoGBAIXwJxoSffKSWBf44VmLYOz7oSWpT6rkdwbTV3KtWYqUtkaSm+CqnfDl+kUzEGLkSrnhnng85lT0uem/oaz+ZT3xHR2Pil1mRVltbEAO9vRkZTXbarC2sVcNod0pk4pZWSylyUVWMnrebFJNEIhNgkvTT67icf6M68u1KEzsjt5fAoGAbQDhbfmjgOVUUPDm3noaEtouljfZYOJRm1qDO/nUbYVyTuz5y6mHDBevxMc0z+TyXlSsfvsZUegWH8IySElD1H+3eEzSRkxVrMM07CzqHqnYfJ1RonLmiTOfy7GvPDr/QWPT7A0/eBUudCYHcr/j21JyMjr4dEAr+Z8ZkD1ubt8=";
//        // 「沙箱环境」支付宝公钥 -【秘钥填写】后提供给你的支付宝公钥
//        public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApOp0AOSMksvV0rGgGSUiPcWZVdakHu7gbLz0jxg7WQ+paZT/8QY12y0STs1iA0A2jU1gMfmN8Ts3hdGEtuX8sx0KOQTT9T7ZgzJ6It8BTpxroWkqK9LdjyLPE545+0EYeSVt/k7X6lVVM4ZReZX/3+g4h1TwQCX8MazsNayyezaQ4fn/kJw8VKLovIGWDKPEtQ1VoaRnro5iXC+VSdFn8es+1JuE8+p8xvWlapBeesEBZTwNOxVe2NB7H9sUnXjnAD1si5q2yUrlfVi6FOjnYdEtDlI+fLV8H0l53fAit/jrs+WgKCHP+wi+GHRaVY2aesVopGJPa6givGlkluXUuwIDAQAB";
//
//        // 「沙箱环境」服务器异步通知回调地址
//        public static String notify_url = "http://xid962.natappfree.cc/alipay_notify_url";
//        // 「沙箱环境」页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//        public static String return_url = "https://www.baidu.com";
//        // 「沙箱环境」
//        public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
//        // 签名方式
//        public static String sign_type = "RSA2";
//        // 字符编码格式
//        public static String charset = "utf-8";


}