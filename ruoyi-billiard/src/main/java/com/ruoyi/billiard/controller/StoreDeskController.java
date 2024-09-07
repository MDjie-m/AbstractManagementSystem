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
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 球桌Controller
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@RestController
@RequestMapping("/billiard/desk")
public class StoreDeskController extends BaseController
{
    @Autowired
    private IStoreDeskService storeDeskService;

    /**
     * 查询球桌列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:desk:list')")
    @GetMapping("/list")
    public PageResVo<StoreDesk> list(StoreDesk storeDesk)
    {
        startPage();
        List<StoreDesk> list = storeDeskService.selectStoreDeskList(storeDesk);
        return PageResVo.success(list);
    }

    /**
     * 导出球桌列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:desk:export')")
    @Log(title = "球桌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreDesk storeDesk)
    {
        List<StoreDesk> list = storeDeskService.selectStoreDeskList(storeDesk);
        ExcelUtil<StoreDesk> util = new ExcelUtil<StoreDesk>(StoreDesk.class);
        util.exportExcel(response, list, "球桌数据");
    }

    /**
     * 获取球桌详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:desk:query')")
    @GetMapping(value = "/{deskId}")
    public ResultVo<StoreDesk> getInfo(@PathVariable("deskId") Long deskId)
    {
        return ResultVo.success(storeDeskService.selectStoreDeskByDeskId(deskId));
    }

    /**
     * 新增球桌
     */
    @PreAuthorize("@ss.hasPermi('billiard:desk:add')")
    @Log(title = "球桌", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody StoreDesk storeDesk)
    {
        return ResultVo.success(storeDeskService.insertStoreDesk(storeDesk));
    }

    /**
     * 修改球桌
     */
    @PreAuthorize("@ss.hasPermi('billiard:desk:edit')")
    @Log(title = "球桌", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody StoreDesk storeDesk)
    {
        return ResultVo.success(storeDeskService.updateStoreDesk(storeDesk));
    }

    /**
     * 删除球桌
     */
    @PreAuthorize("@ss.hasPermi('billiard:desk:remove')")
    @Log(title = "球桌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deskIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] deskIds)
    {
        return  ResultVo.success(storeDeskService.deleteStoreDeskByDeskIds(deskIds));
    }
}
