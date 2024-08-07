package com.ruoyi.system.controller;

import java.util.List;
import java.util.Map;
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
    @PreAuthorize("@ss.hasPermi('system:productType:list')")
    @GetMapping("/list")
    public AjaxResult list(SysProductType sysProductType)
    {
        List<SysProductType> list = sysProductTypeService.selectSysProductTypeList(sysProductType);
        return success(list);
    }

    /**
     * @param depth 产品分类树的深度
     * @param flag 进口或国产 0-国产 1-进口
     * 查询产品分类列表，按照层级组装成树。
     */
//    @PreAuthorize("@ss.hasPermi('system:productType:list')")
    @GetMapping("/treeList")
    public AjaxResult treeList(Integer depth, Integer flag)
    {
        List<Map<String,Object>> list = sysProductTypeService.selectSysProductTypeTreeList(depth ,flag);
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
    @PreAuthorize("@ss.hasPermi('system:productType:query')")
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
	@DeleteMapping("/{productCodes}")
    public AjaxResult remove(@PathVariable String[] productCodes)
    {
        return toAjax(sysProductTypeService.deleteSysProductTypeByProductCodes(productCodes));
    }
}
