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
import com.ruoyi.system.domain.FjxStorage;
import com.ruoyi.system.service.IFjxStorageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存放购物车Controller
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@RestController
@RequestMapping("/fjx/storage")
public class FjxStorageController extends BaseController
{
    @Autowired
    private IFjxStorageService fjxStorageService;

    /**
     * 查询存放购物车列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:storage:list')")
    @GetMapping("/list")
    public TableDataInfo list(FjxStorage fjxStorage)
    {
        startPage();
        List<FjxStorage> list = fjxStorageService.selectFjxStorageList(fjxStorage);
        return getDataTable(list);
    }

    /**
     * 导出存放购物车列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:storage:export')")
    @Log(title = "存放购物车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FjxStorage fjxStorage)
    {
        List<FjxStorage> list = fjxStorageService.selectFjxStorageList(fjxStorage);
        ExcelUtil<FjxStorage> util = new ExcelUtil<FjxStorage>(FjxStorage.class);
        util.exportExcel(response, list, "存放购物车数据");
    }

    /**
     * 获取存放购物车详细信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:storage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(fjxStorageService.selectFjxStorageById(id));
    }

    /**
     * 新增存放购物车
     */
    @PreAuthorize("@ss.hasPermi('fjx:storage:add')")
    @Log(title = "存放购物车", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FjxStorage fjxStorage)
    {
        return toAjax(fjxStorageService.insertFjxStorage(fjxStorage));
    }

    /**
     * 修改存放购物车
     */
    @PreAuthorize("@ss.hasPermi('fjx:storage:edit')")
    @Log(title = "存放购物车", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FjxStorage fjxStorage)
    {
        return toAjax(fjxStorageService.updateFjxStorage(fjxStorage));
    }

    /**
     * 删除存放购物车
     */
    @PreAuthorize("@ss.hasPermi('fjx:storage:remove')")
    @Log(title = "存放购物车", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fjxStorageService.deleteFjxStorageByIds(ids));
    }
}
