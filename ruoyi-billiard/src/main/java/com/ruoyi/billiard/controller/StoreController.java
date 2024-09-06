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
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门店Controller
 * 
 * @author ruoyi
 * @date 2024-09-05
 */
@RestController
@RequestMapping("/billiard/store")
public class StoreController extends BaseController
{
    @Autowired
    private IStoreService storeService;

    /**
     * 查询门店列表
     */
    /**
     * 查询门店列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:store:list')")
    @GetMapping("/list")
    public PageResVo<Store> list(Store store)
    {
        startPage();
        List<Store> list = storeService.selectStoreList(store);
        return PageResVo.success(list);
    }
    @GetMapping("/list/all")
    public ResultVo<List<Store>> listAll( )
    {
        List<Store> list = storeService.selectAll();
        return ResultVo.success(list);
    }

    /**
     * 导出门店列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:store:export')")
    @Log(title = "门店", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Store store)
    {
        List<Store> list = storeService.selectStoreList(store);
        ExcelUtil<Store> util = new ExcelUtil<Store>(Store.class);
        util.exportExcel(response, list, "门店数据");
    }

    /**
     * 获取门店详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:store:query')")
    @GetMapping(value = "/{storeId}")
    public AjaxResult getInfo(@PathVariable("storeId") Long storeId)
    {
        return success(storeService.selectStoreByStoreId(storeId));
    }

    /**
     * 新增门店
     */
    @PreAuthorize("@ss.hasPermi('billiard:store:add')")
    @Log(title = "门店", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Store store)
    {
        store.setCreateBy(getUsername());
        store.setUpdateBy(getUsername());
        return toAjax(storeService.insertStore(store));
    }

    /**
     * 修改门店
     */
    @PreAuthorize("@ss.hasPermi('billiard:store:edit')")
    @Log(title = "门店", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Store store)
    {
        store.setUpdateBy(getUsername());
        return toAjax(storeService.updateStore(store));
    }

    /**
     * 删除门店
     */
    @PreAuthorize("@ss.hasPermi('billiard:store:remove')")
    @Log(title = "门店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{storeIds}")
    public AjaxResult remove(@PathVariable Long[] storeIds)
    {
        return toAjax(storeService.deleteStoreByStoreIds(storeIds));
    }
}
