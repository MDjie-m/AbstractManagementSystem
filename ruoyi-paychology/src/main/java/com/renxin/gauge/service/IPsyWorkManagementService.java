package com.renxin.gauge.service;

import com.renxin.gauge.domain.PsyWorkManagement;

import java.util.List;

/**
 * 排班管理Service接口
 * 
 * @author renxin
 * @date 2023-01-30
 */
public interface IPsyWorkManagementService 
{
    /**
     * 查询排班管理
     * 
     * @param id 排班管理主键
     * @return 排班管理
     */
    public PsyWorkManagement selectPsyWorkManagementById(Long id);

    /**
     * 查询排班管理列表
     * 
     * @param psyWorkManagement 排班管理
     * @return 排班管理集合
     */
    public List<PsyWorkManagement> selectPsyWorkManagementList(PsyWorkManagement psyWorkManagement);

    /**
     * 新增排班管理
     * 
     * @param psyWorkManagement 排班管理
     * @return 结果
     */
    public int insertPsyWorkManagement(PsyWorkManagement psyWorkManagement);

    /**
     * 修改排班管理
     * 
     * @param psyWorkManagement 排班管理
     * @return 结果
     */
    public int updatePsyWorkManagement(PsyWorkManagement psyWorkManagement);

    /**
     * 批量删除排班管理
     * 
     * @param ids 需要删除的排班管理主键集合
     * @return 结果
     */
    public int deletePsyWorkManagementByIds(Long[] ids);

    /**
     * 删除排班管理信息
     * 
     * @param id 排班管理主键
     * @return 结果
     */
    public int deletePsyWorkManagementById(Long id);
}
