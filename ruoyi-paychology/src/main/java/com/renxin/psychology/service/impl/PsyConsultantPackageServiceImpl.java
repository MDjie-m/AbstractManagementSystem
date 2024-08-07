package com.renxin.psychology.service.impl;

import java.util.List;

import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.SecurityUtils;
import com.renxin.psychology.domain.PsyCouponTemplate;
import com.renxin.psychology.service.IPsyCouponTemplateService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantPackageMapper;
import com.renxin.psychology.domain.PsyConsultantPackage;
import com.renxin.psychology.service.IPsyConsultantPackageService;

/**
 * 咨询师成长套餐Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-26
 */
@Service
public class PsyConsultantPackageServiceImpl implements IPsyConsultantPackageService 
{
    @Autowired
    private PsyConsultantPackageMapper psyConsultantPackageMapper;
    
    @Autowired
    private IPsyCouponTemplateService couponTemplateService;

    /**
     * 查询咨询师成长套餐
     * 
     * @param packageId 咨询师成长套餐主键
     * @return 咨询师成长套餐
     */
    @Override
    public PsyConsultantPackage selectPsyConsultantPackageByPackageId(Long packageId)
    {
        return psyConsultantPackageMapper.selectPsyConsultantPackageByPackageId(packageId);
    }

    /**
     * 查询咨询师成长套餐列表
     * 
     * @param psyConsultantPackage 咨询师成长套餐
     * @return 咨询师成长套餐
     */
    @Override
    public List<PsyConsultantPackage> selectPsyConsultantPackageList(PsyConsultantPackage psyConsultantPackage)
    {
        return psyConsultantPackageMapper.selectPsyConsultantPackageList(psyConsultantPackage);
    }

    /**
     * 新增咨询师成长套餐
     * 
     * @param psyConsultantPackage 咨询师成长套餐
     * @return 结果
     */
    @Override
    public int insertPsyConsultantPackage(PsyConsultantPackage psyConsultantPackage)
    {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        psyConsultantPackage.setCreateBy(userId+"");
        psyConsultantPackage.setUpdateBy(userId+"");
        psyConsultantPackage.setCreateTime(DateUtils.getNowDate());
        psyConsultantPackage.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantPackageMapper.insertPsyConsultantPackage(psyConsultantPackage);
    }

    /**
     * 修改咨询师成长套餐
     * 
     * @param psyConsultantPackage 咨询师成长套餐
     * @return 结果
     */
    @Override
    public int updatePsyConsultantPackage(PsyConsultantPackage psyConsultantPackage)
    {
        psyConsultantPackage.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantPackageMapper.updatePsyConsultantPackage(psyConsultantPackage);
    }

    /**
     * 批量删除咨询师成长套餐
     * 
     * @param packageIds 需要删除的咨询师成长套餐主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantPackageByPackageIds(Long[] packageIds)
    {
        return psyConsultantPackageMapper.deletePsyConsultantPackageByPackageIds(packageIds);
    }

    /**
     * 删除咨询师成长套餐信息
     * 
     * @param packageId 咨询师成长套餐主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantPackageByPackageId(Long packageId)
    {
        return psyConsultantPackageMapper.deletePsyConsultantPackageByPackageId(packageId);
    }


    //校验套餐是否支持购买
    @Override
    public void checkConsultantPackageOrder(PsyConsultantPackage pack){
        Integer teamSupNum = pack.getTeamSupNum();//团督券数量
        Long teamSupCouponTemplateId = pack.getTeamSupCouponTemplateId();//团督券模版id
        Integer personSupNum = pack.getPersonSupNum();
        Long personSupCouponTemplateId = pack.getPersonSupCouponTemplateId();
        Integer personExpNum = pack.getPersonExpNum();
        Long personExpCouponTemplateId = pack.getPersonExpCouponTemplateId();
        Integer courseNum = pack.getCourseNum();
        Long courseCouponTemplateId = pack.getCourseCouponTemplateId();

        if (teamSupNum > 0 && ObjectUtils.isNotEmpty(teamSupCouponTemplateId)){
            PsyCouponTemplate template = couponTemplateService.selectPsyCouponTemplateById(teamSupCouponTemplateId);
            if (template.getTotalNum() - template.getUsedNum() < teamSupNum){
                throw new ServiceException("该套餐包含的团督券已达到发行上限, 无法继续发行.");
            }
        }

        if (personSupNum > 0 && ObjectUtils.isNotEmpty(personSupCouponTemplateId)){
            PsyCouponTemplate template = couponTemplateService.selectPsyCouponTemplateById(personSupCouponTemplateId);
            if (template.getTotalNum() - template.getUsedNum() < personSupNum){
                throw new ServiceException("该套餐包含的个督券已达到发行上限, 无法继续发行.");
            }
        }

        if (personExpNum > 0 && ObjectUtils.isNotEmpty(personExpCouponTemplateId)){
            PsyCouponTemplate template = couponTemplateService.selectPsyCouponTemplateById(personExpCouponTemplateId);
            if (template.getTotalNum() - template.getUsedNum() < personExpNum){
                throw new ServiceException("该套餐包含的体验券已达到发行上限, 无法继续发行.");
            }
        }

        if (courseNum > 0 && ObjectUtils.isNotEmpty(courseCouponTemplateId)){
            PsyCouponTemplate template = couponTemplateService.selectPsyCouponTemplateById(courseCouponTemplateId);
            if (template.getTotalNum() - template.getUsedNum() < courseNum){
                throw new ServiceException("该套餐包含的课程券已达到发行上限, 无法继续发行.");
            }
        }
        
    }
}
