package com.renxin.gauge.mapper;

import com.renxin.gauge.domain.PsyGauge;
import com.renxin.gauge.domain.PsyGaugeLabel;

import java.util.List;

/**
 * @User hogan
 * @Time 2022/10/17 17:17
 * @e-mail hkcugwh@163.com
 **/
public interface PsyGaugeLabelMapper {


    /**
     * 查询测评标签
     *
     * @param id 测评标签主键
     * @return 测评标签
     */
    public PsyGaugeLabel selectPsyGaugeLabelById(Long id);

    /**
     * 查询测评标签列表
     *
     * @param psyGaugeLabel 测评标签
     * @return 测评标签集合
     */
    public List<PsyGaugeLabel> selectPsyGaugeLabelList(PsyGaugeLabel psyGaugeLabel);

    /**
     * 新增测评标签
     *
     * @param psyGaugeLabel 测评标签
     * @return 结果
     */
    public int insertPsyGaugeLabel(PsyGaugeLabel psyGaugeLabel);

    /**
     * 修改测评标签
     *
     * @param psyGaugeLabel 测评标签
     * @return 结果
     */
    public int updatePsyGaugeLabel(PsyGaugeLabel psyGaugeLabel);

    /**
     * 删除测评标签
     *
     * @param id 测评标签主键
     * @return 结果
     */
    public int deletePsyGaugeLabelById(Long id);

    /**
     * 批量删除测评标签
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyGaugeLabelByIds(Long[] ids);

    /**
     * 通过标签查询对应量表数据
     * @param labelCode
     * @return
     */
    List<PsyGauge> queryGaugeByLabel(Integer labelCode);

    List<PsyGaugeLabel> queryLabelPage(PsyGaugeLabel psyGaugeLabel);

}
