package com.ruoyi.web.controller.youke;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.bo.GradesBo;
import com.ruoyi.system.domain.vo.GradesVo;
import com.ruoyi.system.service.IGradesService;
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
 * 年级
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yk/grades")
public class GradesController extends BaseController {

    private final IGradesService iGradesService;

    /**
     * 查询年级列表
     */
    @SaCheckPermission("workflow:grades:list")
    @GetMapping("/list")
    public TableDataInfo<GradesVo> list(GradesBo bo, PageQuery pageQuery) {
        return iGradesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出年级列表
     */
    @SaCheckPermission("workflow:grades:export")
    @Log(title = "年级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GradesBo bo, HttpServletResponse response) {
        List<GradesVo> list = iGradesService.queryList(bo);
        ExcelUtil.exportExcel(list, "年级", GradesVo.class, response);
    }

    /**
     * 获取年级详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:grades:query")
    @GetMapping("/{id}")
    public R<GradesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iGradesService.queryById(id));
    }

    /**
     * 新增年级
     */
    @SaCheckPermission("workflow:grades:add")
    @Log(title = "年级", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GradesBo bo) {
        return toAjax(iGradesService.insertByBo(bo));
    }

    /**
     * 修改年级
     */
    @SaCheckPermission("workflow:grades:edit")
    @Log(title = "年级", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GradesBo bo) {
        return toAjax(iGradesService.updateByBo(bo));
    }

    /**
     * 删除年级
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:grades:remove")
    @Log(title = "年级", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iGradesService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
