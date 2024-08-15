package com.renxin.web.controller.course;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.renxin.course.domain.CourCourseBannerConfig;
import com.renxin.course.service.ICourCourseBannerConfigService;
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
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 课程banner配置Controller
 * 
 * @author renxin
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/course/config")
public class CourCourseBannerConfigController extends BaseController
{
    @Autowired
    private ICourCourseBannerConfigService courCourseBannerConfigService;

    /**
     * 查询课程banner配置列表
     */
    @PreAuthorize("@ss.hasPermi('course:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourCourseBannerConfig courCourseBannerConfig)
    {
        startPage();
        List<CourCourseBannerConfig> list = courCourseBannerConfigService.selectCourCourseBannerConfigList(courCourseBannerConfig);
        return getDataTable(list);
    }

    /**
     * 导出课程banner配置列表
     */
    @PreAuthorize("@ss.hasPermi('course:config:export')")
    @Log(title = "课程banner配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourCourseBannerConfig courCourseBannerConfig)
    {
        List<CourCourseBannerConfig> list = courCourseBannerConfigService.selectCourCourseBannerConfigList(courCourseBannerConfig);
        ExcelUtil<CourCourseBannerConfig> util = new ExcelUtil<CourCourseBannerConfig>(CourCourseBannerConfig.class);
        util.exportExcel(response, list, "课程banner配置数据");
    }

    /**
     * 获取课程banner配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('course:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(courCourseBannerConfigService.selectCourCourseBannerConfigById(id));
    }

    /**
     * 新增课程banner配置
     */
    @PreAuthorize("@ss.hasPermi('course:config:add')")
    @Log(title = "课程banner配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CourCourseBannerConfig courCourseBannerConfig)
    {
        try {
            int res = courCourseBannerConfigService.insertCourCourseBannerConfig(courCourseBannerConfig);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "新增课程banner配置失败");
        }
    }

    /**
     * 修改课程banner配置
     */
    @PreAuthorize("@ss.hasPermi('course:config:edit')")
    @Log(title = "课程banner配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CourCourseBannerConfig courCourseBannerConfig)
    {
        try {
            int res = courCourseBannerConfigService.updateCourCourseBannerConfig(courCourseBannerConfig);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "修改课程banner配置失败");
        }
    }

    /**
     * 删除课程banner配置
     */
    @PreAuthorize("@ss.hasPermi('course:config:remove')")
    @Log(title = "课程banner配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        try {
            int res = courCourseBannerConfigService.deleteCourCourseBannerConfigByIds(ids);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "删除课程banner配置失败");
        }
    }
}
