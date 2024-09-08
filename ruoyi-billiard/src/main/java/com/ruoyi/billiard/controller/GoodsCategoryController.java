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
import com.ruoyi.billiard.domain.GoodsCategory;
import com.ruoyi.billiard.service.IGoodsCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品分类Controller
 * 
 * @author ruoyi
 * @date 2024-09-08
 */
@RestController
@RequestMapping("/billiard/category")
public class GoodsCategoryController extends BaseController
{
    @Autowired
    private IGoodsCategoryService goodsCategoryService;

    /**
     * 查询商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:category:list')")
    @GetMapping("/list")
    public PageResVo<GoodsCategory> list(GoodsCategory goodsCategory)
    {
        startPage();
        List<GoodsCategory> list = goodsCategoryService.selectGoodsCategoryList(goodsCategory);
        return PageResVo.success(list);
    }
    @GetMapping("/list/all")
    public ResultVo<List<GoodsCategory>> listAll(GoodsCategory goodsCategory)
    {
        List<GoodsCategory> list = goodsCategoryService.selectGoodsCategoryList(goodsCategory);
        return ResultVo.success(list);
    }
    /**
     * 导出商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:category:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsCategory goodsCategory)
    {
        List<GoodsCategory> list = goodsCategoryService.selectGoodsCategoryList(goodsCategory);
        ExcelUtil<GoodsCategory> util = new ExcelUtil<GoodsCategory>(GoodsCategory.class);
        util.exportExcel(response, list, "商品分类数据");
    }

    /**
     * 获取商品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:category:query')")
    @GetMapping(value = "/{goodsCategoryId}")
    public ResultVo<GoodsCategory> getInfo(@PathVariable("goodsCategoryId") Long goodsCategoryId)
    {
        return ResultVo.success(goodsCategoryService.selectGoodsCategoryByGoodsCategoryId(goodsCategoryId));
    }

    /**
     * 新增商品分类
     */
    @PreAuthorize("@ss.hasPermi('billiard:category:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody GoodsCategory goodsCategory)
    {
        return ResultVo.success(goodsCategoryService.insertGoodsCategory(goodsCategory));
    }

    /**
     * 修改商品分类
     */
    @PreAuthorize("@ss.hasPermi('billiard:category:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody GoodsCategory goodsCategory)
    {
        return ResultVo.success(goodsCategoryService.updateGoodsCategory(goodsCategory));
    }

    /**
     * 删除商品分类
     */
    @PreAuthorize("@ss.hasPermi('billiard:category:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsCategoryIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] goodsCategoryIds)
    {
        return  ResultVo.success(goodsCategoryService.deleteGoodsCategoryByGoodsCategoryIds(goodsCategoryIds));
    }
}
