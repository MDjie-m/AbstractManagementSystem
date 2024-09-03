package com.renxin.web.controller.psychology;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.renxin.psychology.request.PsyAdminPartnerReq;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.BusinessType;
import com.renxin.psychology.domain.PsyConsultPartner;
import com.renxin.psychology.service.IPsyConsultPartnerService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 咨询师入驻申请Controller
 * 
 * @author renxin
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/psychology/partner")
public class PsyConsultPartnerController extends BaseController
{

    @Resource
    private IPsyConsultPartnerService psyConsultPartnerService;

    /**
     * 查询咨询师入驻申请列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:partner:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyAdminPartnerReq req)
    {
        startPage();
        List<PsyConsultPartner> list = psyConsultPartnerService.getList(req);
        return getDataTable(list);
    }

    /**
     * 导出咨询师入驻申请列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:partner:export')")
    @Log(title = "咨询师入驻申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyAdminPartnerReq psyConsultPartner)
    {
        List<PsyConsultPartner> list = psyConsultPartnerService.getList(psyConsultPartner);
        ExcelUtil<PsyConsultPartner> util = new ExcelUtil<PsyConsultPartner>(PsyConsultPartner.class);
        util.exportExcel(response, list, "咨询师入驻申请数据");
    }

    /**
     * 获取咨询师入驻申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('psychology:partner:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultPartnerService.getInfoById(id));
    }

    /**
     * 修改咨询师入驻申请
     */
    @PreAuthorize("@ss.hasPermi('psychology:partner:edit')")
    @Log(title = "咨询师入驻申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultPartner psyConsultPartner)
    {
        return toAjax(psyConsultPartnerService.save(psyConsultPartner));
    }

    /**
     * 审批通过
     */
    @PreAuthorize("@ss.hasPermi('psychology:partner:account')")
    @Log(title = "咨询师账户开通", businessType = BusinessType.UPDATE)
    @PostMapping("/createUser/{id}")
    public AjaxResult createUser(@PathVariable("id") Long id)
    {
        return psyConsultPartnerService.createUser(id);
    }

}
