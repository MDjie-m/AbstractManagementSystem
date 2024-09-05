package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysTag;

/**
 * 产品标签Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public interface SysTagMapper 
{
    /**
     * 查询产品标签
     * 
     * @param id 产品标签主键
     * @return 产品标签
     */
    public SysTag selectSysTagById(Long id);

    /**
     * 查询产品标签列表
     * 
     * @param sysTag 产品标签
     * @return 产品标签集合
     */
    public List<SysTag> selectSysTagList(SysTag sysTag);

    /**
     * 新增产品标签
     * 
     * @param sysTag 产品标签
     * @return 结果
     */
    public int insertSysTag(SysTag sysTag);

    /**
     * 修改产品标签
     * 
     * @param sysTag 产品标签
     * @return 结果
     */
    public int updateSysTag(SysTag sysTag);

    /**
     * 删除产品标签
     * 
     * @param id 产品标签主键
     * @return 结果
     */
    public int deleteSysTagById(Long id);

    /**
     * 批量删除产品标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTagByIds(Long[] ids);
}
