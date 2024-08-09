package com.renxin.psychology.mapper;


import com.renxin.psychology.domain.PsyUserLabel;

import java.util.List;

/**
 * 用户标签Mapper接口
 * 
 * @author renxin
 * @date 2024-08-09
 */
public interface PsyUserLabelMapper 
{
    /**
     * 查询用户标签
     * 
     * @param id 用户标签主键
     * @return 用户标签
     */
    public PsyUserLabel selectPsyUserLabelById(Long id);

    /**
     * 查询用户标签列表
     * 
     * @param psyUserLabel 用户标签
     * @return 用户标签集合
     */
    public List<PsyUserLabel> selectPsyUserLabelList(PsyUserLabel psyUserLabel);

    /**
     * 新增用户标签
     * 
     * @param psyUserLabel 用户标签
     * @return 结果
     */
    public int insertPsyUserLabel(PsyUserLabel psyUserLabel);

    /**
     * 修改用户标签
     * 
     * @param psyUserLabel 用户标签
     * @return 结果
     */
    public int updatePsyUserLabel(PsyUserLabel psyUserLabel);

    /**
     * 删除用户标签
     * 
     * @param id 用户标签主键
     * @return 结果
     */
    public int deletePsyUserLabelById(Long id);

    /**
     * 批量删除用户标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyUserLabelByIds(Long[] ids);
}
