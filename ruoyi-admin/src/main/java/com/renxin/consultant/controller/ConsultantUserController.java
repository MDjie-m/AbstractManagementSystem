package com.renxin.consultant.controller;


import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.constant.Constants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.domain.dto.ConsultLoginDTO;
import com.renxin.common.core.domain.entity.SysDictType;
import com.renxin.consultant.common.dcloud.CloudFunctions;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.service.IPsyConsultConfigService;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.system.service.ISysDictDataService;
import com.renxin.system.service.ISysDictTypeService;
import com.renxin.web.controller.common.CommonCosController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/consultant/user")
public class ConsultantUserController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CommonCosController.class);

    @Resource
    ConsultantTokenService consultantTokenService;

    @Resource
    IPsyConsultService psyConsultService;

    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @Resource
    private IPsyConsultConfigService psyConsultConfigService;
    
    
    @PostMapping("/login")
    @RateLimiter
    public AjaxResult login(@RequestBody ConsultLoginDTO consultLoginDTO)
    {
        try {
           String phoneNumber= new CloudFunctions().getPhoneNumber(consultLoginDTO);
           //获取咨询师信息(若无则新增)
            PsyConsult psyConsult=psyConsultService.getByPhoneOrInsert(phoneNumber);
            ConsultDTO consultDTO=new ConsultDTO();
            consultDTO.setConsultId(psyConsult.getId());
            consultDTO.setPhone( psyConsult.getPhonenumber());
            String token= consultantTokenService.createToken(consultDTO,360000);
            return AjaxResult.success(Constants.TOKEN_PREFIX + token);
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
    /**
     * 获取字典清单
     */
    @PostMapping("/getDictTypeDataList")
    @RateLimiter
    public AjaxResult getDictList()
    {
        List<SysDictType> sysDictTypeList = dictTypeService.selectDictTypeDataList(null);
        return AjaxResult.success(sysDictTypeList);
    }

}
