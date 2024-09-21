package com.ruoyi.framework.web.domain;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.constant.LoginSystem;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;



@Getter
public class CustomAuthToken extends UsernamePasswordAuthenticationToken {
    private LoginSystem system;


    public CustomAuthToken(Object principal, Object credentials,LoginSystem loginSystem) {
        super(principal, credentials);
        this.system=loginSystem;
    }

}
