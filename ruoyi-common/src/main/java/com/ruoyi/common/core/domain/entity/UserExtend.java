package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.constant.LoginSystem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExtend implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long storeId;
    /*

     */
    private LoginSystem loginType;
}
