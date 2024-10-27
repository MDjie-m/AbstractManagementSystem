package com.ruoyi.web.controller.youke;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.bo.SchoolsBo;
import com.ruoyi.system.domain.vo.SchoolsVo;
import com.ruoyi.system.service.ISchoolsService;
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
 * 学校
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/yk/schools")
public class SchoolsController extends BaseController {

    private final ISchoolsService iSchoolsService;

    /**
     * 查询学校列表
     */
    @SaCheckPermission("workflow:schools:list")
    @GetMapping("/list")
    public TableDataInfo<SchoolsVo> list(SchoolsBo bo, PageQuery pageQuery) {
        return iSchoolsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出学校列表
     */
    @SaCheckPermission("workflow:schools:export")
    @Log(title = "学校", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SchoolsBo bo, HttpServletResponse response) {
        List<SchoolsVo> list = iSchoolsService.queryList(bo);
        ExcelUtil.exportExcel(list, "学校", SchoolsVo.class, response);
    }

    /**
     * 获取学校详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:schools:query")
    @GetMapping("/{id}")
    public R<SchoolsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iSchoolsService.queryById(id));
    }

    /**
     * 新增学校
     */
    @SaCheckPermission("workflow:schools:add")
    @Log(title = "学校", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SchoolsBo bo) {
        return toAjax(iSchoolsService.insertByBo(bo));
    }

    /**
     * 修改学校
     */
    @SaCheckPermission("workflow:schools:edit")
    @Log(title = "学校", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SchoolsBo bo) {
        return toAjax(iSchoolsService.updateByBo(bo));
    }

    /**
     * 删除学校
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:schools:remove")
    @Log(title = "学校", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iSchoolsService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
