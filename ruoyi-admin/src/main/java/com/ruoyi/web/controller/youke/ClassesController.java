package com.ruoyi.web.controller.youke;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.bo.ClassesBo;
import com.ruoyi.system.domain.vo.ClassesVo;
import com.ruoyi.system.service.IClassesService;
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
 * 班级
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yk/classes")
public class ClassesController extends BaseController {

    private final IClassesService iClassesService;

    /**
     * 查询班级列表
     */
    @SaCheckPermission("workflow:classes:list")
    @GetMapping("/list")
    public TableDataInfo<ClassesVo> list(ClassesBo bo, PageQuery pageQuery) {
        return iClassesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出班级列表
     */
    @SaCheckPermission("workflow:classes:export")
    @Log(title = "班级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ClassesBo bo, HttpServletResponse response) {
        List<ClassesVo> list = iClassesService.queryList(bo);
        ExcelUtil.exportExcel(list, "班级", ClassesVo.class, response);
    }

    /**
     * 获取班级详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:classes:query")
    @GetMapping("/{id}")
    public R<ClassesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iClassesService.queryById(id));
    }

    /**
     * 新增班级
     */
    @SaCheckPermission("workflow:classes:add")
    @Log(title = "班级", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ClassesBo bo) {
        return toAjax(iClassesService.insertByBo(bo));
    }

    /**
     * 修改班级
     */
    @SaCheckPermission("workflow:classes:edit")
    @Log(title = "班级", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ClassesBo bo) {
        return toAjax(iClassesService.updateByBo(bo));
    }

    /**
     * 删除班级
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:classes:remove")
    @Log(title = "班级", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iClassesService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
