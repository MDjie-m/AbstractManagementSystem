package com.renxin.psychology.service.impl;


import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyCouponTemplate;
import com.renxin.psychology.mapper.PsyCouponTemplateMapper;
import com.renxin.psychology.service.IPsyCouponTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券模版Service业务层处理
 * 
 * @author renxin
 * @date 2024-08-02
 */
@Service
public class PsyCouponTemplateServiceImpl implements IPsyCouponTemplateService 
{
    @Autowired
    private PsyCouponTemplateMapper psyCouponTemplateMapper;

    /**
     * 查询优惠券模版
     * 
     * @param id 优惠券模版主键
     * @return 优惠券模版
     */
    @Override
    public PsyCouponTemplate selectPsyCouponTemplateById(Long id)
    {
        return psyCouponTemplateMapper.selectPsyCouponTemplateById(id);
    }

    /**
     * 查询优惠券模版列表
     * 
     * @param psyCouponTemplate 优惠券模版
     * @return 优惠券模版
     */
    @Override
    public List<PsyCouponTemplate> selectPsyCouponTemplateList(PsyCouponTemplate psyCouponTemplate)
    {
        return psyCouponTemplateMapper.selectPsyCouponTemplateList(psyCouponTemplate);
    }

    /**
     * 新增优惠券模版
     * 
     * @param psyCouponTemplate 优惠券模版
     * @return 结果
     */
    @Override
    public int insertPsyCouponTemplate(PsyCouponTemplate psyCouponTemplate)
    {
        psyCouponTemplate.setCreateTime(DateUtils.getNowDate());
        return psyCouponTemplateMapper.insertPsyCouponTemplate(psyCouponTemplate);
    }

    /**
     * 修改优惠券模版
     * 
     * @param psyCouponTemplate 优惠券模版
     * @return 结果
     */
    @Override
    public int updatePsyCouponTemplate(PsyCouponTemplate psyCouponTemplate)
    {
        psyCouponTemplate.setUpdateTime(DateUtils.getNowDate());
        return psyCouponTemplateMapper.updatePsyCouponTemplate(psyCouponTemplate);
    }

    /**
     * 批量删除优惠券模版
     * 
     * @param ids 需要删除的优惠券模版主键
     * @return 结果
     */
    @Override
    public int deletePsyCouponTemplateByIds(Long[] ids)
    {
        return psyCouponTemplateMapper.deletePsyCouponTemplateByIds(ids);
    }

    /**
     * 删除优惠券模版信息
     * 
     * @param id 优惠券模版主键
     * @return 结果
     */
    @Override
    public int deletePsyCouponTemplateById(Long id)
    {
        return psyCouponTemplateMapper.deletePsyCouponTemplateById(id);
    }

    @Override
    public void switchTemplateStatus(Long id) {
        psyCouponTemplateMapper.switchTemplateStatus(id);
    }

}
