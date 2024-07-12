package com.renxin.psychology.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.SecurityUtils;
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
}
