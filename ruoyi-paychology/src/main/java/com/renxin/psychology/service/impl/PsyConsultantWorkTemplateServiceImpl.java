package com.renxin.psychology.service.impl;


import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyConsultantWorkTemplate;
import com.renxin.psychology.mapper.PsyConsultantWorkTemplateMapper;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyConsultantWorkTemplateService;
import com.renxin.psychology.vo.PsyConsultVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 咨询师排程模版Service业务层处理
 * 
 * @author renxin
 * @date 2024-08-13
 */
@Service
public class PsyConsultantWorkTemplateServiceImpl implements IPsyConsultantWorkTemplateService 
{
    @Autowired
    private PsyConsultantWorkTemplateMapper psyConsultantWorkTemplateMapper;
    
    @Autowired
    private IPsyConsultService psyConsultService;

    /**
     * 查询咨询师排程模版
     * 
     * @param id 咨询师排程模版主键
     * @return 咨询师排程模版
     */
    @Override
    public PsyConsultantWorkTemplate selectPsyConsultantWorkTemplateById(Long id)
    {
        return psyConsultantWorkTemplateMapper.selectPsyConsultantWorkTemplateById(id);
    }

    /**
     * 查询咨询师排程模版列表
     * 
     * @param psyConsultantWorkTemplate 咨询师排程模版
     * @return 咨询师排程模版
     */
    @Override
    public List<PsyConsultantWorkTemplate> selectPsyConsultantWorkTemplateList(PsyConsultantWorkTemplate psyConsultantWorkTemplate)
    {
        return psyConsultantWorkTemplateMapper.selectPsyConsultantWorkTemplateList(psyConsultantWorkTemplate);
    }

    /**
     * 新增咨询师排程模版
     * 
     * @param psyConsultantWorkTemplate 咨询师排程模版
     * @return 结果
     */
    @Override
    public int insertPsyConsultantWorkTemplate(PsyConsultantWorkTemplate req)
    {
        req.setCreateTime(DateUtils.getNowDate());
        req.setCreateBy(ObjectUtils.isNotEmpty(req.getCreateBy()) ? req.getCreateBy() : req.getConsultantId()+"");
        PsyConsultVO consultantVO = psyConsultService.getOne(req.getConsultantId());
        req.setConsultantName(consultantVO.getNickName());
        
        return psyConsultantWorkTemplateMapper.insertPsyConsultantWorkTemplate(req);
    }

    /**
     * 修改咨询师排程模版
     * 
     * @param psyConsultantWorkTemplate 咨询师排程模版
     * @return 结果
     */
    @Override
    public int updatePsyConsultantWorkTemplate(PsyConsultantWorkTemplate req)
    {
        req.setUpdateTime(DateUtils.getNowDate());
        req.setUpdateBy(ObjectUtils.isNotEmpty(req.getUpdateBy()) ? req.getUpdateBy() : req.getConsultantId()+"");
        PsyConsultVO consultantVO = psyConsultService.getOne(req.getConsultantId());
        req.setConsultantName(consultantVO.getNickName());
        
        return psyConsultantWorkTemplateMapper.updatePsyConsultantWorkTemplate(req);
    }

    /**
     * 批量删除咨询师排程模版
     * 
     * @param ids 需要删除的咨询师排程模版主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantWorkTemplateByIds(Long[] ids)
    {
        return psyConsultantWorkTemplateMapper.deletePsyConsultantWorkTemplateByIds(ids);
    }

    /**
     * 删除咨询师排程模版信息
     * 
     * @param id 咨询师排程模版主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantWorkTemplateById(Long id)
    {
        return psyConsultantWorkTemplateMapper.deletePsyConsultantWorkTemplateById(id);
    }

    //执行指定模版, 生成排班数据
    @Override
    public void executeConsultantWorkTemplate(PsyConsultantWorkTemplate req){
        
    }
    
}
