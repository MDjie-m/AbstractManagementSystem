package com.renxin.psychology.service.impl;


import com.renxin.common.constant.PsyConstants;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.OrderIdUtils;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.service.ICourCourseService;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.*;
import com.renxin.psychology.mapper.PsyCouponMapper;
import com.renxin.psychology.service.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * 查询用户-优惠券发行列表
     * 
     * @param psyCoupon 用户-优惠券发行
     * @return 用户-优惠券发行
     */
    @Override
    public List<PsyCoupon> selectPsyCouponList(PsyCoupon psyCoupon)
    {
        BigDecimal payAmount = new BigDecimal(0); //价格
        //根据不同[服务类型和id]获取价格
        switch (psyCoupon.getOrderServerType()) {
            //团队督导
            case PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM:
                PsyConsultantTeamSupervision team = teamService.selectPsyConsultantTeamSupervisionById(Long.valueOf(psyCoupon.getOrderServerId()));
                payAmount = team.getPrice();
                break;

            case PsyConstants.CONSULTANT_ORDER_PERSON_SUP_NUM:
                PsyConsultServeConfig serverDetail = consultServeService.getServerDetailByRelationId(psyCoupon.getOrderServerId());
                payAmount = serverDetail.getPrice();
                break;

            case PsyConstants.CONSULTANT_ORDER_PERSON_EXP_NUM:
                PsyConsultServeConfig serverDetailExp = consultServeService.getServerDetailByRelationId(psyCoupon.getOrderServerId());
                payAmount = serverDetailExp.getPrice();
                break;

            case PsyConstants.CONSULTANT_ORDER_COURSE_NUM:
                CourCourse courCourse = courCourseService.selectCourCourseById(Integer.parseInt(psyCoupon.getOrderServerId()));
                payAmount = courCourse.getPrice();
                break;

            default:
                throw new ServiceException("没有相应的服务类型, 请检查orderServerType为1~4之间的整数");
        }
        
        //查询本咨询师拥有的  本类型的抵扣券清单
        psyCoupon.setOrderServerType("2"+psyCoupon.getOrderServerType());
        List<PsyCoupon> psyCouponList = psyCouponMapper.selectPsyCouponList(psyCoupon);

        //逐条对比判断 是否可用于本次支付
        for (PsyCoupon coupon : psyCouponList) {
            if (coupon.getMaxDeductionPrice().compareTo(payAmount)>0){
                coupon.setIsQualify(true);
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
        String payConsultantId = order.getPayConsultantId();
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
            throw new ServiceException("该抵扣券不存在或不可用");
        }
        if (!couponList.get(0).getIsQualify()){
            throw new ServiceException("该抵扣券的额度不足");
        }
        useCoupon(couponNo);
    }

    //消耗优惠券
    @Override
    public void useCoupon(String couponNo){
        PsyCoupon psyCoupon = psyCouponMapper.selectPsyCouponByCouponNo(couponNo);
        psyCoupon.setIsUsable(1);//已消耗
        psyCouponMapper.updatePsyCoupon(psyCoupon);
    }

    
}
