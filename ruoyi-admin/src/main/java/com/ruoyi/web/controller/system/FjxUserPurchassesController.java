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
import com.ruoyi.system.domain.FjxUserPurchasses;
import com.ruoyi.system.service.IFjxUserPurchassesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户购买商品Controller
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@RestController
@RequestMapping("/fjx/purchasses")
public class FjxUserPurchassesController extends BaseController
{
    @Autowired
    private IFjxUserPurchassesService fjxUserPurchassesService;

    /**
     * 查询用户购买商品列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:purchasses:list')")
    @GetMapping("/list")
    public TableDataInfo list(FjxUserPurchasses fjxUserPurchasses)
    {
        startPage();
        List<FjxUserPurchasses> list = fjxUserPurchassesService.selectFjxUserPurchassesList(fjxUserPurchasses);
        return getDataTable(list);
    }

    /**
     * 导出用户购买商品列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:purchasses:export')")
    @Log(title = "用户购买商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FjxUserPurchasses fjxUserPurchasses)
    {
        List<FjxUserPurchasses> list = fjxUserPurchassesService.selectFjxUserPurchassesList(fjxUserPurchasses);
        ExcelUtil<FjxUserPurchasses> util = new ExcelUtil<FjxUserPurchasses>(FjxUserPurchasses.class);
        util.exportExcel(response, list, "用户购买商品数据");
    }

    /**
     * 获取用户购买商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:purchasses:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(fjxUserPurchassesService.selectFjxUserPurchassesById(id));
    }

    /**
     * 新增用户购买商品
     */
    @PreAuthorize("@ss.hasPermi('fjx:purchasses:add')")
    @Log(title = "用户购买商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FjxUserPurchasses fjxUserPurchasses)
    {
        return toAjax(fjxUserPurchassesService.insertFjxUserPurchasses(fjxUserPurchasses));
    }

    /**
     * 修改用户购买商品
     */
    @PreAuthorize("@ss.hasPermi('fjx:purchasses:edit')")
    @Log(title = "用户购买商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FjxUserPurchasses fjxUserPurchasses)
    {
        return toAjax(fjxUserPurchassesService.updateFjxUserPurchasses(fjxUserPurchasses));
    }

    /**
     * 删除用户购买商品
     */
    @PreAuthorize("@ss.hasPermi('fjx:purchasses:remove')")
    @Log(title = "用户购买商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fjxUserPurchassesService.deleteFjxUserPurchassesByIds(ids));
    }
}
