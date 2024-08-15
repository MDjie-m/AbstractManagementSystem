package com.renxin.gauge.service;

import java.util.List;
import com.renxin.gauge.domain.PsyGaugeClass;

/**
 * 测评分类Service接口
 * 
 * @author renxin
 * @date 2022-08-30
 */
public interface IPsyGaugeClassService 
{
    /**
     * 查询测评分类
     * 
     * @param id 测评分类主键
     * @return 测评分类
     */
    public PsyGaugeClass selectPsyGaugeClassById(Long id);

    /**
     * 查询测评分类列表
     * 
     * @param psyGaugeClass 测评分类
     * @return 测评分类集合
     */
    public List<PsyGaugeClass> selectPsyGaugeClassList(PsyGaugeClass psyGaugeClass);

    /**
     * 新增测评分类
     * 
     * @param psyGaugeClass 测评分类
     * @return 结果
     */
    public int insertPsyGaugeClass(PsyGaugeClass psyGaugeClass);

    /**
     * 修改测评分类
     * 
     * @param psyGaugeClass 测评分类
     * @return 结果
     */
    public int updatePsyGaugeClass(PsyGaugeClass psyGaugeClass);

    /**
     * 批量删除测评分类
     * 
     * @param ids 需要删除的测评分类主键集合
     * @return 结果
     */
    public int deletePsyGaugeClassByIds(Long[] ids);

    /**
     * 删除测评分类信息
     * 
     * @param id 测评分类主键
     * @return 结果
     */
    public int deletePsyGaugeClassById(Long id);
}
