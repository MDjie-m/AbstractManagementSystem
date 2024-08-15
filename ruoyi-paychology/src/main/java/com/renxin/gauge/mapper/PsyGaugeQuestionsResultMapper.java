package com.renxin.gauge.mapper;

import java.util.HashMap;
import java.util.List;

import com.renxin.common.core.domain.dto.GaugeCommitResultDTO;
import com.renxin.gauge.domain.PsyGaugeQuestionsResult;
import com.renxin.gauge.domain.PsyGaugeQuestionsResultAll;

/**
 * 心理测评问题结果Mapper接口
 * 
 * @author renxin
 * @date 2022-09-10
 */
public interface PsyGaugeQuestionsResultMapper 
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
    public int insertPsyGaugeQuestionsResult(PsyGaugeQuestionsResult psyGaugeQuestionsResult);

    /**
     * 修改心理测评问题结果
     * 
     * @param psyGaugeQuestionsResult 心理测评问题结果
     * @return 结果
     */
    public int updatePsyGaugeQuestionsResult(PsyGaugeQuestionsResult psyGaugeQuestionsResult);

    /**
     * 删除心理测评问题结果
     * 
     * @param id 心理测评问题结果主键
     * @return 结果
     */
    public int deletePsyGaugeQuestionsResultById(Long id);

    /**
     * 批量删除心理测评问题结果
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyGaugeQuestionsResultByIds(Long[] ids);

    int batchInsert(List<PsyGaugeQuestionsResult> list);

    int deleteResult(PsyGaugeQuestionsResult psyGaugeQuestionsResult);

    String getSimpleResultByScores(GaugeCommitResultDTO gaugeCommitResultDTO);

    void deleteAllResult(HashMap<String, Object> paramMap);

    int batchAllInsert(List<PsyGaugeQuestionsResultAll> list);

    List<PsyGaugeQuestionsResult> selectPsyGaugeQuestionsResult(HashMap<String, Object> paramMap);

    int getQuestionScore(HashMap<String, Object> paramMap);

    List<String> getQuestionLat(HashMap<String, Object> paramMap);
}

