package com.renxin.common.aliPay;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.renxin.common.core.domain.AjaxResult;

import java.math.BigDecimal;

/**
 * 支付宝接口对接
 */
public class AlipayPayUtil {
    /**
     * 发起支付请求
     * 统一收单下单并支付页面接口
     *
     * @return
     */
    public static AjaxResult alipayAppPay(String orderNo, BigDecimal price, String title){
        try{
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigLocal.alipayGateway, AlipayConfigLocal.appId,  AlipayConfigLocal.privateKey, AlipayConfigLocal.format, 
                    AlipayConfigLocal.input_charset_utf,  AlipayConfigLocal.alipayPublicKey, AlipayConfigLocal.sign_type_rsa2);
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            
            //异步接收地址，仅支持http/https，公网可访问
            request.setNotifyUrl(AlipayConfigLocal.notifyUrl);
            //同步跳转地址，仅支持http/https
            //request.setReturnUrl(AlipayConfig.returnUrl);
            /******必传参数******/
            JSONObject bizContent = new JSONObject();
            //商户订单号，商家自定义，保持唯一性
            bizContent.put("out_trade_no",orderNo);
            //支付金额，最小值0.01元
            bizContent.put("total_amount", price);
            //订单标题，不可使用特殊符号
            bizContent.put("subject",title);
            //订单描述
            bizContent.put("body", title);
            //电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
            bizContent.put("product_code", "QUICK_MSECURITY_PAY");

            request.setBizContent(bizContent.toString());
            
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                return AjaxResult.success(response.getBody());
            } else {
                return AjaxResult.error("调用失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("调用失败");
        }

    }

    /**
     * 统一收单线下交易查询接口
     * @param tradeNo  支付宝交易号
     */
    public static AjaxResult alipayQuery(String tradeNo){
        try{
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigLocal.alipayGateway, AlipayConfigLocal.appId,  AlipayConfigLocal.privateKey, AlipayConfigLocal.format, 
                    AlipayConfigLocal.input_charset_utf,  AlipayConfigLocal.alipayPublicKey, AlipayConfigLocal.sign_type_rsa2);
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("trade_no", tradeNo);
            request.setBizContent(bizContent.toString());
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                return AjaxResult.success(response.getBody());
            } else {
                return AjaxResult.error("调用失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("调用失败");
        }
    }

    /**
     * 统一收单交易关闭接口
     * @param tradeNo 支付宝交易号
     */
    public AjaxResult alipayClose(String tradeNo){
        try{
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigLocal.alipayGateway, AlipayConfigLocal.appId,  AlipayConfigLocal.privateKey, AlipayConfigLocal.format, AlipayConfigLocal.input_charset_utf,  AlipayConfigLocal.alipayPublicKey, AlipayConfigLocal.sign_type_rsa2);
            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("trade_no", tradeNo);
            request.setBizContent(bizContent.toString());
            AlipayTradeCloseResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                return AjaxResult.success(response.getBody());
            } else {
                return AjaxResult.error("调用失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("调用失败");
        }
    }

    /**
     * 统一收单交易退款请求
     *  全额退款，out_request_no 选填。
     * 	部分退款，out_request_no 必传。
     * @param tradeNo 支付宝交易号
     */
    public AjaxResult alipayRefund(String tradeNo,BigDecimal price){
        try{
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigLocal.alipayGateway, AlipayConfigLocal.appId,  AlipayConfigLocal.privateKey, AlipayConfigLocal.format, AlipayConfigLocal.input_charset_utf,  AlipayConfigLocal.alipayPublicKey, AlipayConfigLocal.sign_type_rsa2);
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("trade_no", tradeNo);
            bizContent.put("refund_amount", price);
           // bizContent.put("out_request_no", "T"+ Seq.getOrderNum());
            request.setBizContent(bizContent.toString());
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                return AjaxResult.success(response.getBody());
            } else {
                return AjaxResult.error("调用失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("调用失败");
        }
    }

    /**
     * 统一收单交易退款查询
     * @param tradeNo 支付宝交易号
     */
    public AjaxResult alipayRefundQuery(String tradeNo){
        try{
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigLocal.alipayGateway, AlipayConfigLocal.appId,  AlipayConfigLocal.privateKey, AlipayConfigLocal.format, AlipayConfigLocal.input_charset_utf,  AlipayConfigLocal.alipayPublicKey, AlipayConfigLocal.sign_type_rsa2);
            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("trade_no", tradeNo);
          //  bizContent.put("out_request_no","T"+ Seq.getOrderNum());
            request.setBizContent(bizContent.toString());
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                return AjaxResult.success(response.getBody());
            } else {
                return AjaxResult.error("调用失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("调用失败");
        }
    }

}
