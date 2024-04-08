package com.baoli.sysmanage.service;

import java.util.List;
import com.baoli.sysmanage.domain.BlSysProvinces;

/**
 * 省份信息Service接口
 * 
 * @author niujs
 * @date 2024-04-07
 */
public interface IBlSysProvincesService 
{
    /**
     * 查询省份信息
     * 
     * @param id 省份信息主键
     * @return 省份信息
     */
    public BlSysProvinces selectBlSysProvincesById(Long id);

    /**
     * 查询省份信息列表
     * 
     * @param blSysProvinces 省份信息
     * @return 省份信息集合
     */
    public List<BlSysProvinces> selectBlSysProvincesList(BlSysProvinces blSysProvinces);

    /**
     * 新增省份信息
     * 
     * @param blSysProvinces 省份信息
     * @return 结果
     */
    public int insertBlSysProvinces(BlSysProvinces blSysProvinces);

    /**
     * 修改省份信息
     * 
     * @param blSysProvinces 省份信息
     * @return 结果
     */
    public int updateBlSysProvinces(BlSysProvinces blSysProvinces);

    /**
     * 批量删除省份信息
     * 
     * @param ids 需要删除的省份信息主键集合
     * @return 结果
     */
    public int deleteBlSysProvincesByIds(Long[] ids);

    /**
     * 删除省份信息信息
     * 
     * @param id 省份信息主键
     * @return 结果
     */
    public int deleteBlSysProvincesById(Long id);
}
