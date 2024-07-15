package com.renxin.pocket.controller.wechat;

import com.renxin.common.constant.RespMessageConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.framework.web.service.PocketTokenService;
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
@RequestMapping("/pocket/user")
@Api(value = "PocketUserController" ,tags = {"微信用户信息操作api"})
public class PocketUserController extends BaseController
{
    @Autowired
    private IPsyUserService psyUserService;

    @Autowired
    private PocketTokenService pocketTokenService;


    /**
     * 查看来访者用户信息
     */
    @ApiOperation("查看来访者用户信息")
    @PostMapping("/info")
    public AjaxResult info(HttpServletRequest request)
    {
        LoginDTO loginUser = pocketTokenService.getLoginUser(request);
        PsyUser psyUser = psyUserService.selectPsyUserById(loginUser.getUserId());
            psyUser.setId(null);
        return AjaxResult.success(psyUser);
    }
    

}
