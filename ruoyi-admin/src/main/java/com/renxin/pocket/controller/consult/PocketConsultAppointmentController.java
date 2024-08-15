package com.renxin.pocket.controller.consult;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyAppointment;
import com.renxin.psychology.service.IPsyAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 咨询师预约Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/pocket/appointment")
public class PocketConsultAppointmentController extends BaseController
{
    @Autowired
    private IPsyAppointmentService psyAppointmentService;

    /**
     * 查询咨询师预约列表
     */
    // @PreAuthorize("@ss.hasPermi('psychology:appointment:list')")
    @PostMapping("/list")
    public TableDataInfo list(PsyAppointment psyAppointment)
    {
        startPage();
        List<PsyAppointment> list = psyAppointmentService.selectPsyAppointmentList(psyAppointment);
        return getDataTable(list);
    }

    /**
     * 导出咨询师预约列表
     */
    // @PreAuthorize("@ss.hasPermi('psychology:appointment:export')")
    @Log(title = "咨询师预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyAppointment psyAppointment)
    {
        List<PsyAppointment> list = psyAppointmentService.selectPsyAppointmentList(psyAppointment);
        ExcelUtil<PsyAppointment> util = new ExcelUtil<PsyAppointment>(PsyAppointment.class);
        util.exportExcel(response, list, "咨询师预约数据");
    }

    /**
     * 获取咨询师预约详细信息
     */
    // @PreAuthorize("@ss.hasPermi('psychology:appointment:query')")
    @PostMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyAppointmentService.selectPsyAppointmentById(id));
    }

    /**
     * 新增咨询师预约
     */
    // @PreAuthorize("@ss.hasPermi('psychology:appointment:add')")
    @Log(title = "咨询师预约", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PsyAppointment psyAppointment)
    {
        return toAjax(psyAppointmentService.insertPsyAppointment(psyAppointment));
    }

    /**
     * 修改咨询师预约
     */
    // @PreAuthorize("@ss.hasPermi('psychology:appointment:edit')")
    @Log(title = "咨询师预约", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PsyAppointment psyAppointment)
    {
        return toAjax(psyAppointmentService.updatePsyAppointment(psyAppointment));
    }

    /**
     * 删除咨询师预约
     */
    // @PreAuthorize("@ss.hasPermi('psychology:appointment:remove')")
    @Log(title = "咨询师预约", businessType = BusinessType.DELETE)
    @PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyAppointmentService.deletePsyAppointmentByIds(ids));
    }
}
