package com.renxin.consultant.controller;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.PsyConsultPartner;
import com.renxin.psychology.domain.PsyConsultPartnerItem;
import com.renxin.psychology.dto.PartnerDTO;
import com.renxin.psychology.service.IPsyConsultPartnerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/consultant/partner")
@Api(value = "ConsultantWorkController" ,tags = {"咨询师入驻Api"})
public class ConsultantPartnerController {

    @Resource
    private ConsultantTokenService consultantTokenService;

    @Resource
    private IPsyConsultPartnerService partnerService;

    @PostMapping(value = "/addItem")
    @RateLimiter
    public AjaxResult addItem(@RequestBody PsyConsultPartnerItem item)
    {
        return AjaxResult.success(partnerService.addItem(item));
    }

    /**
     * 保存子信息
     * @param item
     * @return
     */
    @PostMapping(value = "/saveItem")
    @RateLimiter
    public AjaxResult saveItem(@RequestBody PsyConsultPartnerItem item)
    {
        return AjaxResult.success(partnerService.saveItem(item));
    }

    /**
     * 批量保存子信息
     * @param entity
     * @return
     */
    @PostMapping(value = "/saveItemList")
    @RateLimiter
    public AjaxResult saveItemList(@RequestBody PsyConsultPartner entity)
    {
        partnerService.saveItemList(entity);
        return AjaxResult.success();
    }
    
    @PostMapping(value = "/delItem/{id}")
    @RateLimiter
    public AjaxResult delItem(@PathVariable("id") Long id)
    {
        return AjaxResult.success(partnerService.delItem(id));
    }

    //生成申请单草稿
    @PostMapping(value = "/draft")
    @RateLimiter
    public AjaxResult draft(HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        return partnerService.consultantDraft(consultId);
    }

    //修改申请单主体信息
    @PostMapping(value = "/save")
    @RateLimiter
    public AjaxResult save(@RequestBody PsyConsultPartner entity, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        entity.setConsultId(consultId);
        return partnerService.saveByConsultId(entity);
    }

    @PostMapping(value = "/getInfo")
    @RateLimiter
    public AjaxResult getInfo(HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        PartnerDTO partnerDTO = partnerService.getInfoByConsultId(consultId);
        return AjaxResult.success(partnerDTO);
    }

    @PostMapping(value = "/getDetail")
    @RateLimiter
    public AjaxResult getDetail(HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        return AjaxResult.success(partnerService.getDetailByConsultId(consultId));
    }

    /**
     * uni 实人认证
     * https://doc.dcloud.net.cn/uniCloud/frv/intro.html
     *
     * @param entity
     * @param request
     * @return
     */
  /*  @PostMapping(value = "/facialAuth")
    @RateLimiter
    public AjaxResult facialAuth(PsyConsultPartner entity,HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        entity.setUserId(userId);
        entity.setStatus(ConsultConstant.PARTNER_STATUS_1);
        return AjaxResult.success(partnerService.save(entity));
    }*/
}
