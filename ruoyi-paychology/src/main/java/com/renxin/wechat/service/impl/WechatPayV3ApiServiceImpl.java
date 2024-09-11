package com.renxin.wechat.service.impl;

import cn.hutool.core.util.StrUtil;
import com.renxin.common.constant.IntegralRecordConstants;
import com.renxin.common.constant.PsyConstants;
import com.renxin.common.enums.GaugeStatus;
import com.renxin.common.enums.OrderPayStatus;
import com.renxin.common.enums.OrderStatus;
import com.renxin.common.event.publish.IntegralPublisher;
import com.renxin.common.utils.IDhelper;
import com.renxin.common.utils.OrderIdUtils;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.service.ICourOrderService;
import com.renxin.course.service.ICourUserCourseSectionService;
import com.renxin.gauge.constant.GaugeConstant;
import com.renxin.gauge.domain.PsyGauge;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.gauge.domain.PsyOrderPay;
import com.renxin.gauge.service.IPsyGaugeService;
import com.renxin.gauge.service.IPsyOrderPayService;
import com.renxin.gauge.service.IPsyOrderService;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.PsyConsultOrder;
import com.renxin.psychology.domain.PsyConsultPay;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.dto.OrderDTO;
import com.renxin.psychology.request.ReceiveFreeCouponReq;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.user.domain.PsyUserIntegralRecord;
import com.renxin.user.service.IPsyUserIntegralRecordService;
import com.renxin.wechat.service.WechatPayV3ApiService;
import com.renxin.wechat.vo.WechatPayVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

//import com.renxin.course.task.OrderCancelTask;

@Service
@Slf4j
public class WechatPayV3ApiServiceImpl implements WechatPayV3ApiService {

    private static final Integer ORDER_CANCEL_TIME = 30 * 60 * 1000;

    @Resource
    private IPsyUserService psyUserService;

    @Resource
    private IPsyConsultService consultService;
    
    @Resource
    private IPsyConsultantOrderService consultantOrderService;

    @Resource
    private IPsyConsultantTeamSupervisionService teamSupervisionService;

    @Resource
    private IntegralPublisher integralPublisher;

    @Resource
    private ICourOrderService courOrderService;

    @Resource
    private IPsyOrderPayService orderPayService;

    @Resource
    private IPsyOrderService psyOrderService;

    @Resource
    private IPsyConsultPayService psyConsultPayService;

    @Resource
    private IPsyUserIntegralRecordService psyUserIntegralRecordService;

    @Resource
    private IPsyConsultOrderService psyConsultOrderService;

    @Resource
    private IPsyOrderPayService psyOrderPayService;

    @Resource
    private ICourUserCourseSectionService userCourseSectionService;

