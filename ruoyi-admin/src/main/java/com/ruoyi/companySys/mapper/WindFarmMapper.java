package com.ruoyi.companySys.mapper;

import java.util.List;
import com.ruoyi.companySys.domain.WindFarm;

/**
 * 风场管理Mapper接口
 * 
 * @author GG
 * @date 2024-07-13
 */
public interface WindFarmMapper 
{
    /**
     * 查询风场管理
     * 
     * @param wId 风场管理主键
     * @return 风场管理
     */
    public WindFarm selectWindFarmByWId(Long wId);

    /**
     * 查询风场管理列表
     * 
     * @param windFarm 风场管理
     * @return 风场管理集合
     */
    public List<WindFarm> selectWindFarmList(WindFarm windFarm);

    /**
     * 新增风场管理
     * 
     * @param windFarm 风场管理
     * @return 结果
     */
    public int insertWindFarm(WindFarm windFarm);

    /**
     * 修改风场管理
     * 
     * @param windFarm 风场管理
     * @return 结果
     */
    public int updateWindFarm(WindFarm windFarm);

    /**
     * 删除风场管理
     * 
     * @param wId 风场管理主键
     * @return 结果
     */
    public int deleteWindFarmByWId(Long wId);

    /**
     * 批量删除风场管理
     * 
     * @param wIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWindFarmByWIds(Long[] wIds);
}
