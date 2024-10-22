package com.renxin.psychology.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyConsultHistory;
import com.renxin.psychology.mapper.PsyConsultHistoryMapper;
import com.renxin.psychology.service.IPsyConsultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 心理咨询师-修改记录Service业务层处理
 *
 * @author renxin
 * @date 2024-10-22
 */
@Service
public class PsyConsultHistoryServiceImpl implements IPsyConsultHistoryService
{
    @Autowired
    private PsyConsultHistoryMapper psyConsultHistoryMapper;

    /**
     * 查询心理咨询师-修改记录
     *
     * @param id 心理咨询师-修改记录主键
     * @return 心理咨询师-修改记录
     */
    @Override
    public PsyConsultHistory selectPsyConsultHistoryById(Long id)
    {
        return psyConsultHistoryMapper.selectPsyConsultHistoryById(id);
    }

    /**
     * 查询心理咨询师-修改记录列表
     *
     * @param psyConsultHistory 心理咨询师-修改记录
     * @return 心理咨询师-修改记录
     */
    @Override
    public List<PsyConsultHistory> selectPsyConsultHistoryList(PsyConsultHistory psyConsultHistory)
    {
        return psyConsultHistoryMapper.selectPsyConsultHistoryList(psyConsultHistory);
    }

    /**
     * 新增心理咨询师-修改记录
     *
     * @param psyConsultHistory 心理咨询师-修改记录
     * @return 结果
     */
    @Override
    public int insertPsyConsultHistory(PsyConsultHistory psyConsultHistory)
    {
        psyConsultHistory.setCreateTime(DateUtils.getNowDate());
        return psyConsultHistoryMapper.insertPsyConsultHistory(psyConsultHistory);
    }

    /**
     * 修改心理咨询师-修改记录
     *
     * @param psyConsultHistory 心理咨询师-修改记录
     * @return 结果
     */
    @Override
    public int updatePsyConsultHistory(PsyConsultHistory psyConsultHistory)
    {
        psyConsultHistory.setUpdateTime(DateUtils.getNowDate());
        return psyConsultHistoryMapper.updatePsyConsultHistory(psyConsultHistory);
    }

    /**
     * 批量删除心理咨询师-修改记录
     *
     * @param ids 需要删除的心理咨询师-修改记录主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultHistoryByIds(Long[] ids)
    {
        return psyConsultHistoryMapper.deletePsyConsultHistoryByIds(ids);
    }

    /**
     * 删除心理咨询师-修改记录信息
     *
     * @param id 心理咨询师-修改记录主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultHistoryById(Long id)
    {
        return psyConsultHistoryMapper.deletePsyConsultHistoryById(id);
    }
}
