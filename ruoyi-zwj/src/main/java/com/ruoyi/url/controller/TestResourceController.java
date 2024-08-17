package com.ruoyi.url.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.url.domain.TestResource;
import com.ruoyi.url.service.ITestResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 测试图片上传Controller
 * 
 * @author zwj
 * @date 2024-08-17
 */
@RestController
@RequestMapping("/zwj/uploadTest")
public class TestResourceController extends BaseController
{
    @Autowired
    private ITestResourceService testResourceService;

    /**
     * 查询测试图片上传列表
     */
    @PreAuthorize("@ss.hasPermi('zwj:uploadTest:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestResource testResource)
    {
        startPage();
        List<TestResource> list = testResourceService.selectTestResourceList(testResource);
        return getDataTable(list);
    }

    /**
     * 导出测试图片上传列表
     */
    @PreAuthorize("@ss.hasPermi('zwj:uploadTest:export')")
    @Log(title = "测试图片上传", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TestResource testResource)
    {
        List<TestResource> list = testResourceService.selectTestResourceList(testResource);
        ExcelUtil<TestResource> util = new ExcelUtil<TestResource>(TestResource.class);
        util.exportExcel(response, list, "测试图片上传数据");
    }

    /**
     * 获取测试图片上传详细信息
     */
    @PreAuthorize("@ss.hasPermi('zwj:uploadTest:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(testResourceService.selectTestResourceById(id));
    }

    /**
     * 新增测试图片上传
     */
    @PreAuthorize("@ss.hasPermi('zwj:uploadTest:add')")
    @Log(title = "测试图片上传", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestResource testResource)
    {
        return toAjax(testResourceService.insertTestResource(testResource));
    }

    /**
     * 修改测试图片上传
     */
    @PreAuthorize("@ss.hasPermi('zwj:uploadTest:edit')")
    @Log(title = "测试图片上传", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestResource testResource)
    {
        return toAjax(testResourceService.updateTestResource(testResource));
    }

    /**
     * 删除测试图片上传
     */
    @PreAuthorize("@ss.hasPermi('zwj:uploadTest:remove')")
    @Log(title = "测试图片上传", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(testResourceService.deleteTestResourceByIds(ids));
    }
}
