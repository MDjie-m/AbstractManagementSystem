package com.ruoyi.billiard.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.billiard.domain.LevelDiscountPermission;
import com.ruoyi.billiard.service.ILevelDiscountPermissionService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 等级折扣范围Controller
 * 
 * @author zhoukeu
 * @date 2024-09-20
 */
@RestController
@RequestMapping("/billiard/levelDiscountPermission")
public class LevelDiscountPermissionController extends BaseController
{
    @Autowired
    private ILevelDiscountPermissionService levelDiscountPermissionService;

    /**
     * 查询等级折扣范围列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:levelDiscountPermission:list')")
    @GetMapping("/list")
    public PageResVo<LevelDiscountPermission> list(LevelDiscountPermission levelDiscountPermission)
    {
        startPage();
        List<LevelDiscountPermission> list = levelDiscountPermissionService.selectLevelDiscountPermissionList(levelDiscountPermission);
        return PageResVo.success(list);
    }

    /**
     * 导出等级折扣范围列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:levelDiscountPermission:export')")
    @Log(title = "等级折扣范围", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LevelDiscountPermission levelDiscountPermission)
    {
        List<LevelDiscountPermission> list = levelDiscountPermissionService.selectLevelDiscountPermissionList(levelDiscountPermission);
        ExcelUtil<LevelDiscountPermission> util = new ExcelUtil<LevelDiscountPermission>(LevelDiscountPermission.class);
        util.exportExcel(response, list, "等级折扣范围数据");
    }

    /**
     * 获取等级折扣范围详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:levelDiscountPermission:query')")
    @GetMapping(value = "/{levelDiscountPermissionId}")
    public ResultVo<LevelDiscountPermission> getInfo(@PathVariable("levelDiscountPermissionId") Long levelDiscountPermissionId)
    {
        return ResultVo.success(levelDiscountPermissionService.selectLevelDiscountPermissionByLevelDiscountPermissionId(levelDiscountPermissionId));
    }

    /**
     * 新增等级折扣范围
     */
    @PreAuthorize("@ss.hasPermi('billiard:levelDiscountPermission:add')")
    @Log(title = "等级折扣范围", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody LevelDiscountPermission levelDiscountPermission)
    {
        return ResultVo.success(levelDiscountPermissionService.insertLevelDiscountPermission(levelDiscountPermission));
    }

    /**
     * 修改等级折扣范围
     */
    @PreAuthorize("@ss.hasPermi('billiard:levelDiscountPermission:edit')")
    @Log(title = "等级折扣范围", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody LevelDiscountPermission levelDiscountPermission)
    {
        return ResultVo.success(levelDiscountPermissionService.updateLevelDiscountPermission(levelDiscountPermission));
    }

    /**
     * 删除等级折扣范围
     */
    @PreAuthorize("@ss.hasPermi('billiard:levelDiscountPermission:remove')")
    @Log(title = "等级折扣范围", businessType = BusinessType.DELETE)
	@DeleteMapping("/{levelDiscountPermissionIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] levelDiscountPermissionIds)
    {
        return  ResultVo.success(levelDiscountPermissionService.deleteLevelDiscountPermissionByLevelDiscountPermissionIds(levelDiscountPermissionIds));
    }
}
