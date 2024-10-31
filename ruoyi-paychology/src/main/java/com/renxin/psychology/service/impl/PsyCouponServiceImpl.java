package com.renxin.psychology.service.impl;


import com.renxin.common.constant.PsyConstants;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.OrderIdUtils;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.service.ICourCourseService;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.gauge.domain.PsyGauge;
import com.renxin.gauge.service.IPsyGaugeService;
import com.renxin.psychology.domain.*;
import com.renxin.psychology.mapper.PsyCouponMapper;
import com.renxin.psychology.request.ReceiveFreeCouponReq;
import com.renxin.psychology.service.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户-优惠券发行Service业务层处理
 * 
 * @author renxin
 * @date 2024-08-02
 */
@Service
public class PsyCouponServiceImpl implements IPsyCouponService 
{
    @Autowired
    private PsyCouponMapper psyCouponMapper;

    @Autowired
    private IPsyConsultantOrderService consultantOrderService;
    
    @Autowired
    private IPsyCouponTemplateService couponTemplateService;

    @Autowired
    private IPsyConsultantPackageService packageService;

    @Resource
    private IPsyConsultantTeamSupervisionService teamService;

    @Resource
    private ConsultantTokenService consultantTokenService;

    @Resource
    private ICourCourseService courCourseService;

    @Resource
    private IPsyConsultantPackageService consultantPackageService;

    @Resource
    private IPsyConsultantScheduleService consultantScheduleService;

    @Resource
    private IPsyConsultServeService consultServeService;
    
    @Resource
    private IPsyGaugeService gaugeService;

    @Autowired
    private IPsyUserService psyUserService;

    @Resource
    private IPsyConsultService psyConsultService;


    /**
     * 查询用户-优惠券发行
     * 
     * @param couponNo 用户-优惠券发行主键
     * @return 用户-优惠券发行
     */
    @Override
    public PsyCoupon selectPsyCouponByCouponNo(String couponNo)
    {
        return psyCouponMapper.selectPsyCouponByCouponNo(couponNo);
    }

