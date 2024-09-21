package com.ruoyi.billiard.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RegisterThirdUserDto implements Serializable {

    private static final long serialVersionUID=1L;

    /** 用户昵称 */
    private String nickName;

    /** 用户性别 */
    private String sex;

    /** 用户个人资料填写的省份 */
    private String province;

    /** 普通用户个人资料填写的城市 */
    private String city;

    /** 国家，如中国为CN */
    private String country;

    /** 微信小程序用户头像。 */
    private String avatar;


    /** 微信公众号用户头像 */
    private String headimgurl;

    /** 用户类型:wechat-公众号，routine-小程序，h5-H5,iosWx-苹果微信，androidWx-安卓微信 */
    private String type;

    /** 用户openId */
    private String openId;
}
