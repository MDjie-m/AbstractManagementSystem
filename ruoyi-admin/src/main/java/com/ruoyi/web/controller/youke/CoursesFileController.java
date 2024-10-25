package com.ruoyi.web.controller.youke;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.bo.CoursesFileBo;
import com.ruoyi.system.domain.vo.CoursesFileVo;
import com.ruoyi.system.service.ICoursesFileService;
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

/**
 * 课程相关文件
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yk/file")
public class CoursesFileController extends BaseController {

    private final ICoursesFileService iCoursesFileService;

    /**
     * 查询课程相关文件列表
     */
    @SaCheckPermission("workflow:file:list")
    @GetMapping("/list")
    public TableDataInfo<CoursesFileVo> list(CoursesFileBo bo, PageQuery pageQuery) {
        return iCoursesFileService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出课程相关文件列表
     */
    @SaCheckPermission("workflow:file:export")
    @Log(title = "课程相关文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CoursesFileBo bo, HttpServletResponse response) {
        List<CoursesFileVo> list = iCoursesFileService.queryList(bo);
        ExcelUtil.exportExcel(list, "课程相关文件", CoursesFileVo.class, response);
    }

    /**
     * 获取课程相关文件详细信息
     *
     * @param fileId 主键
     */
    @SaCheckPermission("workflow:file:query")
    @GetMapping("/{fileId}")
    public R<CoursesFileVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long fileId) {
        return R.ok(iCoursesFileService.queryById(fileId));
    }

    /**
     * 新增课程相关文件
     */
    @SaCheckPermission("workflow:file:add")
    @Log(title = "课程相关文件", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CoursesFileBo bo) {
        return toAjax(iCoursesFileService.insertByBo(bo));
    }

    /**
     * 修改课程相关文件
     */
    @SaCheckPermission("workflow:file:edit")
    @Log(title = "课程相关文件", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CoursesFileBo bo) {
        return toAjax(iCoursesFileService.updateByBo(bo));
    }

    /**
     * 删除课程相关文件
     *
     * @param fileIds 主键串
     */
    @SaCheckPermission("workflow:file:remove")
    @Log(title = "课程相关文件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fileIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] fileIds) {
        return toAjax(iCoursesFileService.deleteWithValidByIds(Arrays.asList(fileIds), true));
    }
}
