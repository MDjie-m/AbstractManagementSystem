package com.ruoyi.companySys.mapper;

import java.util.List;
import com.ruoyi.companySys.domain.Company;
import com.ruoyi.companySys.domain.WindFarm;

/**
 * 公司管理Mapper接口
 * 
 * @author GG
 * @date 2024-07-13
 */
public interface CompanyMapper 
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
     * 删除公司管理
     * 
     * @param cId 公司管理主键
     * @return 结果
     */
    public int deleteCompanyByCId(Long cId);

    /**
     * 批量删除公司管理
     * 
     * @param cIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyByCIds(Long[] cIds);

    /**
     * 批量删除风场管理
     * 
     * @param cIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWindFarmByCompanyCodes(Long[] cIds);
    
    /**
     * 批量新增风场管理
     * 
     * @param windFarmList 风场管理列表
     * @return 结果
     */
    public int batchWindFarm(List<WindFarm> windFarmList);
    

    /**
     * 通过公司管理主键删除风场管理信息
     * 
     * @param cId 公司管理ID
     * @return 结果
     */
    public int deleteWindFarmByCompanyCode(Long cId);
}
