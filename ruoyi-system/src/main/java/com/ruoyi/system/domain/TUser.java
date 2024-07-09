package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户对象 t_user
 * 
 * @author tz
 * @date 2024-07-09
 */
@Setter
@Getter
@ToString
@ApiModel
@TableName("t_user")
public class TUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "用户名")
    @Excel(name = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户姓名")
    @Excel(name = "用户姓名")
    private String userXm;

    @ApiModelProperty(value = "手机号")
    @Excel(name = "手机号")
    private String mobile;

    @ApiModelProperty(value = "手机号区号")
    private String mobileCode;

    @ApiModelProperty(value = "昵称")
    @Excel(name = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    @Excel(name = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别 0男 1女 2未知")
    @Excel(name = "性别 0男 1女 2未知")
    private Long gender;

    @ApiModelProperty(value = "微信openid")
    @Excel(name = "微信openid")
    private String openid;

    @ApiModelProperty(value = "状态 (1启用 2禁用)")
    private Long status;

    @ApiModelProperty(value = "微信unionid")
    private String unionid;

    @ApiModelProperty(value = "年龄")
    private Long age;

    @ApiModelProperty(value = "地区")
    private String district;

    @ApiModelProperty(value = "用户登录密码")
    private String userLoginPassword;

















}
