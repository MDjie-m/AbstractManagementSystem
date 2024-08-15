package com.renxin.gauge.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.gauge.domain.PsyWorkManagement;
import com.renxin.gauge.mapper.PsyWorkManagementMapper;
import com.renxin.gauge.service.IPsyWorkManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 排班管理Service业务层处理
 * 
 * @author renxin
 * @date 2023-01-30
 */
@Service
public class PsyWorkManagementServiceImpl implements IPsyWorkManagementService
{
    @Autowired
    private PsyWorkManagementMapper psyWorkManagementMapper;

    /**
     * 查询排班管理
     * 
     * @param id 排班管理主键
     * @return 排班管理
     */
    @Override
    public PsyWorkManagement selectPsyWorkManagementById(Long id)
    {
        return psyWorkManagementMapper.selectPsyWorkManagementById(id);
    }

    /**
     * 查询排班管理列表
     * 
     * @param psyWorkManagement 排班管理
     * @return 排班管理
     */
    @Override
    public List<PsyWorkManagement> selectPsyWorkManagementList(PsyWorkManagement psyWorkManagement)
    {
        return psyWorkManagementMapper.selectPsyWorkManagementList(psyWorkManagement);
    }

    /**
     * 新增排班管理
     * 
     * @param psyWorkManagement 排班管理
     * @return 结果
     */
    @Override
    public int insertPsyWorkManagement(PsyWorkManagement psyWorkManagement)
    {
        psyWorkManagement.setCreateTime(DateUtils.getNowDate());
        return psyWorkManagementMapper.insertPsyWorkManagement(psyWorkManagement);
    }

    /**
     * 修改排班管理
     * 
     * @param psyWorkManagement 排班管理
     * @return 结果
     */
    @Override
    public int updatePsyWorkManagement(PsyWorkManagement psyWorkManagement)
    {
        psyWorkManagement.setUpdateTime(DateUtils.getNowDate());
        return psyWorkManagementMapper.updatePsyWorkManagement(psyWorkManagement);
    }

    /**
     * 批量删除排班管理
     * 
     * @param ids 需要删除的排班管理主键
     * @return 结果
     */
    @Override
    public int deletePsyWorkManagementByIds(Long[] ids)
    {
        return psyWorkManagementMapper.deletePsyWorkManagementByIds(ids);
    }

    /**
     * 删除排班管理信息
     * 
     * @param id 排班管理主键
     * @return 结果
     */
    @Override
    public int deletePsyWorkManagementById(Long id)
    {
        return psyWorkManagementMapper.deletePsyWorkManagementById(id);
    }
}
