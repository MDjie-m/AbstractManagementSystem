package com.tianyi.web.controller.sim;

import java.util.List;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tianyi.common.core.controller.BaseController;

import com.tianyi.sim.domain.SimNetStopList;
import com.tianyi.sim.service.ISimNetStopListService;

import com.tianyi.common.core.page.TableDataInfo;

/**
 * 断网清单Controller
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Api(tags = "SIM卡管理")
@RestController
@RequestMapping("/sim/simNetStopList")
public class SimNetStopListController extends BaseController
{
    @Autowired
    private ISimNetStopListService simNetStopListService;

    /**
     * 查询断网清单列表
     */
    @ApiOperation("断网清单查询")
    @PreAuthorize("@ss.hasPermi('sim:simNetStopList:list')")
    @GetMapping("/list")
    public TableDataInfo list(SimEntity simEntity)
    {
        startPage();
        SimNetStopList simNetStopList=new SimNetStopList();
        BeanUtils.copyProperties(simEntity, simNetStopList);
        List<SimNetStopList> list = simNetStopListService.selectSimNetStopListList(simNetStopList);
        return getDataTable(list);
    }

}
