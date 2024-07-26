package com.ruoyi.windSys.service;

import java.util.List;
import com.ruoyi.windSys.domain.WindTurbineInfo;

/**
 * 风机管理Service接口
 * 
 * @author GG
 * @date 2024-07-13
 */
public interface IWindTurbineInfoService 
{
    /**
     * 查询风机管理
     * 
     * @param wId 风机管理主键
     * @return 风机管理
     */
    public WindTurbineInfo selectWindTurbineInfoByWId(Long wId);

    /**
     * 查询风机管理列表
     * 
     * @param windTurbineInfo 风机管理
     * @return 风机管理集合
     */
    public List<WindTurbineInfo> selectWindTurbineInfoList(WindTurbineInfo windTurbineInfo);

    /**
     * 新增风机管理
     * 
     * @param windTurbineInfo 风机管理
     * @return 结果
     */
    public int insertWindTurbineInfo(WindTurbineInfo windTurbineInfo);

    /**
     * 修改风机管理
     * 
     * @param windTurbineInfo 风机管理
     * @return 结果
     */
    public int updateWindTurbineInfo(WindTurbineInfo windTurbineInfo);

    /**
     * 批量删除风机管理
     * 
     * @param wIds 需要删除的风机管理主键集合
     * @return 结果
     */
    public int deleteWindTurbineInfoByWIds(Long[] wIds);

    /**
     * 删除风机管理信息
     * 
     * @param wId 风机管理主键
     * @return 结果
     */
    public int deleteWindTurbineInfoByWId(Long wId);
}
