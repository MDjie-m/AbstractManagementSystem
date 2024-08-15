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
import com.renxin.course.domain.CourUserCourseSection;
import com.renxin.course.service.ICourUserCourseSectionService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 用户-课程-章节关系Controller
 * 
 * @author renxin
 * @date 2023-03-15
 */
@RestController
@RequestMapping("/course/userSection")
public class CourUserCourseSectionController extends BaseController
{
    @Autowired
    private ICourUserCourseSectionService courUserCourseSectionService;

    /**
     * 查询用户-课程-章节关系列表
     */
    @PreAuthorize("@ss.hasPermi('course:userSection:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourUserCourseSection courUserCourseSection)
    {
        startPage();
        List<CourUserCourseSection> list = courUserCourseSectionService.selectCourUserCourseSectionList(courUserCourseSection);
        return getDataTable(list);
    }

    /**
     * 导出用户-课程-章节关系列表
     */
    @PreAuthorize("@ss.hasPermi('course:userSection:export')")
    @Log(title = "用户-课程-章节关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourUserCourseSection courUserCourseSection)
    {
        List<CourUserCourseSection> list = courUserCourseSectionService.selectCourUserCourseSectionList(courUserCourseSection);
        ExcelUtil<CourUserCourseSection> util = new ExcelUtil<CourUserCourseSection>(CourUserCourseSection.class);
        util.exportExcel(response, list, "用户-课程-章节关系数据");
    }

    /**
     * 获取用户-课程-章节关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('course:userSection:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(courUserCourseSectionService.selectCourUserCourseSectionById(id));
    }

    /**
     * 新增用户-课程-章节关系
     */
    @PreAuthorize("@ss.hasPermi('course:userSection:add')")
    @Log(title = "用户-课程-章节关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CourUserCourseSection courUserCourseSection)
    {
        try {
            int res = courUserCourseSectionService.insertCourUserCourseSection(courUserCourseSection);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "新增用户-课程-章节关系失败");
        }
    }

    /**
     * 修改用户-课程-章节关系
     */
    @PreAuthorize("@ss.hasPermi('course:userSection:edit')")
    @Log(title = "用户-课程-章节关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CourUserCourseSection courUserCourseSection)
    {
        try {
            int res = courUserCourseSectionService.updateCourUserCourseSection(courUserCourseSection);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "修改用户-课程-章节关系失败");
        }
    }

    /**
     * 删除用户-课程-章节关系
     */
    @PreAuthorize("@ss.hasPermi('course:userSection:remove')")
    @Log(title = "用户-课程-章节关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        try {
            int res = courUserCourseSectionService.deleteCourUserCourseSectionByIds(ids);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "删除用户-课程-章节关系失败");
        }
    }
}
