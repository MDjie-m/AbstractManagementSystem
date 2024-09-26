package com.renxin.psychology.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyConsultantAccountRecord;
import com.renxin.psychology.mapper.PsyConsultantAccountRecordMapper;
import com.renxin.psychology.service.IPsyConsultantAccountRecordService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户明细流水Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-20
 */
@Service
public class PsyConsultantAccountRecordServiceImpl implements IPsyConsultantAccountRecordService
{
    @Autowired
    private PsyConsultantAccountRecordMapper psyConsultantAccountRecordMapper;

    /**
     * 查询账户明细流水
     * 
     * @param recordId 账户明细流水主键
     * @return 账户明细流水
     */
    @Override
    public PsyConsultantAccountRecord selectPsyConsultantAccountRecordByRecordId(Long recordId)
    {
        return psyConsultantAccountRecordMapper.selectPsyConsultantAccountRecordByRecordId(recordId);
    }

    /**
     * 查询账户明细流水列表
     * 
     * @param psyConsultantAccountRecord 账户明细流水
     * @return 账户明细流水
     */
    @Override
    public List<PsyConsultantAccountRecord> selectPsyConsultantAccountRecordList(PsyConsultantAccountRecord psyConsultantAccountRecord)
    {
        List<PsyConsultantAccountRecord> recordList = psyConsultantAccountRecordMapper.selectPsyConsultantAccountRecordList(psyConsultantAccountRecord);
        for (PsyConsultantAccountRecord record : recordList) {
            if (ObjectUtils.isEmpty(record.getServerTitle())){
                record.setServerTitle(transforScheduleType(record.getScheduleType()));
                if (ObjectUtils.isEmpty(record.getServeName())){
                    record.setServeName(record.getServerTitle());
                }
            }
        }
        return recordList;
    }
    
    //翻译任务类型名
    private String transforScheduleType(String scheduleType){
        if (ObjectUtils.isNotEmpty(scheduleType)){
            switch (scheduleType){
                case "12":
                    return "咨询";
                case "21":
                    return "团队督导";
                case "22":
                    return "个人督导";
                case "23":
                    return "个人体验";
            }
        }
        return "";
    }

    //统计支取清单
    @Override
    public List<PsyConsultantAccountRecord> calcDrawList(PsyConsultantAccountRecord req){
        List<PsyConsultantAccountRecord> drawList = psyConsultantAccountRecordMapper.calcDrawList(req);
        String createTimeEnd = req.getCreateTimeEnd();
        String year = createTimeEnd.substring(0,4);
        String month = createTimeEnd.substring(5,7);
        drawList.forEach(p -> p.setRemark(year + "年" + month + "月咨询费"));
        
        return drawList;
    }

    /**
     * 新增账户明细流水
     * 
     * @param psyConsultantAccountRecord 账户明细流水
     * @return 结果
     */
    @Override
    public int insertPsyConsultantAccountRecord(PsyConsultantAccountRecord req)
    {
        if ("1".equals(req.getPayType())){//提现申请
            //计算账户余额
            BigDecimal acctAmount = calcAcctAmount(req.getConsultantId());
            if (acctAmount.compareTo(req.getPayAmount()) < 0){
                throw new ServiceException("余额不足");
            }
        }
        
        req.setCreateTime(DateUtils.getNowDate());
        req.setUseTime(DateUtils.getNowDate());
        return psyConsultantAccountRecordMapper.insertPsyConsultantAccountRecord(req);
    }
    
    //计算咨询师账户余额
    @Override
    public BigDecimal calcAcctAmount(Long consultantId){
        PsyConsultantAccountRecord queryUserReq = new PsyConsultantAccountRecord();
            queryUserReq.setConsultantId(consultantId);
        List<PsyConsultantAccountRecord> recordList = psyConsultantAccountRecordMapper.selectPsyConsultantAccountRecordList(queryUserReq);
        
        BigDecimal amount = BigDecimal.ZERO;
        for (PsyConsultantAccountRecord record : recordList) {
            if ("0".equals(record.getPayType())){//分成记录
               amount = amount.add(record.getPayAmount());
            }else if ("1".equals(record.getPayType())) {//提现记录
               amount = amount.subtract(record.getPayAmount());
            }
        }
        return amount;
    }

    /**
     * 新增账户明细流水 - 批量
     */
     @Override
    public int insertPsyConsultantAccountRecordBatch(List<PsyConsultantAccountRecord> list){
         return psyConsultantAccountRecordMapper.insertPsyConsultantAccountRecordBatch(list);
    }

    /**
     * 修改账户明细流水
     * 
     * @param psyConsultantAccountRecord 账户明细流水
     * @return 结果
     */
    @Override
    public int updatePsyConsultantAccountRecord(PsyConsultantAccountRecord psyConsultantAccountRecord)
    {
        psyConsultantAccountRecord.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantAccountRecordMapper.updatePsyConsultantAccountRecord(psyConsultantAccountRecord);
    }

    /**
     * 批量删除账户明细流水
     * 
     * @param recordIds 需要删除的账户明细流水主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantAccountRecordByRecordIds(Long[] recordIds)
    {
        return psyConsultantAccountRecordMapper.deletePsyConsultantAccountRecordByRecordIds(recordIds);
    }

    /**
     * 删除账户明细流水信息
     * 
     * @param recordId 账户明细流水主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantAccountRecordByRecordId(Long recordId)
    {
        return psyConsultantAccountRecordMapper.deletePsyConsultantAccountRecordByRecordId(recordId);
    }
}
