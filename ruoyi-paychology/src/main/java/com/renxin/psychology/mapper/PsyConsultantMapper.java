package com.renxin.psychology.mapper;

import java.util.List;
import com.renxin.psychology.domain.PsyConsultant;

/**
 * 心理咨询师Mapper接口
 * 
 * @author renxin
 * @date 2022-08-26
 */
public interface PsyConsultantMapper 
{
    /**
     * 查询心理咨询师
     * 
     * @param id 心理咨询师主键
     * @return 心理咨询师
     */
    public PsyConsultant selectPsyConsultantById(Long id);

    /**
     * 查询心理咨询师列表
     * 
     * @param psyConsultant 心理咨询师
     * @return 心理咨询师集合
     */
    public List<PsyConsultant> selectPsyConsultantList(PsyConsultant psyConsultant);

    /**
     * 新增心理咨询师
     * 
     * @param psyConsultant 心理咨询师
     * @return 结果
     */
    public int insertPsyConsultant(PsyConsultant psyConsultant);

    /**
     * 修改心理咨询师
     * 
     * @param psyConsultant 心理咨询师
     * @return 结果
     */
    public int updatePsyConsultant(PsyConsultant psyConsultant);

    /**
     * 删除心理咨询师
     * 
     * @param id 心理咨询师主键
     * @return 结果
     */
    public int deletePsyConsultantById(Long id);

    /**
     * 批量删除心理咨询师
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultantByIds(Long[] ids);
}
