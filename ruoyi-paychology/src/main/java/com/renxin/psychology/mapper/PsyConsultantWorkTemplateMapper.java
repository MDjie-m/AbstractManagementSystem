package com.renxin.psychology.mapper;


import com.renxin.psychology.domain.PsyConsultantWorkTemplate;

import java.util.List;

/**
 * 咨询师排程模版Mapper接口
 * 
 * @author renxin
 * @date 2024-08-13
 */
public interface PsyConsultantWorkTemplateMapper 
{
    /**
     * 查询咨询师排程模版
     * 
     * @param id 咨询师排程模版主键
     * @return 咨询师排程模版
     */
    public PsyConsultantWorkTemplate selectPsyConsultantWorkTemplateById(Long id);

    /**
     * 查询咨询师排程模版列表
     * 
     * @param psyConsultantWorkTemplate 咨询师排程模版
     * @return 咨询师排程模版集合
     */
    public List<PsyConsultantWorkTemplate> selectPsyConsultantWorkTemplateList(PsyConsultantWorkTemplate psyConsultantWorkTemplate);

    /**
     * 新增咨询师排程模版
     * 
     * @param psyConsultantWorkTemplate 咨询师排程模版
     * @return 结果
     */
    public int insertPsyConsultantWorkTemplate(PsyConsultantWorkTemplate psyConsultantWorkTemplate);

    /**
     * 修改咨询师排程模版
     * 
     * @param psyConsultantWorkTemplate 咨询师排程模版
     * @return 结果
     */
    public int updatePsyConsultantWorkTemplate(PsyConsultantWorkTemplate psyConsultantWorkTemplate);

    /**
     * 删除咨询师排程模版
     * 
     * @param id 咨询师排程模版主键
     * @return 结果
     */
    public int deletePsyConsultantWorkTemplateById(Long id);

    /**
     * 批量删除咨询师排程模版
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultantWorkTemplateByIds(Long[] ids);
}
