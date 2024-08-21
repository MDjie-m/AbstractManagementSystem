package com.ruoyi.test_data_scope.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.test_data_scope.domain.Picture;
import com.ruoyi.test_data_scope.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 测试数据权限Controller
 *
 * @author zwj
 * @date 2024-08-21
 */
@RestController
@RequestMapping("/zwj/picture")
public class PictureController extends BaseController
{
    @Autowired
    private IPictureService pictureService;

    /**
     * 查询测试数据权限列表
     */
    @PreAuthorize("@ss.hasPermi('zwj:picture:list')")
    @GetMapping("/list")
    public TableDataInfo list(Picture picture)
    {
        startPage();
        List<Picture> list = pictureService.selectPictureList(picture);
        return getDataTable(list);
    }

    /**
     * 导出测试数据权限列表
     */
    @PreAuthorize("@ss.hasPermi('zwj:picture:export')")
    @Log(title = "测试数据权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Picture picture)
    {
        List<Picture> list = pictureService.selectPictureList(picture);
        ExcelUtil<Picture> util = new ExcelUtil<Picture>(Picture.class);
        util.exportExcel(response, list, "测试数据权限数据");
    }

    /**
     * 获取测试数据权限详细信息
     */
    @PreAuthorize("@ss.hasPermi('zwj:picture:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pictureService.selectPictureById(id));
    }

    /**
     * 新增测试数据权限
     */
    @PreAuthorize("@ss.hasPermi('zwj:picture:add')")
    @Log(title = "测试数据权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Picture picture)
    {
        return toAjax(pictureService.insertPicture(picture));
    }

    /**
     * 修改测试数据权限
     */
    @PreAuthorize("@ss.hasPermi('zwj:picture:edit')")
    @Log(title = "测试数据权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Picture picture)
    {
        return toAjax(pictureService.updatePicture(picture));
    }

    /**
     * 删除测试数据权限
     */
    @PreAuthorize("@ss.hasPermi('zwj:picture:remove')")
    @Log(title = "测试数据权限", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pictureService.deletePictureByIds(ids));
    }
}
