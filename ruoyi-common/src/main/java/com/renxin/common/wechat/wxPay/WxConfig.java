package com.renxin.common.wechat.wxPay;

import lombok.Data;

@Data
public class WxConfig {

    //商户
    //商户id
    public static String mchId = "1634006526";
    ////平台证书序列号
    public static String mchSerialNo = "63D29D7E1B71E7BE5B1D85B96C32B9E7D95FA400";
    //// API V3密钥
    public static String apiV2Key = "MIIEKDCCAxCgAwIBAgIUY9Kdfhtx5722";
    public static String apiV3Key = "AkNOMREwDwYDVQQHDAhTaGVuWmhlbjC3";
    

    //咨询师app
    public static String consultantAppId = "wxfb9220d65402becc";
    public static String consultantAppSecret = "26cd7edcedc80375008a4d6a12df9be0";
    
    //小程序
    public static String programAppId = "wxff96ed0f6fbd6933";
    public static String programAppSecret = "d8e628314926cc5e1563c6a39f95573d";
    
    
    //其他常量
    ////主地址
    public static final String DOMAIN_API = "https://api.mch.weixin.qq.com/v3";
    ////app下单
    public static final String PAY_TRANSACTIONS_APP = "/pay/transactions/app";
    ////微信支付回调
    public static final String WECHAT_PAY_NOTIFY_URL = "https://api.ssgpsy.com/" + "consultant/order/wxPaySuccess/callback";
    ////申请退款
    public static final String REFUND_DOMESTIC_REFUNDS  = "/refund/domestic/refunds";
    //微信退款回调
    public static final String WECHAT_REFUNDS_NOTIFY_URL = "https://xxx.xxxx.com/api/appPayment/weChatPayRefundsNotify";
    //关闭订单
    public static final String PAY_TRANSACTIONS_OUT_TRADE_NO   = "/pay/transactions/out-trade-no/{}/close";
    public static final String GRANT_TYPE                        = "authorization_code";
    public static final String JS_CODE_LOGIN_URL                 = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String TOKEN_URI                         = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";
    public static final String USER_LIST_URI                     = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
    public static final String SEND_SUBSCRIBE_MESSAGE_URI        = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
    //订阅消息模版
    public static final String CONSULT_START_TEMPLATE_ID         = "xxkVfDGqKTt5CojlhLoFsAj2QjFRL7apYbWd3reEuOk";
    public static final String SCHEDULE_SUCCESS_TEMPLATE_ID      = "1lFm-IIBDKwbc5WCvKgmKMtGNumEAWb7eiPxqA_YPdI";
    public static final String SCHEDULE_LEAVE_TEMPLATE_ID        = "xqXpw0FPvkX8l1ZQ3qYNN1MXaGBvGFkInYy_68UU_5c";
    public static final String UNREAD_MSG_TEMPLATE_ID            = "CDtpVBYOJz1SIrmK-B9nARLPUPVortQ0fCB0Nb6AwTU";
    //小程序code获取openid
    public final static String CODE_2_SESSION = "https://api.weixin.qq.com/sns/jscode2session";
    //微信支付v3 jsapi下单
    public final static String PAY_V3_JSAPI = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    //微信支付v3 申请退款
    public final static String PAY_V3_REFUND = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";
    //微信支付v3 通过商户订单号查询订单
    public final static String PAY_V3_QUERY_OUT = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/%s?mchid=%s";
    //微信支付v3 查询单笔退款
    public final static String PAY_V3_QUERY_REFUND = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds/%s";
    //微信支付v2 支付通知接口地址
    public final static String PAY_V2_NOTIFY = "https://xxx.com/api/wechatPay/wechatPayNotify";
    //微信支付v2 退款通知接口地址
    public final static String PAY_V2_REFUND_NOTIFY = "https://xxx.com/api/wechatPay/wechatRefundNotify";
    //微信支付v3 支付通知接口地址
    public final static String PAY_V3_NOTIFY = "https://api.ssgpsy.com/pocket/wechatProgram/wechatPayNotify";
    //咨询师微信支付v3 支付通知接口地址
    public final static String CONSULTANT_PAY_V3_NOTIFY = "https://api.ssgpsy.com/consulted/api/wechatConsultantPay/v3/wechatConsultantPayNotify";
    //微信支付v3 退款通知接口地址
    public final static String PAY_V3_REFUND_NOTIFY = "https://api.ssgpsy.com/consulted/api/wechatPay/v3/wechatRefundNotify";
    

