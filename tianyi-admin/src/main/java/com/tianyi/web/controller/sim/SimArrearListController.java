package com.tianyi.web.controller.sim;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.tianyi.sim.domain.SimShutdownList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tianyi.common.annotation.Log;
import com.tianyi.common.core.controller.BaseController;
import com.tianyi.common.core.domain.AjaxResult;
import com.tianyi.common.enums.BusinessType;
import com.tianyi.sim.domain.SimArrearList;
import com.tianyi.sim.service.ISimArrearListService;
import com.tianyi.common.utils.poi.ExcelUtil;
import com.tianyi.common.core.page.TableDataInfo;

/**
 * 欠费停机预警Controller
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Api(tags = "SIM卡管理")
@RestController
@RequestMapping("/sim/arrear")
public class SimArrearListController extends BaseController
{
    @Autowired
    private ISimArrearListService simArrearListService;

    /**
     * 查询欠费停机预警列表
     */
    @ApiOperation("欠费预警清单港华")
    @PreAuthorize("@ss.hasPermi('sim:arrear:list')")
    @GetMapping("/list")
    public TableDataInfo list(SimEntity simEntity)
    {
        startPage();
        SimArrearList simArrearList = new SimArrearList();
        BeanUtils.copyProperties(simEntity, simArrearList);
        List<SimArrearList> list = simArrearListService.selectSimArrearListList(simArrearList);
        return getDataTable(list);
    }

    /**
     * 导出欠费停机预警列表
     */
    @PreAuthorize("@ss.hasPermi('sim:arrear:export')")
    @Log(title = "欠费停机预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SimArrearList simArrearList)
    {
        List<SimArrearList> list = simArrearListService.selectSimArrearListList(simArrearList);
        ExcelUtil<SimArrearList> util = new ExcelUtil<SimArrearList>(SimArrearList.class);
        util.exportExcel(response, list, "欠费停机预警数据");
    }

    /**
     * 获取欠费停机预警详细信息
     */
    @PreAuthorize("@ss.hasPermi('sim:arrear:query')")
    @GetMapping(value = "/{provId}")
    public AjaxResult getInfo(@PathVariable("provId") Long provId)
    {
        return success(simArrearListService.selectSimArrearListByProvId(provId));
    }
}
