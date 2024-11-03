package com.ruoyi;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;


import org.junit.Test;


@Slf4j
public class AliPayTest {

    // 「沙箱环境」应用ID - 您的APPID，收款账号既是你的APPID对应支付宝账号。获取地址；https://open.alipay.com/develop/sandbox/app
    public static String app_id = "9021000141662885";
    // 「沙箱环境」商户私钥，你的PKCS8格式RSA2私钥 -【秘钥工具】所创建的公户私钥
//    public static String merchant_private_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgIDaVZ/nUPIddRDxCR8wvozvb1sUHX4Cz/8q97HG01Qge4CAgXM95WRfqgL6YfdjIniWxY7xG3I2pFng9aK+dFypWzSaZeQu8CAyn/+FV6sWzk5nk8L5q22pKnQY3IlVxGZAAc31m1ZN4hdp7cFY/pFGWcgALfU9mfTDnkn/SHHbS0GVf5TEi6Kyf6z8zimLgeXpBSy7JZb+tsS9+7qMYgddGCfJs1jQyTDCtTgsss9QeYh0+lvVIzb4Na4SPCJv25HObEoGxG+HJ15DpaxQ8yOtBrtxj06vU6mxwx3vawZpSzQCe0tbb+s/Of3xthN010qgDBRup8q/7KkA8qyQmQIDAQAB";
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAgNpVn+dQ8h11EPEJHzC+jO9vWxQdfgLP/yr3scbTVCB7gICBcz3lZF+qAvph92MieJbFjvEbcjakWeD1or50XKlbNJpl5C7wIDKf/4VXqxbOTmeTwvmrbakqdBjciVXEZkABzfWbVk3iF2ntwVj+kUZZyAAt9T2Z9MOeSf9IcdtLQZV/lMSLorJ/rPzOKYuB5ekFLLsllv62xL37uoxiB10YJ8mzWNDJMMK1OCyyz1B5iHT6W9UjNvg1rhI8Im/bkc5sSgbEb4cnXkOlrFDzI60Gu3GPTq9TqbHDHe9rBmlLNAJ7S1tv6z85/fG2E3TXSqAMFG6nyr/sqQDyrJCZAgMBAAECggEAFvDqCadGcJkI0UF5Bpve4+XdSRAFg05fyipPzXCbglbeha3nQzUDs9Q7j+qAMMUtmZXVtOdT/AZj4ut40eGABt4UDghSGCaoio30+8NuVrxgkexFaYfBcu1c6gtX9Oc/vJRUkP3n/xdlSBvN+XgvHyNoX9bo0Uua98VOtw+W16PbMOv52RbDHY/x3dUFNC8gJCjMk1lHy9FOKYFq51ZSiQNofo+n/1vRRyU4FZdyvEnlmeMr7/Jfx4PqkaNLPsOoMFgxnpN64D/2Ht+ixZ411Xn0uS2rsfiWpdOVqNO/GmZd8QoLL+IB/bQuzkkm7yAkQQlY+ocxh4DDL7C0qq18kQKBgQC4w86C3e/3HoRVfOmxwE2qTIrweynENpnGgRmhkxD/QIOIRbc+jV7QEUUrZd5fVcAfrFFj7VLhr/Za4c7yWnRvH5DOcg9FBTLopFV+2Tgsv2d3Vmbz66bHRpVEdrjysXLnBzV6ECdCl1YVcDK7+ICCgq1QdeOFkDQWicJuK5pX/wKBgQCyDBAGh7ldkRxGepqd6Re8ukec0EApJZQQJp+5Z9kI0CpDlLvX7Rd7y9INOi5MAz+i2MBCm3gb7mbQ1/zzM1x0sz5PNhHwqhUB9iyHazIpjDlRMH8YNbkMDps4mf7DhsgaCmfQ7nwD2OKbxks6HErXssCgG+uBO5jg3W7WGFTXZwKBgA/6v02KLErPFNrf5sY8v3rPC+UogcA6iKomWDUlJv8scTPD88kxKNYN6FGMieLs46TZnmBqEhT6xu+tP9yt/gvuSvPP4YoynOiJ7er+lmEoXKZxjo3h/5aUgCxfqDc1KZ7ZfMKFyx9zPzqzbW/cWiDvdFXVHExc4GRinbIxizO5AoGBAIXwJxoSffKSWBf44VmLYOz7oSWpT6rkdwbTV3KtWYqUtkaSm+CqnfDl+kUzEGLkSrnhnng85lT0uem/oaz+ZT3xHR2Pil1mRVltbEAO9vRkZTXbarC2sVcNod0pk4pZWSylyUVWMnrebFJNEIhNgkvTT67icf6M68u1KEzsjt5fAoGAbQDhbfmjgOVUUPDm3noaEtouljfZYOJRm1qDO/nUbYVyTuz5y6mHDBevxMc0z+TyXlSsfvsZUegWH8IySElD1H+3eEzSRkxVrMM07CzqHqnYfJ1RonLmiTOfy7GvPDr/QWPT7A0/eBUudCYHcr/j21JyMjr4dEAr+Z8ZkD1ubt8=";
//    public static String merchant_private_key22 = "";


