package com.ruoyi.web.controller.youke;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.bo.CoursesMenuBo;
import com.ruoyi.system.domain.vo.CoursesMenuVo;
import com.ruoyi.system.service.ICoursesMenuService;
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
 * 课程目录
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yk/menu")
public class CoursesMenuController extends BaseController {

    private final ICoursesMenuService iCoursesMenuService;

    /**
     * 查询课程目录列表
     */
    @SaCheckPermission("workflow:menu:list")
    @GetMapping("/list")
    public TableDataInfo<CoursesMenuVo> list(CoursesMenuBo bo, PageQuery pageQuery) {
        return iCoursesMenuService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出课程目录列表
     */
    @SaCheckPermission("workflow:menu:export")
    @Log(title = "课程目录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CoursesMenuBo bo, HttpServletResponse response) {
        List<CoursesMenuVo> list = iCoursesMenuService.queryList(bo);
        ExcelUtil.exportExcel(list, "课程目录", CoursesMenuVo.class, response);
    }

    /**
     * 获取课程目录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:menu:query")
    @GetMapping("/{id}")
    public R<CoursesMenuVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iCoursesMenuService.queryById(id));
    }

    /**
     * 新增课程目录
     */
    @SaCheckPermission("workflow:menu:add")
    @Log(title = "课程目录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CoursesMenuBo bo) {
        return toAjax(iCoursesMenuService.insertByBo(bo));
    }

    /**
     * 修改课程目录
     */
    @SaCheckPermission("workflow:menu:edit")
    @Log(title = "课程目录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CoursesMenuBo bo) {
        return toAjax(iCoursesMenuService.updateByBo(bo));
    }

    /**
     * 删除课程目录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:menu:remove")
    @Log(title = "课程目录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iCoursesMenuService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
