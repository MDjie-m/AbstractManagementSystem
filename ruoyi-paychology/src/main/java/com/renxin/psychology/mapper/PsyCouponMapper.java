package com.renxin.psychology.mapper;


import com.renxin.psychology.domain.PsyCoupon;

import java.util.List;

/**
 * 用户-优惠券发行Mapper接口
 * 
 * @author renxin
 * @date 2024-08-02
 */
public interface PsyCouponMapper 
{
    /**
     * 查询用户-优惠券发行
     * 
     * @param couponNo 用户-优惠券发行主键
     * @return 用户-优惠券发行
     */
    public PsyCoupon selectPsyCouponByCouponNo(Long couponNo);

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
     * 删除用户-优惠券发行
     * 
     * @param couponNo 用户-优惠券发行主键
     * @return 结果
     */
    public int deletePsyCouponByCouponNo(Long couponNo);

    /**
     * 批量删除用户-优惠券发行
     * 
     * @param couponNos 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyCouponByCouponNos(Long[] couponNos);
}
