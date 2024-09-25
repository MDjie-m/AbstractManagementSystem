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
import com.ruoyi.billiard.domain.DeskBooking;
import com.ruoyi.billiard.service.IDeskBookingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 球桌预约Controller
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@RestController
@RequestMapping("/billiard/desk/booking")
public class DeskBookingController extends BaseController
{
    @Autowired
    private IDeskBookingService deskBookingService;

    /**
     * 查询球桌预约列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:list')")
    @GetMapping("/list")
    public PageResVo<DeskBooking> list(DeskBooking deskBooking)
    {
        startPage();
        List<DeskBooking> list = deskBookingService.selectDeskBookingList(deskBooking);
        return PageResVo.success(list);
    }

    /**
     * 导出球桌预约列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:export')")
    @Log(title = "球桌预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeskBooking deskBooking)
    {
        List<DeskBooking> list = deskBookingService.selectDeskBookingList(deskBooking);
        ExcelUtil<DeskBooking> util = new ExcelUtil<DeskBooking>(DeskBooking.class);
        util.exportExcel(response, list, "球桌预约数据");
    }

    /**
     * 获取球桌预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:query')")
    @GetMapping(value = "/{deskBookingId}")
    public ResultVo<DeskBooking> getInfo(@PathVariable("deskBookingId") Long deskBookingId)
    {
        return ResultVo.success(deskBookingService.selectDeskBookingByDeskBookingId(deskBookingId));
    }

    /**
     * 新增球桌预约
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:add')")
    @Log(title = "球桌预约", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<DeskBooking> add(@RequestBody DeskBooking deskBooking)
    {
        return ResultVo.success(deskBookingService.insertDeskBooking(deskBooking));
    }

    /**
     * 修改球桌预约
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:edit')")
    @Log(title = "球桌预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody DeskBooking deskBooking)
    {
        return ResultVo.success(deskBookingService.updateDeskBooking(deskBooking));
    }

    /**
     * 删除球桌预约
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:remove')")
    @Log(title = "球桌预约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deskBookingIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] deskBookingIds)
    {
        return  ResultVo.success(deskBookingService.deleteDeskBookingByDeskBookingIds(deskBookingIds));
    }
}
