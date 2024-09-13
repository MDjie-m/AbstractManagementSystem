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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.billiard.domain.DeskPrice;
import com.ruoyi.billiard.service.IDeskPriceService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 球桌价格Controller
 *
 * @author zhoukeu
 * @date 2024-09-14
 */
@RestController
@RequestMapping("/billiard/deskPrice")
public class DeskPriceController extends BaseController
{
    @Autowired
    private IDeskPriceService deskPriceService;

    /**
     * 查询球桌价格列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPrice:list')")
    @GetMapping("/list")
    public PageResVo<DeskPrice> list(DeskPrice deskPrice)
    {
        startPage();
        List<DeskPrice> list = deskPriceService.selectDeskPriceList(deskPrice);
        return PageResVo.success(list);
    }

    /**
     * 导出球桌价格列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPrice:export')")
    @Log(title = "球桌价格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeskPrice deskPrice)
    {
        List<DeskPrice> list = deskPriceService.selectDeskPriceList(deskPrice);
        ExcelUtil<DeskPrice> util = new ExcelUtil<DeskPrice>(DeskPrice.class);
        util.exportExcel(response, list, "球桌价格数据");
    }

    /**
     * 获取球桌价格详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPrice:query')")
    @GetMapping(value = "/{deskPriceId}")
    public ResultVo<DeskPrice> getInfo(@PathVariable("deskPriceId") Long deskPriceId)
    {
        return ResultVo.success(deskPriceService.selectDeskPriceByDeskPriceId(deskPriceId));
    }

    /**
     * 新增球桌价格
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPrice:add')")
    @Log(title = "球桌价格", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody DeskPrice deskPrice)
    {
        return ResultVo.success(deskPriceService.insertDeskPrice(deskPrice));
    }

    /**
     * 修改球桌价格
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPrice:edit')")
    @Log(title = "球桌价格", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody DeskPrice deskPrice)
    {
        return ResultVo.success(deskPriceService.updateDeskPrice(deskPrice));
    }

    /**
     * 删除球桌价格
     */
    @PreAuthorize("@ss.hasPermi('billiard:deskPrice:remove')")
    @Log(title = "球桌价格", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deskPriceIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] deskPriceIds)
    {
        return  ResultVo.success(deskPriceService.deleteDeskPriceByDeskPriceIds(deskPriceIds));
    }
}
