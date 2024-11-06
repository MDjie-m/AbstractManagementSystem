package com.tianyi.sim.service;

import java.util.List;
import com.tianyi.sim.domain.SimNetStopList;

/**
 * 断网清单Service接口
 * 
 * @author tianyi
 * @date 2024-11-06
 */
public interface ISimNetStopListService 
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
     * 批量删除断网清单
     * 
     * @param provIds 需要删除的断网清单主键集合
     * @return 结果
     */
    public int deleteSimNetStopListByProvIds(Long[] provIds);

    /**
     * 删除断网清单信息
     * 
     * @param provId 断网清单主键
     * @return 结果
     */
    public int deleteSimNetStopListByProvId(Long provId);
}