    @Autowired
    private IPsyCouponService psyCouponService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void wechatPay(WechatPayVO wechatPay) {
//        OrderCancelTask orderCancelTask = new OrderCancelTask();
        String nickName = psyUserService.selectPsyUserById(wechatPay.getUserId()).getName();

        if (CourConstant.MODULE_COURSE.equals(wechatPay.getModule())) {

            // TODO: 内部生成订单
            CourOrder courOrder = new CourOrder();
            courOrder.setOrderId(wechatPay.getOutTradeNo());
            courOrder.setStatus(CourConstant.COUR_ORDER_STATUE_CREATED);
            courOrder.setAmount(wechatPay.getAmount());
            courOrder.setOriginalPrice(wechatPay.getOriginalPrice());
            courOrder.setCouponNo(wechatPay.getCouponNo());
            courOrder.setUserId(wechatPay.getUserId());
            courOrder.setCourseId(wechatPay.getCourseId());
            CourOrder newCourOrder = courOrderService.generateCourOrder(courOrder);
            psyCouponService.useCoupon(wechatPay.getCouponNo());
            // TODO: 定时将未支付的订单取消任务
//            orderCancelTask.setOrderId(newCourOrder.getId());
//            orderCancelTask.setModule(wechatPay.getModule());

            // TODO: 内部生成支付对象
            PsyOrderPay orderPay = new PsyOrderPay();
            orderPay.setOrderId(newCourOrder.getId());
            orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信 支付方式一定是微信,这里标记为课程
            orderPay.setPayStatus(CourConstant.PAY_STATUE_PENDING);
            orderPay.setAmount(wechatPay.getAmount());
            orderPay.setPayId(UUID.randomUUID().toString()); // 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
            orderPayService.insertPsyOrderPay(orderPay);

//            Timer timer = new Timer();
//            timer.schedule(orderCancelTask, ORDER_CANCEL_TIME);
        } else if (GaugeConstant.MODULE_GAUGE.equals(wechatPay.getModule())) {

            // TODO: 内部生成订单
            PsyOrder psyOrder = PsyOrder.builder()
                    .orderId(wechatPay.getOutTradeNo())
                    .amount(wechatPay.getAmount())
                    .orderStatus(OrderStatus.CREATE.getValue())
                    .gaugeStatus(GaugeStatus.UNFINISHED.getValue())
                    .gaugeId(wechatPay.getGaugeId())
                    .userId(wechatPay.getUserId())
                    .originalPrice(wechatPay.getOriginalPrice())
                    .couponNo(wechatPay.getCouponNo())
                    .isUseGaugeAnalyse(wechatPay.getIsUseGaugeAnalyse())
                    .build();
            psyOrder.setCreateBy(nickName);
            PsyOrder newPsyOrder = psyOrderService.generatePsyOrder(psyOrder);
            psyCouponService.useCoupon(wechatPay.getCouponNo());

            // TODO: 定时将未支付的订单取消任务
//            orderCancelTask.setOrderId(newPsyOrder.getId());
//            orderCancelTask.setModule(wechatPay.getModule());

            // TODO: 内部生成支付对象
            PsyOrderPay psyOrderPay = PsyOrderPay.builder()
                    .orderId(newPsyOrder.getId())
                    .amount(wechatPay.getAmount())
                    .payStatus(OrderPayStatus.NEED_PAY.getValue())
                    .payId(UUID.randomUUID().toString())
                    .build();
            psyOrderPay.setCreateBy(psyUserService.selectPsyUserById(wechatPay.getUserId()).getName());
            psyOrderPayService.insertPsyOrderPay(psyOrderPay);

//            Timer timer = new Timer();
//            timer.schedule(orderCancelTask, ORDER_CANCEL_TIME);
        } else if (ConsultConstant.MODULE_CONSULT.equals(wechatPay.getModule())) {
            // 心理咨询服务
            Long id = wechatPay.getOrderId() != null ? wechatPay.getOrderId() : IDhelper.getNextId();
            PsyConsultOrderVO psyConsultOrderVO = new PsyConsultOrderVO();
            psyConsultOrderVO.setId(id);
            psyConsultOrderVO.setConsultId(wechatPay.getConsultId());
            psyConsultOrderVO.setOrderNo(wechatPay.getOutTradeNo());
            // 创建时才需要更新总价
            // psyConsultOrderVO.setAmount(wechatPay.getAmount());
            psyConsultOrderVO.setPay(wechatPay.getAmount());
            psyConsultOrderVO.setOriginalPrice(wechatPay.getOriginalPrice());
            psyConsultOrderVO.setCouponNo(wechatPay.getCouponNo());
            psyConsultOrderVO.setServeId(wechatPay.getServeId());
            psyConsultOrderVO.setUserId(wechatPay.getUserId());
            psyConsultOrderVO.setNickName(nickName);
            psyConsultOrderVO.setWorkId(wechatPay.getWorkId());
            psyConsultOrderVO.setTime(wechatPay.getTime());

            // 更新原订单
            if (wechatPay.getOrderId() != null) {
                psyConsultOrderService.updatePayOrder(psyConsultOrderVO);
            } else {
                psyConsultOrderVO.setAmount(wechatPay.getAmount());
                psyConsultOrderService.add(psyConsultOrderVO);
            }
            psyCouponService.useCoupon(wechatPay.getCouponNo());

            // TODO: 定时将未支付的订单取消任务
//            orderCancelTask.setId(id);
//            orderCancelTask.setModule(wechatPay.getModule());

            // TODO: 内部生成支付对象
            PsyConsultPay pay = new PsyConsultPay();
            pay.setOrderId(id);
            pay.setAmount(wechatPay.getAmount());
            pay.setOutTradeNo(wechatPay.getOutTradeNo());
            pay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信
            pay.setDelFlag("0");
            pay.setStatus(ConsultConstant.PAY_STATUE_PENDING);
            psyConsultPayService.add(pay);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void wechatConsultantPay(WechatPayVO wechatPay) {
//        OrderCancelTask orderCancelTask = new OrderCancelTask();
        String nickName = consultService.getOne(wechatPay.getConsultId()).getNickName();

        if (PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM.equals(wechatPay.getSupServerType())) {
            //内部生成订单
            CourOrder courOrder = new CourOrder();
            courOrder.setOrderId(wechatPay.getOutTradeNo());
            courOrder.setStatus(CourConstant.COUR_ORDER_STATUE_CREATED);
            courOrder.setAmount(wechatPay.getAmount());
            courOrder.setUserId(wechatPay.getUserId());
            courOrder.setCourseId(wechatPay.getCourseId());
            CourOrder newCourOrder = courOrderService.generateCourOrder(courOrder);

            PsyConsultantOrder consultantOrder = new PsyConsultantOrder();
                consultantOrder.setOrderNo(wechatPay.getOutTradeNo());
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_CREATED);
                consultantOrder.setPayAmount(wechatPay.getAmount());
                consultantOrder.setPayConsultantId(wechatPay.getConsultId());
                consultantOrder.setPayConsultantName(nickName);
                consultantOrder.setServerId(wechatPay.getSupServerId());
                consultantOrder.setServerType(wechatPay.getSupServerType());
                PsyConsultantTeamSupervision team = teamSupervisionService.selectPsyConsultantTeamSupervisionById(Long.valueOf(wechatPay.getSupServerId()));
                consultantOrder.setServerName(team.getTitle()+"-第"+team.getPeriodNo()+"期");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);

            // TODO: 内部生成支付对象
            PsyOrderPay orderPay = new PsyOrderPay();
            orderPay.setOrderId(Long.valueOf(newOrder.getId()));
            orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信 支付方式一定是微信
            orderPay.setPayStatus(CourConstant.PAY_STATUE_PENDING);
            orderPay.setAmount(wechatPay.getAmount());
            orderPay.setPayId(UUID.randomUUID().toString()); // 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
            orderPayService.insertPsyOrderPay(orderPay);

//            Timer timer = new Timer();
//            timer.schedule(orderCancelTask, ORDER_CANCEL_TIME);
        } else if (GaugeConstant.MODULE_GAUGE.equals(wechatPay.getModule())) {

            // TODO: 内部生成订单
            PsyOrder psyOrder = PsyOrder.builder()
                    .orderId(wechatPay.getOutTradeNo())
                    .amount(wechatPay.getAmount())
                    .orderStatus(OrderStatus.CREATE.getValue())
                    .gaugeStatus(GaugeStatus.UNFINISHED.getValue())
                    .gaugeId(wechatPay.getGaugeId())
                    .userId(wechatPay.getUserId())
                    .build();
            psyOrder.setCreateBy(nickName);

            PsyOrder newPsyOrder = psyOrderService.generatePsyOrder(psyOrder);

            // TODO: 定时将未支付的订单取消任务
//            orderCancelTask.setOrderId(newPsyOrder.getId());
//            orderCancelTask.setModule(wechatPay.getModule());

            // TODO: 内部生成支付对象
            PsyOrderPay psyOrderPay = PsyOrderPay.builder()
                    .orderId(newPsyOrder.getId())
                    .amount(wechatPay.getAmount())
                    .payStatus(OrderPayStatus.NEED_PAY.getValue())
                    .payId(UUID.randomUUID().toString())
                    .build();
            psyOrderPay.setCreateBy(psyUserService.selectPsyUserById(wechatPay.getUserId()).getName());
            psyOrderPayService.insertPsyOrderPay(psyOrderPay);

//            Timer timer = new Timer();
//            timer.schedule(orderCancelTask, ORDER_CANCEL_TIME);
        } else if (ConsultConstant.MODULE_CONSULT.equals(wechatPay.getModule())) {
            // 心理咨询服务
            Long id = wechatPay.getOrderId() != null ? wechatPay.getOrderId() : IDhelper.getNextId();
            PsyConsultOrderVO psyConsultOrderVO = new PsyConsultOrderVO();
            psyConsultOrderVO.setId(id);
            psyConsultOrderVO.setConsultId(wechatPay.getConsultId());
            psyConsultOrderVO.setOrderNo(wechatPay.getOutTradeNo());
            // 创建时才需要更新总价
            // psyConsultOrderVO.setAmount(wechatPay.getAmount());
            psyConsultOrderVO.setPay(wechatPay.getAmount());
            psyConsultOrderVO.setServeId(wechatPay.getServeId());
            psyConsultOrderVO.setUserId(wechatPay.getUserId());
            psyConsultOrderVO.setNickName(nickName);
            psyConsultOrderVO.setWorkId(wechatPay.getWorkId());
            psyConsultOrderVO.setTime(wechatPay.getTime());

            // 更新原订单
            if (wechatPay.getOrderId() != null) {
                psyConsultOrderService.updatePayOrder(psyConsultOrderVO);
            } else {
                psyConsultOrderVO.setAmount(wechatPay.getAmount());
                psyConsultOrderService.add(psyConsultOrderVO);
            }

            // TODO: 定时将未支付的订单取消任务
//            orderCancelTask.setId(id);
//            orderCancelTask.setModule(wechatPay.getModule());

            // TODO: 内部生成支付对象
            PsyConsultPay pay = new PsyConsultPay();
            pay.setOrderId(id);
            pay.setAmount(wechatPay.getAmount());
            pay.setOutTradeNo(wechatPay.getOutTradeNo());
            pay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信
            pay.setDelFlag("0");
            pay.setStatus(ConsultConstant.PAY_STATUE_PENDING);
            psyConsultPayService.add(pay);
        }
    }

