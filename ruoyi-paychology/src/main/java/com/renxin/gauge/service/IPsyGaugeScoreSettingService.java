package com.renxin.gauge.service;

import java.util.List;
import com.renxin.gauge.domain.PsyGaugeScoreSetting;

/**
 * 心理测评普通设置Service接口
 * 
 * @author renxin
 * @date 2022-09-10
 */
public interface IPsyGaugeScoreSettingService 
{
    /**
     * 查询心理测评普通设置
     * 
     * @param id 心理测评普通设置主键
     * @return 心理测评普通设置
     */
    public PsyGaugeScoreSetting selectPsyGaugeScoreSettingById(Long id);

    /**
     * 查询心理测评普通设置列表
     * 
     * @param psyGaugeScoreSetting 心理测评普通设置
     * @return 心理测评普通设置集合
     */
    public List<PsyGaugeScoreSetting> selectPsyGaugeScoreSettingList(PsyGaugeScoreSetting psyGaugeScoreSetting);


    public List<PsyGaugeScoreSetting> selectPsyGaugeScoreSettingListByIds(Long[] ids);


    /**
     * 新增心理测评普通设置
     * 
     * @param psyGaugeScoreSetting 心理测评普通设置
     * @return 结果
     */
    public PsyGaugeScoreSetting insertPsyGaugeScoreSetting(PsyGaugeScoreSetting psyGaugeScoreSetting);

    /**
     * 修改心理测评普通设置
     * 
     * @param psyGaugeScoreSetting 心理测评普通设置
     * @return 结果
     */
    public int updatePsyGaugeScoreSetting(PsyGaugeScoreSetting psyGaugeScoreSetting);

    /**
     * 批量删除心理测评普通设置
     * 
     * @param ids 需要删除的心理测评普通设置主键集合
     * @return 结果
     */
    public int deletePsyGaugeScoreSettingByIds(Long[] ids);

    /**
     * 删除心理测评普通设置信息
     * 
     * @param id 心理测评普通设置主键
     * @return 结果
     */
    public int deletePsyGaugeScoreSettingById(Long id);
}
