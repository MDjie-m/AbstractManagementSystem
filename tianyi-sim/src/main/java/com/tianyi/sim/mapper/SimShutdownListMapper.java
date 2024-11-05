package com.tianyi.sim.mapper;

import java.util.List;
import com.tianyi.sim.domain.SimShutdownList;

/**
 * 停机清单Mapper接口
 * 
 * @author tianyi
 * @date 2024-11-05
 */
public interface SimShutdownListMapper 
{
    /**
     * 查询停机清单
     * 
     * @param provId 停机清单主键
     * @return 停机清单
     */
    public SimShutdownList selectSimShutdownListByProvId(Long provId);

    /**
     * 查询停机清单列表
     * 
     * @param simShutdownList 停机清单
     * @return 停机清单集合
     */
    public List<SimShutdownList> selectSimShutdownListList(SimShutdownList simShutdownList);

    /**
     * 新增停机清单
     * 
     * @param simShutdownList 停机清单
     * @return 结果
     */
    public int insertSimShutdownList(SimShutdownList simShutdownList);

    /**
     * 修改停机清单
     * 
     * @param simShutdownList 停机清单
     * @return 结果
     */
    public int updateSimShutdownList(SimShutdownList simShutdownList);

    /**
     * 删除停机清单
     * 
     * @param provId 停机清单主键
     * @return 结果
     */
    public int deleteSimShutdownListByProvId(Long provId);

    /**
     * 批量删除停机清单
     * 
     * @param provIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSimShutdownListByProvIds(Long[] provIds);
}
