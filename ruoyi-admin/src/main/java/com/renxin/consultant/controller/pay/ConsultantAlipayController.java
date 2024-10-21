package com.renxin.consultant.controller.pay;

import com.renxin.common.aliPay.AlipayPayUtil;
import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.LimitType;
import com.renxin.common.exception.ServiceException;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.pocket.controller.wechat.dto.WechatPayDTO;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.service.IPsyConsultantOrderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/consultant/order/aliPay")
@Slf4j
@Api(value = "ConsultantAlipayController" ,tags = {"支付宝Api"})
public class ConsultantAlipayController {

    @Resource
    private ConsultantTokenService consultantTokenService;

    @Resource
    private IPsyConsultantOrderService psyConsultantOrderService;
    
    /**
     * 支付咨询师端指定订单
     */
    @PostMapping("/pay")
    @RateLimiter(limitType = LimitType.IP)
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult payOrder(@RequestBody WechatPayDTO req, HttpServletRequest request) {
        Long consultId = consultantTokenService.getConsultId(request);
        //查询订单
        PsyConsultantOrder order = psyConsultantOrderService.selectPsyConsultantOrderByOrderNo(req.getOutTradeNo());
        
        ////若已发起过支付, 则直接返回相关参数
        if (ObjectUtils.isNotEmpty(order.getPayParam())){
            Map<String, String> parameters = parseQueryString(order.getPayParam());
            return AjaxResult.success(parameters);
        }
        
        //初次发起支付
        AjaxResult result = AlipayPayUtil.alipayAppPay(order.getOrderNo(), order.getPayAmount(), order.getServerName());
        if ((Integer) result.get("code") == 200) {
            String msg = (String) result.get("msg");
            // Map<String, String> parameters = parseQueryString(msg);
            order.setPayParam(msg);
            psyConsultantOrderService.updatePsyConsultantOrder(order);
            return AjaxResult.success(msg);
        } else {
            log.error("发起支付失败, result : " + result);
            throw new ServiceException("发起支付失败");
        }
    }

    //String 转 map
    private Map<String, String> parseQueryString(String queryString) {
        Map<String, String> queryPairs = new HashMap<>();
        String[] pairs = queryString.split("&");
        try {
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
                queryPairs.put(key, value);
            }
            return queryPairs;
        } catch (Exception e) {
            log.error("发起支付返回结果解析异常, queryString: " + queryString);
            throw new ServiceException("发起支付返回结果解析异常");
        }
    }
}
