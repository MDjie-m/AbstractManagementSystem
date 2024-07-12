package com.renxin.wechat.service;

import com.renxin.wechat.vo.WechatPayVO;

public interface WechatPayV3ApiService {

    void wechatPay(WechatPayVO wechatPay);
    
    void wechatConsultantPay(WechatPayVO wechatPay);

    void wechatPayNotify(String outTradeNo, String payId);
    
    void wechatConsultantPayNotify(String outTradeNo, String payId);

}
