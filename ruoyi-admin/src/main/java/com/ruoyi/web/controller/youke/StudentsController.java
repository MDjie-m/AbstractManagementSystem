package com.ruoyi.web.controller.youke;

import java.util.List;
import java.util.Arrays;

import com.ruoyi.system.domain.bo.StudentsBo;
import com.ruoyi.system.domain.vo.StudentsVo;
import com.ruoyi.system.service.IStudentsService;
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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yk/students")
public class StudentsController extends BaseController {

    private final IStudentsService iStudentsService;

    /**
     * 查询学生列表
     */
    @SaCheckPermission("workflow:students:list")
    @GetMapping("/list")
    public TableDataInfo<StudentsVo> list(StudentsBo bo, PageQuery pageQuery) {
        return iStudentsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出学生列表
     */
    @SaCheckPermission("workflow:students:export")
    @Log(title = "学生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(StudentsBo bo, HttpServletResponse response) {
        List<StudentsVo> list = iStudentsService.queryList(bo);
        ExcelUtil.exportExcel(list, "学生", StudentsVo.class, response);
    }

    /**
     * 获取学生详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:students:query")
    @GetMapping("/{id}")
    public R<StudentsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iStudentsService.queryById(id));
    }

    /**
     * 新增学生
     */
    @SaCheckPermission("workflow:students:add")
    @Log(title = "学生", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody StudentsBo bo) {
        return toAjax(iStudentsService.insertByBo(bo));
    }

    /**
     * 修改学生
     */
    @SaCheckPermission("workflow:students:edit")
    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody StudentsBo bo) {
        return toAjax(iStudentsService.updateByBo(bo));
    }

    /**
     * 删除学生
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:students:remove")
    @Log(title = "学生", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iStudentsService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