    //支付完成回调
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void wechatPayNotify(String outTradeNo, String payId) {
        PsyUserIntegralRecord record = new PsyUserIntegralRecord();
        record.setIntegral(0);

        if (outTradeNo.startsWith(PsyConstants.ORDER_COURSE)) {
            // TODO: 修改订单状态为已完成
            CourOrder courOrder = courOrderService.selectCourOrderByOrderId(outTradeNo);
            // 判断状态，防止重复更新
            if (CourConstant.COUR_ORDER_STATUE_CREATED == courOrder.getStatus()) {
                courOrder.setStatus(CourConstant.COUR_ORDER_STATUE_FINISHED);
                courOrderService.updateCourOrder(courOrder);

                // TODO: 将用户-课程-章节关系初始化

                userCourseSectionService.initCourUserCourseSection(courOrder.getUserId(), courOrder.getCourseId(),PsyConstants.USER_CONSULTED);

                // TODO: 修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setOrderId(courOrder.getId()); // 订单ID
                orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 这里标记为课程
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);

                if (courOrder.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    record.setLinkId(String.valueOf(courOrder.getId()));
                    record.setLinkType(IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_COURSE);
                    record.setUid(courOrder.getUserId());
                    record.setIntegral(psyUserIntegralRecordService.getIntegral(courOrder.getAmount(), IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_COURSE));
                }
            }
        } else if (outTradeNo.startsWith(PsyConstants.ORDER_GAUGE)) {
            // TODO: 修改订单状态为已完成
            PsyOrder psyOrder = psyOrderService.selectPsyOrderByOrderId(outTradeNo);
            if (OrderStatus.CREATE.getValue() == psyOrder.getOrderStatus()) {
                psyOrder.setOrderStatus(OrderStatus.FINISHED.getValue());
                psyOrderService.updatePsyOrder(psyOrder);
                
                //若购买了解析服务, 则领取优惠券
                if ("Y".equals(psyOrder.getIsUseGaugeAnalyse())){
                    ReceiveFreeCouponReq couponReq = new ReceiveFreeCouponReq();
                    couponReq.setUserId(psyOrder.getUserId());
                    couponReq.setCouponTemplateIdStr("10000");//pocket测评解析券
                    couponReq.setIsCanGetChargeCoupon("Y");//可领取收费权
                    psyCouponService.receiveFreeCoupon(couponReq);
                }

                // TODO: 修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setOrderId(psyOrder.getId()); // 订单ID
                orderPay.setPayType(0); // 这里标记为测评
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);

                if (psyOrder.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    record.setLinkId(String.valueOf(psyOrder.getId()));
                    record.setLinkType(IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_COURSE);
                    record.setUid(psyOrder.getUserId());
                    record.setIntegral(psyUserIntegralRecordService.getIntegral(psyOrder.getAmount(), IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_COURSE));
                }
            }
        } else if (outTradeNo.startsWith(PsyConstants.ORDER_CONSULT)) {
            // TODO: 修改订单状态为已完成
            PsyConsultPay pay = psyConsultPayService.getOneByOrder(outTradeNo);
            PsyConsultOrderVO psyOrder = psyConsultOrderService.getOne(pay.getOrderId());

            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(psyOrder.getStatus())) {
                pay.setPayId(payId);
                pay.setStatus(ConsultConstant.PAY_STATUE_PAID);
                psyOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_PENDING);
                psyOrder.setPayStatus(ConsultConstant.PAY_STATUE_PAID);
                psyOrder.setPayTime(new Date());

                psyConsultPayService.update(pay);
                psyConsultOrderService.wechatPayNotify(psyOrder);

                if (psyOrder.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    record.setLinkId(String.valueOf(psyOrder.getId()));
                    record.setLinkType(IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_ORDER);
                    record.setUid(psyOrder.getUserId());
                    record.setIntegral(psyUserIntegralRecordService.getIntegral(psyOrder.getAmount(), IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_ORDER));
                }
            }
        }
        // 有积分时候,才赠送积分
        if (record.getIntegral() > 0 && StringUtils.isNotBlank(record.getLinkId())) {
            record.setDelFlag(0);
            record.setFrozenTime(0);
            record.setMark(StrUtil.format("用户付款成功,订单增加{}积分", record.getIntegral()));
            record.setType(IntegralRecordConstants.INTEGRAL_RECORD_TYPE_ADD);
            record.setTitle(IntegralRecordConstants.BROKERAGE_RECORD_TITLE_ORDER);
            record.setStatus(IntegralRecordConstants.INTEGRAL_RECORD_STATUS_COMPLETE);
            integralPublisher.publish(record);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void wechatConsultantPayNotify(String outTradeNo, String payId) {
        PsyUserIntegralRecord record = new PsyUserIntegralRecord();
        record.setIntegral(0);

        if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_TEAM_SUP)) {
            // TODO: 修改订单状态为已完成
            PsyConsultantOrder consultantOrder = consultantOrderService.selectPsyConsultantOrderByOrderNo(outTradeNo);
            // 判断状态，防止重复更新
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED == consultantOrder.getStatus()) {
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_FINISHED);
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);

                // TODO: 修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setOrderId(Long.valueOf(consultantOrder.getId())); // 订单ID
                orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); 
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);
                
                //将付款咨询师加入团队内
                teamSupervisionService.handleOrder(consultantOrder);
                
            }
        } else if (outTradeNo.startsWith(PsyConstants.ORDER_COURSE)) {
            // TODO: 修改订单状态为已完成
            CourOrder courOrder = courOrderService.selectCourOrderByOrderId(outTradeNo);
            // 判断状态，防止重复更新
            if (CourConstant.COUR_ORDER_STATUE_CREATED == courOrder.getStatus()) {
                courOrder.setStatus(CourConstant.COUR_ORDER_STATUE_FINISHED);
                courOrderService.updateCourOrder(courOrder);

                // TODO: 将用户-课程-章节关系初始化
                userCourseSectionService.initCourUserCourseSection(courOrder.getUserId(), courOrder.getCourseId(),PsyConstants.USER_CONSULTED);

                // TODO: 修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setOrderId(courOrder.getId()); // 订单ID
                orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 这里标记为课程
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);
                
            }
        }
        else if (outTradeNo.startsWith(PsyConstants.ORDER_GAUGE)) {
            // TODO: 修改订单状态为已完成
            PsyOrder psyOrder = psyOrderService.selectPsyOrderByOrderId(outTradeNo);
            if (OrderStatus.CREATE.getValue() == psyOrder.getOrderStatus()) {
                psyOrder.setOrderStatus(OrderStatus.FINISHED.getValue());
                psyOrderService.updatePsyOrder(psyOrder);

                // TODO: 修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setOrderId(psyOrder.getId()); // 订单ID
                orderPay.setPayType(0); // 这里标记为测评
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);

                if (psyOrder.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    record.setLinkId(String.valueOf(psyOrder.getId()));
                    record.setLinkType(IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_COURSE);
                    record.setUid(psyOrder.getUserId());
                    record.setIntegral(psyUserIntegralRecordService.getIntegral(psyOrder.getAmount(), IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_COURSE));
                }
            }
        } else if (outTradeNo.startsWith(PsyConstants.ORDER_CONSULT)) {
            // TODO: 修改订单状态为已完成
            PsyConsultPay pay = psyConsultPayService.getOneByOrder(outTradeNo);
            PsyConsultOrderVO psyOrder = psyConsultOrderService.getOne(pay.getOrderId());

            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(psyOrder.getStatus())) {
                pay.setPayId(payId);
                pay.setStatus(ConsultConstant.PAY_STATUE_PAID);
                psyOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_PENDING);
                psyOrder.setPayStatus(ConsultConstant.PAY_STATUE_PAID);
                psyOrder.setPayTime(new Date());

                psyConsultPayService.update(pay);
                psyConsultOrderService.wechatPayNotify(psyOrder);

                if (psyOrder.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    record.setLinkId(String.valueOf(psyOrder.getId()));
                    record.setLinkType(IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_ORDER);
                    record.setUid(psyOrder.getUserId());
                    record.setIntegral(psyUserIntegralRecordService.getIntegral(psyOrder.getAmount(), IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_ORDER));
                }
            }
        }
    }

    @Resource
    private IPsyOrderService gaugeOrderService;
    
    //维护订单的支付参数
    @Override
    public void updatePayParam(WechatPayVO req){
        String orderNo = req.getOutTradeNo();
        String payParam = req.getPayParam();
        switch (req.getModule()) {
            case CourConstant.MODULE_COURSE:
                CourOrder courOrder = courOrderService.selectCourOrderByOrderId(orderNo);
                    courOrder.setPayParam(payParam);
                courOrderService.updateCourOrder(courOrder);
                break;
            case GaugeConstant.MODULE_GAUGE:
                PsyOrder gaugeOrder = gaugeOrderService.selectPsyOrderByOrderId(orderNo);
                    gaugeOrder.setPayParam(payParam);
                gaugeOrderService.updatePsyOrder(gaugeOrder);
                break;
            case ConsultConstant.MODULE_CONSULT:
                OrderDTO consultOrder = psyConsultOrderService.getOrderDetailByNo(orderNo);
                    consultOrder.setPayParam(payParam);
                PsyConsultOrderVO orderVO = new PsyConsultOrderVO();
                BeanUtils.copyProperties(consultOrder, orderVO);
                psyConsultOrderService.update(orderVO);
                break;
        }
    }
    
}
