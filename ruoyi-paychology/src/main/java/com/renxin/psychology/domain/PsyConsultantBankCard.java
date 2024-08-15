package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;

import java.util.Date;

/**
 * 心理咨询师银行卡对象 psy_consultant_bankcard
 * 
 * @author jack
 * @date 2024-05-26
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_consultant_bankcard")
public class PsyConsultantBankCard
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名字 */
    @Excel(name = "名字")
    private String name;

    /** 风格标签 */
    @Excel(name = "风格标签")
    private String style;

    /** 受训经历 */
    @Excel(name = "受训经历")
    private String experience;

    /** 证书 */
    @Excel(name = "证书")
    private String certificate;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 创建时间 */
    @Excel(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("style", getStyle())
            .append("experience", getExperience())
            .append("certificate", getCertificate())
            .append("createTime", getCreateTime())
            .append("avatar", getAvatar())
            .toString();
    }


}
