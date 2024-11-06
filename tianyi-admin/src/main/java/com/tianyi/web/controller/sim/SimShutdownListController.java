package com.tianyi.web.controller.sim;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.tianyi.common.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.tianyi.sim.domain.SimShutdownList;
import com.tianyi.sim.service.ISimShutdownListService;
import com.tianyi.common.utils.poi.ExcelUtil;
import com.tianyi.common.core.page.TableDataInfo;

/**
 * 停机清单Controller
 * 
 * @author tianyi
 * @date 2024-11-05
 */
@Api(tags = "停机清单管理")
@RestController
@RequestMapping("/sim/list")
public class SimShutdownListController extends BaseController
{
    @Autowired
    private ISimShutdownListService simShutdownListService;

    /**
     * 查询停机清单列表
     */
    @ApiOperation("停机清单查询")
    @PreAuthorize("@ss.hasPermi('sim:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(SimShutdownList simShutdownList)
    {
        startPage();
        List<SimShutdownList> list = simShutdownListService.selectSimShutdownListList(simShutdownList);
        return getDataTable(list);
    }

    /**
     * 导出停机清单列表
     */
    @PreAuthorize("@ss.hasPermi('sim:list:export')")
    @Log(title = "停机清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SimShutdownList simShutdownList)
    {
        List<SimShutdownList> list = simShutdownListService.selectSimShutdownListList(simShutdownList);
        ExcelUtil<SimShutdownList> util = new ExcelUtil<SimShutdownList>(SimShutdownList.class);
        util.exportExcel(response, list, "停机清单数据");
    }

    /**
     * 获取停机清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('sim:list:query')")
    @GetMapping(value = "/{provId}")
    public AjaxResult getInfo(@PathVariable("provId") Long provId)
    {
        return success(simShutdownListService.selectSimShutdownListByProvId(provId));
    }

    /**
     * 新增停机清单
     */
    @PreAuthorize("@ss.hasPermi('sim:list:add')")
    @Log(title = "停机清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SimShutdownList simShutdownList)
    {
        return toAjax(simShutdownListService.insertSimShutdownList(simShutdownList));
    }

    /**
     * 修改停机清单
     */
    @PreAuthorize("@ss.hasPermi('sim:list:edit')")
    @Log(title = "停机清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SimShutdownList simShutdownList)
    {
        return toAjax(simShutdownListService.updateSimShutdownList(simShutdownList));
    }

    /**
     * 删除停机清单
     */
    @PreAuthorize("@ss.hasPermi('sim:list:remove')")
    @Log(title = "停机清单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{provIds}")
    public AjaxResult remove(@PathVariable Long[] provIds)
    {
        return toAjax(simShutdownListService.deleteSimShutdownListByProvIds(provIds));
    }
}
