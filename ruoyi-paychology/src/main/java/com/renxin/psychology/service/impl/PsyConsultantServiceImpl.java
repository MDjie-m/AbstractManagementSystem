package com.renxin.psychology.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantMapper;
import com.renxin.psychology.domain.PsyConsultant;
import com.renxin.psychology.service.IPsyConsultantService;

/**
 * 心理咨询师Service业务层处理
 * 
 * @author renxin
 * @date 2022-08-26
 */
@Service
public class PsyConsultantServiceImpl implements IPsyConsultantService 
{
    @Autowired
    private PsyConsultantMapper psyConsultantMapper;

    /**
     * 查询心理咨询师
     * 
     * @param id 心理咨询师主键
     * @return 心理咨询师
     */
    @Override
    public PsyConsultant selectPsyConsultantById(Long id)
    {
        return psyConsultantMapper.selectPsyConsultantById(id);
    }

    /**
     * 查询心理咨询师列表
     * 
     * @param psyConsultant 心理咨询师
     * @return 心理咨询师
     */
    @Override
    public List<PsyConsultant> selectPsyConsultantList(PsyConsultant psyConsultant)
    {
        return psyConsultantMapper.selectPsyConsultantList(psyConsultant);
    }

    /**
     * 新增心理咨询师
     * 
     * @param psyConsultant 心理咨询师
     * @return 结果
     */
    @Override
    public int insertPsyConsultant(PsyConsultant psyConsultant)
    {
        psyConsultant.setCreateTime(DateUtils.getNowDate());
        return psyConsultantMapper.insertPsyConsultant(psyConsultant);
    }

    /**
     * 修改心理咨询师
     * 
     * @param psyConsultant 心理咨询师
     * @return 结果
     */
    @Override
    public int updatePsyConsultant(PsyConsultant psyConsultant)
    {
        return psyConsultantMapper.updatePsyConsultant(psyConsultant);
    }

    /**
     * 批量删除心理咨询师
     * 
     * @param ids 需要删除的心理咨询师主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantByIds(Long[] ids)
    {
        return psyConsultantMapper.deletePsyConsultantByIds(ids);
    }

    /**
     * 删除心理咨询师信息
     * 
     * @param id 心理咨询师主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantById(Long id)
    {
        return psyConsultantMapper.deletePsyConsultantById(id);
    }
}
