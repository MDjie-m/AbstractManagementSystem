package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.impl.SysRegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public AjaxResult getCountry()
    {
        return success(sysRegionService.selectAllCountries());
    }

    /**
     * 查询省份(州)
     * @param id 国家id
     * @return 省份(州)
     */
    @PostMapping("/state")
    @Anonymous
    public AjaxResult getState(@RequestParam Integer id)
    {
        return success(sysRegionService.selectAllStates(id));
    }

    /**
     * 查询区(市)
     * @param id 省份(州)id
     * @return 区(市)
     */
    @PostMapping("/city")
    @Anonymous
    public AjaxResult getCity(Integer id)
    {
        return success(sysRegionService.selectAllCities(id));
    }

    /**
     * 查询县
     * @param id 区(市)id
     * @return 县
     */
    @PostMapping("/region")
    @Anonymous
    public AjaxResult getRegion(Integer id)
    {
        return success(sysRegionService.selectAllRegions(id));
    }

}
