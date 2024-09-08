package com.ruoyi.common.core.domain.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户登录对象
 * 
 * @author ruoyi
 */
@Data
public class LoginBody
{
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    @NotNull(groups = {ICashierSystemValidate.class},message = "门店标识不能为空")
    private Long storeId;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public  static interface ICashierSystemValidate{

    }
}
