package com.renxin.psychology.mapper;

import com.renxin.psychology.domain.PsyConsultantAccountRecord;

import java.util.List;

/**
 * 账户明细流水Mapper接口
 * 
 * @author renxin
 * @date 2024-06-20
 */
public interface PsyConsultantAccountRecordMapper 
{
    /**
     * 查询账户明细流水
     * 
     * @param recordId 账户明细流水主键
     * @return 账户明细流水
     */
    public PsyConsultantAccountRecord selectPsyConsultantAccountRecordByRecordId(Long recordId);

    /**
     * 查询账户明细流水列表
     * 
     * @param psyConsultantAccountRecord 账户明细流水
     * @return 账户明细流水集合
     */
    public List<PsyConsultantAccountRecord> selectPsyConsultantAccountRecordList(PsyConsultantAccountRecord psyConsultantAccountRecord);

    //统计支取清单
    public List<PsyConsultantAccountRecord> calcDrawList(PsyConsultantAccountRecord psyConsultantAccountRecord);

    /**
     * 新增账户明细流水
     * 
     * @param psyConsultantAccountRecord 账户明细流水
     * @return 结果
     */
    public int insertPsyConsultantAccountRecord(PsyConsultantAccountRecord psyConsultantAccountRecord);

    int insertPsyConsultantAccountRecordBatch(List<PsyConsultantAccountRecord> list);

    /**
     * 修改账户明细流水
     * 
     * @param psyConsultantAccountRecord 账户明细流水
     * @return 结果
     */
    public int updatePsyConsultantAccountRecord(PsyConsultantAccountRecord psyConsultantAccountRecord);

    /**
     * 删除账户明细流水
     * 
     * @param recordId 账户明细流水主键
     * @return 结果
     */
    public int deletePsyConsultantAccountRecordByRecordId(Long recordId);

    /**
     * 批量删除账户明细流水
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultantAccountRecordByRecordIds(Long[] recordIds);
}
