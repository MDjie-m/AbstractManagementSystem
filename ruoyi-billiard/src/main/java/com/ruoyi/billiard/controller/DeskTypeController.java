package com.ruoyi.billiard.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.billiard.domain.DeskPlace;
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
import com.ruoyi.billiard.domain.DeskType;
import com.ruoyi.billiard.service.IDeskTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 台桌类型Controller
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/billiard/deskType")
public class DeskTypeController extends BaseController
{
    @Autowired
    private IDeskTypeService deskTypeService;

    /**
     * 查询台桌类型列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskType:list')")
    @GetMapping("/list")
    public PageResVo<DeskType> list(DeskType deskType)
    {
        startPage();
        List<DeskType> list = deskTypeService.selectDeskTypeList(deskType);
        return PageResVo.success(list);
    }
    @GetMapping("/list/all")
    public ResultVo<List<DeskType>> listAll(DeskType deskType)
    {
        List<DeskType> list = deskTypeService.selectDeskTypeList(deskType);
        return ResultVo.success(list);
    }
    /**
     * 导出台桌类型列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskType:export')")
    @Log(title = "台桌类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeskType deskType)
    {
        List<DeskType> list = deskTypeService.selectDeskTypeList(deskType);
        ExcelUtil<DeskType> util = new ExcelUtil<DeskType>(DeskType.class);
        util.exportExcel(response, list, "台桌类型数据");
    }

    /**
     * 获取台桌类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskType:query')")
    @GetMapping(value = "/{deskTypeId}")
    public ResultVo<DeskType> getInfo(@PathVariable("deskTypeId") Long deskTypeId)
    {
        return ResultVo.success(deskTypeService.selectDeskTypeByDeskTypeId(deskTypeId));
    }

    /**
     * 新增台桌类型
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskType:add')")
    @Log(title = "台桌类型", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody DeskType deskType)
    {
        return ResultVo.success(deskTypeService.insertDeskType(deskType));
    }

    /**
     * 修改台桌类型
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskType:edit')")
    @Log(title = "台桌类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody DeskType deskType)
    {
        return ResultVo.success(deskTypeService.updateDeskType(deskType));
    }

    /**
     * 删除台桌类型
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskType:remove')")
    @Log(title = "台桌类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deskTypeIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] deskTypeIds)
    {
        return  ResultVo.success(deskTypeService.deleteDeskTypeByDeskTypeIds(deskTypeIds));
    }
}
