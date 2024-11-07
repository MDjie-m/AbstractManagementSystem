package com.tianyi.sim.mapper;

import java.util.List;
import com.tianyi.sim.domain.SimNbList;

/**
 * NB异常清单Mapper接口
 * 
 * @author tianyi
 * @date 2024-11-06
 */
public interface SimNbListMapper 
{
    /**
     * 查询NB异常清单
     * 
     * @param provId NB异常清单主键
     * @return NB异常清单
     */
    public SimNbList selectSimNbListByProvId(Long provId);

    /**
     * 查询NB异常清单列表
     * 
     * @param simNbList NB异常清单
     * @return NB异常清单集合
     */
    public List<SimNbList> selectSimNbListList(SimNbList simNbList);

    /**
     * 新增NB异常清单
     * 
     * @param simNbList NB异常清单
     * @return 结果
     */
    public int insertSimNbList(SimNbList simNbList);

    /**
     * 修改NB异常清单
     * 
     * @param simNbList NB异常清单
     * @return 结果
     */
    public int updateSimNbList(SimNbList simNbList);

    /**
     * 删除NB异常清单
     * 
     * @param provId NB异常清单主键
     * @return 结果
     */
    public int deleteSimNbListByProvId(Long provId);

    /**
     * 批量删除NB异常清单
     * 
     * @param provIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSimNbListByProvIds(Long[] provIds);
}
