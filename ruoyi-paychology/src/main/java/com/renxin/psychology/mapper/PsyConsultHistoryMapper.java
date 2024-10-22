package com.renxin.psychology.mapper;

import com.renxin.psychology.domain.PsyConsultHistory;

import java.util.List;

/**
 * 心理咨询师-修改记录Mapper接口
 *
 * @author renxin
 * @date 2024-10-22
 */
public interface PsyConsultHistoryMapper
{
    /**
     * 查询心理咨询师-修改记录
     *
     * @param id 心理咨询师-修改记录主键
     * @return 心理咨询师-修改记录
     */
    public PsyConsultHistory selectPsyConsultHistoryById(Long id);

    /**
     * 查询心理咨询师-修改记录列表
     *
     * @param psyConsultHistory 心理咨询师-修改记录
     * @return 心理咨询师-修改记录集合
     */
    public List<PsyConsultHistory> selectPsyConsultHistoryList(PsyConsultHistory psyConsultHistory);

    /**
     * 新增心理咨询师-修改记录
     *
     * @param psyConsultHistory 心理咨询师-修改记录
     * @return 结果
     */
    public int insertPsyConsultHistory(PsyConsultHistory psyConsultHistory);

    /**
     * 修改心理咨询师-修改记录
     *
     * @param psyConsultHistory 心理咨询师-修改记录
     * @return 结果
     */
    public int updatePsyConsultHistory(PsyConsultHistory psyConsultHistory);

    /**
     * 删除心理咨询师-修改记录
     *
     * @param id 心理咨询师-修改记录主键
     * @return 结果
     */
    public int deletePsyConsultHistoryById(Long id);

    /**
     * 批量删除心理咨询师-修改记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultHistoryByIds(Long[] ids);
}
