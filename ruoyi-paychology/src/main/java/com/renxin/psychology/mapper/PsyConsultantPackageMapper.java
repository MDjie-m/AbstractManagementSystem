package com.renxin.psychology.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.course.domain.CourCourse;
import com.renxin.psychology.domain.PsyConsultantPackage;

/**
 * 咨询师成长套餐Mapper接口
 * 
 * @author renxin
 * @date 2024-06-26
 */
public interface PsyConsultantPackageMapper extends BaseMapper<PsyConsultantPackage>
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
     * 删除咨询师成长套餐
     * 
     * @param packageId 咨询师成长套餐主键
     * @return 结果
     */
    public int deletePsyConsultantPackageByPackageId(Long packageId);

    /**
     * 批量删除咨询师成长套餐
     * 
     * @param packageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultantPackageByPackageIds(Long[] packageIds);
}
