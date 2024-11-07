package com.tianyi.sim.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.tianyi.sim.domain.SimNetStopList;
import com.tianyi.sim.service.ISimNetStopListService;
import com.tianyi.common.utils.poi.ExcelUtil;
import com.tianyi.common.core.page.TableDataInfo;

/**
 * 断网清单Controller
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@RestController
@RequestMapping("/sim/simNetStopList")
public class SimNetStopListController extends BaseController
{
    @Autowired
    private ISimNetStopListService simNetStopListService;

    /**
     * 查询断网清单列表
     */
    @PreAuthorize("@ss.hasPermi('sim:simNetStopList:list')")
    @GetMapping("/list")
    public TableDataInfo list(SimNetStopList simNetStopList)
    {
        startPage();
        List<SimNetStopList> list = simNetStopListService.selectSimNetStopListList(simNetStopList);
        return getDataTable(list);
    }

    /**
     * 导出断网清单列表
     */
    @PreAuthorize("@ss.hasPermi('sim:simNetStopList:export')")
    @Log(title = "断网清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SimNetStopList simNetStopList)
    {
        List<SimNetStopList> list = simNetStopListService.selectSimNetStopListList(simNetStopList);
        ExcelUtil<SimNetStopList> util = new ExcelUtil<SimNetStopList>(SimNetStopList.class);
        util.exportExcel(response, list, "断网清单数据");
    }

    /**
     * 获取断网清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('sim:simNetStopList:query')")
    @GetMapping(value = "/{provId}")
    public AjaxResult getInfo(@PathVariable("provId") Long provId)
    {
        return success(simNetStopListService.selectSimNetStopListByProvId(provId));
    }

    /**
     * 新增断网清单
     */
    @PreAuthorize("@ss.hasPermi('sim:simNetStopList:add')")
    @Log(title = "断网清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SimNetStopList simNetStopList)
    {
        return toAjax(simNetStopListService.insertSimNetStopList(simNetStopList));
    }

    /**
     * 修改断网清单
     */
    @PreAuthorize("@ss.hasPermi('sim:simNetStopList:edit')")
    @Log(title = "断网清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SimNetStopList simNetStopList)
    {
        return toAjax(simNetStopListService.updateSimNetStopList(simNetStopList));
    }

    /**
     * 删除断网清单
     */
    @PreAuthorize("@ss.hasPermi('sim:simNetStopList:remove')")
    @Log(title = "断网清单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{provIds}")
    public AjaxResult remove(@PathVariable Long[] provIds)
    {
        return toAjax(simNetStopListService.deleteSimNetStopListByProvIds(provIds));
    }
}
