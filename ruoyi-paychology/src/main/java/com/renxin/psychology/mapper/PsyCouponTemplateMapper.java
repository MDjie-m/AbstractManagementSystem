package com.renxin.psychology.mapper;


import com.renxin.psychology.domain.PsyCouponTemplate;

import java.util.List;

/**
 * 优惠券模版Mapper接口
 * 
 * @author renxin
 * @date 2024-08-02
 */
public interface PsyCouponTemplateMapper 
{
    /**
     * 查询优惠券模版
     * 
     * @param id 优惠券模版主键
     * @return 优惠券模版
     */
    public PsyCouponTemplate selectPsyCouponTemplateById(Long id);

    /**
     * 查询优惠券模版列表
     * 
     * @param psyCouponTemplate 优惠券模版
     * @return 优惠券模版集合
     */
    public List<PsyCouponTemplate> selectPsyCouponTemplateList(PsyCouponTemplate psyCouponTemplate);

    /**
     * 新增优惠券模版
     * 
     * @param psyCouponTemplate 优惠券模版
     * @return 结果
     */
    public int insertPsyCouponTemplate(PsyCouponTemplate psyCouponTemplate);

    /**
     * 修改优惠券模版
     * 
     * @param psyCouponTemplate 优惠券模版
     * @return 结果
     */
    public int updatePsyCouponTemplate(PsyCouponTemplate psyCouponTemplate);

    /**
     * 删除优惠券模版
     * 
     * @param id 优惠券模版主键
     * @return 结果
     */
    public int deletePsyCouponTemplateById(Long id);

    /**
     * 批量删除优惠券模版
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyCouponTemplateByIds(Long[] ids);
}
