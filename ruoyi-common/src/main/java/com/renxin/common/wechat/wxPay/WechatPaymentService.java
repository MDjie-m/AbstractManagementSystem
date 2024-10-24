package com.renxin.common.wechat.wxPay;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 影子
 */
public interface WechatPaymentService
{


    /**
     * 微信商品支付
     * @return
     */
    public Map<String, Object> weChatDoUnifiedOrder(String orderNum, BigDecimal price, String title) ;




    /**
     * 微信支付回调通知
     * @param
     * @return
     */
    public Map<String, Object> weChatNotificationHandler(HttpServletRequest request, HttpServletResponse response);

    /**
     *微信关闭订单
     * @param outTradeNo
     * @return
     */
    public Map<String, Object> closeOrder(String outTradeNo);




    /**
     * 申请退款
     * @param
     * @return
     */
    public Map<String, Object> weChatPayRefundsNotify(HttpServletRequest request);




    /**
     * 微信退款
     * @param outTradeNo 订单号
     * @return
     */
    public Map<String, Object> weChatRefunds(String outTradeNo);
}