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
import com.ruoyi.billiard.domain.StoreSchedule;
import com.ruoyi.billiard.service.IStoreScheduleService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 门店班次Controller
 * 
 * @author zhoukeu
 * @date 2024-09-22
 */
@RestController
@RequestMapping("/billiard/schedule")
public class StoreScheduleController extends BaseController
{
    @Autowired
    private IStoreScheduleService storeScheduleService;

    /**
     * 查询门店班次列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:schedule:list')")
    @GetMapping("/list")
    public PageResVo<StoreSchedule> list(StoreSchedule storeSchedule)
    {
        startPage();
        List<StoreSchedule> list = storeScheduleService.selectStoreScheduleList(storeSchedule);
        return PageResVo.success(list);
    }

    /**
     * 导出门店班次列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:schedule:export')")
    @Log(title = "门店班次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreSchedule storeSchedule)
    {
        List<StoreSchedule> list = storeScheduleService.selectStoreScheduleList(storeSchedule);
        ExcelUtil<StoreSchedule> util = new ExcelUtil<StoreSchedule>(StoreSchedule.class);
        util.exportExcel(response, list, "门店班次数据");
    }

    /**
     * 获取门店班次详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:schedule:query')")
    @GetMapping(value = "/{storeScheduleId}")
    public ResultVo<StoreSchedule> getInfo(@PathVariable("storeScheduleId") Long storeScheduleId)
    {
        return ResultVo.success(storeScheduleService.selectStoreScheduleByStoreScheduleId(storeScheduleId));
    }

    /**
     * 新增门店班次
     */
    @PreAuthorize("@ss.hasPermi('billiard:schedule:add')")
    @Log(title = "门店班次", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody StoreSchedule storeSchedule)
    {
        return ResultVo.success(storeScheduleService.insertStoreSchedule(storeSchedule));
    }

    /**
     * 修改门店班次
     */
    @PreAuthorize("@ss.hasPermi('billiard:schedule:edit')")
    @Log(title = "门店班次", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody StoreSchedule storeSchedule)
    {
        return ResultVo.success(storeScheduleService.updateStoreSchedule(storeSchedule));
    }

    /**
     * 删除门店班次
     */
    @PreAuthorize("@ss.hasPermi('billiard:schedule:remove')")
    @Log(title = "门店班次", businessType = BusinessType.DELETE)
	@DeleteMapping("/{storeScheduleIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] storeScheduleIds)
    {
        return  ResultVo.success(storeScheduleService.deleteStoreScheduleByStoreScheduleIds(storeScheduleIds));
    }
}
