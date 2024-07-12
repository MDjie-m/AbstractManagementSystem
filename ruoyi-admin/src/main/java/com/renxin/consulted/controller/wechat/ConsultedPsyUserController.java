package com.renxin.consulted.controller.wechat;

import com.renxin.common.constant.RespMessageConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.framework.web.service.ConsultedTokenService;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.service.IPsyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/consulted/user")
@Api(value = "ConsultedPsyUserController" ,tags = {"app用户操作api"})
public class ConsultedPsyUserController extends BaseController
{
    @Autowired
    private IPsyUserService psyUserService;

    @Autowired
    private ConsultedTokenService consultedTokenService;

    /**
     * 微信用户绑定手机号
     */
    @ApiOperation("微信用户绑定手机号")
    @PostMapping("/bindPhone")
    public AjaxResult bindPhone(@RequestBody PsyUser psyUser , HttpServletRequest request)
    {
        LoginDTO loginUser = consultedTokenService.getLoginUser(request);
        loginUser.setPhone(psyUser.getPhone());
        psyUserService.bindPhone(loginUser);
        consultedTokenService.refreshToken(loginUser ,null);
        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS);
    }

    /**
     * 查看来访者用户信息
     */
    @ApiOperation("查看来访者用户信息")
    @PostMapping("/info")
    public AjaxResult info(HttpServletRequest request)
    {
        LoginDTO loginUser = consultedTokenService.getLoginUser(request);
        PsyUser psyUser = psyUserService.selectPsyUserById(loginUser.getUserId());
            psyUser.setId(null);
        return AjaxResult.success(psyUser);
    }
    
    /**
     * 修改来访者用户信息
     */
    @ApiOperation("修改来访者用户信息")
    @PostMapping("/update")
    public AjaxResult update(@RequestBody PsyUser psyUser , HttpServletRequest request)
    {
        LoginDTO loginUser = consultedTokenService.getLoginUser(request);
            psyUser.setId(loginUser.getUserId());
        psyUserService.updatePsyUser(psyUser);
        consultedTokenService.refreshToken(loginUser ,null);
        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS);
    }
}
