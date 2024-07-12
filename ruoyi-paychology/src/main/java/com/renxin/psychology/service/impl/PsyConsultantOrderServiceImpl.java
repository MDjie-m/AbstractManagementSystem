package com.renxin.psychology.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.renxin.common.constant.IntegralRecordConstants;
import com.renxin.common.constant.PsyConstants;
import com.renxin.common.enums.GaugeStatus;
import com.renxin.common.enums.OrderPayStatus;
import com.renxin.common.enums.OrderStatus;
import com.renxin.common.event.publish.IntegralPublisher;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.IDhelper;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.service.ICourOrderService;
import com.renxin.course.service.ICourUserCourseSectionService;
import com.renxin.gauge.constant.GaugeConstant;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.gauge.domain.PsyOrderPay;
import com.renxin.gauge.service.IPsyOrderPayService;
import com.renxin.gauge.service.IPsyOrderService;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.PsyConsultOrderItem;
import com.renxin.psychology.domain.PsyConsultPay;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.user.domain.PsyUserIntegralRecord;
import com.renxin.user.service.IPsyUserIntegralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantOrderMapper;
import com.renxin.psychology.domain.PsyConsultantOrder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 团队督导(组织)订单Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-26
 */
@Service
public class PsyConsultantOrderServiceImpl implements IPsyConsultantOrderService 
{
    @Autowired
    private PsyConsultantOrderMapper psyConsultantOrderMapper;

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

    @Resource
    private IPsyConsultWorkService psyConsultWorkService;
    
    @Resource
    private IPsyConsultantPackageEquityService packageEquityService;
    
    /**
     * 查询团队督导(组织)订单
     * 
     * @param orderNo 团队督导(组织)订单主键
     * @return 团队督导(组织)订单
     */
    @Override
    public PsyConsultantOrder selectPsyConsultantOrderByOrderNo(String orderNo)
    {
        return psyConsultantOrderMapper.selectPsyConsultantOrderByOrderNo(orderNo);
    }

    /**
     * 查询团队督导(组织)订单列表
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 团队督导(组织)订单
     */
    @Override
    public List<PsyConsultantOrder> selectPsyConsultantOrderList(PsyConsultantOrder psyConsultantOrder)
    {
        return psyConsultantOrderMapper.selectPsyConsultantOrderList(psyConsultantOrder);
    }

    /**
     * 新增团队督导(组织)订单
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 结果
     */
    @Override
    public int insertPsyConsultantOrder(PsyConsultantOrder psyConsultantOrder)
    {
        psyConsultantOrder.setCreateTime(DateUtils.getNowDate());
        return psyConsultantOrderMapper.insertPsyConsultantOrder(psyConsultantOrder);
    }

    /**
     * 修改团队督导(组织)订单
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 结果
     */
    @Override
    public int updatePsyConsultantOrder(PsyConsultantOrder psyConsultantOrder)
    {
        psyConsultantOrder.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantOrderMapper.updatePsyConsultantOrder(psyConsultantOrder);
    }

    /**
     * 批量删除团队督导(组织)订单
     * 
     * @param orderNos 需要删除的团队督导(组织)订单主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantOrderByOrderNos(String[] orderNos)
    {
        return psyConsultantOrderMapper.deletePsyConsultantOrderByOrderNos(orderNos);
    }

    /**
     * 删除团队督导(组织)订单信息
     * 
     * @param orderNo 团队督导(组织)订单主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantOrderByOrderNo(String orderNo)
    {
        return psyConsultantOrderMapper.deletePsyConsultantOrderByOrderNo(orderNo);
    }

    /**
     * 生成团队督导(组织)订单
     *
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PsyConsultantOrder generatePsyConsultantOrder(PsyConsultantOrder psyConsultantOrder){
        // TODO 根据课程查询之前未支付的订单，并取消历史的未支付的订单
        /*CourOrder orderWithCourseId = new CourOrder();
        orderWithCourseId.setCourseId(courOrder.getCourseId());
        orderWithCourseId.setUserId(courOrder.getUserId());// 只取消本用户的课程
        List<CourOrder> historyCreatedOrderList = courOrderService.selectCourOrderList(orderWithCourseId)
                .stream()
                .filter(item -> item.getStatus() == CourConstant.COUR_ORDER_STATUE_CREATED)
                .collect(Collectors.toList());
        if (historyCreatedOrderList.size() > 0) {
            historyCreatedOrderList.forEach(item -> {
                item.setStatus(CourConstant.COUR_ORDER_STATUE_CANCELED);
                updateCourOrder(item);
            });
        }*/

