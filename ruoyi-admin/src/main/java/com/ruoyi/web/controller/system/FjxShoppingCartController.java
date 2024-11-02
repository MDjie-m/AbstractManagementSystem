package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.FjxShoppingCart;
import com.ruoyi.system.domain.dto.FjxShopCartDto;
import com.ruoyi.system.service.IFjxShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物车Controller
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@RestController
@RequestMapping("/fjx/cart")
public class FjxShoppingCartController extends BaseController
{
    @Autowired
    private IFjxShoppingCartService fjxShoppingCartService;

    /**
     * 查询购物车列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:cart:list')")
    @GetMapping("/list")
    public TableDataInfo list(FjxShoppingCart fjxShoppingCart)
    {
        startPage();
        List<FjxShopCartDto> list = fjxShoppingCartService.selectFjxShoppingCartList2(fjxShoppingCart);
        return getDataTable(list);
    }

    /**
     * 导出购物车列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:cart:export')")
    @Log(title = "购物车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FjxShoppingCart fjxShoppingCart)
    {
        List<FjxShoppingCart> list = fjxShoppingCartService.selectFjxShoppingCartList(fjxShoppingCart);
        ExcelUtil<FjxShoppingCart> util = new ExcelUtil<FjxShoppingCart>(FjxShoppingCart.class);
        util.exportExcel(response, list, "购物车数据");
    }

    /**
     * 获取购物车详细信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:cart:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(fjxShoppingCartService.selectFjxShoppingCartById(id));
    }

    /**
     * 新增购物车
     */
    @PreAuthorize("@ss.hasPermi('fjx:cart:add')")
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FjxShoppingCart fjxShoppingCart)
    {
        return toAjax(fjxShoppingCartService.insertFjxShoppingCart(fjxShoppingCart));
    }

    /**
     * 修改购物车
     */
    @PreAuthorize("@ss.hasPermi('fjx:cart:edit')")
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FjxShoppingCart fjxShoppingCart)
    {
        return toAjax(fjxShoppingCartService.updateFjxShoppingCart(fjxShoppingCart));
    }

    /**
     * 删除购物车
     */
    @PreAuthorize("@ss.hasPermi('fjx:cart:remove')")
    @Log(title = "购物车", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fjxShoppingCartService.deleteFjxShoppingCartByIds(ids));
    }

//    public AjaxResult payOrder(@RequestBody FJXShopCard card){
//
//    }
}
