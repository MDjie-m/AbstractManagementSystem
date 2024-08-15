package com.renxin.web.controller.course;

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
import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.BusinessType;
import com.renxin.course.domain.CourCourseClass;
import com.renxin.course.service.ICourCourseClassService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 类型Controller
 * 
 * @author renxin
 * @date 2023-04-07
 */
@RestController
@RequestMapping("/course/class")
public class CourCourseClassController extends BaseController
{
    @Autowired
    private ICourCourseClassService courCourseClassService;

    /**
     * 查询类型列表
     */
    @PreAuthorize("@ss.hasPermi('course:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourCourseClass courCourseClass)
    {
        startPage();
        List<CourCourseClass> list = courCourseClassService.selectCourCourseClassList(courCourseClass);
        return getDataTable(list);
    }

    /**
     * 导出类型列表
     */
    @PreAuthorize("@ss.hasPermi('course:class:export')")
    @Log(title = "类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourCourseClass courCourseClass)
    {
        List<CourCourseClass> list = courCourseClassService.selectCourCourseClassList(courCourseClass);
        ExcelUtil<CourCourseClass> util = new ExcelUtil<CourCourseClass>(CourCourseClass.class);
        util.exportExcel(response, list, "类型数据");
    }

    /**
     * 获取类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('course:class:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(courCourseClassService.selectCourCourseClassById(id));
    }

    /**
     * 新增类型
     */
    @PreAuthorize("@ss.hasPermi('course:class:add')")
    @Log(title = "类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CourCourseClass courCourseClass)
    {
        try {
            int res = courCourseClassService.insertCourCourseClass(courCourseClass);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "新增课程类型失败");
        }
    }

    /**
     * 修改类型
     */
    @PreAuthorize("@ss.hasPermi('course:class:edit')")
    @Log(title = "类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CourCourseClass courCourseClass)
    {
        try {
            int res = courCourseClassService.updateCourCourseClass(courCourseClass);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "修改课程类型失败");
        }
    }

    /**
     * 删除类型
     */
    @PreAuthorize("@ss.hasPermi('course:class:remove')")
    @Log(title = "类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        try {
            int res = courCourseClassService.deleteCourCourseClassByIds(ids);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "删除课程类型失败");
        }
    }
}
