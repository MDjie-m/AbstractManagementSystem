package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Set;

import com.ruoyi.billiard.service.IStoreDeskService;
import com.ruoyi.common.constant.LoginSystem;
import com.ruoyi.common.enums.MenuCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.system.service.ISysMenuService;

import javax.annotation.Resource;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Resource
    private IStoreDeskService storeDeskService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid(), LoginSystem.BACKEND_SYSTEM);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    @PostMapping("/api/login")
    public AjaxResult loginApi(@RequestBody  LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid(), LoginSystem.CASHIER_SYSTEM);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    @PostMapping("/api/mini-app/login")
    public AjaxResult miniAppLogin(@RequestBody  LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid(), LoginSystem.MINI_APP_SYSTEM);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping({"getInfo","api/mini-app/getInfo"})

    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        ajax.put("stores",storeDeskService.getByLoginUserId(user.getUserId()));

        return ajax;
    }

    /**
     * 获取小程序路由
     * @return
     */
    @GetMapping("api/mini-app/getRouters")
    public AjaxResult getMiniAppRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId, MenuCategory.MINI_APP);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId, MenuCategory.BACKEND);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
    @GetMapping("getCashierRouters")
    public AjaxResult getCashierRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId, MenuCategory.CASHIER);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
