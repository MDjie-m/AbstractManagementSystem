package com.ruoyi.ve.mapper;

import java.util.List;
import com.ruoyi.ve.domain.THgzBuMittHgzinfo;

/**
 * 国家合格证Mapper接口
 * 
 * @author cherigo
 * @date 2024-04-22
 */
public interface THgzBuMittHgzinfoMapper 
{
    /**
     * 查询国家合格证
     * 
     * @param id 国家合格证主键
     * @return 国家合格证
     */
    public THgzBuMittHgzinfo selectTHgzBuMittHgzinfoById(Long id);

    /**
     * 查询国家合格证列表
     * 
     * @param tHgzBuMittHgzinfo 国家合格证
     * @return 国家合格证集合
     */
    public List<THgzBuMittHgzinfo> selectTHgzBuMittHgzinfoList(THgzBuMittHgzinfo tHgzBuMittHgzinfo);

    /**
     * 新增国家合格证
     * 
     * @param tHgzBuMittHgzinfo 国家合格证
     * @return 结果
     */
    public int insertTHgzBuMittHgzinfo(THgzBuMittHgzinfo tHgzBuMittHgzinfo);

    /**
     * 修改国家合格证
     * 
     * @param tHgzBuMittHgzinfo 国家合格证
     * @return 结果
     */
    public int updateTHgzBuMittHgzinfo(THgzBuMittHgzinfo tHgzBuMittHgzinfo);

    /**
     * 删除国家合格证
     * 
     * @param id 国家合格证主键
     * @return 结果
     */
    public int deleteTHgzBuMittHgzinfoById(Long id);

    /**
     * 批量删除国家合格证
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTHgzBuMittHgzinfoByIds(Long[] ids);
}
