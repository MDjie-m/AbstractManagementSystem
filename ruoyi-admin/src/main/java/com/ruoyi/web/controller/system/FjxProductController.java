package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.FjxProduct;
import com.ruoyi.system.service.IFjxProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@RestController
@RequestMapping("/fjx/product")
public class FjxProductController extends BaseController
{
    @Autowired
    private IFjxProductService fjxProductService;

    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(FjxProduct fjxProduct)
    {
        startPage();
        List<FjxProduct> list = fjxProductService.selectFjxProductList(fjxProduct);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:product:export')")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FjxProduct fjxProduct)
    {
        List<FjxProduct> list = fjxProductService.selectFjxProductList(fjxProduct);
        ExcelUtil<FjxProduct> util = new ExcelUtil<FjxProduct>(FjxProduct.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(fjxProductService.selectFjxProductById(id));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('fjx:product:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FjxProduct fjxProduct)
    {
        return toAjax(fjxProductService.insertFjxProduct(fjxProduct));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('fjx:product:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FjxProduct fjxProduct)
    {
        return toAjax(fjxProductService.updateFjxProduct(fjxProduct));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('fjx:product:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fjxProductService.deleteFjxProductByIds(ids));
    }
}