    /**
     * 查询优惠券发行列表
     * 
     * @param psyCoupon 用户-优惠券发行
     * @return 用户-优惠券发行
     */
    @Override
    public List<PsyCoupon> selectPsyCouponList(PsyCoupon psyCoupon)
    {
        String userType = ObjectUtils.isNotEmpty(psyCoupon.getUserId()) ? "1" : "2";
        psyCoupon.setOrderServerType(ObjectUtils.isEmpty(psyCoupon.getOrderServerType()) ? null : userType + psyCoupon.getOrderServerType());
        //未指定服务id, 直接查询清单返回
        if (ObjectUtils.isEmpty(psyCoupon.getOrderServerId())){
            List<PsyCoupon> psyCouponList = psyCouponMapper.selectPsyCouponList(psyCoupon);
            return psyCouponList;
        }
        
        //指定服务id, 则需校验各个券是否可用, 及使用之后的价格
        BigDecimal originalPrice = new BigDecimal(0); //价格
        //根据不同[服务类型和id]获取服务原价格
        String orderServerId = psyCoupon.getOrderServerId();
        switch (psyCoupon.getOrderServerType()) {
            // 11.倾诉
            // 12.咨询  
            case "1"+PsyConstants.POCKET_ORDER_CONSULT_NUM:
                PsyConsultServeConfig serverDetailConsult = consultServeService.getServerDetailByRelationId(orderServerId);
                originalPrice = serverDetailConsult.getPrice();
                break;
            // 13.测评
            case "1"+PsyConstants.POCKET_ORDER_GAUGE_NUM:
                PsyGauge psyGauge = gaugeService.selectPsyGaugeById(Long.valueOf(orderServerId));
                originalPrice = psyGauge.getPrice();
                break;
            // 14.来访者课程
            case "1"+PsyConstants.POCKET_ORDER_COURSE_NUM:
                CourCourse pocketCourse = courCourseService.selectCourCourseById(Long.valueOf(orderServerId));
                originalPrice = pocketCourse.getPrice();
                break;
            
            //21.团队督导
            case "2"+PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM:
                PsyConsultantTeamSupervision team = teamService.selectPsyConsultantTeamSupervisionById(Long.valueOf(orderServerId));
                originalPrice = team.getPrice();
                break;
            //22.个督
            case "2"+PsyConstants.CONSULTANT_ORDER_PERSON_SUP_NUM:
                PsyConsultServeConfig serverDetailSup = consultServeService.getServerDetailByRelationId(orderServerId);
                originalPrice = serverDetailSup.getPrice();
                break;
            //23.个人体验
            case "2"+PsyConstants.CONSULTANT_ORDER_PERSON_EXP_NUM:
                PsyConsultServeConfig serverDetailExp = consultServeService.getServerDetailByRelationId(orderServerId);
                originalPrice = serverDetailExp.getPrice();
                break;
            //24.咨询师课程
            case "2"+PsyConstants.CONSULTANT_ORDER_COURSE_NUM:
                CourCourse courCourse = courCourseService.selectCourCourseById(Long.valueOf(orderServerId));
                originalPrice = courCourse.getPrice();
                break;

            default:
                throw new ServiceException("没有相应的服务类型, 请检查orderServerType为1~4之间的整数");
        }
        
        //查询本人拥有的  本类型的可用优惠券清单
        psyCoupon.setIsUsable(0);
        psyCoupon.setIsExpire(0);
        List<PsyCoupon> psyCouponList = psyCouponMapper.selectPsyCouponList(psyCoupon);

        //逐条对比判断 是否可用于本次支付, 并计算优惠后的价格
        for (PsyCoupon coupon : psyCouponList) {
            //原价达到了门槛
            if (originalPrice.compareTo(coupon.getUseThresholdPrice())>0){
                coupon.setIsQualify(true);
                if (coupon.getCouponType() == 1){//抵扣券
                    BigDecimal payAmount = originalPrice.subtract(coupon.getMaxDeductionPrice());
                    if (payAmount.compareTo(BigDecimal.ZERO) <= 0) {
                        coupon.setPayAmount(BigDecimal.ZERO);
                    }else {
                        coupon.setPayAmount(payAmount);
                    }
                }
                else if (coupon.getCouponType() == 2){//折扣券
                    coupon.setPayAmount(originalPrice.multiply(coupon.getDiscountRate()));
                }
            }else{
                coupon.setIsQualify(false);
            }
        }
        
        return psyCouponList;
    }

    /**
     * 新增用户-优惠券发行
     * 
     * @param psyCoupon 用户-优惠券发行
     * @return 结果
     */
    @Override
    public int insertPsyCoupon(PsyCoupon psyCoupon)
    {
        psyCoupon.setCreateTime(DateUtils.getNowDate());
        return psyCouponMapper.insertPsyCoupon(psyCoupon);
    }

