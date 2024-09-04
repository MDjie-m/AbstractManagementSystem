package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.regions.SysCities;
import com.ruoyi.system.domain.regions.SysCountries;
import com.ruoyi.system.domain.regions.SysRegions;
import com.ruoyi.system.domain.regions.SysStates;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRegionMapper {
    /**
     * 查询所有国家
     * @return 国家
     */
    public List<SysCountries> selectAllCountries(SysCountries sysCountries);

    /**
     * 根据国家id查询省份(州)信息
     * @param id 国家id
     * @return 省份(州)
     */
    public List<SysStates> selectAllStates(@Param("id") Integer id);

    /**
     * 根据省份(州)id查询区(市)信息
     * @param id 省份(州)id
     * @return 区(市)
     */
    public List<SysCities> selectAllCities(@Param("id") Integer id);

    /**
     * 根据区(市)id查询县id
     * @param id 区(市)id
     * @return 县
     */
    public List<SysRegions> selectAllRegions(@Param("id") Integer id);
}
