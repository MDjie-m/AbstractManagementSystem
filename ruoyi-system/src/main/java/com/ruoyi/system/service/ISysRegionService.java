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
    public List<SysCountries> selectAllCountries();

    /**
     * 根据国家id查询省份(州)信息
     * @param id 国家id
     * @return 省份(州)
     */
    public List<SysStates> selectAllStates(Integer id);

    /**
     * 根据省份(州)id查询区(市)信息
     * @param id 省份(州)id
     * @return 区(市)
     */
    public List<SysCities> selectAllCities(Integer id);

    /**
     * 根据区(市)id查询县id
     * @param id 区(市)id
     * @return 县
     */
    public List<SysRegions> selectAllRegions(Integer id);
}
