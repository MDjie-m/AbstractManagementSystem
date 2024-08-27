package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.dto.SysProDuctDTO;
import com.ruoyi.system.service.ISysProductService;
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
import com.ruoyi.system.domain.SysInquiry;
import com.ruoyi.system.service.ISysInquiryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author tyc
 * @date 2024-08-26
 */
@RestController
@RequestMapping("/system/inquiry")
public class SysInquiryController extends BaseController
{
    @Autowired
    private ISysInquiryService sysInquiryService;
    @Autowired
    private ISysProductService sysProductService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysInquiry sysInquiry)
    {
        startPage();
        List<SysInquiry> list = sysInquiryService.selectSysInquiryList(sysInquiry);
        return getDataTable(list);
    }


    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:query')")
    @GetMapping(value = "/{inquiryId}")
    public AjaxResult getInfo(@PathVariable("inquiryId") String inquiryId)
    {
        return success(sysInquiryService.selectSysInquiryByInquiryId(inquiryId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysInquiry sysInquiry)
    {
        return toAjax(sysInquiryService.insertSysInquiry(sysInquiry));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysInquiry sysInquiry)
    {
        return toAjax(sysInquiryService.updateSysInquiry(sysInquiry));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inquiryIds}")
    public AjaxResult remove(@PathVariable String[] inquiryIds)
    {
        return toAjax(sysInquiryService.deleteSysInquiryByInquiryIds(inquiryIds));
    }

    /**
     * 切换询价清单状态
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:add')")
    @PostMapping("/updateInquiryListStatus")
    public AjaxResult updateInquiryListStatus(@RequestBody SysProDuctDTO sysProDuctDTO)
    {
        return toAjax(sysProductService.updateInquiryListStatus(sysProDuctDTO));
    }

}
