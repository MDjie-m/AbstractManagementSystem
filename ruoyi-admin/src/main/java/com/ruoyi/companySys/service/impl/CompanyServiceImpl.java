package com.ruoyi.companySys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.companySys.domain.WindFarm;
import com.ruoyi.companySys.mapper.CompanyMapper;
import com.ruoyi.companySys.domain.Company;
import com.ruoyi.companySys.service.ICompanyService;

/**
 * 公司管理Service业务层处理
 * 
 * @author GG
 * @date 2024-07-13
 */
@Service
public class CompanyServiceImpl implements ICompanyService 
{
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 查询公司管理
     * 
     * @param cId 公司管理主键
     * @return 公司管理
     */
    @Override
    public Company selectCompanyByCId(Long cId)
    {
        return companyMapper.selectCompanyByCId(cId);
    }

    /**
     * 查询公司管理列表
     * 
     * @param company 公司管理
     * @return 公司管理
     */
    @Override
    public List<Company> selectCompanyList(Company company)
    {
        return companyMapper.selectCompanyList(company);
    }

    /**
     * 新增公司管理
     * 
     * @param company 公司管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCompany(Company company)
    {
        int rows = companyMapper.insertCompany(company);
        insertWindFarm(company);
        return rows;
    }

    /**
     * 修改公司管理
     * 
     * @param company 公司管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCompany(Company company)
    {
        companyMapper.deleteWindFarmByCompanyCode(company.getcId());
        insertWindFarm(company);
        return companyMapper.updateCompany(company);
    }

    /**
     * 批量删除公司管理
     * 
     * @param cIds 需要删除的公司管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCompanyByCIds(Long[] cIds)
    {
        companyMapper.deleteWindFarmByCompanyCodes(cIds);
        return companyMapper.deleteCompanyByCIds(cIds);
    }

    /**
     * 删除公司管理信息
     * 
     * @param cId 公司管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCompanyByCId(Long cId)
    {
        companyMapper.deleteWindFarmByCompanyCode(cId);
        return companyMapper.deleteCompanyByCId(cId);
    }

    /**
     * 新增风场管理信息
     * 
     * @param company 公司管理对象
     */
    public void insertWindFarm(Company company)
    {
        List<WindFarm> windFarmList = company.getWindFarmList();
        Long cId = company.getcId();
        if (StringUtils.isNotNull(windFarmList))
        {
            List<WindFarm> list = new ArrayList<WindFarm>();
            for (WindFarm windFarm : windFarmList)
            {
                windFarm.setCompanyCode(cId);
                list.add(windFarm);
            }
            if (list.size() > 0)
            {
                companyMapper.batchWindFarm(list);
            }
        }
    }
}
