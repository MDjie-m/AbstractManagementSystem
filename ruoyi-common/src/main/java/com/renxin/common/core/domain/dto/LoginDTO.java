package com.renxin.common.core.domain.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.renxin.common.enums.LoginType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Data
@ApiModel("登录实体类")
public class LoginDTO implements UserDetails {

    /**
     * 账号(手机号)
     **/
    @ApiModelProperty("账号(手机号)")
    @NotBlank(message = "手机号不能为空")
    private String account;

    /**
     * 验证码
     **/
    @ApiModelProperty("验证码")
    private String code;

    /**
     * 验证码
     **/
    @ApiModelProperty("验证字符串(验证码)")
    private String validStr;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 微信openId
     */
    private String wxOpenId;

    /**
     * 登录方式，微信等
     */
    private LoginType loginType;

    /**
     * 终端类型
     */
    private String clientType;
    
    

    @JSONField(serialize = false)
    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public String getUsername()
    {
        return null;
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }
}