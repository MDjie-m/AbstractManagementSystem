package com.renxin.psychology.service;

import com.renxin.psychology.domain.PsyConsultantAccountRecord;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户明细流水Service接口
 * 
 * @author renxin
 * @date 2024-06-20
 */
public interface IPsyConsultantAccountRecordService 
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
    
    //统计提现申请
    public List<PsyConsultantAccountRecord> calcDrawList(PsyConsultantAccountRecord psyConsultantAccountRecord);
    //标记提现完成
    public void drawFinish(PsyConsultantAccountRecord psyConsultantAccountRecord);

    /**
     * 新增账户明细流水
     * 
     * @param psyConsultantAccountRecord 账户明细流水
     * @return 结果
     */
    public int insertPsyConsultantAccountRecord(PsyConsultantAccountRecord psyConsultantAccountRecord);
    
    public int insertPsyConsultantAccountRecordBatch(List<PsyConsultantAccountRecord> list);
    
    //计算账户余额
    public BigDecimal calcAcctAmount(Long consultantId);

    /**
     * 修改账户明细流水
     * 
     * @param psyConsultantAccountRecord 账户明细流水
     * @return 结果
     */
    public int updatePsyConsultantAccountRecord(PsyConsultantAccountRecord psyConsultantAccountRecord);

    /**
     * 批量删除账户明细流水
     * 
     * @param recordIds 需要删除的账户明细流水主键集合
     * @return 结果
     */
    public int deletePsyConsultantAccountRecordByRecordIds(Long[] recordIds);

    /**
     * 删除账户明细流水信息
     * 
     * @param recordId 账户明细流水主键
     * @return 结果
     */
    public int deletePsyConsultantAccountRecordByRecordId(Long recordId);
}
