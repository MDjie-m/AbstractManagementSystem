package com.renxin.gauge.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.gauge.domain.PsyConsultantManagement;
import com.renxin.gauge.mapper.PsyConsultantManagementMapper;
import com.renxin.gauge.service.IPsyConsultantManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 咨询师管理Service业务层处理
 * 
 * @author yangchuang
 * @date 2023-01-29
 */
@Service
public class PsyConsultantManagementServiceImpl implements IPsyConsultantManagementService
{
    @Autowired
    private PsyConsultantManagementMapper psyConsultantManagementMapper;

    /**
     * 查询咨询师管理
     * 
     * @param id 咨询师管理主键
     * @return 咨询师管理
     */
    @Override
    public PsyConsultantManagement selectPsyConsultantManagementById(Long id)
    {
        return psyConsultantManagementMapper.selectPsyConsultantManagementById(id);
    }

    /**
     * 查询咨询师管理列表
     * 
     * @param psyConsultantManagement 咨询师管理
     * @return 咨询师管理
     */
    @Override
    public List<PsyConsultantManagement> selectPsyConsultantManagementList(PsyConsultantManagement psyConsultantManagement)
    {
        return psyConsultantManagementMapper.selectPsyConsultantManagementList(psyConsultantManagement);
    }

    /**
     * 新增咨询师管理
     * 
     * @param psyConsultantManagement 咨询师管理
     * @return 结果
     */
    @Override
    public int insertPsyConsultantManagement(PsyConsultantManagement psyConsultantManagement)
    {
        psyConsultantManagement.setCreateTime(DateUtils.getNowDate());
        return psyConsultantManagementMapper.insertPsyConsultantManagement(psyConsultantManagement);
    }

    /**
     * 修改咨询师管理
     * 
     * @param psyConsultantManagement 咨询师管理
     * @return 结果
     */
    @Override
    public int updatePsyConsultantManagement(PsyConsultantManagement psyConsultantManagement)
    {
        psyConsultantManagement.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantManagementMapper.updatePsyConsultantManagement(psyConsultantManagement);
    }

    /**
     * 批量删除咨询师管理
     * 
     * @param ids 需要删除的咨询师管理主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantManagementByIds(Long[] ids)
    {
        return psyConsultantManagementMapper.deletePsyConsultantManagementByIds(ids);
    }

    /**
     * 删除咨询师管理信息
     * 
     * @param id 咨询师管理主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantManagementById(Long id)
    {
        return psyConsultantManagementMapper.deletePsyConsultantManagementById(id);
    }
}
