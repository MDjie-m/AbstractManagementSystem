package com.ruoyi.billiard.domain.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
@Data
public class MemberPwdReqVo {
    @NotNull(message = "会员id不能为空")
    private Long memberId;
    private Long storeId;

    @NotNull(message = "旧密码不能为空")
    @Length(min = 6,max = 60,message = "密码长度应在6~20之间")
    private String oldPwd;
    @NotNull(message = "新密码不能为空")
    @Length(min = 6,max = 60,message = "密码长度应在6~20之间")
    private String pwd;
}
