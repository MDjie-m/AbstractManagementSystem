package com.tianyi.sim.mapper;

import java.util.List;
import com.tianyi.sim.domain.SimNetStopList;

/**
 * 断网清单Mapper接口
 * 
 * @author tianyi
 * @date 2024-11-06
 */
public interface SimNetStopListMapper 
{
    /**
     * 查询断网清单
     * 
     * @param provId 断网清单主键
     * @return 断网清单
     */
    public SimNetStopList selectSimNetStopListByProvId(Long provId);

    /**
     * 查询断网清单列表
     * 
     * @param simNetStopList 断网清单
     * @return 断网清单集合
     */
    public List<SimNetStopList> selectSimNetStopListList(SimNetStopList simNetStopList);

    /**
     * 新增断网清单
     * 
     * @param simNetStopList 断网清单
     * @return 结果
     */
    public int insertSimNetStopList(SimNetStopList simNetStopList);

    /**
     * 修改断网清单
     * 
     * @param simNetStopList 断网清单
     * @return 结果
     */
    public int updateSimNetStopList(SimNetStopList simNetStopList);

    /**
     * 删除断网清单
     * 
     * @param provId 断网清单主键
     * @return 结果
     */
    public int deleteSimNetStopListByProvId(Long provId);

    /**
     * 批量删除断网清单
     * 
     * @param provIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSimNetStopListByProvIds(Long[] provIds);
}
