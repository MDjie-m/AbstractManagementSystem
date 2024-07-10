package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.PageVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysStudent;
import com.ruoyi.system.service.ISysStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * test学生信息Controller
 * 
 * @author ruoyi
 * @date 2024-07-08
 */
@Slf4j
@Api(tags = "test学生信息")
@RestController
@RequestMapping("/system/student")
public class SysStudentController extends BaseController
{
    @Autowired
    private ISysStudentService sysStudentService;


    @ApiOperation("查询test学生信息列表")
    @PreAuthorize("@ss.hasPermi('system:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStudent sysStudent)
    {
        startPage();
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        return getDataTable(list);
    }

    @ApiOperation("list2获取用户列表")
    @GetMapping("/list2")
    public R<PageVo<List<SysStudent>>> userList(SysStudent sysStudent)
    {

        startPage();
        List<SysStudent> list = sysStudentService.list();
//        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        PageVo pageVo = new PageVo(list);
        return R.success(pageVo);
    }


    @ApiOperation("导出test学生信息列表")
    @PreAuthorize("@ss.hasPermi('system:student:export')")
    @Log(title = "test学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysStudent sysStudent)
    {
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        util.exportExcel(response, list, "test学生信息数据");
    }

    @ApiOperation("获取test学生信息详细信息")
    @PreAuthorize("@ss.hasPermi('system:student:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return success(sysStudentService.selectSysStudentByStudentId(studentId));
    }

    @ApiOperation("新增test学生信息")
    @PreAuthorize("@ss.hasPermi('system:student:add')")
    @Log(title = "test学生信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.insertSysStudent(sysStudent));
    }

    @ApiOperation("修改test学生信息")
    @PreAuthorize("@ss.hasPermi('system:student:edit')")
    @Log(title = "test学生信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStudent sysStudent)
    {
        return toAjax(sysStudentService.updateSysStudent(sysStudent));
    }

    @ApiOperation("删除test学生信息")
    @PreAuthorize("@ss.hasPermi('system:student:remove')")
    @Log(title = "test学生信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(sysStudentService.deleteSysStudentByStudentIds(studentIds));
    }
}
