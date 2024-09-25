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
import com.ruoyi.billiard.domain.DeskImage;
import com.ruoyi.billiard.service.IDeskImageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 球桌抓拍等Controller
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@RestController
@RequestMapping("/billiard/desk/image")
public class DeskImageController extends BaseController
{
    @Autowired
    private IDeskImageService deskImageService;

    /**
     * 查询球桌抓拍等列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:image:list')")
    @GetMapping("/list")
    public PageResVo<DeskImage> list(DeskImage deskImage)
    {
        startPage();
        List<DeskImage> list = deskImageService.selectDeskImageList(deskImage);
        return PageResVo.success(list);
    }

    /**
     * 导出球桌抓拍等列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:image:export')")
    @Log(title = "球桌抓拍等", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeskImage deskImage)
    {
        List<DeskImage> list = deskImageService.selectDeskImageList(deskImage);
        ExcelUtil<DeskImage> util = new ExcelUtil<DeskImage>(DeskImage.class);
        util.exportExcel(response, list, "球桌抓拍等数据");
    }

    /**
     * 获取球桌抓拍等详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:image:query')")
    @GetMapping(value = "/{deskMultimediaId}")
    public ResultVo<DeskImage> getInfo(@PathVariable("deskMultimediaId") Long deskMultimediaId)
    {
        return ResultVo.success(deskImageService.selectDeskImageByDeskMultimediaId(deskMultimediaId));
    }

    /**
     * 新增球桌抓拍等
     */
    @PreAuthorize("@ss.hasPermi('billiard:image:add')")
    @Log(title = "球桌抓拍等", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody DeskImage deskImage)
    {
        return ResultVo.success(deskImageService.insertDeskImage(deskImage));
    }

    /**
     * 修改球桌抓拍等
     */
    @PreAuthorize("@ss.hasPermi('billiard:image:edit')")
    @Log(title = "球桌抓拍等", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody DeskImage deskImage)
    {
        return ResultVo.success(deskImageService.updateDeskImage(deskImage));
    }

    /**
     * 删除球桌抓拍等
     */
    @PreAuthorize("@ss.hasPermi('billiard:image:remove')")
    @Log(title = "球桌抓拍等", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deskMultimediaIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] deskMultimediaIds)
    {
        return  ResultVo.success(deskImageService.deleteDeskImageByDeskMultimediaIds(deskMultimediaIds));
    }
}
