package com.renxin.consulted.controller.notice;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.renxin.notice.domain.PsyCourNotice;
import com.renxin.notice.service.IPsyCourNoticeService;
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
import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 咨询师、用户通知Controller
 * 
 * @author renxin
 * @date 2024-06-17
 */
@RestController
@RequestMapping("/customer/notice")
public class PsyCourNoticeController extends BaseController
{
    @Autowired
    private IPsyCourNoticeService psyCourNoticeService;

    /**
     * 查询咨询师、用户通知列表
     */
    @PreAuthorize("@ss.hasPermi('customer:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyCourNotice psyCourNotice)
    {
        startPage();
        List<PsyCourNotice> list = psyCourNoticeService.selectPsyCourNoticeList(psyCourNotice);
        return getDataTable(list);
    }

    /**
     * 导出咨询师、用户通知列表
     */
    @PreAuthorize("@ss.hasPermi('customer:notice:export')")
    @Log(title = "咨询师、用户通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyCourNotice psyCourNotice)
    {
        List<PsyCourNotice> list = psyCourNoticeService.selectPsyCourNoticeList(psyCourNotice);
        ExcelUtil<PsyCourNotice> util = new ExcelUtil<PsyCourNotice>(PsyCourNotice.class);
        util.exportExcel(response, list, "咨询师、用户通知数据");
    }

    /**
     * 获取咨询师、用户通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Long noticeId)
    {
        return AjaxResult.success(psyCourNoticeService.selectPsyCourNoticeByNoticeId(noticeId));
    }

    /**
     * 新增咨询师、用户通知
     */
    @PreAuthorize("@ss.hasPermi('customer:notice:add')")
    @Log(title = "咨询师、用户通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyCourNotice psyCourNotice)
    {
        return toAjax(psyCourNoticeService.insertPsyCourNotice(psyCourNotice));
    }

    /**
     * 修改咨询师、用户通知
     */
    @PreAuthorize("@ss.hasPermi('customer:notice:edit')")
    @Log(title = "咨询师、用户通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyCourNotice psyCourNotice)
    {
        return toAjax(psyCourNoticeService.updatePsyCourNotice(psyCourNotice));
    }

    /**
     * 删除咨询师、用户通知
     */
    @PreAuthorize("@ss.hasPermi('customer:notice:remove')")
    @Log(title = "咨询师、用户通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(psyCourNoticeService.deletePsyCourNoticeByNoticeIds(noticeIds));
    }
}
