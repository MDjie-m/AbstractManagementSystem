package com.renxin.gauge.mapper;

import java.util.List;
import com.renxin.gauge.domain.PsyGaugeQuestions;
import com.renxin.gauge.vo.PsyQuestionVO;

/**
 * 心理测评问题Mapper接口
 * 
 * @author renxin
 * @date 2022-09-06
 */
public interface PsyGaugeQuestionsMapper 
{
    /**
     * 查询心理测评问题
     * 
     * @param id 心理测评问题主键
     * @return 心理测评问题
     */
    public PsyGaugeQuestions selectPsyGaugeQuestionsById(Long id);

    /**
     * 查询心理测评问题列表
     * 
     * @param psyGaugeQuestions 心理测评问题
     * @return 心理测评问题集合
     */
    public List<PsyGaugeQuestions> selectPsyGaugeQuestionsList(PsyGaugeQuestions psyGaugeQuestions);

    /**
     * 新增心理测评问题
     * 
     * @param psyGaugeQuestions 心理测评问题
     * @return 结果
     */
    public int insertPsyGaugeQuestions(PsyGaugeQuestions psyGaugeQuestions);

    /**
     * 修改心理测评问题
     * 
     * @param psyGaugeQuestions 心理测评问题
     * @return 结果
     */
    public int updatePsyGaugeQuestions(PsyGaugeQuestions psyGaugeQuestions);

    /**
     * 删除心理测评问题
     * 
     * @param id 心理测评问题主键
     * @return 结果
     */
    public int deletePsyGaugeQuestionsById(Long id);

    /**
     * 批量删除心理测评问题
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyGaugeQuestionsByIds(Long[] ids);

    List<PsyQuestionVO> appQueryQuesList(PsyGaugeQuestions psyGaugeQuestions);

}
