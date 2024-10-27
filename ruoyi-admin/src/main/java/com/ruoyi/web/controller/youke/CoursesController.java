package com.ruoyi.web.controller.youke;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.bo.CoursesBo;
import com.ruoyi.system.domain.vo.CoursesVo;
import com.ruoyi.system.service.ICoursesService;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yk/courses")
public class CoursesController extends BaseController {

    private final ICoursesService iCoursesService;

    /**
     * 查询课程列表
     */
    @SaCheckPermission("workflow:courses:list")
    @GetMapping("/list")
    public TableDataInfo<CoursesVo> list(CoursesBo bo, PageQuery pageQuery) {
        return iCoursesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出课程列表
     */
    @SaCheckPermission("workflow:courses:export")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CoursesBo bo, HttpServletResponse response) {
        List<CoursesVo> list = iCoursesService.queryList(bo);
        ExcelUtil.exportExcel(list, "课程", CoursesVo.class, response);
    }

    /**
     * 获取课程详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:courses:query")
    @GetMapping("/{id}")
    public R<CoursesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iCoursesService.queryById(id));
    }

    /**
     * 新增课程
     */
    @SaCheckPermission("workflow:courses:add")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestPart CoursesBo bo, List<MultipartFile> coverfiles, List<MultipartFile> systemFiles,
                       List<MultipartFile> effectFiles, List<MultipartFile> packageFiles) {
        return toAjax(iCoursesService.insertByBo(bo,coverfiles, systemFiles, effectFiles, packageFiles));
    }

    /**
     * 修改课程
     */
    @SaCheckPermission("workflow:courses:edit")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CoursesBo bo) {
        return toAjax(iCoursesService.updateByBo(bo));
    }

    /**
     * 删除课程
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:courses:remove")
    @Log(title = "课程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iCoursesService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
