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
import com.ruoyi.billiard.domain.DeskPlace;
import com.ruoyi.billiard.service.IDeskPlaceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 台桌区域Controller
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/billiard/deskPlace")
public class DeskPlaceController extends BaseController
{
    @Autowired
    private IDeskPlaceService deskPlaceService;

    /**
     * 查询台桌区域列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPlace:list')")
    @GetMapping("/list")
    public PageResVo<DeskPlace> list(DeskPlace deskPlace)
    {
        startPage();
        List<DeskPlace> list = deskPlaceService.selectDeskPlaceList(deskPlace);
        return PageResVo.success(list);
    }
    @GetMapping("/list/all")
    public ResultVo<List<DeskPlace>> listAll(DeskPlace deskPlace)
    {
        List<DeskPlace> list = deskPlaceService.selectDeskPlaceList(deskPlace);
        return ResultVo.success(list);
    }


    /**
     * 导出台桌区域列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPlace:export')")
    @Log(title = "台桌区域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeskPlace deskPlace)
    {
        List<DeskPlace> list = deskPlaceService.selectDeskPlaceList(deskPlace);
        ExcelUtil<DeskPlace> util = new ExcelUtil<DeskPlace>(DeskPlace.class);
        util.exportExcel(response, list, "台桌区域数据");
    }

    /**
     * 获取台桌区域详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPlace:query')")
    @GetMapping(value = "/{deskPlaceId}")
    public ResultVo<DeskPlace> getInfo(@PathVariable("deskPlaceId") Long deskPlaceId)
    {
        return ResultVo.success(deskPlaceService.selectDeskPlaceByDeskPlaceId(deskPlaceId));
    }

    /**
     * 新增台桌区域
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPlace:add')")
    @Log(title = "台桌区域", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody DeskPlace deskPlace)
    {
        return ResultVo.success(deskPlaceService.insertDeskPlace(deskPlace));
    }

    /**
     * 修改台桌区域
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPlace:edit')")
    @Log(title = "台桌区域", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody DeskPlace deskPlace)
    {
        return ResultVo.success(deskPlaceService.updateDeskPlace(deskPlace));
    }

    /**
     * 删除台桌区域
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPlace:remove')")
    @Log(title = "台桌区域", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deskPlaceIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] deskPlaceIds)
    {
        return  ResultVo.success(deskPlaceService.deleteDeskPlaceByDeskPlaceIds(deskPlaceIds));
    }
}
