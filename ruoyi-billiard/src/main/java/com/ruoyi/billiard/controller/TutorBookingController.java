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
import com.ruoyi.billiard.domain.TutorBooking;
import com.ruoyi.billiard.service.ITutorBookingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教练预约Controller
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@RestController
@RequestMapping("/billiard/tutor/booking")
public class TutorBookingController extends BaseController
{
    @Autowired
    private ITutorBookingService tutorBookingService;

    /**
     * 查询教练预约列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:list')")
    @GetMapping("/list")
    public PageResVo<TutorBooking> list(TutorBooking tutorBooking)
    {
        startPage();
        List<TutorBooking> list = tutorBookingService.selectTutorBookingList(tutorBooking);
        return PageResVo.success(list);
    }

    /**
     * 导出教练预约列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:export')")
    @Log(title = "教练预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TutorBooking tutorBooking)
    {
        List<TutorBooking> list = tutorBookingService.selectTutorBookingList(tutorBooking);
        ExcelUtil<TutorBooking> util = new ExcelUtil<TutorBooking>(TutorBooking.class);
        util.exportExcel(response, list, "教练预约数据");
    }

    /**
     * 获取教练预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:query')")
    @GetMapping(value = "/{tutorBookingId}")
    public ResultVo<TutorBooking> getInfo(@PathVariable("tutorBookingId") Long tutorBookingId)
    {
        return ResultVo.success(tutorBookingService.selectTutorBookingByTutorBookingId(tutorBookingId));
    }

    /**
     * 新增教练预约
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:add')")
    @Log(title = "教练预约", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<TutorBooking> add(@RequestBody TutorBooking tutorBooking)
    {
        return ResultVo.success(tutorBookingService.insertTutorBooking(tutorBooking));
    }

    /**
     * 修改教练预约
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:edit')")
    @Log(title = "教练预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody TutorBooking tutorBooking)
    {
        return ResultVo.success(tutorBookingService.updateTutorBooking(tutorBooking));
    }

    /**
     * 删除教练预约
     */
    @PreAuthorize("@ss.hasPermi('billiard:booking:remove')")
    @Log(title = "教练预约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tutorBookingIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] tutorBookingIds)
    {
        return  ResultVo.success(tutorBookingService.deleteTutorBookingByTutorBookingIds(tutorBookingIds));
    }
}
