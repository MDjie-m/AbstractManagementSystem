package com.renxin.psychology.service.impl;


import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyCoupon;
import com.renxin.psychology.mapper.PsyCouponMapper;
import com.renxin.psychology.service.IPsyCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 查询用户-优惠券发行
     * 
     * @param couponNo 用户-优惠券发行主键
     * @return 用户-优惠券发行
     */
    @Override
    public PsyCoupon selectPsyCouponByCouponNo(Long couponNo)
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
        return psyCouponMapper.selectPsyCouponList(psyCoupon);
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
    public int deletePsyCouponByCouponNo(Long couponNo)
    {
        return psyCouponMapper.deletePsyCouponByCouponNo(couponNo);
    }
}
