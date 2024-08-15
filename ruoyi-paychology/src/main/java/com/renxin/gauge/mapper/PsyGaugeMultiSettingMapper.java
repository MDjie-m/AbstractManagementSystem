package com.renxin.gauge.mapper;

import java.util.List;
import com.renxin.gauge.domain.PsyGaugeMultiSetting;

/**
 * 心理测评多维设置Mapper接口
 * 
 * @author renxin
 * @date 2022-09-10
 */
public interface PsyGaugeMultiSettingMapper 
{
    /**
     * 查询心理测评多维设置
     * 
     * @param id 心理测评多维设置主键
     * @return 心理测评多维设置
     */
    public PsyGaugeMultiSetting selectPsyGaugeMultiSettingById(Long id);

    /**
     * 查询心理测评多维设置列表
     * 
     * @param psyGaugeMultiSetting 心理测评多维设置
     * @return 心理测评多维设置集合
     */
    public List<PsyGaugeMultiSetting> selectPsyGaugeMultiSettingList(PsyGaugeMultiSetting psyGaugeMultiSetting);

    /**
     * 新增心理测评多维设置
     * 
     * @param psyGaugeMultiSetting 心理测评多维设置
     * @return 结果
     */
    public int insertPsyGaugeMultiSetting(PsyGaugeMultiSetting psyGaugeMultiSetting);

    /**
     * 修改心理测评多维设置
     * 
     * @param psyGaugeMultiSetting 心理测评多维设置
     * @return 结果
     */
    public int updatePsyGaugeMultiSetting(PsyGaugeMultiSetting psyGaugeMultiSetting);

    /**
     * 删除心理测评多维设置
     * 
     * @param id 心理测评多维设置主键
     * @return 结果
     */
    public int deletePsyGaugeMultiSettingById(Long id);

    /**
     * 批量删除心理测评多维设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyGaugeMultiSettingByIds(Long[] ids);
}
