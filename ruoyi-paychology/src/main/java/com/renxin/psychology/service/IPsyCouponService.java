package com.renxin.psychology.service;


import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.domain.PsyConsultantPackage;
import com.renxin.psychology.domain.PsyCoupon;

import java.util.List;

/**
 * 用户-优惠券发行Service接口
 * 
 * @author renxin
 * @date 2024-08-02
 */
public interface IPsyCouponService 
{
    /**
     * 查询用户-优惠券发行
     * 
     * @param couponNo 用户-优惠券发行主键
     * @return 用户-优惠券发行
     */
    public PsyCoupon selectPsyCouponByCouponNo(String couponNo);

    /**
     * 查询用户-优惠券发行列表
     * 
     * @param psyCoupon 用户-优惠券发行
     * @return 用户-优惠券发行集合
     */
    public List<PsyCoupon> selectPsyCouponList(PsyCoupon psyCoupon);

    /**
     * 新增用户-优惠券发行
     * 
     * @param psyCoupon 用户-优惠券发行
     * @return 结果
     */
    public int insertPsyCoupon(PsyCoupon psyCoupon);

    /**
     * 修改用户-优惠券发行
     * 
     * @param psyCoupon 用户-优惠券发行
     * @return 结果
     */
    public int updatePsyCoupon(PsyCoupon psyCoupon);

    /**
     * 批量删除用户-优惠券发行
     * 
     * @param couponNos 需要删除的用户-优惠券发行主键集合
     * @return 结果
     */
    public int deletePsyCouponByCouponNos(Long[] couponNos);

    /**
     * 删除用户-优惠券发行信息
     * 
     * @param couponNo 用户-优惠券发行主键
     * @return 结果
     */
    public int deletePsyCouponByCouponNo(String couponNo);

    /**
     * 处理套餐订单 （购买套餐-获得券)
     */
    public void handleConsultantPackageOrder(String orderNo);
    
    //处理抵扣订单 (消耗抵扣券-完成支付)
    public void handleConsultantCouponOrder(PsyConsultantOrder consultantOrder);
    
    //消耗优惠券
    public void useCoupon(String no);
    
    
}
