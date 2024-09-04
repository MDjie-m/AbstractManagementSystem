package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.regions.SysCities;
import com.ruoyi.system.domain.regions.SysCountries;
import com.ruoyi.system.domain.regions.SysRegions;
import com.ruoyi.system.domain.regions.SysStates;
import com.ruoyi.system.mapper.SysRegionMapper;
import com.ruoyi.system.service.ISysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRegionServiceImpl implements ISysRegionService {
    @Autowired
    private SysRegionMapper sysRegionMapper;

    /**
     * 查询所有国家
     * @return 国家
     */
    @Override
    public List<SysCountries> selectAllCountries(SysCountries sysCountries) {
        return sysRegionMapper.selectAllCountries(sysCountries);
    }

    /**
     * 查询省份(州)
     * @param sysStates 国家id
     * @return 省份(州)
     */
    @Override
    public List<SysStates> selectAllStates(SysStates sysStates) {
        return sysRegionMapper.selectAllStates(sysStates);
    }

    /**
     * 查询区(市)
     * @param sysCities 省份(州)id
     * @return 区(市)
     */
    @Override
    public List<SysCities> selectAllCities(SysCities sysCities) {
        return sysRegionMapper.selectAllCities(sysCities);
    }

    /**
     * 查询县
     * @param sysRegions 区(市)id
     * @return 县
     */
    @Override
    public List<SysRegions> selectAllRegions(SysRegions sysRegions) {
        return sysRegionMapper.selectAllRegions(sysRegions);
    }
}
