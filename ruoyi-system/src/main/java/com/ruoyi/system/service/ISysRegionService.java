package com.ruoyi.system.service;

import com.ruoyi.system.domain.regions.SysCities;
import com.ruoyi.system.domain.regions.SysCountries;
import com.ruoyi.system.domain.regions.SysRegions;
import com.ruoyi.system.domain.regions.SysStates;

import java.util.List;

public interface ISysRegionService {
    /**
     * 查询所有国家
     * @return 国家
     */
    public List<SysCountries> selectAllCountries(SysCountries sysCountries);

    /**
     * 根据国家id查询省份(州)信息
     * @param sysStates 国家id
     * @return 省份(州)
     */
    public List<SysStates> selectAllStates(SysStates sysStates);

    /**
     * 根据省份(州)id查询区(市)信息
     * @param sysCities 省份(州)id
     * @return 区(市)
     */
    public List<SysCities> selectAllCities(SysCities sysCities);

    /**
     * 根据区(市)id查询县id
     * @param sysRegions 区(市)id
     * @return 县
     */
    public List<SysRegions> selectAllRegions(SysRegions sysRegions);
}
