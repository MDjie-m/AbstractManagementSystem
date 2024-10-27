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
import com.ruoyi.system.domain.FjxCollection;
import com.ruoyi.system.service.IFjxCollectionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收藏Controller
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@RestController
@RequestMapping("/fjx/collection")
public class FjxCollectionController extends BaseController
{
    @Autowired
    private IFjxCollectionService fjxCollectionService;

    /**
     * 查询收藏列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:collection:list')")
    @GetMapping("/list")
    public TableDataInfo list(FjxCollection fjxCollection)
    {
        startPage();
        List<FjxCollection> list = fjxCollectionService.selectFjxCollectionList(fjxCollection);
        return getDataTable(list);
    }

    /**
     * 导出收藏列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:collection:export')")
    @Log(title = "收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FjxCollection fjxCollection)
    {
        List<FjxCollection> list = fjxCollectionService.selectFjxCollectionList(fjxCollection);
        ExcelUtil<FjxCollection> util = new ExcelUtil<FjxCollection>(FjxCollection.class);
        util.exportExcel(response, list, "收藏数据");
    }

    /**
     * 获取收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:collection:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(fjxCollectionService.selectFjxCollectionById(id));
    }

    /**
     * 新增收藏
     */
    @PreAuthorize("@ss.hasPermi('fjx:collection:add')")
    @Log(title = "收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FjxCollection fjxCollection)
    {
        return toAjax(fjxCollectionService.insertFjxCollection(fjxCollection));
    }

    /**
     * 修改收藏
     */
    @PreAuthorize("@ss.hasPermi('fjx:collection:edit')")
    @Log(title = "收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FjxCollection fjxCollection)
    {
        return toAjax(fjxCollectionService.updateFjxCollection(fjxCollection));
    }

    /**
     * 删除收藏
     */
    @PreAuthorize("@ss.hasPermi('fjx:collection:remove')")
    @Log(title = "收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fjxCollectionService.deleteFjxCollectionByIds(ids));
    }
}
