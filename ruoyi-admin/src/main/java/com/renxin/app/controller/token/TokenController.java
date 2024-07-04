package com.renxin.app.controller.token;

import com.renxin.common.constant.Constants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.domain.entity.SysUser;
import com.renxin.common.core.domain.model.LoginUser;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.framework.web.service.TokenService;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController extends BaseController {

    @Resource
    private ConsultantTokenService consultantTokenService;

    @Resource
    private TokenService tokenService;

    @Resource
    private IPsyConsultService psyConsultService;

    @Resource
    private ISysUserService sysUserService;

    @GetMapping("/getToken")
    public AjaxResult getToken(@RequestParam("type") Integer type, @RequestParam("id") Long id){
        String token = "";
        
        if (type == 1){//管理员
            SysUser sysUser = sysUserService.selectUserById(id);
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(sysUser.getUserId());
            // loginUser.setp(psyConsult.getPhonenumber());
            loginUser.setUser(sysUser);
             token = tokenService.createToken(loginUser);
        }
        if (type == 2){//咨询师
            PsyConsultVO psyConsult= psyConsultService.getOne(id);
            ConsultDTO consultDTO=new ConsultDTO();
            consultDTO.setConsultId(psyConsult.getId());
            consultDTO.setPhone(psyConsult.getPhonenumber());
             token = consultantTokenService.createToken(consultDTO,3600000);
        }
        
        log.info("token:{}",token);
        return AjaxResult.successData(Constants.TOKEN_PREFIX+ token);
      
    }
    
    @GetMapping("/consultant/uuid/{id}")
    public AjaxResult getConsultantToken(@PathVariable("id") Long id){
        PsyConsultVO psyConsult= psyConsultService.getOne(id);
        ConsultDTO consultDTO=new ConsultDTO();
        consultDTO.setConsultId(psyConsult.getId());
        consultDTO.setPhone(psyConsult.getPhonenumber());
        String token = consultantTokenService.createToken(consultDTO,3600000);
        log.info("token:{}",token);
        return AjaxResult.successData(Constants.TOKEN_PREFIX+ token);
    }

    @GetMapping("/admin/uuid/{id}")
    public AjaxResult getAdminToken(@PathVariable("id") Long id){
        SysUser sysUser = sysUserService.selectUserById(id);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(sysUser.getUserId());
       // loginUser.setp(psyConsult.getPhonenumber());
        loginUser.setUser(sysUser);
        String token = tokenService.createToken(loginUser);
        log.info("token:{}",token);
        return AjaxResult.successData(Constants.TOKEN_PREFIX+ token);
    }
}
