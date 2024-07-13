package com.ruoyi.windSys.mapper;

import java.util.List;
import com.ruoyi.windSys.domain.WindTurbineInfo;
import com.ruoyi.windSys.domain.BladePart;

/**
 * 风机管理Mapper接口
 * 
 * @author GG
 * @date 2024-07-13
 */
public interface WindTurbineInfoMapper 
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
     * 删除风机管理
     * 
     * @param wId 风机管理主键
     * @return 结果
     */
    public int deleteWindTurbineInfoByWId(Long wId);

    /**
     * 批量删除风机管理
     * 
     * @param wIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWindTurbineInfoByWIds(Long[] wIds);

    /**
     * 批量删除叶片管理
     * 
     * @param wIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBladePartByTurbineCodes(Long[] wIds);
    
    /**
     * 批量新增叶片管理
     * 
     * @param bladePartList 叶片管理列表
     * @return 结果
     */
    public int batchBladePart(List<BladePart> bladePartList);
    

    /**
     * 通过风机管理主键删除叶片管理信息
     * 
     * @param wId 风机管理ID
     * @return 结果
     */
    public int deleteBladePartByTurbineCode(Long wId);
}
