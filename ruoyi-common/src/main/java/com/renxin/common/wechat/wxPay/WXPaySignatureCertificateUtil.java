package com.renxin.common.wechat.wxPay;

import com.renxin.common.wechat.wxPay.WxV3PayConfig;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.SneakyThrows;
import org.apache.http.impl.client.CloseableHttpClient;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WXPaySignatureCertificateUtil {




    /**
     * 证书验证
     * 自动更新的签名验证器
     */
    public static CloseableHttpClient checkSign() throws IOException {
        //验签
        CloseableHttpClient httpClient = null;
        PrivateKey merchantPrivateKey = WXPaySignatureCertificateUtil.getPrivateKey();
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(WxV3PayConfig.Mch_ID, WxV3PayConfig.mchSerialNo, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(WXPaySignatureCertificateUtil.getVerifier()))
                .build();

        return httpClient;
    }




    /**
     * 保存微信平台证书
     */
    private static final ConcurrentHashMap<String, AutoUpdateCertificatesVerifier> verifierMap = new ConcurrentHashMap<>();

    /**
     * 功能描述:获取平台证书，自动更新
     * 注意：这个方法内置了平台证书的获取和返回值解密
     */
    static AutoUpdateCertificatesVerifier getVerifier() throws IOException {
        String mchSerialNo = WxV3PayConfig.mchSerialNo;
        AutoUpdateCertificatesVerifier verifier = null;
        if (verifierMap.isEmpty() || !verifierMap.containsKey(mchSerialNo)) {
            verifierMap.clear();
            try {
                //传入证书
                PrivateKey privateKey = getPrivateKey();
                //刷新
                PrivateKeySigner signer = new PrivateKeySigner(mchSerialNo, privateKey);
                WechatPay2Credentials credentials = new WechatPay2Credentials(WxV3PayConfig.Mch_ID, signer);
                verifier = new AutoUpdateCertificatesVerifier(credentials
                        , WxV3PayConfig.apiV3Key.getBytes("utf-8"));
                verifierMap.put(verifier.getValidCertificate().getSerialNumber()+"", verifier);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            verifier = verifierMap.get(mchSerialNo);
        }
        return verifier;
    }



    /**
     * app生成带签名支付信息
     *
     * @param timestamp 时间戳
     * @param nonceStr  随机数
     * @param prepayId  预付单
     * @return 支付信息
     * @throws Exception
     */
    public static String appPaySign(String timestamp, String nonceStr, String prepayId) throws Exception {
        //上传私钥
        PrivateKey privateKey = getPrivateKey();
        String signatureStr = Stream.of(WxV3PayConfig.APP_ID, timestamp, nonceStr, prepayId)
                .collect(Collectors.joining("\n", "", "\n"));
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(signatureStr.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    /**
     * 小程序及其它支付生成带签名支付信息
     *
     * @param timestamp 时间戳
     * @param nonceStr  随机数
     * @param prepayId  预付单
     * @return 支付信息
     * @throws Exception
     */
    public static String jsApiPaySign(String timestamp, String nonceStr, String prepayId) throws Exception {
        //上传私钥
        PrivateKey privateKey = getPrivateKey();
        String signatureStr = Stream.of(WxV3PayConfig.APP_ID, timestamp, nonceStr, "prepay_id="+prepayId)
                .collect(Collectors.joining("\n", "", "\n"));
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(signatureStr.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(sign.sign());
    }


    /**
     * 获取私钥。
     * 证书路径 本地使用如： D:\\微信平台证书工具\\7.9\\apiclient_key.pem
     * 证书路径 线上使用如： /usr/apiclient_key.pem
     * String filename 私钥文件路径  (required)
     * @return 私钥对象
     */
    public static PrivateKey getPrivateKey() throws IOException {
        //String content = new String(Files.readAllBytes(Paths.get("D:\\微信平台证书工具\\7.9\\apiclient_key.pem")), "utf-8");
        String content = WXPayConstants.WECHAT_MCH_PRIVATE_KEY;
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }

}