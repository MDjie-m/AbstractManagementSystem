package com.ruoyi.web.controller.youke;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.bo.GradeSegmentBo;
import com.ruoyi.system.domain.vo.GradeSegmentVo;
import com.ruoyi.system.service.IGradeSegmentService;
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
 * 年段
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yk/segment")
public class GradeSegmentController extends BaseController {

    private final IGradeSegmentService iGradeSegmentService;

    /**
     * 查询年段列表
     */
    @SaCheckPermission("workflow:segment:list")
    @GetMapping("/list")
    public TableDataInfo<GradeSegmentVo> list(GradeSegmentBo bo, PageQuery pageQuery) {
        return iGradeSegmentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出年段列表
     */
    @SaCheckPermission("workflow:segment:export")
    @Log(title = "年段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GradeSegmentBo bo, HttpServletResponse response) {
        List<GradeSegmentVo> list = iGradeSegmentService.queryList(bo);
        ExcelUtil.exportExcel(list, "年段", GradeSegmentVo.class, response);
    }

    /**
     * 获取年段详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:segment:query")
    @GetMapping("/{id}")
    public R<GradeSegmentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iGradeSegmentService.queryById(id));
    }

    /**
     * 新增年段
     */
    @SaCheckPermission("workflow:segment:add")
    @Log(title = "年段", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GradeSegmentBo bo) {
        return toAjax(iGradeSegmentService.insertByBo(bo));
    }

    /**
     * 修改年段
     */
    @SaCheckPermission("workflow:segment:edit")
    @Log(title = "年段", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GradeSegmentBo bo) {
        return toAjax(iGradeSegmentService.updateByBo(bo));
    }

    /**
     * 删除年段
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:segment:remove")
    @Log(title = "年段", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iGradeSegmentService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
