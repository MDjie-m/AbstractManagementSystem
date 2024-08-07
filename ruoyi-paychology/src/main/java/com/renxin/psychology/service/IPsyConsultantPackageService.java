package com.renxin.psychology.service;

import java.util.List;
import com.renxin.psychology.domain.PsyConsultantPackage;

/**
 * 咨询师成长套餐Service接口
 * 
 * @author renxin
 * @date 2024-06-26
 */
public interface IPsyConsultantPackageService 
{
    /**
     * 查询咨询师成长套餐
     * 
     * @param packageId 咨询师成长套餐主键
     * @return 咨询师成长套餐
     */
    public PsyConsultantPackage selectPsyConsultantPackageByPackageId(Long packageId);

    /**
     * 查询咨询师成长套餐列表
     * 
     * @param psyConsultantPackage 咨询师成长套餐
     * @return 咨询师成长套餐集合
     */
    public List<PsyConsultantPackage> selectPsyConsultantPackageList(PsyConsultantPackage psyConsultantPackage);

    /**
     * 新增咨询师成长套餐
     * 
     * @param psyConsultantPackage 咨询师成长套餐
     * @return 结果
     */
    public int insertPsyConsultantPackage(PsyConsultantPackage psyConsultantPackage);

    /**
     * 修改咨询师成长套餐
     * 
     * @param psyConsultantPackage 咨询师成长套餐
     * @return 结果
     */
    public int updatePsyConsultantPackage(PsyConsultantPackage psyConsultantPackage);

    /**
     * 批量删除咨询师成长套餐
     * 
     * @param packageIds 需要删除的咨询师成长套餐主键集合
     * @return 结果
     */
    public int deletePsyConsultantPackageByPackageIds(Long[] packageIds);

    /**
     * 删除咨询师成长套餐信息
     * 
     * @param packageId 咨询师成长套餐主键
     * @return 结果
     */
    public int deletePsyConsultantPackageByPackageId(Long packageId);

    //校验套餐是否支持购买
    public void checkConsultantPackageOrder(PsyConsultantPackage psyConsultantPackage);
}