    //发放优惠券
    @Override
    public void grantCoupon(PsyCoupon coupon){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //根据模版id查询券模版信息
        PsyCouponTemplate couponTemplate = couponTemplateService.selectPsyCouponTemplateById(coupon.getTemplateId());

        //根据券类型生成券编号
        Integer serverType = couponTemplate.getServerType();
        String couponNo = "";
        switch (serverType){
            case 12 : //咨询
                couponNo = OrderIdUtils.createOrderNo(PsyConstants.POCKET_ORDER_CONSULT + PsyConstants.COUPON_NO, null);
                break;
            case 13 : //测评
                couponNo = OrderIdUtils.createOrderNo(PsyConstants.POCKET_ORDER_GAUGE + PsyConstants.COUPON_NO, null);
                break;
            case 14 : //来访者课程
                couponNo = OrderIdUtils.createOrderNo(PsyConstants.POCKET_ORDER_COURSE + PsyConstants.COUPON_NO, null);
                break;
            case 21 : //团督
                couponNo = OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_TEAM_SUP + PsyConstants.COUPON_NO, null);
                break;
            case 22 : //个督    
                couponNo = OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_PERSON_SUP + PsyConstants.COUPON_NO, null);
                break;
            case 23 : //个人体验
                couponNo = OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_PERSON_EXP + PsyConstants.COUPON_NO, null);
                break;
            case 24 : //咨询师课程
                couponNo = OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_COURSE + PsyConstants.COUPON_NO, null);
                break;
        }
        coupon.setExpireDate(LocalDate.now().plusDays(couponTemplate.getValidityDay()).format(formatter));//到期日
        coupon.setCouponNo(couponNo);

        //发放优惠券
        insertPsyCoupon(coupon);
    }
    /**
     * 修改用户-优惠券发行
     * 
     * @param psyCoupon 用户-优惠券发行
     * @return 结果
     */
    @Override
    public int updatePsyCoupon(PsyCoupon psyCoupon)
    {
        return psyCouponMapper.updatePsyCoupon(psyCoupon);
    }

    /**
     * 批量删除用户-优惠券发行
     * 
     * @param couponNos 需要删除的用户-优惠券发行主键
     * @return 结果
     */
    @Override
    public int deletePsyCouponByCouponNos(Long[] couponNos)
    {
        return psyCouponMapper.deletePsyCouponByCouponNos(couponNos);
    }

    /**
     * 删除用户-优惠券发行信息
     * 
     * @param couponNo 用户-优惠券发行主键
     * @return 结果
     */
    @Override
    public int deletePsyCouponByCouponNo(String couponNo)
    {
        return psyCouponMapper.deletePsyCouponByCouponNo(couponNo);
    }

    /**
     * 处理套餐订单（购买套餐-获得券)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleConsultantPackageOrder(String orderNo){
        //订单详情
        PsyConsultantOrder order = consultantOrderService.selectPsyConsultantOrderByOrderNo(orderNo);
        //套餐详情
        PsyConsultantPackage consultantPackage = packageService.selectPsyConsultantPackageByPackageId(Long.valueOf(order.getServerId()));
        Long payConsultantId = order.getPayConsultantId();
        ArrayList<PsyCoupon> couponList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //使用的各个优惠券模版
        PsyCouponTemplate teamSupTemplate = couponTemplateService.selectPsyCouponTemplateById(consultantPackage.getTeamSupCouponTemplateId());
        PsyCouponTemplate personSupTemplate = couponTemplateService.selectPsyCouponTemplateById(consultantPackage.getPersonSupCouponTemplateId());
        PsyCouponTemplate personExpTemplate = couponTemplateService.selectPsyCouponTemplateById(consultantPackage.getPersonExpCouponTemplateId());
        PsyCouponTemplate courseTemplate = couponTemplateService.selectPsyCouponTemplateById(consultantPackage.getCourseCouponTemplateId());
        
        //生成团督优惠券
        for (Integer i = 0; i < consultantPackage.getTeamSupNum(); i++) {
            PsyCoupon coupon = new PsyCoupon();
            //优惠券编号
            coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_TEAM_SUP + PsyConstants.COUPON_NO, null));
            //持有人
            coupon.setConsultantId(Long.valueOf(payConsultantId));
            //优惠券模版id
            coupon.setTemplateId(consultantPackage.getTeamSupCouponTemplateId());
            coupon.setCreateTime(new Date());
            coupon.setExpireDate(LocalDate.now().plusDays(teamSupTemplate.getValidityDay()).format(formatter));//到期日
            couponList.add(coupon);
        }

        //个督
        for (Integer i = 0; i < consultantPackage.getPersonSupNum(); i++) {
            PsyCoupon coupon = new PsyCoupon();
            //优惠券编号
            coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_PERSON_SUP + PsyConstants.COUPON_NO, null));
            //持有人
            coupon.setConsultantId(Long.valueOf(payConsultantId));
            //优惠券模版id
            coupon.setTemplateId(consultantPackage.getPersonSupCouponTemplateId());
            coupon.setCreateTime(new Date());
            coupon.setExpireDate(LocalDate.now().plusDays(personSupTemplate.getValidityDay()).format(formatter));//到期日
            couponList.add(coupon);
        }

        //体验
        for (Integer i = 0; i < consultantPackage.getPersonExpNum(); i++) {
            PsyCoupon coupon = new PsyCoupon();
            //优惠券编号
            coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_PERSON_EXP + PsyConstants.COUPON_NO, null));
            //持有人
            coupon.setConsultantId(Long.valueOf(payConsultantId));
            //优惠券模版id
            coupon.setTemplateId(consultantPackage.getPersonExpCouponTemplateId());
            coupon.setCreateTime(new Date());
            coupon.setExpireDate(LocalDate.now().plusDays(personExpTemplate.getValidityDay()).format(formatter));//到期日
            couponList.add(coupon);
        }

        //课程
        for (Integer i = 0; i < consultantPackage.getCourseNum(); i++) {
            PsyCoupon coupon = new PsyCoupon();
            //优惠券编号
            coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_COURSE + PsyConstants.COUPON_NO, null));
            //持有人
            coupon.setConsultantId(Long.valueOf(payConsultantId));
            //优惠券模版id
            coupon.setTemplateId(consultantPackage.getCourseCouponTemplateId());
            coupon.setCreateTime(new Date());
            coupon.setExpireDate(LocalDate.now().plusDays(courseTemplate.getValidityDay()).format(formatter));//到期日
            couponList.add(coupon);
        }
        
        psyCouponMapper.insertPsyCouponList(couponList);
        
    }


    //计算券后价格
    public BigDecimal calcPayAmount(PsyConsultantOrder order){
        String couponNo = order.getCouponNo();
        if (ObjectUtils.isEmpty(couponNo)){
            throw new ServiceException("选择抵扣券支付时, couponNo不能为空");
        }

        PsyCoupon psyCoupon = new PsyCoupon();
        psyCoupon.setCouponNo(couponNo);
        psyCoupon.setConsultantId(Long.valueOf(order.getPayConsultantId()));
        psyCoupon.setOrderServerType(order.getServerType());
        psyCoupon.setOrderServerId(order.getServerId());
        List<PsyCoupon> couponList = selectPsyCouponList(psyCoupon);

        if (ObjectUtils.isEmpty(couponList)){
            throw new ServiceException("该优惠券不存在或不可用");
        }
        if (!couponList.get(0).getIsQualify()){
            throw new ServiceException("未达到优惠券的使用门槛");
        }
        
        return couponList.get(0).getPayAmount();
    }
    
    //处理抵扣订单 (消耗抵扣券-完成支付)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleConsultantCouponOrder(PsyConsultantOrder order){
        String couponNo = order.getCouponNo();
        if (ObjectUtils.isEmpty(couponNo)){
            throw new ServiceException("选择抵扣券支付时, couponNo不能为空");
        }
        
        PsyCoupon psyCoupon = new PsyCoupon();
            psyCoupon.setCouponNo(couponNo);
            psyCoupon.setConsultantId(Long.valueOf(order.getPayConsultantId()));
            psyCoupon.setOrderServerType(order.getServerType());
            psyCoupon.setOrderServerId(order.getServerId());
        List<PsyCoupon> couponList = selectPsyCouponList(psyCoupon);
        
        if (ObjectUtils.isEmpty(couponList)){
            throw new ServiceException("该优惠券不存在或不可用");
        }
        if (!couponList.get(0).getIsQualify()){
            throw new ServiceException("未达到优惠券的使用门槛");
        }
        useCoupon(couponNo);
    }

    //消耗优惠券
    @Override
    public void useCoupon(String couponNo){
        if (ObjectUtils.isEmpty(couponNo)){
            return;
        }
        PsyCoupon psyCoupon = psyCouponMapper.selectPsyCouponByCouponNo(couponNo);
        psyCoupon.setIsUsable(1);//已消耗
        psyCouponMapper.updatePsyCoupon(psyCoupon);
    }

    //归还优惠券
    @Override
    public void returnCoupon(String couponNo){
        if (ObjectUtils.isEmpty(couponNo)){
            return;
        }
        PsyCoupon psyCoupon = psyCouponMapper.selectPsyCouponByCouponNo(couponNo);
        psyCoupon.setIsUsable(0);//未使用
        psyCouponMapper.updatePsyCoupon(psyCoupon);
    }


    //领取优惠券
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void receiveFreeCoupon(ReceiveFreeCouponReq req){
        List<Long> couponTemplateIdList = Arrays.stream(req.getCouponTemplateIdStr().split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<PsyCoupon> couponList = new ArrayList<>();

        for (Long temId : couponTemplateIdList) {
            //查询相应模版
            PsyCouponTemplate couponTemplate = couponTemplateService.selectPsyCouponTemplateById(temId);
            if (!"Y".equals(couponTemplate.getIsFreeGet()) && !"Y".equals(req.getIsCanGetChargeCoupon())){
                throw new ServiceException(couponTemplate.getCouponName() + "-该优惠券不支持免费领取");
            }
            PsyCoupon coupon = new PsyCoupon();
            
            //根据服务类型生成编号
            Integer serverType = couponTemplate.getServerType();
            switch ( serverType + ""){
                //来访者咨询
                case 1 + PsyConstants.POCKET_ORDER_CONSULT_NUM:
                    coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.POCKET_ORDER_CONSULT + PsyConstants.COUPON_NO, null));
                    break;
                //来访者测评
                case 1 + PsyConstants.POCKET_ORDER_GAUGE_NUM:
                    coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.POCKET_ORDER_GAUGE + PsyConstants.COUPON_NO, null));
                    break;
                //来访者课程
                case 1 + PsyConstants.POCKET_ORDER_COURSE_NUM:
                    coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.POCKET_ORDER_COURSE + PsyConstants.COUPON_NO, null));
                    break;
                
                //团队督导
                case 2 + PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM:
                    coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_TEAM_SUP + PsyConstants.COUPON_NO, null));
                    break;
                //个督
                case 2 + PsyConstants.CONSULTANT_ORDER_PERSON_SUP_NUM:
                    coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_PERSON_SUP + PsyConstants.COUPON_NO, null));
                    break;
                //个人体验
                case 2 + PsyConstants.CONSULTANT_ORDER_PERSON_EXP_NUM:
                    coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_PERSON_EXP + PsyConstants.COUPON_NO, null));
                    break;
                //咨询师课程
                case 2 + PsyConstants.CONSULTANT_ORDER_COURSE_NUM:
                    coupon.setCouponNo(OrderIdUtils.createOrderNo(PsyConstants.CONSULTANT_ORDER_COURSE + PsyConstants.COUPON_NO, null));
                    break;
                    
                default:
                    throw new ServiceException(temId + "优惠券模版领取异常, 没有相应的服务类型.");
            }
            coupon.setTemplateId(temId);
            coupon.setConsultantId(req.getConsultId());
            coupon.setUserId(req.getUserId());
            coupon.setIsUsable(0);
            coupon.setCreateTime(new Date());
            coupon.setExpireDate(LocalDate.now().plusDays(couponTemplate.getValidityDay()).format(formatter));
            couponList.add(coupon);
        }
        psyCouponMapper.insertPsyCouponList(couponList);
        
        //修改"新用户"标识
        if (ObjectUtils.isNotEmpty(req.getUserId())){
            PsyUser psyUser = new PsyUser();
                psyUser.setId(req.getUserId());
                psyUser.setIsNewPeople(1);//老用户
            psyUserService.updatePsyUser(psyUser);
        }else if (ObjectUtils.isNotEmpty(req.getConsultId())){
            PsyConsult psyConsult = new PsyConsult();
                psyConsult.setId(req.getConsultId());
                psyConsult.setIsNewPeople(1);//老用户
            psyConsultService.updateById(psyConsult);
            psyConsultService.refreshCacheById(psyConsult.getId());
        }
        
    }
    
}
