package com.renxin.gauge.service.impl;

import com.renxin.common.utils.DateUtils;
import com.renxin.gauge.domain.PsyGaugeQuestions;
import com.renxin.gauge.domain.PsyGaugeQuestionsOptions;
import com.renxin.gauge.domain.PsyGaugeQuestionsResult;
import com.renxin.gauge.mapper.PsyGaugeQuestionsMapper;
import com.renxin.gauge.mapper.PsyGaugeQuestionsResultMapper;
import com.renxin.gauge.service.IPsyGaugeQuestionsService;
import com.renxin.gauge.vo.PsyQuestionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 心理测评问题Service业务层处理
 *
 * @author renxin
 * @date 2022-09-06
 */
@Service
public class PsyGaugeQuestionsServiceImpl implements IPsyGaugeQuestionsService {

    @Resource
    private PsyGaugeQuestionsResultMapper psyGaugeQuestionsResultMapper;

    @Resource
    private PsyGaugeQuestionsMapper psyGaugeQuestionsMapper;

    /**
     * 查询心理测评问题
     *
     * @param id 心理测评问题主键
     * @return 心理测评问题
     */
    @Override
    public PsyGaugeQuestions selectPsyGaugeQuestionsById(Long id) {
        return psyGaugeQuestionsMapper.selectPsyGaugeQuestionsById(id);
    }

    /**
     * 查询心理测评问题列表
     *
     * @param psyGaugeQuestions 心理测评问题
     * @return 心理测评问题
     */
    @Override
    public List<PsyGaugeQuestions> selectPsyGaugeQuestionsList(PsyGaugeQuestions psyGaugeQuestions) {
        return psyGaugeQuestionsMapper.selectPsyGaugeQuestionsList(psyGaugeQuestions);
    }

    /**
     * 新增心理测评问题
     *
     * @param psyGaugeQuestions 心理测评问题
     * @return 结果
     */
    @Override
    public int insertPsyGaugeQuestions(PsyGaugeQuestions psyGaugeQuestions) {
        psyGaugeQuestions.setCreateTime(DateUtils.getNowDate());
        return psyGaugeQuestionsMapper.insertPsyGaugeQuestions(psyGaugeQuestions);
    }

    /**
     * 修改心理测评问题
     *
     * @param psyGaugeQuestions 心理测评问题
     * @return 结果
     */
    @Override
    public int updatePsyGaugeQuestions(PsyGaugeQuestions psyGaugeQuestions) {
        psyGaugeQuestions.setUpdateTime(DateUtils.getNowDate());
        return psyGaugeQuestionsMapper.updatePsyGaugeQuestions(psyGaugeQuestions);
    }

    /**
     * 批量删除心理测评问题
     *
     * @param ids 需要删除的心理测评问题主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeQuestionsByIds(Long[] ids) {
        return psyGaugeQuestionsMapper.deletePsyGaugeQuestionsByIds(ids);
    }

    /**
     * 删除心理测评问题信息
     *
     * @param id 心理测评问题主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeQuestionsById(Long id) {
        return psyGaugeQuestionsMapper.deletePsyGaugeQuestionsById(id);
    }

    @Override
    public List<PsyQuestionVO> appQueryQuesList(PsyGaugeQuestions psyGaugeQuestions) {

        List<PsyQuestionVO> psyQuestionVOS = psyGaugeQuestionsMapper.appQueryQuesList(psyGaugeQuestions);
        psyQuestionVOS.forEach(item ->
            item.setAnswers(
                item.getOptions().stream().filter(PsyGaugeQuestionsOptions::isSelectedFlag)
                        .map(PsyGaugeQuestionsOptions::getId).collect(Collectors.toList())
            )
        );
        return psyQuestionVOS;
    }

    @Override
    public List<PsyQuestionVO> wrongs(Long orderId, Long gaugeId) {
        PsyGaugeQuestions query = new PsyGaugeQuestions();
        PsyGaugeQuestionsResult queryResult = new PsyGaugeQuestionsResult();
        query.setGaugeId(gaugeId);
        query.setOrderId(orderId);
        queryResult.setGaugeId(gaugeId);
        queryResult.setOrderId(orderId);
        queryResult.setScore(0);

        List<PsyQuestionVO> questionList = psyGaugeQuestionsMapper.appQueryQuesList(query);
        List<PsyGaugeQuestionsResult> results = psyGaugeQuestionsResultMapper.selectPsyGaugeQuestionsResultList(queryResult);
        List<Integer> ids = results.stream().filter(i -> i.getScore() == 0).map(PsyGaugeQuestionsResult::getQuestionsId).collect(Collectors.toList());

        questionList = questionList.stream().filter(item -> ids.contains(item.getId())).collect(Collectors.toList());
        questionList.forEach(item -> {
            item.setAnswerTitle(item.getOptions().stream().filter(PsyGaugeQuestionsOptions::isSelectedFlag)
                    .map(PsyGaugeQuestionsOptions::getName).collect(Collectors.toList()));
            item.setReferenceAnswerTitle(item.getOptions().stream().filter(a -> a.getValue() > 0)
                    .map(PsyGaugeQuestionsOptions::getName).collect(Collectors.toList()));
        });

        return questionList;
    }

}
