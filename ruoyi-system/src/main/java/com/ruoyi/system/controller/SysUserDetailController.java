package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.domain.entity.SysUserDetail;
import com.ruoyi.system.service.ISysUserDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户详细信息Controller
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@RestController
@RequestMapping("/system/detail")
public class SysUserDetailController extends BaseController
{
    @Autowired
    private ISysUserDetailService sysUserDetailService;

    /**
     * 查询用户详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserDetail sysUserDetail)
    {
        startPage();
        List<SysUserDetail> list = sysUserDetailService.selectSysUserDetailList(sysUserDetail);
        return getDataTable(list);
    }

    /**
     * 导出用户详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserDetail sysUserDetail)
    {
        List<SysUserDetail> list = sysUserDetailService.selectSysUserDetailList(sysUserDetail);
        ExcelUtil<SysUserDetail> util = new ExcelUtil<SysUserDetail>(SysUserDetail.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取用户详细信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{userDetailsId}")
    public AjaxResult getInfo(@PathVariable("userDetailsId") String userDetailsId)
    {
        return success(sysUserDetailService.selectSysUserDetailByUserDetailsId(userDetailsId));
    }

    /**
     * 新增用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserDetail sysUserDetail)
    {
        return toAjax(sysUserDetailService.insertSysUserDetail(sysUserDetail));
    }

    /**
     * 修改用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserDetail sysUserDetail)
    {
        return toAjax(sysUserDetailService.updateSysUserDetail(sysUserDetail));
    }

    /**
     * 删除用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userDetailsIds}")
    public AjaxResult remove(@PathVariable String[] userDetailsIds)
    {
        return toAjax(sysUserDetailService.deleteSysUserDetailByUserDetailsIds(userDetailsIds));
    }
}
