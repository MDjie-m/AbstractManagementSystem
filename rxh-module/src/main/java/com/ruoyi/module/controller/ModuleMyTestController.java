package com.ruoyi.module.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.module.domain.ModuleMyTest;
import com.ruoyi.module.service.IModuleMyTestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测试Controller
 * 
 * @author ruoyi
 * @date 2024-08-12
 */
@RestController
@RequestMapping("/module/test")
public class ModuleMyTestController extends BaseController
{
    @Autowired
    private IModuleMyTestService moduleMyTestService;

    /**
     * 查询测试列表
     */
    @PreAuthorize("@ss.hasPermi('module:test:list')")
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(ModuleMyTest moduleMyTest)
    {
        startPage();
        List<ModuleMyTest> list = moduleMyTestService.selectModuleMyTestList(moduleMyTest);
        return getDataTable(list);
    }

    /**
     * 导出测试列表
     */
    @PreAuthorize("@ss.hasPermi('module:test:export')")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ModuleMyTest moduleMyTest)
    {
        List<ModuleMyTest> list = moduleMyTestService.selectModuleMyTestList(moduleMyTest);
        ExcelUtil<ModuleMyTest> util = new ExcelUtil<ModuleMyTest>(ModuleMyTest.class);
        util.exportExcel(response, list, "测试数据");
    }

    /**
     * 获取测试详细信息
     */
    @PreAuthorize("@ss.hasPermi('module:test:query')")
    @GetMapping(value = "/{testId}")
    public AjaxResult getInfo(@PathVariable("testId") Integer testId)
    {
        return success(moduleMyTestService.selectModuleMyTestByTestId(testId));
    }

    /**
     * 新增测试
     */
    @PreAuthorize("@ss.hasPermi('module:test:add')")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ModuleMyTest moduleMyTest)
    {
        return toAjax(moduleMyTestService.insertModuleMyTest(moduleMyTest));
    }

    /**
     * 修改测试
     */
    @PreAuthorize("@ss.hasPermi('module:test:edit')")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ModuleMyTest moduleMyTest)
    {
        return toAjax(moduleMyTestService.updateModuleMyTest(moduleMyTest));
    }

    /**
     * 删除测试
     */
    @PreAuthorize("@ss.hasPermi('module:test:remove')")
    @Log(title = "测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{testIds}")
    public AjaxResult remove(@PathVariable Integer[] testIds)
    {
        return toAjax(moduleMyTestService.deleteModuleMyTestByTestIds(testIds));
    }
}
