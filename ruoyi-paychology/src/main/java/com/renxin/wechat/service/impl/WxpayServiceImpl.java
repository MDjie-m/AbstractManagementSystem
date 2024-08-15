package com.renxin.wechat.service.impl;

import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.domain.dto.WxPayDTO;
import com.renxin.common.enums.GaugeStatus;
import com.renxin.common.enums.OrderPayStatus;
import com.renxin.common.enums.OrderStatus;
import com.renxin.common.utils.OrderIdUtils;
import com.renxin.course.service.ICourOrderService;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.course.domain.CourOrder;
import com.renxin.gauge.domain.PsyOrderPay;
import com.renxin.gauge.service.IPsyOrderPayService;
import com.renxin.gauge.service.IPsyOrderService;
import com.renxin.wechat.service.IWxpayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @User hogan
 * @Time 2022/10/24 17:22
 * @e-mail hkcugwh@163.com
 **/
@Service
@Slf4j
public class WxpayServiceImpl implements IWxpayService {


    @Autowired
    private IPsyOrderService psyOrderService;

    @Autowired
    private IPsyOrderPayService psyOrderPayService;

    @Autowired
    private ICourOrderService courOrderService;

    @Override
    public String pay(WxPayDTO wxPayDTO, LoginDTO loginUser) {

        String orderId = OrderIdUtils.getOrderId();
        String serverName = wxPayDTO.getName();
        Long id = 0L;
        if (serverName==null){
            id = generateOrder(wxPayDTO, loginUser, orderId);
        }
        if(serverName.equals("cour")){

            id = generateCourseOrder(wxPayDTO, loginUser, orderId);
        }
        //生成订单
//        //生成订单支付信息
        generatePay(wxPayDTO, loginUser, id);

        return String.valueOf(id);

    }

    @Override
    public String payCallBack(WxPayDTO wxPayDTO, LoginDTO loginUser) {

        String orderId = OrderIdUtils.getOrderId();
        String serverName = wxPayDTO.getName();
        int id = 0;
        if (serverName==null){
            id = generateOrderUpdate(wxPayDTO, loginUser, orderId);
        }
        if(serverName.equals("cour")){

            id = generateCourseOrderUpdate(wxPayDTO, loginUser, orderId);
        }
        //生成订单
//        //生成订单支付信息
        generatePayUpdate(wxPayDTO, loginUser, id);

        return String.valueOf(id);

    }

    private void generatePay(WxPayDTO wxPayDTO, LoginDTO loginUser, Long id) {
        PsyOrderPay psyOrderPay = PsyOrderPay.builder()
                .orderId(id)
                .amount(wxPayDTO.getAmount())
                .payStatus(OrderPayStatus.NEED_PAY.getValue())
                .payId(UUID.randomUUID().toString())
                .build();
        psyOrderPay.setCreateBy(loginUser.getUsername());
        psyOrderPayService.insertPsyOrderPay(psyOrderPay);
    }

    /**
     *
     * 支付回调，修改支付状态
     * @param loginUser
     * @param id
     */
    private void  generatePayUpdate(WxPayDTO wxPayDTO, LoginDTO loginUser, int id){
        PsyOrderPay psyOrderPay = PsyOrderPay.builder()
                .orderId(Long.valueOf(wxPayDTO.getOrderId()))
                .payStatus(wxPayDTO.getStatus())
                .build();
        psyOrderPay.setCreateBy(loginUser.getUsername());
        psyOrderPayService.updatePsyOrderPay(psyOrderPay);
    }


    private Long generateOrder(WxPayDTO wxPayDTO, LoginDTO loginUser, String orderId) {
        PsyOrder psyOrder = PsyOrder.builder()
                .orderId(orderId)
                .amount(wxPayDTO.getAmount())
                .orderStatus(OrderStatus.CREATE.getValue())
                .gaugeStatus(GaugeStatus.UNFINISHED.getValue())
                .gaugeId(wxPayDTO.getGaugeId())
                .build();
        psyOrder.setCreateBy(loginUser.getUsername());

        Long id = psyOrderService.insertPsyOrder(psyOrder);
        return id;
    }

    /**
     *
     * 修改测评订单信息
     * @param loginUser
     * @param orderId
     * @return
     */
    private int generateOrderUpdate(WxPayDTO wxPayDTO, LoginDTO loginUser, String orderId){
        PsyOrder psyOrder = PsyOrder.builder()
                .orderId(String.valueOf(wxPayDTO.getOrderId()))
                .orderStatus(wxPayDTO.getStatus())
                .build();
        int id = psyOrderService.updatePsyOrder(psyOrder);
        return id;

    }

    /***
     *
     *  增加课程订单
     * @param loginUser
     * @param orderId
     * @return
     */
    private Long generateCourseOrder(WxPayDTO wxPayDTO, LoginDTO loginUser, String orderId){
        CourOrder courOrder = CourOrder.builder()
                .orderId(orderId)
                .amount(wxPayDTO.getAmount())
                .status(OrderStatus.CREATE.getValue())
                .courseId(wxPayDTO.getGaugeId())
                .build();
        courOrder.setUserId(loginUser.getUserId().longValue());
        Long id = courOrderService.insertCourOrder(courOrder);
        return id;
    }

    /**
     *
     * 修改课程订单状态
     * @param loginUser
     * @param orderId
     * @return
     */
    private int generateCourseOrderUpdate(WxPayDTO wxPayDTO, LoginDTO loginUser, String orderId){
        CourOrder courOrder = CourOrder.builder()
                .orderId(orderId)
                .status(OrderStatus.CREATE.getValue())
                .build();
        int id = courOrderService.updateCourOrder(courOrder);
        return id;
    }


}
