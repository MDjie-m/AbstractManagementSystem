package com.stdiet.custom.service;

import java.util.List;
import java.util.Map;

import com.stdiet.custom.domain.SysNutritionQuestion;

/**
 * 营养知识小问答Service接口
 *
 * @author xzj
 * @date 2021-04-13
 */
public interface ISysNutritionQuestionService
{
    /**
     * 查询营养知识小问答
     *
     * @param id 营养知识小问答ID
     * @return 营养知识小问答
     */
    public SysNutritionQuestion selectSysNutritionQuestionById(Long id);

    /**
     * 查询营养知识小问答列表
     *
     * @param sysNutritionQuestion 营养知识小问答
     * @return 营养知识小问答集合
     */
    public List<SysNutritionQuestion> selectSysNutritionQuestionList(SysNutritionQuestion sysNutritionQuestion);

    /**
     * 新增营养知识小问答
     *
     * @param sysNutritionQuestion 营养知识小问答
     * @return 结果
     */
    public int insertSysNutritionQuestion(SysNutritionQuestion sysNutritionQuestion);

    /**
     * 修改营养知识小问答
     *
     * @param sysNutritionQuestion 营养知识小问答
     * @return 结果
     */
    public int updateSysNutritionQuestion(SysNutritionQuestion sysNutritionQuestion);

    /**
     * 批量删除营养知识小问答
     *
     * @param ids 需要删除的营养知识小问答ID
     * @return 结果
     */
    public int deleteSysNutritionQuestionByIds(Long[] ids);

    /**
     * 删除营养知识小问答信息
     *
     * @param id 营养知识小问答ID
     * @return 结果
     */
    public int deleteSysNutritionQuestionById(Long id);

    /**
     * 根据关键词搜索对应营养知识问答(Lucene索引分词查询)
     * @return
     */
    public Map<String, Object> getNutritionQuestionListByKey(SysNutritionQuestion sysNutritionQuestion, int pageNum, int pageSize);

}