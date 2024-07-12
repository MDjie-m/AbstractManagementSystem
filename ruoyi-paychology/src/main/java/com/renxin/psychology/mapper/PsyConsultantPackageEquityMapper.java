package com.renxin.psychology.mapper;

import com.renxin.psychology.domain.PsyConsultantPackageEquity;

import java.util.List;

/**
 * 咨询师套餐权益Mapper接口
 * 
 * @author renxin
 * @date 2024-07-10
 */
public interface PsyConsultantPackageEquityMapper 
{
    /**
     * 查询咨询师套餐权益
     * 
     * @param consultantId 咨询师套餐权益主键
     * @return 咨询师套餐权益
     */
    public PsyConsultantPackageEquity selectPsyConsultantPackageEquityByConsultantId(Long consultantId);

    /**
     * 查询咨询师套餐权益列表
     * 
     * @param psyConsultantPackageEquity 咨询师套餐权益
     * @return 咨询师套餐权益集合
     */
    public List<PsyConsultantPackageEquity> selectPsyConsultantPackageEquityList(PsyConsultantPackageEquity psyConsultantPackageEquity);

    /**
     * 新增咨询师套餐权益
     * 
     * @param psyConsultantPackageEquity 咨询师套餐权益
     * @return 结果
     */
    public int insertPsyConsultantPackageEquity(PsyConsultantPackageEquity psyConsultantPackageEquity);

    /**
     * 修改咨询师套餐权益
     * 
     * @param psyConsultantPackageEquity 咨询师套餐权益
     * @return 结果
     */
    public int updatePsyConsultantPackageEquity(PsyConsultantPackageEquity psyConsultantPackageEquity);

    /**
     * 删除咨询师套餐权益
     * 
     * @param consultantId 咨询师套餐权益主键
     * @return 结果
     */
    public int deletePsyConsultantPackageEquityByConsultantId(Long consultantId);

    /**
     * 批量删除咨询师套餐权益
     * 
     * @param consultantIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultantPackageEquityByConsultantIds(Long[] consultantIds);
}
