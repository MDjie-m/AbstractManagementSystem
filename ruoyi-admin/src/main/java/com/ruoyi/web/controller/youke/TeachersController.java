package com.ruoyi.web.controller.youke;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.bo.TeachersBo;
import com.ruoyi.system.domain.vo.TeachersVo;
import com.ruoyi.system.service.ITeachersService;
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
 * 教师
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yk/teachers")
public class TeachersController extends BaseController {

    private final ITeachersService iTeachersService;

    /**
     * 查询教师列表
     */
    @SaCheckPermission("workflow:teachers:list")
    @GetMapping("/list")
    public TableDataInfo<TeachersVo> list(TeachersBo bo, PageQuery pageQuery) {
        return iTeachersService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出教师列表
     */
    @SaCheckPermission("workflow:teachers:export")
    @Log(title = "教师", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TeachersBo bo, HttpServletResponse response) {
        List<TeachersVo> list = iTeachersService.queryList(bo);
        ExcelUtil.exportExcel(list, "教师", TeachersVo.class, response);
    }

    /**
     * 获取教师详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:teachers:query")
    @GetMapping("/{id}")
    public R<TeachersVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iTeachersService.queryById(id));
    }

    /**
     * 新增教师
     */
    @SaCheckPermission("workflow:teachers:add")
    @Log(title = "教师", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TeachersBo bo) {
        return toAjax(iTeachersService.insertByBo(bo));
    }

    /**
     * 修改教师
     */
    @SaCheckPermission("workflow:teachers:edit")
    @Log(title = "教师", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TeachersBo bo) {
        return toAjax(iTeachersService.updateByBo(bo));
    }

    /**
     * 删除教师
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:teachers:remove")
    @Log(title = "教师", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iTeachersService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
