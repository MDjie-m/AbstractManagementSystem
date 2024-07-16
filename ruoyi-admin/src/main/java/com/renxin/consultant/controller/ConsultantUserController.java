package com.renxin.consultant.controller;


import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.domain.dto.ConsultLoginDTO;
import com.renxin.consultant.common.dcloud.CloudFunctions;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.web.controller.common.CommonCosController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/consultant/user")
public class ConsultantUserController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CommonCosController.class);

    @Resource
    ConsultantTokenService consultantTokenService;

    @Resource
    IPsyConsultService psyConsultService;

    @GetMapping("/login")
    @RateLimiter
    public AjaxResult login(ConsultLoginDTO consultLoginDTO)
    {
        try {
           String phoneNumber= new CloudFunctions().getPhoneNumber(consultLoginDTO);
           //获取咨询师信息
            PsyConsult psyConsult=psyConsultService.getByPhone(phoneNumber);
            ConsultDTO consultDTO=new ConsultDTO();
            consultDTO.setConsultId(psyConsult.getUserId());
            consultDTO.setPhone( psyConsult.getPhonenumber());
            String token= consultantTokenService.createToken(consultDTO,360000);
            return AjaxResult.success(token);
        } catch (Exception e) {
            log.error("login error",e);
            return AjaxResult.error("login error");
        }
    }

    @GetMapping("/getUserInfo")
    public AjaxResult getUserInfo(HttpServletRequest request)
    {
        try {
            Long consultId = consultantTokenService.getConsultId(request);
            PsyConsultVO one = psyConsultService.getOne(consultId);
            return AjaxResult.success(one);
        } catch (Exception e) {
            log.error("login error",e);
            return AjaxResult.error("login error");
        }
    }

}
