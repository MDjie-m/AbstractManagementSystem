package com.renxin.gauge.service;

import java.util.List;

import com.renxin.common.core.domain.dto.GaugeCommitResultDTO;
import com.renxin.gauge.domain.PsyGaugeQuestionsResult;
import com.renxin.gauge.domain.PsyGaugeQuestionsResultAll;
import com.renxin.gauge.vo.GaugeReportVO;

/**
 * 心理测评问题结果Service接口
 * 
 * @author renxin
 * @date 2022-09-10
 */
public interface IPsyGaugeQuestionsResultService 
{
    /**
     * 查询心理测评问题结果
     * 
     * @param id 心理测评问题结果主键
     * @return 心理测评问题结果
     */
    public PsyGaugeQuestionsResult selectPsyGaugeQuestionsResultById(Long id);

    /**
     * 查询心理测评问题结果列表
     * 
     * @param psyGaugeQuestionsResult 心理测评问题结果
     * @return 心理测评问题结果集合
     */
    public List<PsyGaugeQuestionsResult> selectPsyGaugeQuestionsResultList(PsyGaugeQuestionsResult psyGaugeQuestionsResult);

    /**
     * 新增心理测评问题结果
     * 
     * @param psyGaugeQuestionsResult 心理测评问题结果
     * @return 结果
     */
    public int answer(PsyGaugeQuestionsResult psyGaugeQuestionsResult , Long userId);

    /**
     * 修改心理测评问题结果
     * 
     * @param psyGaugeQuestionsResult 心理测评问题结果
     * @return 结果
     */
    public int updatePsyGaugeQuestionsResult(PsyGaugeQuestionsResult psyGaugeQuestionsResult);

    /**
     * 批量删除心理测评问题结果
     * 
     * @param ids 需要删除的心理测评问题结果主键集合
     * @return 结果
     */
    public int deletePsyGaugeQuestionsResultByIds(Long[] ids);

    /**
     * 删除心理测评问题结果信息
     * 
     * @param id 心理测评问题结果主键
     * @return 结果
     */
    public int deletePsyGaugeQuestionsResultById(Long id);

    String commitResult(GaugeCommitResultDTO gaugeCommitResultDTO ,Long userId);

    int addList(List<PsyGaugeQuestionsResultAll> psyGaugeQuestionsResultAlls);

    GaugeReportVO getReport(String orderId);
}
