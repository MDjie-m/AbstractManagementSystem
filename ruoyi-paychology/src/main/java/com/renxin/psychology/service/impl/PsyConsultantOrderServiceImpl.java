package com.renxin.psychology.service.impl;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.renxin.common.constant.IntegralRecordConstants;
import com.renxin.common.constant.PsyConstants;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.GaugeStatus;
import com.renxin.common.enums.OrderPayStatus;
import com.renxin.common.enums.OrderStatus;
import com.renxin.common.event.publish.IntegralPublisher;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.IDhelper;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.domain.CourSection;
import com.renxin.course.domain.CourUserCourseSection;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.service.ICourOrderService;
import com.renxin.course.service.ICourSectionService;
import com.renxin.course.service.ICourUserCourseSectionService;
import com.renxin.course.vo.CourseVO;
import com.renxin.course.vo.SectionVO;
import com.renxin.gauge.constant.GaugeConstant;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.gauge.domain.PsyOrderPay;
import com.renxin.gauge.service.IPsyOrderPayService;
import com.renxin.gauge.service.IPsyOrderService;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.*;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.psychology.vo.PsyConsultServeConfigVO;
import com.renxin.user.domain.PsyUserIntegralRecord;
import com.renxin.user.service.IPsyUserIntegralRecordService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantOrderMapper;
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
    
    @Resource
    private IPsyConsultantPackageEquityService consultantPackageEquityService;
    
    @Resource
    private IPsyCouponService couponService;
    
    @Resource
    private IPsyConsultServeConfigService serveConfigService;
    
    @Resource
    private IPsyConsultantScheduleService consultantScheduleService;
    
    @Resource
    private IPsyConsultServeService consultServeService;
    
    @Resource
    private ICourCourseService courCourseService;

    @Autowired
    private ICourUserCourseSectionService courUserCourseSectionService;
    
    @Autowired
    private ICourSectionService courSectionService;
    
    /**
     * 查询订单
     */
    @Override
    public PsyConsultantOrder selectPsyConsultantOrderByOrderNo(String orderNo)
    {
        PsyConsultantOrder order  = psyConsultantOrderMapper.selectPsyConsultantOrderByOrderNo(orderNo);
        String serverType = order.getServerType();
        //若类型为[个督/体验] , 则需计算剩余可用次数
        if (PsyConstants.CONSULTANT_ORDER_PERSON_SUP_NUM.equals(serverType) || PsyConstants.CONSULTANT_ORDER_PERSON_EXP_NUM.equals(serverType)){
            //指定服务信息
            PsyConsultServeConfig serverDetail = consultServeService.getServerDetailByRelationId(order.getServerId());
            order.setChargeConsultantId(serverDetail.getConsultantId());
            //总服务次数
            order.setTotalNum(serverDetail.getNum());

            //已使用的次数(排班信息)
            PsyConsultantSchedule scheduleReq = new PsyConsultantSchedule();
            scheduleReq.setOrderId(orderNo);
            List<PsyConsultantSchedule> scheduleList = consultantScheduleService.selectPsyConsultantScheduleList(scheduleReq);
            order.setUsedNum(ObjectUtils.isNotEmpty(scheduleList) ? scheduleList.size() : 0);

            //剩余可用次数
            order.setSurplusNum(order.getTotalNum() - order.getUsedNum());
            
            order.setServerDetail(serverDetail);
        }
        //团队督导
        else if (PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM.equals(serverType)){
            PsyConsultantTeamSupervision team = teamSupervisionService.selectPsyConsultantTeamSupervisionById(Long.valueOf(order.getServerId()));
            order.setChargeConsultantId(team.getConsultantId());
            order.setChargeConsultantName(team.getConsultUserName());
            order.setTotalNum(team.getCycleNumber());
            
            order.setTeamDetail(team);
        }
        //课程
        else if (PsyConstants.CONSULTANT_ORDER_COURSE_NUM.equals(serverType)){
            CourCourse course = courCourseService.selectCourCourseById(Long.valueOf(order.getServerId()));
            Long courseId = course.getId();
            if (course == null) {
               throw new ServiceException("查询课程详情失败");
            }

            // 查询课程的学习人数
            CourUserCourseSection courUserCourseSection = new CourUserCourseSection();
            courUserCourseSection.setCourseId(courseId);
            List<CourUserCourseSection> courUserCourseSectionList = courUserCourseSectionService.selectCourUserCourseSectionList(courUserCourseSection);

            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(course, courseVO);
            courseVO.setStudyNum(courUserCourseSectionList.size());

            // 增加章节列表
            CourSection courSection = CourSection.builder()
                    .courseId(courseId)
                    .build();
            List<CourSection> sectionList = courSectionService.selectCourSectionList(courSection);
            // 查询章节的学习情况
            List<SectionVO> sectionVOList = new ArrayList<>();


            for (CourSection section: sectionList) {
                SectionVO sectionVO = new SectionVO();
                CourUserCourseSection userCourseSection = new CourUserCourseSection();
                userCourseSection.setUserId(Long.valueOf(order.getPayConsultantId()));
                userCourseSection.setUserType(2);//咨询师
                userCourseSection.setCourseId(courseId);
                BeanUtils.copyProperties(section, sectionVO);
                userCourseSection.setSectionId(section.getId());
                sectionVO.setEndTime(courUserCourseSectionService.findEndTime(userCourseSection));

                // 未购买课程的普通章节不返回内容链接
                if (courCourseService.getPaidCourseCount(Long.valueOf(order.getPayConsultantId()), courseId) == 0 && sectionVO.getType() == CourConstant.SECTION_NORMAL) {
                    sectionVO.setContentUrl(null);
                }
                sectionVOList.add(sectionVO);
            }
            course.setSectionList(sectionVOList);
            order.setCourseDetail(course);
        }
        
        return order;
    }

    /**
     * 查询订单列表
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 团队督导(组织)订单
     */
    @Override
    public List<PsyConsultantOrder> selectPsyConsultantOrderList(PsyConsultantOrder req)
    {
        List<PsyConsultantOrder> orderList = psyConsultantOrderMapper.selectPsyConsultantOrderList(req);
        
        //咨询师端的请求   
        if (req.getIsConsultantReq()){
            for (PsyConsultantOrder order : orderList) {
                String serverType = order.getServerType();

                //团队督导
                if (PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM.equals(serverType)){
                    PsyConsultantTeamSupervision team = teamSupervisionService.selectPsyConsultantTeamSupervisionById(Long.valueOf(order.getServerId()));
                    order.setChargeConsultantId(team.getConsultantId());
                    order.setChargeConsultantName(team.getConsultUserName());
                    order.setTotalNum(team.getCycleNumber());
                    order.setNextBeginTime(team.getNextBeginTime());

                    team.setMemberList(null);
                    order.setTeamDetail(team);
                }
                //个督/体验
                else if (PsyConstants.CONSULTANT_ORDER_PERSON_SUP_NUM.equals(serverType) || PsyConstants.CONSULTANT_ORDER_PERSON_EXP_NUM.equals(serverType)){
                    //指定服务信息
                    PsyConsultServeConfig serverDetail = consultServeService.getServerDetailByRelationId(order.getServerId());
                    order.setChargeConsultantId(serverDetail.getConsultantId());
                    //总服务次数
                    order.setTotalNum(serverDetail.getNum());
                    //已使用的次数(排班信息)
                    PsyConsultantSchedule scheduleReq = new PsyConsultantSchedule();
                    scheduleReq.setOrderId(order.getOrderNo());
                    List<PsyConsultantSchedule> scheduleList = consultantScheduleService.selectPsyConsultantScheduleList(scheduleReq);
                    order.setUsedNum(ObjectUtils.isNotEmpty(scheduleList) ? scheduleList.size() : 0);
                    //剩余可用次数
                    order.setSurplusNum(order.getTotalNum() - order.getUsedNum());

                    serverDetail.setTotalNum(order.getTotalNum());
                    serverDetail.setUsedNum(order.getUsedNum());
                    serverDetail.setSurplusNum(order.getSurplusNum());

                    //下次个督时间
                    PsyConsultantSchedule querySche = new PsyConsultantSchedule();
                        querySche.setOrderId(order.getOrderNo());
                        querySche.setRealTimeStart(LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    List<PsyConsultantSchedule> scList = consultantScheduleService.selectPsyConsultantScheduleList(querySche);
                    if (ObjectUtils.isNotEmpty(scList)){
                        order.setNextBeginTime(scList.get(0).getRealTime());
                    }
                    
                    order.setServerDetail(serverDetail);
                }
                //课程
                else if (PsyConstants.CONSULTANT_ORDER_COURSE_NUM.equals(serverType)){
                    CourCourse course = courCourseService.selectCourCourseById(Long.valueOf(order.getServerId()));
                    Long courseId = course.getId();
                    if (course == null) {
                        throw new ServiceException("查询课程详情失败");
                    }

                    // 查询课程的学习人数
                    CourUserCourseSection courUserCourseSection = new CourUserCourseSection();
                    courUserCourseSection.setCourseId(courseId);
                    List<CourUserCourseSection> courUserCourseSectionList = courUserCourseSectionService.selectCourUserCourseSectionList(courUserCourseSection);

                    CourseVO courseVO = new CourseVO();
                    BeanUtils.copyProperties(course, courseVO);
                    courseVO.setStudyNum(courUserCourseSectionList.size());

                    // 增加章节列表
                    CourSection courSection = CourSection.builder()
                            .courseId(courseId)
                            .build();
                    List<CourSection> sectionList = courSectionService.selectCourSectionList(courSection);
                    // 查询章节的学习情况
                    List<SectionVO> sectionVOList = new ArrayList<>();


                    for (CourSection section: sectionList) {
                        SectionVO sectionVO = new SectionVO();
                        CourUserCourseSection userCourseSection = new CourUserCourseSection();
                        userCourseSection.setUserId(Long.valueOf(order.getPayConsultantId()));
                        userCourseSection.setUserType(2);//咨询师
                        userCourseSection.setCourseId(courseId);
                        BeanUtils.copyProperties(section, sectionVO);
                        userCourseSection.setSectionId(section.getId());
                        sectionVO.setEndTime(courUserCourseSectionService.findEndTime(userCourseSection));

                        // 未购买课程的普通章节不返回内容链接
                        if (courCourseService.getPaidCourseCount(Long.valueOf(order.getPayConsultantId()), courseId) == 0 && sectionVO.getType() == CourConstant.SECTION_NORMAL) {
                            sectionVO.setContentUrl(null);
                        }
                        sectionVOList.add(sectionVO);
                    }
                    course.setSectionList(sectionVOList);
                    
                    order.setCourseDetail(course);
                }
            }
            
            //筛选收款咨询师及剩余可用次数
            if (ObjectUtils.isNotEmpty(req.getChargeConsultantId())){
                orderList = orderList.stream().filter(o -> o.getChargeConsultantId().equals(req.getChargeConsultantId()) && o.getSurplusNum() > 0).collect(Collectors.toList());
            }
        }
        
        return orderList;
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
     * 生成咨询师订单
     *
     * @param psyConsultantOrder 
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
    public void createConsultantOrder(PsyConsultantOrder consultantOrder)  {
        String nickName = consultService.getOne(Long.valueOf(consultantOrder.getPayConsultantId())).getNickName();
        consultantOrder.setCreateBy(consultantOrder.getPayConsultantId());
        consultantOrder.setUpdateBy(consultantOrder.getPayConsultantId());
        consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_CREATED);
        String payId = UUID.randomUUID().toString();// 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
        consultantOrder.setPayId(payId);
        consultantOrder.setPayConsultantName(nickName);
        consultantOrder.setPayAmount(consultantOrder.getOriginalPrice());//默认原价
        String serverType = consultantOrder.getServerType();
        String couponNo = consultantOrder.getCouponNo();
        
        //若[支付类型]为"权益支付", 则直接校验并扣减
        //Integer payType = consultantOrder.getPayType();
        
        /*
        //PsyConsultantPackageEquity consultantPackageEquity = new PsyConsultantPackageEquity();
        if(PsyConstants.PAY_TYPE_COUPON == payType){
            //查询当前咨询师的权益清单
            consultantPackageEquity = consultantPackageEquityService.selectPsyConsultantPackageEquityByConsultantId(Long.valueOf(consultantOrder.getPayConsultantId()));
            if (ObjectUtils.isEmpty(consultantPackageEquity)){
                throw new ServiceException("当前用户的权益不足, 请先购买套餐或直接现款支付");
            }
        }*/
        
        boolean isPayed = false;
        if (PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM.equals(serverType)) {
            //计算券后价格
            if(ObjectUtils.isNotEmpty(couponNo)) {
                consultantOrder.setPayAmount(couponService.calcPayAmount(consultantOrder));
            }
            
            //生成团督订单
            PsyConsultantTeamSupervision team = teamSupervisionService.selectPsyConsultantTeamSupervisionById(Long.valueOf(consultantOrder.getServerId()));
            //consultantOrder.setServerName(team.getTitle()+"-第"+team.getPeriodNo()+"期");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);
            //内部生成支付对象
            /*PsyOrderPay orderPay = new PsyOrderPay();
            orderPay.setConsultantOrderId(Integer.valueOf(newOrder.getId()));
            orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN); // 微信 支付方式一定是微信
            orderPay.setPayStatus(CourConstant.PAY_STATUE_PENDING);
            orderPay.setAmount(consultantOrder.getPayAmount());
            orderPay.setPayId(payId); // 当前使用随机生成的支付ID，后续使用第三方支付平台返回的
            orderPayService.insertPsyOrderPay(orderPay);*/

        } else if (PsyConstants.CONSULTANT_ORDER_PERSON_SUP_NUM.equals(serverType)) {
            //计算券后价格
            if(ObjectUtils.isNotEmpty(couponNo)) {
                consultantOrder.setPayAmount(couponService.calcPayAmount(consultantOrder));
            }
            
            // 个人督导服务
            //Long id = consultantOrder.getOrderId() != null ? consultantOrder.getOrderId() : IDhelper.getNextId();
            //consultantOrder.setServerName("个人督导服务");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);
        } else if (PsyConstants.CONSULTANT_ORDER_PERSON_EXP_NUM.equals(serverType)) {
            //计算券后价格
            if(ObjectUtils.isNotEmpty(couponNo)) {
                consultantOrder.setPayAmount(couponService.calcPayAmount(consultantOrder));
            }
            // 个人体验服务
            //Long id = consultantOrder.getOrderId() != null ? consultantOrder.getOrderId() : IDhelper.getNextId();
            //consultantOrder.setServerName("个人体验服务");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);
        } else if (PsyConstants.CONSULTANT_ORDER_COURSE_NUM.equals(serverType)) {
            //计算券后价格
            if(ObjectUtils.isNotEmpty(couponNo)) {
                consultantOrder.setPayAmount(couponService.calcPayAmount(consultantOrder));
            }
            
            // 购买课程
            //consultantOrder.setServerName("购买课程");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);
        } else if (PsyConstants.CONSULTANT_ORDER_PACKAGE_NUM.equals(serverType)) {
            if(ObjectUtils.isNotEmpty(couponNo)) {
                throw new ServiceException("套餐不可使用优惠券购买");
            }
            // 套餐权益
            //consultantOrder.setServerName("套餐权益");
            PsyConsultantOrder newOrder = consultantOrderService.generatePsyConsultantOrder(consultantOrder);
            
        } else {
            throw new ServiceException("不存在的服务类型, 请确认serverType的值为1~5的整数");
        }
        
        //若应付金额为0, 则直接回调处理订单
        if (consultantOrder.getPayAmount().compareTo(BigDecimal.ZERO) >= 0){
            paySuccessCallback(consultantOrder.getOrderNo(),consultantOrder.getPayId());
            couponService.useCoupon(couponNo);
        }
        
    }

    /**
     * 支付成功回调
     * @param outTradeNo
     * @param payId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void paySuccessCallback(String outTradeNo, String payId) {
        PsyConsultantOrder consultantOrder = consultantOrderService.selectPsyConsultantOrderByOrderNo(outTradeNo);
        if (!consultantOrder.getPayId().equals(payId)){
            throw new ServiceException("payId不相符, 请确认已付款成功");
        }

        if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_TEAM_SUP)) {
            //团队督导 , 更新订单状态
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(consultantOrder.getStatus())) {
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_PENDING);
                consultantOrder.setPayStatus(ConsultConstant.PAY_STATUE_PAID);
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);

                //修改支付对象状态为已支付
                /*PsyOrderPay orderPay = new PsyOrderPay();
                orderPay.setConsultantOrderId(Integer.valueOf(consultantOrder.getId())); // 订单ID
                orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN);
                orderPay.setPayStatus(CourConstant.PAY_STATUE_PAID);
                orderPay.setPayId(payId);
                orderPayService.updatePsyOrderPayByOrderId(orderPay);*/

                //将付款咨询师加入团队内
                teamSupervisionService.handleOrder(consultantOrder);
            }
        } else if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_PERSON_SUP)) {
            //个人督导 , 更新订单状态
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(consultantOrder.getStatus())) {
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_PENDING);
                consultantOrder.setPayStatus(ConsultConstant.PAY_STATUE_PAID);
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);

                //占用相应时段
                //psyConsultWorkService.handleWork(consultantOrder.getWorkId(), null , consultantOrder.getTime(), 1);

            }
        } else if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_PERSON_EXP)) {
            //个人体验 , 更新订单状态
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(consultantOrder.getStatus())) {
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_PENDING);
                consultantOrder.setPayStatus(ConsultConstant.PAY_STATUE_PAID);
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);

                //占用相应时段
                //psyConsultWorkService.handleWork(consultantOrder.getWorkId(), null , consultantOrder.getTime(), 1);

            }
        } else if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_COURSE)) {
            // 课程 , 更新订单状态
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(consultantOrder.getStatus())) {
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_FINISHED);
                consultantOrder.setPayStatus(ConsultConstant.PAY_STATUE_PAID);
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);
                
                // 将用户-课程-章节关系初始化
                userCourseSectionService.initCourUserCourseSection(Long.valueOf(consultantOrder.getPayConsultantId()), Long.valueOf(consultantOrder.getServerId()),PsyConstants.USER_CONSULTANT);
            }
        }else if (outTradeNo.startsWith(PsyConstants.CONSULTANT_ORDER_PACKAGE)) {
            //套餐权益 , 更新订单状态
            if (ConsultConstant.CONSULT_ORDER_STATUE_CREATED.equals(consultantOrder.getStatus())) {
                //修改订单状态为已完成
                consultantOrder.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_FINISHED);
                consultantOrder.setPayStatus(ConsultConstant.PAY_STATUE_PAID);
                consultantOrder.setPayDatetime(new Date());
                consultantOrderService.updatePsyConsultantOrder(consultantOrder);
                
                //分配套餐权益(优惠券)
                couponService.handleConsultantPackageOrder(consultantOrder.getOrderNo());
            }
        }
        
    }
    
}
