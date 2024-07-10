package com.ruoyi.web.controller.userman;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TUser;
import com.ruoyi.system.service.ITUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户Controller
 * 
 * @author tz
 * @date 2024-07-09
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/userman/user")
public class TUserController extends BaseController
{
    @Autowired
    private ITUserService tUserService;


    @ApiOperation("查询用户列表")
    @PreAuthorize("@ss.hasPermi('userman:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUser tUser)
    {
        startPage();
        List<TUser> list = tUserService.selectTUserList(tUser);
        return getDataTable(list);
    }


    @ApiOperation("导出用户列表")
    @PreAuthorize("@ss.hasPermi('userman:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TUser tUser)
    {
        tUser.setPageSize(99999);
        List<TUser> list = tUserService.selectTUserList(tUser);
        ExcelUtil<TUser> util = new ExcelUtil<TUser>(TUser.class);
        util.exportExcel(response, list, "用户数据");
    }

    @ApiOperation("获取用户详细信息")
    @PreAuthorize("@ss.hasPermi('userman:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tUserService.selectTUserById(id));
    }

    @ApiOperation("新增用户")
    @PreAuthorize("@ss.hasPermi('userman:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUser tUser)
    {
        return toAjax(tUserService.insertTUser(tUser));
    }

    @ApiOperation("修改用户")
    @PreAuthorize("@ss.hasPermi('userman:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUser tUser)
    {
        return toAjax(tUserService.updateTUser(tUser));
    }

    @ApiOperation("删除用户")
    @PreAuthorize("@ss.hasPermi('userman:user:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tUserService.deleteTUserByIds(ids));
    }
}
