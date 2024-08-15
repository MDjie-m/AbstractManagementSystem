package com.renxin.gauge.mapper;

import java.util.List;
import com.renxin.gauge.domain.PsyGauge;

/**
 * 心理测评Mapper接口
 * 
 * @author renxin
 * @date 2022-08-30
 */
public interface PsyGaugeMapper 
{
    /**
     * 查询心理测评
     * 
     * @param id 心理测评主键
     * @return 心理测评
     */
    public PsyGauge selectPsyGaugeById(Long id);

    /**
     * 查询心理测评列表
     * 
     * @param psyGauge 心理测评
     * @return 心理测评集合
     */
    public List<PsyGauge> selectPsyGaugeList(PsyGauge psyGauge);

    /**
     * 新增心理测评
     * 
     * @param psyGauge 心理测评
     * @return 结果
     */
    public int insertPsyGauge(PsyGauge psyGauge);

    /**
     * 修改心理测评
     * 
     * @param psyGauge 心理测评
     * @return 结果
     */
    public int updatePsyGauge(PsyGauge psyGauge);

    /**
     * 删除心理测评
     * 
     * @param id 心理测评主键
     * @return 结果
     */
    public int deletePsyGaugeById(Long id);

    /**
     * 批量删除心理测评
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyGaugeByIds(Long[] ids);
}
