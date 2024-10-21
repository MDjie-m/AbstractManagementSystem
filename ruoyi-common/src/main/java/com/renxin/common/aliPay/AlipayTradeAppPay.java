//package com.renxin.common.aliPay;
//
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipayTradeAppPayRequest;
//import com.alipay.api.domain.ExtUserInfo;
//import com.alipay.api.AlipayConfig;
//import com.alipay.api.domain.AlipayTradeAppPayModel;
//import com.alipay.api.response.AlipayTradeAppPayResponse;
//import com.alipay.api.domain.ExtendParams;
//import com.alipay.api.domain.GoodsDetail;
//
//import com.alipay.api.FileItem;
//import java.util.Base64;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AlipayTradeAppPay {
//    
//    public static void main(String[] args) throws AlipayApiException {
//        // 初始化SDK
//        AlipayClient alipayClient = new DefaultAlipayClient(getAlipayConfig());
//
//        // 构造请求参数以调用接口
//        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//
//        // 设置商户订单号
//        model.setOutTradeNo("testOrder11111");
//
//        // 设置订单总金额
//        model.setTotalAmount("9.00");
//
//        // 设置订单标题
//        model.setSubject("大乐透");
//
//        // 设置产品码
//        model.setProductCode("QUICK_MSECURITY_PAY");
//
//        // 设置订单包含的商品列表信息
//        List<GoodsDetail> goodsDetail = new ArrayList<GoodsDetail>();
//        GoodsDetail goodsDetail0 = new GoodsDetail();
//        goodsDetail0.setGoodsName("ipad");
//        goodsDetail0.setAlipayGoodsId("20010001");
//        goodsDetail0.setQuantity(1L);
//        goodsDetail0.setPrice("2000");
//        goodsDetail0.setGoodsId("apple-01");
//        goodsDetail0.setGoodsCategory("34543238");
//        goodsDetail0.setCategoriesTree("124868003|126232002|126252004");
//        goodsDetail0.setShowUrl("http://www.alipay.com/xxx.jpg");
//        goodsDetail.add(goodsDetail0);
//        model.setGoodsDetail(goodsDetail);
//
//        // 设置订单绝对超时时间
//        model.setTimeExpire("2024-12-31 10:05:00");
//
//        // 设置业务扩展参数
//        ExtendParams extendParams = new ExtendParams();
//        extendParams.setSysServiceProviderId("2088511833207846");
//        extendParams.setHbFqSellerPercent("100");
//        extendParams.setHbFqNum("3");
//        extendParams.setIndustryRefluxInfo("{\"scene_code\":\"metro_tradeorder\",\"channel\":\"xxxx\",\"scene_data\":{\"asset_name\":\"ALIPAY\"}}");
//        extendParams.setSpecifiedSellerName("XXX的跨境小铺");
//        extendParams.setRoyaltyFreeze("true");
//        extendParams.setCardType("S0JP0000");
//        model.setExtendParams(extendParams);
//
//        // 设置公用回传参数
//        model.setPassbackParams("merchantBizType%3d3C%26merchantBizNo%3d2016010101111");
//
//        // 设置商户的原始订单号
//        model.setMerchantOrderNo("20161008001");
//
//        // 设置外部指定买家
//        ExtUserInfo extUserInfo = new ExtUserInfo();
//        extUserInfo.setCertType("IDENTITY_CARD");
//        extUserInfo.setCertNo("362334768769238881");
//        extUserInfo.setName("李明");
//        extUserInfo.setMobile("16587658765");
//        extUserInfo.setMinAge("18");
//        extUserInfo.setNeedCheckInfo("F");
//        extUserInfo.setIdentityHash("27bfcd1dee4f22c8fe8a2374af9b660419d1361b1c207e9b41a754a113f38fcc");
//        model.setExtUserInfo(extUserInfo);
//
//        // 设置通知参数选项
//        List<String> queryOptions = new ArrayList<String>();
//        queryOptions.add("hyb_amount");
//        queryOptions.add("enterprise_pay_info");
//        model.setQueryOptions(queryOptions);
//
//        request.setBizModel(model);
//        // 第三方代调用模式下请设置app_auth_token
//        // request.putOtherTextParam("app_auth_token", "<-- 请填写应用授权令牌 -->");
//
//        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
//        String orderStr = response.getBody();
//        System.out.println(orderStr);
//
//        if (response.isSuccess()) {
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
//            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
//            // System.out.println(diagnosisUrl);
//        }
//    }
//
//    private static AlipayConfig getAlipayConfig() {
//        String privateKey  = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDHEAEUoIjQdviFSQSrj29SyGsIRdJ5+kF4q6QpXuFYYVmc+kD9qLjQubz6BdL0wbJ/rIpdD9kvfDT947JcLATSxQx5EKVsREVyrEzDHhS4BrrIPurRycJB+qfykxRqjxDggLDq8B6+t5UUHRUNFfUcar5S7WJgIchds5r2Dv634xazQDwvKLT20zC0N2CO3NdY7x49a10lSdsq4YztluY0LNVLwcCFnXSAYGnINDSzvLWXNSnkPtyNvd/lRWXhHCAukl0BXHTuPY0USAi1ABJn/FGIsAqFsgEbTUIJDLEI5ImVGmem5HXp9WyD1jqpSp1jSBAuWS1iF+DtFpFGO8ydAgMBAAECggEAAVh3WWdYZWhYd7YtpOc8TmfyaUTnjlGFvcYTPwBYL5vA2lHKvKw2S5rlzx7Y9TSV+0z0RnrD8hE53nl6jCr0AWS5l9vn8lpnycWmYy9B3piVDKN4PODLQlzqY7ZgzfOXnpzgFUZ4PrvI8zDbIpKCbKXAEZlvL80qqI99PaqOSDnqsHM6EMJHv8Ox/GLZAav23YDGd/mzKM62lJaNoXs6uGL/whyOYQi667YMYPwfuK4G4LIC5Onu5Wz/yrFBnBqSD7YAAIueL6TqA8IjbVM9dvM+QawUupvyRcAOCgHaTbqq/88V65BxuzbvCSzx0JVwCQQXKbJyUX6VX/PAMqdxAQKBgQDrAdDuuO/ad5wGZKsBOIf5NPCwatyDUFHj+XaxgwrFu923GdzjvTjgTyBmppPlDvo0BTOU28xtGlzRWDUFR7yI7GeFD6Z8PEEfjXOoqC21sRJ75IljmFxcFoP6Wuy3l7ap+rb100JIGQDIvyQgdAiV4Xo43mH/zDj8HycfyWN8XQKBgQDY2DN7s0cBxE3YyGvIyeyKFAVhQNXaHdrY+CFXD+a8uAmwVYpuESR140Edsc4iGsraJW3h7x7AuVziz33/4qjizD6oPjXWcvB9UZOLs3FmbbEVQbd3uFt95fg1uuaVtfaN2MAPyi1G3LTtrG+m560oLxhIswImG2rQ2Nep4sKNQQKBgQCKB+YM4QjYG5rD0oryS57py6N3Kzzbz+hZa6PAfYRtQXvnWMR56yEl9rPENyD00Ag7V7bFPxhiGLLdrt5tfIYLMjdXWyrhG47wlmeRbz5x0lBV/pSHr+zHld3sxUz2RKDgU8b0uiHbMPxZ7JKukRid1/KuxkdvNCdgRnf3XpUVbQKBgQDJhZPDAEebQr4+G3Tf85iaciTVzxVvUZOTVHZXnPWIqViHwdeWluPVk09us1GorFGdDm/iKwHfheTm/IJSjmj57WrFnJsYI1WQgYGnsSpQUNzjrmE9J94PUOuts3ab57Gs/aTwh6owUujqgPh6mpB36dydo475rz1DvmIfBhhcgQKBgEjMI+PgfLvvq7IuI++jzvJpEtltYLc4C/lDpd5isQaPais2iuIJGk4Ma7jlWeZLuPvBjO9hgxXFAy+oaQGw1xLsOrQmJBqe25+uVKF2cBfgsooZL7VVzKu017PAvrL3hxtV8cFzxyrhu6Uk6uveru5rff2rBvXQPHwo73NjFm4m";;
//        //String privateKey  = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNogTmoczE2RYMeWhaOIU+Uuuan+7+YBmH7tpuvDxGQM1VpEY9KmS4MxFaD3I7j/GfxmCLH6vPXKYhyD+ZMWkUaBChJ+Qc4IYJwXXHrqGKFoYR0EWsEr485wBYCpG5mg71qAF1z2Rb7/97I6KtuMwWZC9v6o3KWetONN1F/raj/jIXN/yItqouwvhGJ4PB37xWiOudUIguQq7WOARxsiJevRYvdMd7ca/uGQ8bPPrOKSF18diTUPSGOh3PFz9gCWUZZpo4XYKO74B1/g5px2hnOgllwPg0MpKeuIxOK3cyyAdMI9LHmkv3R5cgUVFBo0x2k1V2qJgJKBS1DKAP7lfTAgMBAAECggEAHzMyyy+XcUG1NrhEAaPzzj/sANr8hfLbjPNUERLMYFmEIjqW8oB1QZKWXvWKH47dFmpX+C3Y5kwtmMGJKREWadVm3GlyOd5ICLwiWwOgOFAv5pqvznEukTH+BGcHpRBH/VuuPqqaHBqpeBHok0ZjRwd437mW1J2sVUUV/YZX65YOwiyATLuLP8y5lXTVOX/dm4IPDslc6mgfBHaIYwQATmCixhltxLWsu99+JySTCmYaJ+eCuHnBI555G4ZSMAQ1EVKWUSvdYMnktb1qagMP23unXPk8HiN8knH3xGKd4PTBN1DCUsu7fJ8zRO61TBoowGfmaR+w5t8mzuvbATq+AQKBgQDA7BvogNNLHaHxWzKg0uHteQh0CfKsQAHylQ7hk6/3yAf2ii6pvActSgp53z1+OmmPKLC0xChqVI3He8hkee5zOhX8hD/7cc07zpRr6iBfePYFEEz4fA5JtjzwT4IW+gpM9ltuwtNgXNuQWqzHarTBHwY2RJdxMSs+dxFVOUf7AQKBgQC78OhvArsaEJRr5kkewZPfuNHdnAxHSRyyLBpQkjbOvzDx6z3a9zqtvPd6N4jIry1yvQJAF6OkySYOVSmNNUE+slWa7ISvH7NBc2/x17zZC0jFmpSWzxOWD+1tvi40Bdajeh3UtvwcqXbl8oX7VEFJERJmg52bsGQCMvC+M+h20wKBgESlv2iELqtT6AQSB3nikDIIF1HzGD7ip87B64GpI7xvmSXJFcGf/o3HAuNK5H6+GkGuoPIxuo/bdne9PBlEqeymhpxMEthOLlFRuM6NiOdwC1rR6hGhUxQ3nIeGQywZ3QB1ySMogD6RzC8ZlNGAzsDJYVR+I8VLUa/wfmJs94gBAoGADqyPqWNAMpfKzuXrHaClqBkpIbF2b/ZwbCTvZQ0VhGz/KFRivv8AsOIJPJyrbGfpF4VUcKan20ztUL8rgGABGSEZeAAxl+7Usy6NNgfPMe8knnEkWuYONHBH7IKl3t/500fMnngvFRnSM8AM4APDGS5hDmoGW0qZdGZ/z4oEA4sCgYAPuMnvZ9YK+iowGEwz2j0HRsQNzBc3hEGznQg5oZ8HlBApnFcTC7Hj9BCkBThu6nC/wVMcxnR2VrOX71obnPHgtHWDlUnN/arvIHowb6Xr/8GRZbBwqGdcWGgjoW1SV3V9Vb4nHKQCcq9cR/O4mEEV7lSyOyHI8FnyLpF8+OD4lw==";
//        String alipayPublicKey =   "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAksnfpLwBaeIbPrWbejmTDUZEqwOMtIIaQQNqZmq+Bt8t7wqQkPs058Pd3d0NtzOYdf1fhW+Q935UVfeNBfG8lXPhlJdsKSA360OYYpwmzfwonF5GLKZ3TPaV73ji9n/ThnqdsW346yhGOVccfU2ciuEkKOOaTHDOUcJMosYE7aW9/Snll+ZYPJNjERjgNsuOJEFrePm9rXaAp//E1CW9SKHUp6PV3G1SwOWYAQkRAu0JM8pfWGcynWJd+sCMoOAMAIdDLuVsjDKPsfLMLXMzneLOP2pobaznokTVw5/G1pyk1uGR2bf76kq1nn79Un618ytwk8JNsSo/6EI0YkW8JwIDAQAB";;
//        //String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmBZxbXUrki6u3Fvae5Rcvc39bOj91U6r9cPQFP42jhHC01X7PxXXBMogZxovi0OfsJmiIK9GLU84yPdxINTILaSR0UGVdY11mSyN9qhAO6333xDIcRUb5mEBdZK4X8mx3l5gUKynd0FZlA6i1YhBqvn53OWuFsUxleOeM1xWk1Nvy+rKljoPOZYHXVxpRkNScsig+0tqD8H0kzWXN9ps5KuJxugXIBugVAVVwo/dKBMTgk8iSA3bb6djGEpxIzPlGrzkKg/fv3Sc4zB3bt/19aSpL8kzi3NuIRIyoYdjOQg9dGriYvNlUuSJLlH1TREqvRF2xV3kxJdPwOMICiy7XwIDAQAB";
//        AlipayConfig alipayConfig = new AlipayConfig();
//        alipayConfig.setServerUrl("https://openapi.alipay.com/gateway.do");
//        alipayConfig.setAppId("2021004188692037");
//        //alipayConfig.setAppId("9021000141643145");//沙箱
//        alipayConfig.setPrivateKey(privateKey);
//        alipayConfig.setFormat("json");
//        alipayConfig.setAlipayPublicKey(alipayPublicKey);
//        alipayConfig.setCharset("UTF-8");
//        alipayConfig.setSignType("RSA2");
//        return alipayConfig;
//    }
//}
