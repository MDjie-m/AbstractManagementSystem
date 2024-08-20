package com.ruoyi.system.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysProductType;
import com.ruoyi.system.service.ISysProductTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品分类Controller
 * 
 * @author xgg
 * @date 2024-07-22
 */
@RestController
@RequestMapping("/system/productType")
public class SysProductTypeController extends BaseController
{
    @Autowired
    private ISysProductTypeService sysProductTypeService;

    /**
     * 查询产品分类列表
     */
//    @PreAuthorize("@ss.hasPermi('system:productType:list')")
    @Anonymous
    @GetMapping("/list")
    public AjaxResult list(SysProductType sysProductType)
    {
        List<SysProductType> list = sysProductTypeService.selectSysProductTypeList(sysProductType);
        return success(list);
    }

    /**
     * @param depth 产品分类树的深度
     * @param classification 进口或国产0-国产,1-进口,2-国产进口
     * 查询产品分类列表，按照层级组装成树。
     */
//    @PreAuthorize("@ss.hasPermi('system:productType:list')")
    @Anonymous
    @GetMapping("/treeList")
    public AjaxResult treeList( Integer depth, Integer classification)
    {
        List<Map<String,Object>> list = sysProductTypeService.selectSysProductTypeTreeList(depth ,classification);
        return success(list);
    }

    /**
     * 导出产品分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:productType:export')")
    @Log(title = "产品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysProductType sysProductType)
    {
        List<SysProductType> list = sysProductTypeService.selectSysProductTypeList(sysProductType);
        ExcelUtil<SysProductType> util = new ExcelUtil<SysProductType>(SysProductType.class);
        util.exportExcel(response, list, "产品分类数据");
    }

    /**
     * 获取产品分类详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:productType:query')")
    @Anonymous
    @GetMapping(value = "/{productCode}")
    public AjaxResult getInfo(@PathVariable("productCode") String productCode)
    {
        return success(sysProductTypeService.selectSysProductTypeByProductCode(productCode));
    }

    /**
     * 新增产品分类
     */
    @PreAuthorize("@ss.hasPermi('system:productType:add')")
    @Log(title = "产品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysProductType sysProductType)
    {
        return toAjax(sysProductTypeService.insertSysProductType(sysProductType));
    }

    /**
     * 修改产品分类
     */
    @PreAuthorize("@ss.hasPermi('system:productType:edit')")
    @Log(title = "产品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysProductType sysProductType)
    {
        return toAjax(sysProductTypeService.updateSysProductType(sysProductType));
    }

    /**
     * 删除产品分类
     */
    @PreAuthorize("@ss.hasPermi('system:productType:remove')")
    @Log(title = "产品分类", businessType = BusinessType.DELETE)
	@PostMapping("/{productCodes}")
    public AjaxResult remove(@PathVariable String[] productCodes)
    {
        return toAjax(sysProductTypeService.deleteSysProductTypeByProductCodes(productCodes));
    }
}