    // 「沙箱环境」支付宝公钥 -【秘钥填写】后提供给你的支付宝公钥
//    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApOp0AOSMksvV0rGgGSUiPcWZVdakHu7gbLz0jxg7WQ+paZT/8QY12y0STs1iA0A2jU1gMfmN8Ts3hdGEtuX8sx0KOQTT9T7ZgzJ6It8BTpxroWkqK9LdjyLPE545+0EYeSVt/k7X6lVVM4ZReZX/3+g4h1TwQCX8MazsNayyezaQ4fn/kJw8VKLovIGWDKPEtQ1VoaRnro5iXC+VSdFn8es+1JuE8+p8xvWlapBeesEBZTwNOxVe2NB7H9sUnXjnAD1si5q2yUrlfVi6FOjnYdEtDlI+fLV8H0l53fAit/jrs+WgKCHP+wi+GHRaVY2aesVopGJPa6givGlkluXUuwIDAQAB";
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApOp0AOSMksvV0rGgGSUiPcWZVdakHu7gbLz0jxg7WQ+paZT/8QY12y0STs1iA0A2jU1gMfmN8Ts3hdGEtuX8sx0KOQTT9T7ZgzJ6It8BTpxroWkqK9LdjyLPE545+0EYeSVt/k7X6lVVM4ZReZX/3+g4h1TwQCX8MazsNayyezaQ4fn/kJw8VKLovIGWDKPEtQ1VoaRnro5iXC+VSdFn8es+1JuE8+p8xvWlapBeesEBZTwNOxVe2NB7H9sUnXjnAD1si5q2yUrlfVi6FOjnYdEtDlI+fLV8H0l53fAit/jrs+WgKCHP+wi+GHRaVY2aesVopGJPa6givGlkluXUuwIDAQAB";
//    public static String alipay_public_key2 = "";

    // 「沙箱环境」服务器异步通知回调地址
    public static String notify_url = "http://3in2sf.natappfree.cc/alipay_notify_url";
    // 「沙箱环境」页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "https://www.baidu.com";
    // 「沙箱环境」
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";

    private AlipayClient alipayClient;

    @Before
    public void init() {
        this.alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id,
                merchant_private_key,
                "json",
                charset,
                alipay_public_key,
                sign_type);
    }

    @Test
    public void test_aliPay_pageExecute() throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(notify_url);
        request.setReturnUrl(return_url);

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "xfg2024092709120005");  // 我们自己生成的订单编号
        bizContent.put("total_amount", "0.01"); // 订单的总金额
        bizContent.put("subject", "测试商品");   // 支付的名称
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        request.setBizContent(bizContent.toString());

        String form = alipayClient.pageExecute(request).getBody();
        log.info("测试结果：{}", form);

        /**
         * 会生成一个form表单；
         * <form name="punchout_form" method="post" action="https://openapi-sandbox.dl.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=CAAYYDIbvUNRDvY%2B%2BF5vghx2dL9wovodww8CK0%2FferNP1KtyXdytBVLdZKssaFJV%2B8QksVuKlU3qneWhWUuI7atLDgzpussJlJhxTMYQ3GpAfOP4PEBYQFE%2FORemzA2XPjEn88HU7esdJdUxCs602kiFoZO8nMac9iqN6P8deoGWYO4UAwE0RCV65PKeJTcy8mzhOTgkz7V018N9yIL0%2BEBf5iQJaP9tGXM4ODWwFRxJ4l1Egx46FNfjLAMzysy7D14LvTwBi5uDXV4Y%2Bp4VCnkxh3Jhkp%2BDP9SXx6Ay7QaoerxHA09kwYyLQrZ%2FdMZgoQ%2BxSEOgklIZtYj%2FLbfx1A%3D%3D&return_url=https%3A%2F%2Fgaga.plus&notify_url=http%3A%2F%2Fngrok.sscai.club%2Falipay%2FaliPayNotify_url&version=1.0&app_id=9021000132689924&sign_type=RSA2&timestamp=2023-12-13+11%3A36%3A29&alipay_sdk=alipay-sdk-java-4.38.157.ALL&format=json">
         * <input type="hidden" name="biz_content" value="{&quot;out_trade_no&quot;:&quot;100001001&quot;,&quot;total_amount&quot;:&quot;1.00&quot;,&quot;subject&quot;:&quot;测试&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}">
         * <input type="submit" value="立即支付" style="display:none" >
         * </form>
         * <script>document.forms[0].submit();</script>
         */
    }

    /**
     * 查询订单
     */
    @Test
    public void test_alipay_certificateExecute() throws AlipayApiException {

        AlipayTradeQueryModel bizModel = new AlipayTradeQueryModel();
        bizModel.setOutTradeNo("xfg2024092709120001");

        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(bizModel);

        String body = alipayClient.execute(request).getBody();
        log.info("测试结果：{}", body);
    }

    /**
     * 退款接口
     */
    @Test
    public void test_alipay_refund() throws AlipayApiException {
        AlipayTradeRefundRequest request =new AlipayTradeRefundRequest();
        AlipayTradeRefundModel refundModel =new AlipayTradeRefundModel();
        refundModel.setOutTradeNo("daniel82AAAA000032333361X03");
        refundModel.setRefundAmount("1.00");
        refundModel.setRefundReason("退款说明");
        request.setBizModel(refundModel);

        AlipayTradeRefundResponse execute = alipayClient.execute(request);
        log.info("测试结果：{}", execute.isSuccess());
    }

}
