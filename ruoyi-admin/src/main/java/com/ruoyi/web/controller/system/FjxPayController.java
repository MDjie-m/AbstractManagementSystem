package com.ruoyi.web.controller.system;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ruoyi.common.config.AliPayProperties;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.service.IFjxShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author:
 * @description:
 * @create: 2024-10-30 14:23
 **/
@Slf4j
@RequestMapping
@RestController
public class FjxPayController {


    @Autowired
    private IFjxShoppingCartService fjxShoppingCartService;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AliPayProperties aliPayProperties;


    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public String pay() throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(aliPayProperties.notifyUrl);
        request.setReturnUrl(aliPayProperties.returnUrl);

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "fjx"+ UUID.randomUUID());  // 我们自己生成的订单编号
        bizContent.put("total_amount", "0.01"); // 订单的总金额
        bizContent.put("subject", "测试商品");   // 支付的名称
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        request.setBizContent(bizContent.toString());

        String form = alipayClient.pageExecute(request).getBody();
        log.info("测试结果：{}", form);
        return form;
    }
}
