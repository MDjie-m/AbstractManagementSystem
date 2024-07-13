package com.ruoyi.companySys.service;

import java.util.List;
import com.ruoyi.companySys.domain.Company;

/**
 * 公司管理Service接口
 * 
 * @author GG
 * @date 2024-07-13
 */
public interface ICompanyService 
{
    /**
     * 查询公司管理
     * 
     * @param cId 公司管理主键
     * @return 公司管理
     */
    public Company selectCompanyByCId(Long cId);

    /**
     * 查询公司管理列表
     * 
     * @param company 公司管理
     * @return 公司管理集合
     */
    public List<Company> selectCompanyList(Company company);

    /**
     * 新增公司管理
     * 
     * @param company 公司管理
     * @return 结果
     */
    public int insertCompany(Company company);

    /**
     * 修改公司管理
     * 
     * @param company 公司管理
     * @return 结果
     */
    public int updateCompany(Company company);

    /**
     * 批量删除公司管理
     * 
     * @param cIds 需要删除的公司管理主键集合
     * @return 结果
     */
    public int deleteCompanyByCIds(Long[] cIds);

    /**
     * 删除公司管理信息
     * 
     * @param cId 公司管理主键
     * @return 结果
     */
    public int deleteCompanyByCId(Long cId);
}