    //微信商户平台证书私钥 即证书中的apiclient_key.pem文件中的内容 可以直接写在这里 也可以用流读取文件
    public static final String WECHAT_MCH_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbsajUx4MfyT/D\n" +
            "C3Xg6RuCVPJzOyIYdQigkZ0ORmVCh52zUM8L1aKd3LMCnCTXTlzJGvxYEsly/Cx1\n" +
            "3uechBKSobLZJDeoseXnRTLGSB8aoaS0AMIJK4zim7tzw+KxhwQaKSu0d9YekjQy\n" +
            "lkQoSczOA4jQRTBILHc3gV4T9t88tuWTWimUUgdDOmZ6sTrdYqUt/9G4w7wTfuWK\n" +
            "lRdk1dX7rKZ5el1dyu8y1PDSlg/pV4l2cF45IvE/nsZWtDPuTsUXFybmkbCziBC4\n" +
            "aLAtxhfV72k7ix3HAVoc7lWS0E4Ie3C6Ay0ZU5rCAJQbLFOBYNnT11lUEpPuc3pq\n" +
            "D0CFqBtVAgMBAAECggEASb9ujFNw3eaS6E2OaZmhaSvZqBFzhggxrPUsYq2fm1Ln\n" +
            "tR9q410vaP04Yd0aak35D11T+Ff1yhx+5tpN9UpuDbzAHbDVGHgjwVd6xIc3DINS\n" +
            "o9zUDEnvsws3UI+R2qtsmwOzawl0iAiS5v9ci4ThmRf9f0G2rAk78zO5H1XJylTO\n" +
            "4LmYMRXTdT9FFRM93Vk4N6FF+vfMFbgzBhXvNEg3JoOV6Wzmi14JVhkTTP7a4HqJ\n" +
            "Oo6YWbOwGjshRDhTGSHNo88si+DnAd2mzDhki1qw/xcfuIvw9JCpdDJ2QDeEP9eT\n" +
            "XOPx022WjhQO3rnN2Gb6yzP3rHxlSY9XkJ+YreqvRQKBgQDLgaEiokvnlCRIzSvQ\n" +
            "cC23WTypjvI713sr0ffa/J7JTz94Ah9lRJSFiTfiafBTflGEcygcMH91aM5e/plA\n" +
            "djvEKbJCmwMgusfFd0pjy9WMV98Do1w8JIxj4etQbsK3TFyw50gbV6iyv+C9ttrO\n" +
            "kBuT12tJDIoc55rFQhS0h2mJOwKBgQDD2sekDfSBJnNGfM5AG7idXrlt2GjVlS7X\n" +
            "SG+J/VO6Sr9UtKupoS+17zpmC4quDLhOSIfXFsw5Q8h3YYa7WNQd4mlAVdYzpxI1\n" +
            "4VLwQvi7HDmfkA4Dsn3RjPnHuP0y7EgnZ7ptr9OlDoOUsVpUipOqCFcozzS7B159\n" +
            "2sW/3DUkrwKBgQDJmyEj636g68zKZp+6Pfe8ROZo39kv2XgHYFpYEp3Htv+chxXN\n" +
            "QBSW+epoHiVcwa5GNcMNsioDCZIw3665AqiQ2/HiNCj0GrXf9R64IO3su/yOYR71\n" +
            "4gtGxEBXjXLPHhJRmr5/f+b7NkWSDH1V18PKQcXiN0739aYJNz7F3cR6kwKBgQC2\n" +
            "PsNcpdupvdSwd3wYbXMphLY5P5G1hN8UVoPrvD2H8Pv+3yuJacKSoewOfpjqAk4S\n" +
            "Ei3M+JrKgodshuSvOiYPNNpJklYCtGkel+/sP/Vhbw8zqgFQcWvgbTUUItAA7Zrq\n" +
            "h/wTCvR+93V7DFkIp/Zl1uL7x2vCyUWlCMjQEiGlYwKBgHUM+c5gIrcozF+ec+eb\n" +
            "FSmCqmIiLRZkecMm7JbEvk1IhCNQG3LfjmipEeUQdEyMQt33GejGg9lcdYcWxr62\n" +
            "noR7dG34ndLGV/d7wTxgIcqfVxQ/ZCz/bT5SYMZhExq7r3zy3uUqeeh6MSukMJA5\n" +
            "My4+WvMs9HW82I2pYHWJVpJ2\n" +
            "-----END PRIVATE KEY-----\n";



}
