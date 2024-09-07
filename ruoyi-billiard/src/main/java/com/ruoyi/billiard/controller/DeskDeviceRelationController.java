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
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.billiard.domain.DeskDeviceRelation;
import com.ruoyi.billiard.service.IDeskDeviceRelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 桌子设备关联关系Controller
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@RestController
@RequestMapping("/billiard/relation")
public class DeskDeviceRelationController extends BaseController
{
    @Autowired
    private IDeskDeviceRelationService deskDeviceRelationService;

    /**
     * 查询桌子设备关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:relation:list')")
    @GetMapping("/list")
    public PageResVo<DeskDeviceRelation> list(DeskDeviceRelation deskDeviceRelation)
    {
        startPage();
        List<DeskDeviceRelation> list = deskDeviceRelationService.selectDeskDeviceRelationList(deskDeviceRelation);
        return PageResVo.success(list);
    }

    /**
     * 导出桌子设备关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:relation:export')")
    @Log(title = "桌子设备关联关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeskDeviceRelation deskDeviceRelation)
    {
        List<DeskDeviceRelation> list = deskDeviceRelationService.selectDeskDeviceRelationList(deskDeviceRelation);
        ExcelUtil<DeskDeviceRelation> util = new ExcelUtil<DeskDeviceRelation>(DeskDeviceRelation.class);
        util.exportExcel(response, list, "桌子设备关联关系数据");
    }

    /**
     * 获取桌子设备关联关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:relation:query')")
    @GetMapping(value = "/{deskDeviceRelationId}")
    public ResultVo<DeskDeviceRelation> getInfo(@PathVariable("deskDeviceRelationId") Long deskDeviceRelationId)
    {
        return ResultVo.success(deskDeviceRelationService.selectDeskDeviceRelationByDeskDeviceRelationId(deskDeviceRelationId));
    }

    /**
     * 新增桌子设备关联关系
     */
    @PreAuthorize("@ss.hasPermi('billiard:relation:add')")
    @Log(title = "桌子设备关联关系", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody DeskDeviceRelation deskDeviceRelation)
    {
        return ResultVo.success(deskDeviceRelationService.insertDeskDeviceRelation(deskDeviceRelation));
    }

    /**
     * 修改桌子设备关联关系
     */
    @PreAuthorize("@ss.hasPermi('billiard:relation:edit')")
    @Log(title = "桌子设备关联关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody DeskDeviceRelation deskDeviceRelation)
    {
        return ResultVo.success(deskDeviceRelationService.updateDeskDeviceRelation(deskDeviceRelation));
    }

    /**
     * 删除桌子设备关联关系
     */
    @PreAuthorize("@ss.hasPermi('billiard:relation:remove')")
    @Log(title = "桌子设备关联关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deskDeviceRelationIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] deskDeviceRelationIds)
    {
        return  ResultVo.success(deskDeviceRelationService.deleteDeskDeviceRelationByDeskDeviceRelationIds(deskDeviceRelationIds));
    }
}
