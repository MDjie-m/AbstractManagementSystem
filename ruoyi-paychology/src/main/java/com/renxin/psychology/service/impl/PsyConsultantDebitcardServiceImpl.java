package com.renxin.psychology.service.impl;

import java.util.List;

import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyConsultantDebitcard;
import com.renxin.psychology.mapper.PsyConsultantDebitcardMapper;
import com.renxin.psychology.service.IPsyConsultantDebitcardService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户银行卡Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-20
 */
@Service
public class PsyConsultantDebitcardServiceImpl implements IPsyConsultantDebitcardService
{
    @Autowired
    private PsyConsultantDebitcardMapper psyConsultantDebitcardMapper;

    /**
     * 查询客户银行卡
     * 
     * @param cardNumber 客户银行卡主键
     * @return 客户银行卡
     */
    @Override
    public PsyConsultantDebitcard selectPsyConsultantDebitcardByCardNumber(String cardNumber)
    {
        return psyConsultantDebitcardMapper.selectPsyConsultantDebitcardByCardNumber(cardNumber);
    }

    /**
     * 查询客户银行卡列表
     * 
     * @param psyConsultantDebitcard 客户银行卡
     * @return 客户银行卡
     */
    @Override
    public List<PsyConsultantDebitcard> selectPsyConsultantDebitcardList(PsyConsultantDebitcard psyConsultantDebitcard)
    {
        return psyConsultantDebitcardMapper.selectPsyConsultantDebitcardList(psyConsultantDebitcard);
    }

    /**
     * 新增客户银行卡
     * 
     * @param req 客户银行卡
     * @return 结果
     */
    @Override
    public int insertPsyConsultantDebitcard(PsyConsultantDebitcard req)
    {
        req.setCreateTime(DateUtils.getNowDate());
        PsyConsultantDebitcard one = psyConsultantDebitcardMapper.selectPsyConsultantDebitcardByCardNumber(req.getCardNumber());
        if (ObjectUtils.isNotEmpty(one)){
            throw new ServiceException("该银行卡号已存在");
        }
        int i = psyConsultantDebitcardMapper.insertPsyConsultantDebitcard(req);

        //若用户只有一张卡, 则将该卡设为默认
        PsyConsultantDebitcard debitcardReq = new PsyConsultantDebitcard();
        debitcardReq.setConsultantId(req.getConsultantId());
        List<PsyConsultantDebitcard> debitcardList = psyConsultantDebitcardMapper.selectPsyConsultantDebitcardList(debitcardReq);
        if (debitcardList.size() == 1){
            PsyConsultantDebitcard debitcard = debitcardList.get(0);
            debitcard.setIsDefault(0);//默认
            psyConsultantDebitcardMapper.updatePsyConsultantDebitcard(debitcard);
        }
        //若不止一张卡, 且新增的这张卡为默认,  则将该用户的其他卡都置为"非默认"
        else if (0 == req.getIsDefault()){
         {
            for (PsyConsultantDebitcard debitcard : debitcardList) {
                if (!debitcard.getCardNumber().equals(req.getCardNumber())){
                    debitcard.setIsDefault(1);//非默认
                    psyConsultantDebitcardMapper.updatePsyConsultantDebitcard(debitcard);
                }
            }
         }
            
        }
        
        
        return i;
    }

    /**
     * 修改客户银行卡
     * @return 结果
     */
    @Override
    public int updatePsyConsultantDebitcard(PsyConsultantDebitcard req)
    {
        req.setUpdateTime(DateUtils.getNowDate());
        int i = psyConsultantDebitcardMapper.updatePsyConsultantDebitcard(req);
        
        //若设置默认卡, 则将该用户的其他卡都置为"非默认"
        if (0 == req.getIsDefault()){
            PsyConsultantDebitcard debitcardReq = new PsyConsultantDebitcard();
            debitcardReq.setConsultantId(req.getConsultantId());
            List<PsyConsultantDebitcard> debitcardList = psyConsultantDebitcardMapper.selectPsyConsultantDebitcardList(debitcardReq);
            for (PsyConsultantDebitcard debitcard : debitcardList) {
                if (!debitcard.getCardNumber().equals(req.getCardNumber())){
                    debitcard.setIsDefault(1);//非默认
                    psyConsultantDebitcardMapper.updatePsyConsultantDebitcard(debitcard);
                }
            }
        }
        
        return i;
    }

    /**
     * 批量删除客户银行卡
     * 
     * @param cardNumbers 需要删除的客户银行卡主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantDebitcardByCardNumbers(String[] cardNumbers)
    {
        return psyConsultantDebitcardMapper.deletePsyConsultantDebitcardByCardNumbers(cardNumbers);
    }

    /**
     * 删除客户银行卡信息
     * 
     * @param cardNumber 客户银行卡主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantDebitcardByCardNumber(String cardNumber)
    {
        return psyConsultantDebitcardMapper.deletePsyConsultantDebitcardByCardNumber(cardNumber);
    }
}
