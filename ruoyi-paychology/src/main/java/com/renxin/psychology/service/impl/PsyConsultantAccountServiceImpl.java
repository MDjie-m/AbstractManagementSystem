package com.renxin.psychology.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyConsultantAccount;
import com.renxin.psychology.mapper.PsyConsultantAccountMapper;
import com.renxin.psychology.service.IPsyConsultantAccountService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-20
 */
@Service
public class PsyConsultantAccountServiceImpl implements IPsyConsultantAccountService
{
    @Autowired
    private PsyConsultantAccountMapper psyConsultantAccountMapper;

    @Override
    public void initAccount() {
        psyConsultantAccountMapper.initAccount();
    }

    /**
     * 查询账户
     * 
     * @param consultantId 账户主键
     * @return 账户
     */
    @Override
    public PsyConsultantAccount selectPsyConsultantAccountByConsultantId(Long consultantId)
    {
        return psyConsultantAccountMapper.selectPsyConsultantAccountByConsultantId(consultantId);
    }

    /**
     * 查询账户列表
     * 
     * @param psyConsultantAccount 账户
     * @return 账户
     */
    @Override
    public List<PsyConsultantAccount> selectPsyConsultantAccountList(PsyConsultantAccount psyConsultantAccount)
    {
        return psyConsultantAccountMapper.selectPsyConsultantAccountList(psyConsultantAccount);
    }

    /**
     * 新增账户
     * 
     * @param psyConsultantAccount 账户
     * @return 结果
     */
    @Override
    public int insertPsyConsultantAccount(PsyConsultantAccount psyConsultantAccount)
    {
        psyConsultantAccount.setCreateTime(DateUtils.getNowDate());
        return psyConsultantAccountMapper.insertPsyConsultantAccount(psyConsultantAccount);
    }

    /**
     * 修改账户
     * 
     * @param psyConsultantAccount 账户
     * @return 结果
     */
    @Override
    public int updatePsyConsultantAccount(PsyConsultantAccount psyConsultantAccount)
    {
        psyConsultantAccount.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantAccountMapper.updatePsyConsultantAccount(psyConsultantAccount);
    }

    /**
     * 批量删除账户
     * 
     * @param consultantIds 需要删除的账户主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantAccountByConsultantIds(Long[] consultantIds)
    {
        return psyConsultantAccountMapper.deletePsyConsultantAccountByConsultantIds(consultantIds);
    }

    /**
     * 删除账户信息
     * 
     * @param consultantId 账户主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantAccountByConsultantId(Long consultantId)
    {
        return psyConsultantAccountMapper.deletePsyConsultantAccountByConsultantId(consultantId);
    }

    /**
     * 创建账户
     */
    @Override
    public void createAccountIfNotExist(Long consultantId){
        PsyConsultantAccount account = psyConsultantAccountMapper.selectPsyConsultantAccountByConsultantId(consultantId);
        
        if (ObjectUtils.isEmpty(account)){
            PsyConsultantAccount newAccount = new PsyConsultantAccount();
            newAccount.setConsultantId(consultantId);
            newAccount.setAmount(BigDecimal.ZERO);
            psyConsultantAccountMapper.insertPsyConsultantAccount(newAccount);
        }
    }
}
