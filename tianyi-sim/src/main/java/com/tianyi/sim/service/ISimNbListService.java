package com.tianyi.sim.service;

import java.util.List;
import com.tianyi.sim.domain.SimNbList;

/**
 * NB异常清单Service接口
 * 
 * @author tianyi
 * @date 2024-11-06
 */
public interface ISimNbListService 
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
     * 批量删除NB异常清单
     * 
     * @param provIds 需要删除的NB异常清单主键集合
     * @return 结果
     */
    public int deleteSimNbListByProvIds(Long[] provIds);

    /**
     * 删除NB异常清单信息
     * 
     * @param provId NB异常清单主键
     * @return 结果
     */
    public int deleteSimNbListByProvId(Long provId);
}