        psyConsultantOrder.setCreateTime(DateUtils.getNowDate()); // 下单时间
        psyConsultantOrder.setUpdateTime(DateUtils.getNowDate()); // 下单时间
        int count = psyConsultantOrderMapper.insertPsyConsultantOrder(psyConsultantOrder);
        if (count == 1) {
            return selectPsyConsultantOrderByOrderNo(psyConsultantOrder.getOrderNo());
        }
        return null;
    }


    /**
     * 创建[待付款订单]及[支付对象]
     * @param consultantOrder
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createConsultantOrder(PsyConsultantOrder consultantOrder) {
        String nickName = consultService.getOne(Long.valueOf(consultantOrder.getPayConsultantId())).getNickName();
        consultantOrder.setCreateBy(consultantOrder.getPayConsultantId());
        consultantOrder.setUpdateBy(consultantOrder.getPayConsultantId());

        String payId = UUID.randomUUID().toString();// 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
        if (PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM.equals(consultantOrder.getServerType())) {
            //生成团督订单
            consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_CREATED);
            consultantOrder.setPayId(payId);
            consultantOrder.setPayConsultantName(nickName);
            PsyConsultantTeamSupervision team = teamSupervisionService.selectPsyConsultantTeamSupervisionById(Long.valueOf(consultantOrder.getServerId()));
            consultantOrder.setServerName(team.getTitle()+"-第"+team.getPeriodNo()+"期");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);

            //内部生成支付对象
            PsyOrderPay orderPay = new PsyOrderPay();
            orderPay.setConsultantOrderId(Integer.valueOf(newOrder.getId()));
            orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信 支付方式一定是微信
            orderPay.setPayStatus(CourConstant.PAY_STATUE_PENDING);
            orderPay.setAmount(consultantOrder.getPayAmount());
            orderPay.setPayId(payId); // 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
            orderPayService.insertPsyOrderPay(orderPay);

        } else if (PsyConstants.CONSULTANT_ORDER_PERSON_SUP_NUM.equals(consultantOrder.getServerType())) {
            // 个人督导服务
            //Long id = consultantOrder.getOrderId() != null ? consultantOrder.getOrderId() : IDhelper.getNextId();
            consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_CREATED);
            consultantOrder.setPayId(payId);
            consultantOrder.setPayConsultantName(nickName);
            consultantOrder.setServerName("个人督导服务");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);
            
            //内部生成支付对象
            PsyOrderPay orderPay = new PsyOrderPay();
            orderPay.setConsultantOrderId(Integer.valueOf(newOrder.getId()));
            orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信 支付方式一定是微信
            orderPay.setPayStatus(CourConstant.PAY_STATUE_PENDING);
            orderPay.setAmount(consultantOrder.getPayAmount());
            orderPay.setPayId(payId); // 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
            orderPayService.insertPsyOrderPay(orderPay);
            
        } else if (PsyConstants.CONSULTANT_ORDER_PERSON_EXP_NUM.equals(consultantOrder.getServerType())) {
            // 个人体验服务
            //Long id = consultantOrder.getOrderId() != null ? consultantOrder.getOrderId() : IDhelper.getNextId();
            consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_CREATED);
            consultantOrder.setPayId(payId);
            consultantOrder.setPayConsultantName(nickName);
            consultantOrder.setServerName("个人体验服务");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);

            //内部生成支付对象
            PsyOrderPay orderPay = new PsyOrderPay();
            orderPay.setConsultantOrderId(Integer.valueOf(newOrder.getId()));
            orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信 支付方式一定是微信
            orderPay.setPayStatus(CourConstant.PAY_STATUE_PENDING);
            orderPay.setAmount(consultantOrder.getPayAmount());
            orderPay.setPayId(payId); // 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
            orderPayService.insertPsyOrderPay(orderPay);

        } else if (PsyConstants.CONSULTANT_ORDER_COURSE_NUM.equals(consultantOrder.getServerType())) {
            // 购买课程
            consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_CREATED);
            consultantOrder.setPayId(payId);
            consultantOrder.setPayConsultantName(nickName);
            consultantOrder.setServerName("购买课程");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);

            //内部生成支付对象
            PsyOrderPay orderPay = new PsyOrderPay();
            orderPay.setConsultantOrderId(Integer.valueOf(newOrder.getId()));
            orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信 支付方式一定是微信
            orderPay.setPayStatus(CourConstant.PAY_STATUE_PENDING);
            orderPay.setAmount(consultantOrder.getPayAmount());
            orderPay.setPayId(payId); // 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
            orderPayService.insertPsyOrderPay(orderPay);

        } else if (PsyConstants.CONSULTANT_ORDER_PACKAGE_NUM.equals(consultantOrder.getServerType())) {
            // 套餐权益
            consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_CREATED);
            consultantOrder.setPayId(payId);
            consultantOrder.setPayConsultantName(nickName);
            consultantOrder.setServerName("套餐权益");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);

            //内部生成支付对象
            PsyOrderPay orderPay = new PsyOrderPay();
            orderPay.setConsultantOrderId(Integer.valueOf(newOrder.getId()));
            orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信 支付方式一定是微信
            orderPay.setPayStatus(CourConstant.PAY_STATUE_PENDING);
            orderPay.setAmount(consultantOrder.getPayAmount());
            orderPay.setPayId(payId); // 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
            orderPayService.insertPsyOrderPay(orderPay);

        }/*else if (GaugeConstant.MODULE_GAUGE.equals(consultantOrder.getModule())) {
            // TODO: 内部生成订单
            PsyOrder psyOrder = PsyOrder.builder()
                    .orderId(consultantOrder.getOutTradeNo())
                    .amount(consultantOrder.getAmount())
                    .orderStatus(OrderStatus.CREATE.getValue())
                    .gaugeStatus(GaugeStatus.UNFINISHED.getValue())
                    .gaugeId(consultantOrder.getGaugeId())
                    .userId(consultantOrder.getUserId())
                    .build();
            psyOrder.setCreateBy(nickName);

            PsyOrder newPsyOrder = psyOrderService.generatePsyOrder(psyOrder);

            // TODO: 定时将未支付的订单取消任务
//            orderCancelTask.setOrderId(newPsyOrder.getId());
//            orderCancelTask.setModule(consultantOrder.getModule());

            // TODO: 内部生成支付对象
            PsyOrderPay psyOrderPay = PsyOrderPay.builder()
                    .orderId(newPsyOrder.getId())
                    .amount(consultantOrder.getAmount())
                    .payStatus(OrderPayStatus.NEED_PAY.getValue())
                    .payId(UUID.randomUUID().toString())
                    .build();
            psyOrderPay.setCreateBy(psyUserService.selectPsyUserById(consultantOrder.getUserId()).getName());
            psyOrderPayService.insertPsyOrderPay(psyOrderPay);

//            Timer timer = new Timer();
//            timer.schedule(orderCancelTask, ORDER_CANCEL_TIME);
        }*/
    }

    /**
     * 支付成功回调
     * @param outTradeNo
     * @param payId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void paySuccessCallback(String outTradeNo, String payId) {
        PsyUserIntegralRecord record = new PsyUserIntegralRecord();
        record.setIntegral(0);

        if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_TEAM_SUP)) {
            //团队督导 , 更新订单状态
            PsyConsultantOrder consultantOrder = consultantOrderService.selectPsyConsultantOrderByOrderNo(outTradeNo);
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(consultantOrder.getStatus())) {
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_FINISHED);
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);

                //修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setConsultantOrderId(Integer.valueOf(consultantOrder.getId())); // 订单ID
                orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN);
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);

                //将付款咨询师加入团队内
                teamSupervisionService.handleOrder(consultantOrder);
            }
        } else if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_PERSON_SUP)) {
            //个人督导 , 更新订单状态
            PsyConsultantOrder consultantOrder = consultantOrderService.selectPsyConsultantOrderByOrderNo(outTradeNo);
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(consultantOrder.getStatus())) {
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_FINISHED);
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);

                //修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setConsultantOrderId(Integer.valueOf(consultantOrder.getId())); // 订单ID
                orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN);
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);

                //占用相应时段
                psyConsultWorkService.handleWork(consultantOrder.getWorkId(), null , consultantOrder.getTime(), 1);

            }
        } else if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_PERSON_EXP)) {
            //个人体验 , 更新订单状态
            PsyConsultantOrder consultantOrder = consultantOrderService.selectPsyConsultantOrderByOrderNo(outTradeNo);
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(consultantOrder.getStatus())) {
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_FINISHED);
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);

                //修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setConsultantOrderId(Integer.valueOf(consultantOrder.getId())); // 订单ID
                orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN);
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);

                //占用相应时段
                psyConsultWorkService.handleWork(consultantOrder.getWorkId(), null , consultantOrder.getTime(), 1);

            }
        } else if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_COURSE)) {
            // 课程 , 更新订单状态
            CourOrder courOrder = courOrderService.selectCourOrderByOrderId(outTradeNo);
            if (CourConstant.COUR_ORDER_STATUE_CREATED == courOrder.getStatus()) {
                courOrder.setStatus(CourConstant.COUR_ORDER_STATUE_FINISHED);
                courOrderService.updateCourOrder(courOrder);
                
                // 修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setOrderId(courOrder.getId()); // 订单ID
                orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 这里标记为课程
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);
                
                // todo 将用户-课程-章节关系初始化
                userCourseSectionService.initCourUserCourseSection(courOrder.getUserId(), courOrder.getCourseId());
            }
        }else if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_PACKAGE)) {
            //套餐权益 , 更新订单状态
            PsyConsultantOrder consultantOrder = consultantOrderService.selectPsyConsultantOrderByOrderNo(outTradeNo);
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(consultantOrder.getStatus())) {
                //修改订单状态为已完成
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_FINISHED);
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);

                //修改支付对象状态为已支付
                PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setConsultantOrderId(Integer.valueOf(consultantOrder.getId())); // 订单ID
                orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN);
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);

                //分配套餐权益
                packageEquityService.handleConsultantOrder(consultantOrder.getOrderNo());
            }
        } else if (outTradeNo.startsWith(PsyConstants.ORDER_GAUGE)) {
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
        }
        
    }
    
}
