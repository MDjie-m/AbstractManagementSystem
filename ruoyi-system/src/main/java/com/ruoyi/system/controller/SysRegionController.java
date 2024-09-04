package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.regions.SysCities;
import com.ruoyi.system.domain.regions.SysCountries;
import com.ruoyi.system.domain.regions.SysRegions;
import com.ruoyi.system.domain.regions.SysStates;
import com.ruoyi.system.service.impl.SysRegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/area")
public class SysRegionController extends BaseController {
    @Autowired
    private SysRegionServiceImpl sysRegionService;

    /**
     * 查询国家
     * @return 国家
     */
    @PostMapping("/country")
    @Anonymous
    public AjaxResult getCountry(@RequestBody SysCountries sysCountries)
    {
        return success(sysRegionService.selectAllCountries(sysCountries));
    }

    /**
     * 查询省份(州)
     * @param sysStates 国家id
     * @return 省份(州)
     */
    @PostMapping("/state")
    @Anonymous
    public AjaxResult getState(@RequestBody SysStates sysStates)
    {
        return success(sysRegionService.selectAllStates(sysStates));
    }

    /**
     * 查询区(市)
     * @param sysCities 省份(州)id
     * @return 区(市)
     */
    @PostMapping("/city")
    @Anonymous
    public AjaxResult getCity(@RequestBody SysCities sysCities)
    {
        return success(sysRegionService.selectAllCities(sysCities));
    }

    /**
     * 查询县
     * @param sysRegions 区(市)id
     * @return 县
     */
    @PostMapping("/region")
    @Anonymous
    public AjaxResult getRegion(@RequestBody SysRegions sysRegions)
    {
        return success(sysRegionService.selectAllRegions(sysRegions));
    }

}
