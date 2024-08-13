package com.renxin.consultant.controller;


import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsultantWorkTemplate;
import com.renxin.psychology.service.IPsyConsultantWorkTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 咨询师排程模版Controller
 * 
 * @author renxin
 * @date 2024-08-13
 */
@RestController
@RequestMapping("/consultant/workTemplate")
public class ConsultantWorkTemplateController extends BaseController
{
    @Autowired
    private IPsyConsultantWorkTemplateService psyConsultantWorkTemplateService;

    @Resource
    ConsultantTokenService consultantTokenService;

    /**
     * 查询咨询师排程模版列表
     */
    //@PreAuthorize("@ss.hasPermi('system:template:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantWorkTemplate req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setConsultantId(consultId);
        startPage();
        List<PsyConsultantWorkTemplate> list = psyConsultantWorkTemplateService.selectPsyConsultantWorkTemplateList(req);
        return getDataTable(list);
    }

    /**
     * 获取咨询师排程模版详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:template:query')")
    @PostMapping(value = "/queryById")
    public AjaxResult getInfo(@RequestBody PsyConsultantWorkTemplate req)
    {
        return AjaxResult.success(psyConsultantWorkTemplateService.selectPsyConsultantWorkTemplateById(req.getId()));
    }

    /**
     * 新增咨询师排程模版
     */
    //@PreAuthorize("@ss.hasPermi('system:template:add')")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PsyConsultantWorkTemplate req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setConsultantId(consultId);
        int i = psyConsultantWorkTemplateService.insertPsyConsultantWorkTemplate(req);
        
        return toAjax(i);
    }

    /**
     * 修改咨询师排程模版
     */
    //@PreAuthorize("@ss.hasPermi('system:template:edit')")
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PsyConsultantWorkTemplate req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setConsultantId(consultId);
        int i = psyConsultantWorkTemplateService.updatePsyConsultantWorkTemplate(req);
        return toAjax(i);
    }

    /**
     * 删除咨询师排程模版
     */
    //@PreAuthorize("@ss.hasPermi('system:template:remove')")
	/*@PostMapping("/{ids}")
    public AjaxResult remove(@RequestBody PsyConsultantWorkTemplate req)
    {
        return toAjax(psyConsultantWorkTemplateService.deletePsyConsultantWorkTemplateByIds(null));
    }*/
}
